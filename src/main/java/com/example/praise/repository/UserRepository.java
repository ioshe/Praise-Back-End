package com.example.praise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praise.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Object findByUsername(int userId);

}