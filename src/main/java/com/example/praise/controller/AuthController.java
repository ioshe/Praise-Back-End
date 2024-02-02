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
			if(e.getMessage().equals("이미 존재하는 ID 입니다.")) {
				model.addAttribute("errormsg1", dto.getUsername()+"는/은"+e.getMessage()); // -> jsp
			} else if (e.getMessage().equals("이미 존재하는 닉네임입니다.")) {
			model.addAttribute("errormsg2", dto.getNickname()+"는/은"+e.getMessage()); // -> jsp
			}
			return "user/signup";
		}
	}
	
	// 2) 회원탈퇴
	// 탈퇴진행
	@PostMapping("/signout")
	public String signoutUser(HttpSession session, Model model) {
		try{
			service.signout(session);
			model.addAttribute("signoutmsg1", "회원탈퇴가 완료되었습니다.");
			session.invalidate();
		} catch (RuntimeException e) {
			model.addAttribute("signoutmsg2", "회원탈퇴에 실패했습니다.");
		}
		return "user/mypage";
	}

	// 3) 로그인
	@PostMapping("/login")
	public String login(@ModelAttribute UserDto dto, Model model, HttpSession session) {
		try {
			UserDto result = service.login(dto);
			session.setAttribute("loginUser", result);
			return "redirect:/";
		} catch (RuntimeException e) {
			session.setAttribute("loginmsg", e.getMessage());
			model.addAttribute("loginmsg", e.getMessage());
			return "redirect:/";
		}
		
	}

	// 4) 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}