package com.example.Gym.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class MemberRequest {
    
    @NotBlank
    private String fullName;
    @NotBlank
    @Email
    private String email;
    private String phoneNumber;
    
    private String jobTitle;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
}