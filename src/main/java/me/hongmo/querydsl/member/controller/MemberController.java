package me.hongmo.querydsl.member.controller;

import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.dto.MemberDto;
import me.hongmo.querydsl.member.dto.MemberTeamDto;
import me.hongmo.querydsl.member.dto.ReqMemberDTO;
import me.hongmo.querydsl.member.dto.ResMemberDto;
import me.hongmo.querydsl.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/save")
    public ResponseEntity<Member> save(@RequestBody MemberDto member) {
        return ResponseEntity.ok(memberService.signup(member));
    }

    @GetMapping("/member/{username}")
    @ResponseBody
    public ResponseEntity<ResMemberDto> searchByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(memberService.findByUsername(username).get());
    }

    @PostMapping("/member/update")
    public ResponseEntity<Long> updateMember(@RequestBody ReqMemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.memberUpdate(memberDTO));
    }

    @GetMapping("/member/list")
    public ResponseEntity<List<Member>> members() {
        return ResponseEntity.ok(memberService.members());
    }

    @GetMapping("/member/team/{teamId}")
    @ResponseBody
    public ResponseEntity<List<MemberTeamDto>> findByTeamId(@PathVariable("teamId") String teamId) {
        return ResponseEntity.ok(memberService.searchMembersByTeamId(teamId));
    }

}
