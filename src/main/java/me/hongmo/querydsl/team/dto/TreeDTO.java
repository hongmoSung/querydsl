package me.hongmo.querydsl.team.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(of = {"teamId", "parentTeamId", "title", "path"})
public class TreeDTO {
    private String teamId;
    private String parentTeamId;
    private String title;
    private String path;
    private String level;
    private List<TreeDTO> children = new ArrayList<>();
}
