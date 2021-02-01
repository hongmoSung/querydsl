package me.hongmo.querydsl.member.repo;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.hongmo.querydsl.entity.QTeam;
import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.member.dto.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static me.hongmo.querydsl.entity.QMember.member;
import static me.hongmo.querydsl.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchDto condition) {
        BooleanBuilder builder = new BooleanBuilder();
        if (hasText(condition.getUsername())) {
            builder.and(member.username.eq(condition.getUsername()));
        }
        if (hasText(condition.getTeamName())) {
            builder.and(team.name.eq(condition.getTeamName()));
        }
        if (condition.getAgeGoe() != null) {
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if (condition.getAgeLoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }
        return queryFactory
                .select(new QMemberTeamDto(
                        member.memberId.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    @Override
    public ResMemberDto searchByUsername(String username) {
        ResMemberDto m = queryFactory
                .select(Projections.bean(
                        ResMemberDto.class,
                        member.memberId,
                        member.username,
                        team.id.as("teamId")
                        )
                )
                .from(member)
                .leftJoin(member.team, team)
                .where(member.username.eq(username))
                .fetchOne();
        return m;
    }

    @Override
    @Transactional
    public Long updateUser(ReqMemberDTO memberDTO) {
        Team team = queryFactory.selectFrom(QTeam.team).where(QTeam.team.id.eq(memberDTO.getTeamId())).fetchOne();
        long execute = queryFactory
                .update(member)
                .where(member.memberId.eq(memberDTO.getMemberId()))
                .set(member.username, memberDTO.getUsername())
                .set(member.team, team)
                .set(member.updateDate, LocalDateTime.now())
                .execute();
        return execute;
    }
}
