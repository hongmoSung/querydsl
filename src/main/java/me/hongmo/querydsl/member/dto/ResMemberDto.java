package me.hongmo.querydsl.member.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResMemberDto {

    private Long memberId;
    private String username;
    private Long teamId;
}
