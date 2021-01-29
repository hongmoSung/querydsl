package me.hongmo.querydsl.board.controller;

import me.hongmo.querydsl.board.dto.RequestBoard;
import me.hongmo.querydsl.board.service.BoardService;
import me.hongmo.querydsl.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board/save")
    public ResponseEntity<Board> boardSave(@RequestBody RequestBoard requestBoard) {
        return ResponseEntity.ok(boardService.save(requestBoard));
    }

    @GetMapping("/board/list")
    public ResponseEntity<List<Board>> boardList() {
        return ResponseEntity.ok(boardService.findAll());
    }

}
