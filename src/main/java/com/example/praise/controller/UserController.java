package com.example.praise.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;
import com.example.praise.model.form.PasswordForm;
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
			UserDto loginUser = (UserDto) session.getAttribute("loginUser");
			Optional<User> userInfo = userService.getUserById((int) loginUser.getId());
			model.addAttribute("userInfo", userInfo.get());
			return "user/mypage";
		} catch (RuntimeException e) {
			return "오류: " + e;
		}
	}
	
	@PostMapping("/password")
	public String updatePassword(HttpSession session, PasswordForm pwForm, Model model) {	
		try {
			// 1. 현재 비밀번호 일치 여부 확인(Get)
			UserDto loginUser = (UserDto) session.getAttribute("loginUser");
			User user = userService.getPasswordById(loginUser.getId(), pwForm.getCurPassword()).get();
			
			// 2. 비밀번호 변경
			user.setPassword(pwForm.getNewPassword());
			userService.saveUser(user);
			model.addAttribute("successMessage", "비밀번호를 변경했습니다.");
			return "user/mypage";
		} catch(RuntimeException e) {
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			return "user/mypage";
		}
		
	}
}
