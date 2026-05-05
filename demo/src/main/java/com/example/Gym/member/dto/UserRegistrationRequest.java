package com.example.Gym.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

import com.example.Gym.member.MemberType;

public class UserRegistrationRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @NotBlank
    @Email
    private String email;
    private String phoneNumber;
    private LocalDate membershipStart;
    private LocalDate membershipEnd;
    private MemberType membershipType;

    
    
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
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
    public MemberType getMembershipType(){
        return membershipType;
    }
    public void setMembershipType(MemberType membershipType){
        this.membershipType = membershipType;
    }
}