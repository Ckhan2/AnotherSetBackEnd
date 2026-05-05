package com.example.Gym.member;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
@Entity
@Table(name = "user_registration")

public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String firstName;
    @Column(nullable = false, length = 200)
    private String lastName;
    @Column(nullable = false, length = 200)
    private String address;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = true, length = 200)
    private String phoneNumber;
    @Column(nullable = true)
    private String membershipStart;
    @Column(nullable = true)
    private String membershipEnd;
    @Column(nullable = true, length = 200)
    private String membershipType; 
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private MemberType memberType; 
    private String paymentId;
    private String paymentClientSecret;   


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;           
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }  
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getMembershipStart() {
        return membershipStart;
    }
    public void setMembershipStart(String membershipStart) {
        this.membershipStart = membershipStart;
    }
    public String getMembershipEnd() {
        return membershipEnd;
    }
    public void setMembershipEnd(String membershipEnd) {
        this.membershipEnd = membershipEnd;
    }
    public String getMembershipType() {
        return membershipType;
    }
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
    public MemberType getMemberType() {
        return memberType;
    }
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentClientSecret() {
        return paymentClientSecret;
    }

    public void setPaymentClientSecret(String paymentClientSecret) {
        this.paymentClientSecret = paymentClientSecret;
    }
}
