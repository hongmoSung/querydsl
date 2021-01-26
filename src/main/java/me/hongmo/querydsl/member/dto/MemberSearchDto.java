package me.hongmo.querydsl.member.dto;

import lombok.Data;

@Data
public class MemberSearchDto {

    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
