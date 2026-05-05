package com.example.Gym.member.dto;

import java.time.LocalDate;


import com.example.Gym.member.MemberType;
import com.example.Gym.member.UserRegistration;



public class UserRegistrationResponse {
    private long id;
    private String firstName;
    private String lastName;   
    private String address;
    private String email; 
    private String phoneNumber;
    private LocalDate membershipStart;
    private LocalDate membershipEnd;
    private MemberType membershipType;
    private String paymentId;
    private String paymentClientSecret;

    public static UserRegistrationResponse fromEntity(UserRegistration entity) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(entity.getId());
        response.firstName = entity.getFirstName();
        response.lastName = entity.getLastName();
        response.address = entity.getAddress();
        response.email = entity.getEmail();
        response.phoneNumber = entity.getPhoneNumber();
        response.membershipStart = entity.getMembershipStart() != null
                ? LocalDate.parse(entity.getMembershipStart())
                : null;
        response.membershipEnd = entity.getMembershipEnd() != null
                ? LocalDate.parse(entity.getMembershipEnd())
                : null;
        response.membershipType = entity.getMemberType();
        
        return response;
    }
    public static UserRegistrationResponse fromMemberEntityPayment(
            UserRegistration entity, String paymentId, String paymentClientSecret) {
        UserRegistrationResponse response = fromEntity(entity);
        response.setPaymentId(paymentId);
        response.setPaymentClientSecret(paymentClientSecret);
        return response;
    }

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    public LocalDate getMembershipStart() {
        return membershipStart;
    }

    public void setMembershipStart(LocalDate membershipStart) {
        this.membershipStart = membershipStart;
    }

    public LocalDate getMembershipEnd() {
        return membershipEnd;
    }

    public void setMembershipEnd(LocalDate membershipEnd) {
        this.membershipEnd = membershipEnd;
    }

    public MemberType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MemberType membershipType) {
        this.membershipType = membershipType;
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
