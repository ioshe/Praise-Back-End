package com.example.praise.service;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<Integer, String> getAllRealNames() {
        List<User> users = urepo.findAll();
        Map<Integer, String> realnames = new HashMap<>();
        
        for (User user : users) {
            realnames.put(user.getId(), user.getRealname());
        }

        return realnames;
	}
}