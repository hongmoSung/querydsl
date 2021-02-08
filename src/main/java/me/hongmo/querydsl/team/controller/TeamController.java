package me.hongmo.querydsl.team.controller;

import me.hongmo.querydsl.team.dto.RequestTeam;
import me.hongmo.querydsl.team.dto.ResTeam;
import me.hongmo.querydsl.team.dto.TreeDTO;
import me.hongmo.querydsl.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Optional<List<ResTeam>>> list() {
        return ResponseEntity.ok(Optional.ofNullable(teamService.teamList()));
    }

    @PostMapping("/team/update")
    @ResponseBody
    public ResponseEntity<String> list(@RequestBody RequestTeam requestTeam) {
        Long updateteam = teamService.updateteam(requestTeam);
        System.out.println("updateteam" + updateteam);
        return ResponseEntity.ok("성공" + updateteam);
    }

    @GetMapping("/team/treeView")
    @ResponseBody
    public ResponseEntity<List<TreeDTO>> treeView() {
        return ResponseEntity.ok(teamService.treeView());
    }
}
