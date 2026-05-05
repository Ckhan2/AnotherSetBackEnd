package com.example.Gym.payment;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.List;
import com.example.Gym.payment.dto.PaymentRequest;
import com.example.Gym.payment.dto.PaymentResponse;

@RestController
@RequestMapping("/api/payments")

public class PaymentController {
private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
@GetMapping
public List<PaymentResponse> getPaymentsByMemberId(@RequestParam Long memberId) {
    return paymentService.findByMemberId(memberId);
}
@GetMapping("/{id}")
public PaymentResponse getById(@PathVariable Long id) {
    return paymentService.findById(id);
}
@PostMapping
public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
    PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
    paymentService.delete(id);
    return ResponseEntity.noContent().build();
}
    
}