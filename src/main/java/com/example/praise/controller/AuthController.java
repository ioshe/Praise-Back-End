package com.example.praise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.praise.model.dto.UserDto;
import com.example.praise.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private UserService service;
	// 생성자
	public AuthController(UserService service) {
		this.service = service;
	}
	
	// 1) 회원가입
	// 회원가입 빈 화면으로 전달 -> jsp
	@GetMapping("/signup")
	public String registerForm() {
		return "user/signup"; 
	}
	
	// 회원가입 form에서 받은 정보를 함께 전달
	@PostMapping("/signup")
	public String doregister(@ModelAttribute UserDto dto, Model model) {
		try {
			service.register(dto);
			return "redirect:/";
		} catch (RuntimeException e) {
			model.addAttribute("msg", dto.getUsername()+"는 이미 존재하는 ID입니다."); // -> jsp
			return "user/signup";
		}
	}
	
	// 2) 회원탈퇴
	@PostMapping("/signout")
	public void signoutUser(@ModelAttribute UserDto dto, Model model) {
		try{
			service.signout(dto);
			model.addAttribute("message", "회원탈퇴가 완료되었습니다.");
		} catch (RuntimeException e) {
			model.addAttribute("msg", "회원탈퇴에 실패했습니다.");
//			return "user/mypage";
		}
		
	}
	
	// 3) 로그인
	@PostMapping("/login")
	public String login(@ModelAttribute UserDto dto, Model model, HttpSession session) {
		try {
			UserDto result = service.login(dto);
			session.setAttribute("loginUser", result);
			return "redirect:/";
		} catch (RuntimeException e) {
			model.addAttribute("loginmsg", e.getMessage());
			return "index";
		}
		
	}
}