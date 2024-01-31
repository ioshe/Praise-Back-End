package com.example.likeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.likeBoard.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}