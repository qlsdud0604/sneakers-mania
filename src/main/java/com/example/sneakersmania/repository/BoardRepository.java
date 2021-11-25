package com.example.sneakersmania.repository;

import com.example.sneakersmania.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findAllByUserId(int userId);
}
