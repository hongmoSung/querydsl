package me.hongmo.querydsl.team.repo;

import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.team.dto.ResTeam;

import java.util.List;

public interface TeamRepositoryCustom {

    List<ResTeam> teamList();
}
