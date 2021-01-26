package me.hongmo.querydsl.member.service;

import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.entity.QMember;
import me.hongmo.querydsl.member.dto.MemberDto;
import me.hongmo.querydsl.member.repo.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    public void join(MemberDto memberDto) {
        Member m = new Member(memberDto.getUsername(), memberDto.getAge());
        memberJpaRepository.save(m);
    }


}
