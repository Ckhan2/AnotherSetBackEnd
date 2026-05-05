package com.example.Gym.member;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.Gym.member.dto.MemberRequest;
import com.example.Gym.member.dto.MemberResponse;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.createMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        MemberResponse memberResponse = memberService.getMemberById(id);
        return ResponseEntity.ok(memberResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @RequestBody @Valid MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.updateMember(id, memberRequest);
        return ResponseEntity.ok(memberResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}