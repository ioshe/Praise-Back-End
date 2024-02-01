package com.example.praise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praise.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username); // SELECT * FROM User WHERE username  = ?

}