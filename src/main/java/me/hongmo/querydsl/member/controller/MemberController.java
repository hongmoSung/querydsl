package me.hongmo.querydsl.member.controller;

import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.dto.MemberDto;
import me.hongmo.querydsl.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/save")
    @ResponseBody
    public MemberDto save(@RequestBody MemberDto member) {
        memberService.join(member);
        return member;
    }

//    @GetMapping("/member/{username}")
//    @ResponseBody
//    public List<Member> searchByUsername(@PathVariable("username") String username) {
//        return memberService.findByUsername(username);
//    }

    @GetMapping("/member/{id}")
    @ResponseBody
    public Optional<Member> searchByUsername(@PathVariable("id") Long id) {
        Optional<Member> member = memberService.findById(id);
        return Optional.ofNullable(member).get();
    }

}
