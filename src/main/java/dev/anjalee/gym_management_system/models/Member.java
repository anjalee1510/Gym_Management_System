package dev.anjalee.gym_management_system.models;

import dev.anjalee.gym_management_system.dtos.MemberDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    private LocalDate membershipStartDate;

    private LocalDate membershipEndDate;

//    public Members(int id,String name, String email, String phoneNumber,
//                  LocalDate membershipStartDate, LocalDate membershipEndDate) {
//        this.id=id;
//        this.name = name;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.membershipStartDate = membershipStartDate;
//        this.membershipEndDate = membershipEndDate;
//    }

    public Member(String name, String email, String phoneNumber, LocalDate membershipStartDate, LocalDate membershipEndDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
    }

    public Member() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public LocalDate getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(LocalDate membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public MemberDTO memberToMemberDTO(){
        return new MemberDTO(name,email,phoneNumber,membershipStartDate,membershipEndDate);
    }
}
