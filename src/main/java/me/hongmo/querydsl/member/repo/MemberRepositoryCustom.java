package me.hongmo.querydsl.member.repo;

import me.hongmo.querydsl.member.dto.MemberSearchDto;
import me.hongmo.querydsl.member.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchDto condition);
}
