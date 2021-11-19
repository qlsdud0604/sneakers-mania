package com.example.sneakersmania.service;

import com.example.sneakersmania.model.Board;
import com.example.sneakersmania.model.Reply;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.BoardRepository;
import com.example.sneakersmania.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;


    @Transactional
    public void saveBoard(Board board, User user) {
        board.setCount(0);
        board.setUser(user);

        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getBoardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)   // 영속화
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
                });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());

        // 해당 함수가 종료될 시 트랜잭션이 종료됨
        // 이때, 업데이트된 데이터가 DB에 반영
    }

    @Transactional
    public void saveReply(User user, int boardId, Reply requestReply) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 작성에 실패하였습니다.");
                });

        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }

}
