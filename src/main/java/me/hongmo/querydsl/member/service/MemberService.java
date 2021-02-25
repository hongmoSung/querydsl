package me.hongmo.querydsl.member.service;

import com.google.gson.JsonObject;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import lombok.RequiredArgsConstructor;
import me.hongmo.querydsl.authority.repo.AuthorityRepository;
import me.hongmo.querydsl.entity.Authority;
import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.dto.*;
import me.hongmo.querydsl.member.repo.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final IGraphServiceClient graphServiceClient;

    public Optional<ResMemberDto> findByUsername(String username) {
        ResMemberDto resMemberDto = memberRepository.searchByUsername(username);
        if(resMemberDto.getTeamId() == null) resMemberDto.setTeamId(0L);
        return Optional.ofNullable(resMemberDto);
    }

    public Optional<Member> findById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }

    public List<Member> members() {
        return memberRepository.findAll();
    }

    public Long memberUpdate(ReqMemberDTO memberDto) {
        return memberRepository.updateUser(memberDto);
    }

    @Transactional
    public Member signup(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByAadid(memberDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        final Authority authority = authorityRepository.findByAuthorityName("ROLE_USER");

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .authority(authority)
                .activated(true)
                .build();

        return memberRepository.save(member);
    }

//    @Transactional(readOnly = true)
//    public Optional<Member> getUserWithAuthorities(String username) {
//        return memberRepository.findOneWithAuthoritiesByUsername(username);
//    }

//    @Transactional(readOnly = true)
//    public Optional<Member> getMyUserWithAuthorities() {
//        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername);
//    }

    public List<MemberTeamDto> searchMembersByTeamId(String teamId) {
        MemberSearchDto condition = MemberSearchDto.builder()
                .teamId(teamId)
                .build();
        return memberRepository.search(condition);
    }

    public JsonObject graphMembers() {
        return graphServiceClient
                .users()
                .buildRequest()
                .get()
                .getRawObject();
    }

    public JsonObject graphGroups() {
        return graphServiceClient
                .groups()
                .buildRequest()
                .get()
                .getRawObject();
    }


}
