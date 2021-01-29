package me.hongmo.querydsl.board.service;

import me.hongmo.querydsl.board.dto.RequestBoard;
import me.hongmo.querydsl.board.repo.BoardRepository;
import me.hongmo.querydsl.entity.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board save(RequestBoard requestBoard) {
        Board build = Board.builder()
                .title(requestBoard.getContent())
                .content(requestBoard.getContent())
                .build();
        Board save = boardRepository.save(build);
        return save;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

}
