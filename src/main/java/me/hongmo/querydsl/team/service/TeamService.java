package me.hongmo.querydsl.team.service;

import lombok.ToString;
import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.team.dto.RequestTeam;
import me.hongmo.querydsl.team.dto.ResTeam;
import me.hongmo.querydsl.team.dto.TreeDTO;
import me.hongmo.querydsl.team.repo.TeamRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

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

    public Long updateteam(RequestTeam requestTeam) {
        return teamRepository.updateTeam(requestTeam);
    }

    public List<TreeDTO> treeView() {
        List<Object[]> objects = teamRepository.treeTeams();
        List<TreeDTO> trees = objects.stream().map(team -> {
            String teamId = StringUtils.trimToEmpty(team[0].toString());
            String title = StringUtils.trimToEmpty(team[1].toString());
            String parentTeamId = StringUtils.trimToEmpty(team[2].toString());
            String path = StringUtils.trimToEmpty(team[3].toString());
            String level = StringUtils.trimToEmpty(team[4].toString());
            TreeDTO tree = new TreeDTO();
            tree.setTeamId(teamId);
            tree.setTitle(title);
            tree.setParentTeamId(parentTeamId);
            tree.setPath(path);
            tree.setLevel(level);
            return tree;
        }).collect(Collectors.toList());
// path && level로 찾기

        List<String> levels = trees.stream().map(t -> t.getLevel()).distinct().sorted().collect(Collectors.toList());

        levels.forEach(System.out::println);
        List<TreeDTO> results = new ArrayList<>();

        levels.stream().forEach(level -> {
            List<TreeDTO> collect = trees.stream().filter(treeDTO -> level.equals(treeDTO.getLevel())).collect(Collectors.toList());
            collect.forEach(treeDTO -> {
                switch (treeDTO.getLevel()) {
                    case "1" :
                        results.add(treeDTO);
                        break;

                    case "2" :
                        String path = treeDTO.getPath();
                        String parentPath = path.substring(0, path.length() - 2);

                        Optional<TreeDTO> any = results.stream().filter(t -> t.getTeamId().contains(parentPath)).findAny();

                        if(any.isPresent()) {
                            TreeDTO treeDTO1 = any.get();
                            List<TreeDTO> children = treeDTO1.getChildren();
                            children.add(treeDTO);
                            treeDTO1.setChildren(children);
                        }
                        break;

                    case "3" :
                }
                if(treeDTO.getLevel().equals("1")) {
                    results.add(treeDTO);
                } else {

//                    results.stream().flatMap(t -> t.getChildren().stream())
                }
            });
        });

        return results;
    }

}
