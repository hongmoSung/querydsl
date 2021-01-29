package me.hongmo.querydsl.team.service;

import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.team.dto.RequestTeam;
import me.hongmo.querydsl.team.dto.ResTeam;
import me.hongmo.querydsl.team.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Long join(RequestTeam requestTeam) {
        Team team = new Team(requestTeam.getName());
        Long id = teamRepository.save(team).getId();
        return id;
    }

    public List<Team> list() {
         return teamRepository.findAll();
    }

    public List<ResTeam> teamList() {
         return teamRepository.teamList();
    }

}
