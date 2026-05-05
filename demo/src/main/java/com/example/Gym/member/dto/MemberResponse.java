package com.example.Gym.member.dto;

import com.example.Gym.member.Member;

public class MemberResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String jobTitle;
    
    

    public static MemberResponse fromEntity(Member member) {
        MemberResponse response = new MemberResponse();
        response.id = member.getId();
        response.fullName = member.getFullName();
        response.email = member.getEmail();
        response.phoneNumber = member.getPhoneNumber();
        response.jobTitle = member.getJobTitle();
        return response;
    }


    public Long getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }    
    public String getJobTitle() {
        return jobTitle;
    }
    
}