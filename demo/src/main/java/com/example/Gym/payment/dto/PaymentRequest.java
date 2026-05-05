package com.example.Gym.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.Gym.payment.PaymentMethod;
import com.example.Gym.payment.PaymentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PaymentRequest {
    @NotNull
    private Long memberId;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal amount;
    @Size(max = 255)
    private String currency = "USD";
    @NotNull
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    @Size(max = 255)
    private String description;
    private LocalDateTime paymentDate = LocalDateTime.now();
    @Size(max = 255)
    private String referenceId;
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }



}