package com.example.likeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.likeBoard.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}