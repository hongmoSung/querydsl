package me.hongmo.querydsl.team.dto;

import lombok.Data;

@Data
public class ResTeam {
    private Long id;
    private String name;
    private Long parentTeamId;
}
