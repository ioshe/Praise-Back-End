package com.example.praise.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.praise.model.entity.User;
import com.example.praise.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String getUserById(HttpSession session, Model model) {
		try {
			Optional<User> user = userService.getUserById(Integer.parseInt(session.getId()));
			return "mypage/";
		} catch (RuntimeException e) {
			return "오류";
		}
	}
}
