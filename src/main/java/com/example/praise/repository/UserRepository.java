package com.example.praise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praise.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username); // SELECT * FROM User WHERE username  = ?

	void deleteByUsername(String username);

}