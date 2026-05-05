package com.example.Gym.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id", nullable = false)
    private Long memberId;
    @Column(nullable = false)

    private BigDecimal amount;
    @Column(nullable = false, length = 255)
    private String currency;
    @Column()
    private PaymentMethod paymentMethod;
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
    private String description;
    private LocalDateTime paymentDate;
    private String referenceId;

    public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
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