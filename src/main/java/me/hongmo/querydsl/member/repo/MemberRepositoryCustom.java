package me.hongmo.querydsl.member.repo;

import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.dto.MemberSearchDto;
import me.hongmo.querydsl.member.dto.MemberTeamDto;
import me.hongmo.querydsl.member.dto.ReqMemberDTO;
import me.hongmo.querydsl.member.dto.ResMemberDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchDto condition);

    ResMemberDto searchByUsername(String username);

    Long updateUser(ReqMemberDTO memberDTO);

}
