package com.example.praise.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;
import com.example.praise.model.form.PasswordForm;
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
	public User register(UserDto dto) {
		User user = dto.toEntity();
		if (urepo.findByUsername(user.getUsername()) != null) {
			throw new RuntimeException("이미 존재하는 사용자 입니다.");
		} else {
			return urepo.saveAndFlush(user);
		}
	}

	public Optional<User> getUserById(int id) {
		return urepo.findById(id);
	}

	public Optional<User> getPasswordById(int id, String curPassword) {
		System.out.println(id + "/ " + curPassword);
		Optional<User> user = urepo.findByIdAndPassword(id, curPassword);
		if(user.isPresent()) {
			System.out.println("비밀번호 맞았어요 굿굿");
			return user;
		} else {
			System.out.println("비밀번호가 틀렸습니다.!!!!!!");
			throw new RuntimeException("비밀번호가 틀렸습니다.");
		}
	}
	
	public User saveUser(User user) {
		return urepo.save(user);
	}
	
}
