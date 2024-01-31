package com.example.praise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praise.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}