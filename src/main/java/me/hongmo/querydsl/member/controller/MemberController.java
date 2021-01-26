package me.hongmo.querydsl.member.controller;

import me.hongmo.querydsl.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MemberController {

    @PostMapping("/member/save")
    public MemberDto save(@RequestBody MemberDto member) {

    }
}
