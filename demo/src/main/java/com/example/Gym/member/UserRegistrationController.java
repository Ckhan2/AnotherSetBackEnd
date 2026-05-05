package com.example.Gym.member;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.time.LocalDate;

import com.example.Gym.member.dto.UserRegistrationRequest;
import com.example.Gym.member.dto.UserRegistrationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {
    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody @Valid UserRegistrationRequest request) {
        UserRegistration userRegistration = convertToEntity(request);
        UserRegistration savedUser = userService.save(userRegistration);
        UserRegistrationResponse response = convertToResponse(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private UserRegistration convertToEntity(UserRegistrationRequest request) {
        UserRegistration entity = new UserRegistration();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setMembershipStart(request.getMembershipStart() != null ? request.getMembershipStart().toString() : null);
        entity.setMembershipEnd(request.getMembershipEnd() != null ? request.getMembershipEnd().toString() : null);
        entity.setMemberType(request.getMembershipType());
        return entity;
    }

    private UserRegistrationResponse convertToResponse(UserRegistration entity) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setAddress(entity.getAddress());
        response.setEmail(entity.getEmail());
        response.setPhoneNumber(entity.getPhoneNumber());
        if (entity.getMembershipStart() != null) {
            response.setMembershipStart(LocalDate.parse(entity.getMembershipStart()));
        }
        if (entity.getMembershipEnd() != null) {
            response.setMembershipEnd(LocalDate.parse(entity.getMembershipEnd()));
        }
        response.setMembershipType(entity.getMemberType());
        response.setPaymentId(entity.getPaymentId());
        response.setPaymentClientSecret(entity.getPaymentClientSecret());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<UserRegistrationResponse>> getAllUsers() {
        List<UserRegistrationResponse> users = userService.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

