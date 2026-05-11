package dev.anjalee.gym_management_system.controllers;

import dev.anjalee.gym_management_system.dtos.MemberDTO;
import dev.anjalee.gym_management_system.models.Member;
import dev.anjalee.gym_management_system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @PostMapping
    ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){

        Member member= memberService.createMember(memberDTO.MemberDTOToMember());
        return new ResponseEntity<>(member.memberToMemberDTO(), HttpStatus.CREATED); // Placeholder return statement
    }

    @GetMapping("/{id}")
    ResponseEntity<MemberDTO> getMemberById(@PathVariable int id) {
        Member member = memberService.getMemberById(id);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member.memberToMemberDTO(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<MemberDTO>> getAllMembers() {
        // Placeholder implementation
        List<Member> memberList = memberService.getAllMembers();
        List<MemberDTO> memberDTOList = memberList.stream()
                .map(Member::memberToMemberDTO)
                .toList();
        return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<MemberDTO> updateMember(@PathVariable int id, @RequestBody MemberDTO memberDTO) {

        Member updatedMember = memberService.updateMember(id, memberDTO.MemberDTOToMember());
        if (updatedMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMember.memberToMemberDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMember(@PathVariable int id) {
        Member existingMember = memberService.getMemberById(id);
        if(existingMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
