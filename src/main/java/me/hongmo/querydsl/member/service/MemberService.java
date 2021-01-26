package me.hongmo.querydsl.member.service;

import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.dto.MemberDto;
import me.hongmo.querydsl.member.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void join(MemberDto memberDto) {
        Member m = new Member(memberDto.getUsername(), memberDto.getAge());
        memberRepository.save(m);
    }

    public List<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }


}
