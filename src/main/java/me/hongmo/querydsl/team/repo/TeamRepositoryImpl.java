package me.hongmo.querydsl.team.repo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.hongmo.querydsl.team.dto.ResTeam;

import javax.persistence.EntityManager;
import java.util.List;

import static me.hongmo.querydsl.entity.QTeam.team;

public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public TeamRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResTeam> teamList() {
        return jpaQueryFactory
                .select(Projections.bean(ResTeam.class, team.id, team.name, team.parentTeamId))
                .from(team)
                .fetch();
    }
}
