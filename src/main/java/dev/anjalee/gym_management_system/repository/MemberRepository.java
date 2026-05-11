package dev.anjalee.gym_management_system.repository;

import dev.anjalee.gym_management_system.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findByMembershipEndDateBetween(LocalDate today, LocalDate nextWeek);
}
