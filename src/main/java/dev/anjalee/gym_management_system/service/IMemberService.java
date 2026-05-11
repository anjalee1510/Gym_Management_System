package dev.anjalee.gym_management_system.service;

import dev.anjalee.gym_management_system.models.Member;

import java.util.List;


public interface IMemberService {
    Member createMember(Member member);

    Member getMemberById(int id);

    List<Member> getAllMembers();

    Member updateMember(int id, Member member);

    void deleteMember(int id);

    List<Member> getMembersExpiringWithinAWeek();
}
