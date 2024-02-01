package com.example.praise.service;

import org.springframework.stereotype.Service;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;
import com.example.praise.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	private UserRepository urepo;
	// urepo에 데이터 넣기 위한 생성자 - 전달받은 데이터를 데리고 갈 준비
	public UserService(UserRepository urepo) {
		this.urepo = urepo;
	}
	
	// 1. auth
	// 1) 회원가입
	@Transactional	// 무결성 유지
	public void register(UserDto dto) {
		User user = dto.toEntity();
		if (urepo.findByUsername(user.getUserId()).isPresent()) {
			throw new RuntimeException("이미 존재하는 사용자 입니다.");
		} else {
			urepo.saveAndFlush(user);
		}
	}
	
}
