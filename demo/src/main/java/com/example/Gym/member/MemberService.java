package com.example.Gym.member;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Gym.member.dto.MemberRequest;
import com.example.Gym.member.dto.MemberResponse;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        return toResponse(member);
    }

    @Transactional
    public MemberResponse createMember(MemberRequest request) {
        Member member = new Member();
        member.setFullName(request.getFullName());
        member.setEmail(request.getEmail());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setJobTitle(request.getJobTitle());
        Member savedMember = memberRepository.save(member);
        return MemberResponse.fromEntity(savedMember);
    }

    @Transactional
    public MemberResponse updateMember(Long id, MemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        member.setFullName(request.getFullName());
        member.setEmail(request.getEmail());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setJobTitle(request.getJobTitle());
        Member updatedMember = memberRepository.save(member);
        return MemberResponse.fromEntity(updatedMember);
    }

    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    private MemberResponse toResponse(Member member) {
        return MemberResponse.fromEntity(member);
    }
}