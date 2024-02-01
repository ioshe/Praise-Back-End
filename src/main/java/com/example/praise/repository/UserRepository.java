package com.example.praise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praise.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username); // SELECT * FROM User WHERE username  = ?
	Optional<User> findByIdAndPassword(int id, String password);
}