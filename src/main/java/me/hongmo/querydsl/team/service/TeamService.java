package me.hongmo.querydsl.team.service;

import me.hongmo.querydsl.entity.Member;
import me.hongmo.querydsl.entity.Team;
import me.hongmo.querydsl.member.repo.MemberRepository;
import me.hongmo.querydsl.team.dto.RequestTeam;
import me.hongmo.querydsl.team.dto.ResTeam;
import me.hongmo.querydsl.team.dto.TreeDTO;
import me.hongmo.querydsl.team.repo.TeamRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

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
            tree.setKey(path);
            tree.setLevel(level);
            tree.setIsLeaf(false);

            return tree;
        }).collect(Collectors.toList());

        List<Member> members = memberRepository.findAll();

        for(var i = 0; i < trees.size(); i++) {
            for(var j = 0; j < trees.size(); j++) {
                TreeDTO parentTree = trees.get(i);
                TreeDTO childTree = trees.get(j);
                if(parentTree.getTeamId().equals(childTree.getParentTeamId())) {
                    parentTree.getChildren().add(childTree);
                }
            }
        }

        for(var i = 0; i < trees.size(); i++) {
            TreeDTO treeDTO = trees.get(i);
            List<Member> collect = members.stream().filter(m -> Long.toString(m.getTeamId()).equals(treeDTO.getTeamId())).collect(Collectors.toList());

            if(collect.size() != 0) {
                AtomicInteger number = new AtomicInteger();
                collect.stream().forEach((member) -> {
                    TreeDTO t = new TreeDTO();
                    t.setTitle(member.getUsername());
                    t.setKey(treeDTO.getKey() + number.getAndIncrement());
                    t.setIsLeaf(true);
                    t.setIcon("<CarryOutOutlined />");
                    treeDTO.getChildren().add(t);
                });
            }
        }

        List<TreeDTO> result = trees.stream().filter(treeDTO -> treeDTO.getLevel().equals("1")).collect(Collectors.toList());
        return result;
    }

}
