package me.hongmo.querydsl.team.repo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.team.dto.RequestTeam;
import me.hongmo.querydsl.team.dto.ResTeam;
import me.hongmo.querydsl.team.dto.TreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static me.hongmo.querydsl.entity.QTeam.team;

public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public TeamRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    @Override
    public List<ResTeam> teamList() {
        return jpaQueryFactory
                .select(Projections.bean(ResTeam.class, team.id, team.name, team.parentTeamId))
                .from(team)
                .fetch();
    }

    @Override
    @Transactional
    public Long updateTeam(RequestTeam requestTeam) {
        long execute = jpaQueryFactory
                .update(team)
                .where(team.id.eq(requestTeam.getId()))
                .set(team.parentTeamId, requestTeam.getParentTeamId())
                .execute();
        return execute;
    }

    @Override
    public List<Object[]> treeTeams() {
        List<Object[]> resultList = em.createNativeQuery(
                "WITH recursive cte AS\n" +
                        "(\n" +
                        "  select     team_id,\n" +
                        "             name,\n" +
                        "             IFNULL(parent_team_id, ''),\n" +
                        "             cast(team_id as char(100)) as path,\n" +
                        "             1 as lvl" +
                        "  from       team\n" +
                        "  where      parent_team_id is null\n" +
                        "  union all\n" +
                        "  select     p.team_id,\n" +
                        "             p.name,\n" +
                        "             IFNULL(p.parent_team_id, ''),\n" +
                        "             concat(cte.path, '-', p.team_id) as path,\n" +
                        "             cte.lvl + 1 as lvl" +
                        "  from       team p\n" +
                        "  inner join cte\n" +
                        "          on p.parent_team_id = cte.team_id\n" +
                        ")\n" +
                        "SELECT * FROM cte;"
        )
                .getResultList();
        return resultList;
    }
}
