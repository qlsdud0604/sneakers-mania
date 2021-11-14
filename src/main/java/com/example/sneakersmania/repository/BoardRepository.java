package com.example.sneakersmania.repository;

import com.example.sneakersmania.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
