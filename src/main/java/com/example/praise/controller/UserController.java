package com.example.praise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.example.praise.model.entity.Board;
import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;
import com.example.praise.model.form.PasswordForm;
import com.example.praise.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/mypage")
	public String getUserByIdAndBoardList(@RequestParam(defaultValue = "0") Integer page, HttpSession session, Model model) {
		try {
			UserDto loginUser = (UserDto) session.getAttribute("loginUser");
			Optional<User> userInfo = userService.getUserById((int) loginUser.getId());
			model.addAttribute("userInfo", userInfo.get());
			
			// 2. 사용자가 작성한 글 목록 조회
			Page<Board> boardPage = userService.getUserBoardList((int) loginUser.getId(), page, 10);
		    model.addAttribute("boardPage", boardPage);
			
			return "user/mypage";
		} catch (RuntimeException e) {
			return "오류: " + e;
		}
	}
	
	@PostMapping("/password")
	public String updatePassword(HttpSession session, PasswordForm pwForm, Model model, RedirectAttributes attribredirectAttributes) {	
		try {
			// 1. 현재 비밀번호 일치 여부 확인(Get)
			UserDto loginUser = (UserDto) session.getAttribute("loginUser");
//			model.addAttribute("userInfo", loginUser);
			User user = userService.getPasswordById(loginUser.getId(), pwForm.getCurPassword()).get();
			
			// 2. 비밀번호 변경
			user.setPassword(pwForm.getNewPassword());
			userService.saveUser(user);
			log.info("info -- id: {} update password", user.getId());
			
			attribredirectAttributes.addFlashAttribute("successMessage", "비밀번호를 변경했습니다.");
		} catch(RuntimeException e) {
			attribredirectAttributes.addFlashAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
		}
		return "redirect:/user/mypage";
	}
}