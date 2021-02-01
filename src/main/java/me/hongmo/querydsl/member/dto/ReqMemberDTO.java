package me.hongmo.querydsl.member.dto;

import lombok.Data;

@Data
public class ReqMemberDTO {
    private Long memberId;
    private String username;
    private Long teamId;
}
