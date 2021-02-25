package me.hongmo.querydsl.member.service;

import lombok.extern.slf4j.Slf4j;
import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.repo.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String aadid) {
        return memberRepository.findOneWithAuthoritiesByAadid(aadid)
                .map(user -> createUser(aadid, user))
                .orElseThrow(() -> new UsernameNotFoundException(aadid + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String aadid, Member member) {

        final String authorityName = member.getAuthority().getAuthorityName();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(authorityName));

//        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//                .collect(Collectors.toList());

        return new User(member.getAadid(), member.getPassword(), grantedAuthorities);
    }
}