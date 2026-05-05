package com.example.Gym.staff;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByMemberId(Long memberId);
    void deleteByMemberId(Long memberId);
}
