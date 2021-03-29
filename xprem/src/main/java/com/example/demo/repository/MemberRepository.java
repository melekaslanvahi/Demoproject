package com.example.demo.repository;

import com.example.demo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.membershipType = :type AND m.membershipStatus = :status AND m.membershipEndDate < :endDate")
    List<Member> getPaymentOfAutomaticly(@Param("type") int type, @Param("status") int status, @Param("endDate") OffsetDateTime endDate );
}
