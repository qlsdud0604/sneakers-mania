package com.example.sneakersmania.service;

import com.example.sneakersmania.model.Board;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void save(Board board, User user) {
        board.setCount(0);
        board.setUser(user);

        boardRepository.save(board);
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }
}
