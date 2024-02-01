package com.example.praise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praise.model.entity.Board;
import com.example.praise.model.entity.User;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	Page<Board> findBySender(User sender, Pageable pageable);
}