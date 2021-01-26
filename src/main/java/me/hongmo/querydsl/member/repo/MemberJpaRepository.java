package me.hongmo.querydsl.member.repo;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.member.dto.MemberSearchDto;
import me.hongmo.querydsl.member.dto.MemberTeamDto;
import me.hongmo.querydsl.member.dto.QMemberTeamDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static me.hongmo.querydsl.entity.QMember.member;
import static me.hongmo.querydsl.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public Optional<Member> findById(Long id) {
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
    }

}
