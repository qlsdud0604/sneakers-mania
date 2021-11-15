package com.example.sneakersmania.service;

import com.example.sneakersmania.model.Board;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board getBoardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
                });
    }
}
