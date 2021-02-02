package me.hongmo.querydsl.team.dto;

import lombok.Data;

@Data
public class RequestTeam {

    private Long id;
    private String name;
    private Long parentTeamId;
}
