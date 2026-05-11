package dev.anjalee.gym_management_system.service;

import dev.anjalee.gym_management_system.models.Member;
import dev.anjalee.gym_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) {
        Member saved  = memberRepository.save(member);
        return saved;

    }

    @Override
    public Member getMemberById(int id) {
        Optional<Member> memberOptional = memberRepository.findById(id);

        return memberOptional.orElse(null);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(int id, Member member) {
        Member existingMember = getMemberById(id);
        if (existingMember == null) {
            return null;
        } else {
            existingMember.setName(member.getName());
            existingMember.setEmail(member.getEmail());
            existingMember.setPhoneNumber(member.getPhoneNumber());
            existingMember.setMembershipStartDate(member.getMembershipStartDate());
            existingMember.setMembershipEndDate(member.getMembershipEndDate());
            memberRepository.save(existingMember);
            return existingMember;
        }
    }

    @Override
    public void deleteMember(int id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<Member> getMembersExpiringWithinAWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        return memberRepository.findByMembershipEndDateBetween(today, nextWeek);
    }

}
