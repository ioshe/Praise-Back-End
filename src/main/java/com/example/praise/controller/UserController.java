package com.example.praise.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;
import com.example.praise.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/mypage")
	public String getUserById(HttpSession session, Model model) {
		try {
			Optional<User> userInfo = userService.getUserById(Integer.parseInt(session.getId()));
//			Optional<User> userInfo = userService.getUserById(1);
			model.addAttribute("userInfo", userInfo.get());
			return "user/mypage";
		} catch (RuntimeException e) {
			return "오류: " + e;
		}
	}
}
