package me.hongmo.querydsl.team.controller;

import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.team.dto.RequestTeam;
import me.hongmo.querydsl.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/team/save")
    @ResponseBody
    public Long save(@RequestBody RequestTeam team) {
        return teamService.join(team);
    }

    @GetMapping("/team/list")
    @ResponseBody
    public List<Team> list() {
        return teamService.list();
    }
}
