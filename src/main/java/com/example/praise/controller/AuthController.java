package com.example.praise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;
import com.example.praise.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserService service;
	// 생성자
	public AuthController(UserService service) {
		this.service = service;
	}
	
	// 회원가입 빈 화면으로 전달 -> jsp
	@GetMapping("/signup")
		public void registerForm() {
		}
	
	// 회원가입 form에서 받은 정보를 함께 전달
	@PostMapping("/signup")
	public User doregister(@RequestBody UserDto dto) {
		try {
			return service.register(dto);
//			User registeredUser = service.register(dto);
//			return ResponseEntity.ok(registeredUser);
		} catch (RuntimeException e) {
//			model.addAttribute("msg", dto.getUsername()+"는 사용할 수 없습니다."); -> jsp
//			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
			return null;
		}
	}
	}

