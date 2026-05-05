package com.example.Gym.member;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import jakarta.transaction.Transactional;
import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.util.List;
import org.springframework.stereotype.Service;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
@Service
public class UserService {
    private final String stripeApiKey;
    private final String stripeRestrictedApiKey;
    private final String stripeCurrency;
    private final UserRegistrationRepository userRepository;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    
    public UserService(
            UserRegistrationRepository userRepository,
            ObjectMapper objectMapper,
            @Value("${stripes.api.key:}") String stripeApiKey,
            @Value("${stripes.api.restrictedKey:}") String stripeRestrictedApiKey,
            @Value("${stripes.currency:}") String stripeCurrency
    ) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.stripeApiKey = normalizeConfigValue(stripeApiKey);
        this.stripeRestrictedApiKey = normalizeConfigValue(stripeRestrictedApiKey);
        this.stripeCurrency = normalizeConfigValue(stripeCurrency);
        this.httpClient = HttpClient.newHttpClient();
    }

    @Transactional
    public UserRegistration save(UserRegistration userRegistration) {
        UserRegistration detail = new UserRegistration();
        detail.setFirstName(userRegistration.getFirstName());
        detail.setLastName(userRegistration.getLastName());
        detail.setAddress(userRegistration.getAddress());
        detail.setEmail(userRegistration.getEmail());
        detail.setPhoneNumber(userRegistration.getPhoneNumber());
        detail.setMembershipStart(userRegistration.getMembershipStart());
        detail.setMembershipEnd(userRegistration.getMembershipEnd());
        detail.setMemberType(userRegistration.getMemberType());
        detail.setMembershipType(userRegistration.getMemberType() != null ? userRegistration.getMemberType().name() : null);

        StripeIntentResult stripeIntentResult = createStripePaymentIntent(detail);
        detail.setPaymentId(stripeIntentResult.paymentIntentId());
        detail.setPaymentClientSecret(stripeIntentResult.clientSecret());
        return userRepository.save(detail);
    }

    @Transactional
    public List<UserRegistration> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    private StripeIntentResult createStripePaymentIntent(UserRegistration userRegistration) {
        if (stripeApiKey == null || stripeApiKey.isBlank()
                || stripeRestrictedApiKey == null || stripeRestrictedApiKey.isBlank()
                || stripeCurrency == null || stripeCurrency.isBlank()) {
            throw new IllegalStateException("Stripe configuration is missing");
        }

        long amount = resolveAmount(userRegistration);
        String formBody = "amount=" + encode(String.valueOf(amount))
                + "&currency=" + encode(stripeCurrency.toLowerCase())
                + "&automatic_payment_methods[enabled]=true"
                + "&metadata[email]=" + encode(userRegistration.getEmail())
                + "&metadata[membershipType]=" + encode(userRegistration.getMembershipType());

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.stripe.com/v1/payment_intents"))
                .header("Authorization", "Bearer " + stripeApiKey)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("Stripe payment intent creation failed: " + response.body());
            }

            JsonNode body = objectMapper.readTree(response.body());
            String paymentIntentId = body.path("id").asText();
            String clientSecret = body.path("client_secret").asText();
            if (paymentIntentId.isBlank() || clientSecret.isBlank()) {
                throw new IllegalStateException("Stripe response is missing required fields");
            }
            return new StripeIntentResult(paymentIntentId, clientSecret);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to parse Stripe response", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Stripe request was interrupted", e);
        }
    }

    private long resolveAmount(UserRegistration userRegistration) {
        String membershipType = userRegistration.getMembershipType();
        if (membershipType == null || membershipType.isBlank()) {
            throw new IllegalArgumentException("Membership type is required to create Stripe payment intent");
        }

        return switch (membershipType.trim().toLowerCase()) {
            case "bronze" -> 2900L;
            case "silver" -> 4900L;
            case "platinum" -> 7900L;
            default -> throw new IllegalArgumentException("Unsupported membership type: " + membershipType);
        };
    }

    private String encode(String value) {
        return URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
    }

    private String normalizeConfigValue(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.length() >= 2
                && ((trimmed.startsWith("\"") && trimmed.endsWith("\""))
                || (trimmed.startsWith("'") && trimmed.endsWith("'")))) {
            return trimmed.substring(1, trimmed.length() - 1).trim();
        }
        return trimmed;
    }

    private record StripeIntentResult(String paymentIntentId, String clientSecret) {}
}
