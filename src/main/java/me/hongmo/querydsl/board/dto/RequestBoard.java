package me.hongmo.querydsl.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestBoard {

    private String title;
    private String content;
    private String username;
}
