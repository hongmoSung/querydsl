package me.hongmo.querydsl.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class MemberTeamDto {

    private Long memberId;
    private String username;
    private Long teamId;
    private String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, Long teamId, String teamName) {
        this.memberId = memberId;
        this.username = username;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
