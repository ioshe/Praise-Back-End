package com.example.praise.service;

import java.util.Optional;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.praise.controller.AuthController;
import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.Board;
import com.example.praise.model.entity.User;
import com.example.praise.model.form.PasswordForm;
import com.example.praise.repository.BoardRepository;
import com.example.praise.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	private UserRepository urepo;
	private BoardRepository brepo;
	
	// urepo에 데이터 넣기 위한 생성자 - 전달받은 데이터를 데리고 갈 준비
	public UserService(UserRepository urepo, BoardRepository brepo) {
		this.urepo = urepo;
		this.brepo = brepo;
	}
	
	public Map<Integer, String> getAllRealNames() {
        List<User> users = urepo.findAll();
        Map<Integer, String> realnames = new HashMap<>();
        
        for (User user : users) {
            realnames.put(user.getId(), user.getRealname());
        }

        return realnames;
	}
	
	public Optional<User> getUserById(int id) {
		return urepo.findById(id);
	}
	
	

	// 1. auth
	// 1) 회원가입
	@Transactional	// 무결성 유지
	public User register(UserDto dto) {
		User user = dto.toEntity();
		if (urepo.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("이미 존재하는 ID 입니다.");
		} else if (urepo.findByNickname(user.getNickname()).isPresent()){
			throw new RuntimeException("이미 존재하는 닉네임입니다.");
		}else {
			return urepo.saveAndFlush(user);
			
		}
	}

	// 2) 회원탈퇴
	public void signout(HttpSession session) {
		UserDto loginUser = (UserDto) session.getAttribute("loginUser");
		urepo.deleteById(loginUser.getId());
	}
	
	// 3) 로그인
	public UserDto login(UserDto dto) {
		Optional<User> result = urepo.findByUsername(dto.getUsername());
		if (result.isPresent()) {
			User selected = result.get();
			if(selected.getPassword().equals(dto.getPassword())) {
				return selected.toDto();
			}
		}
		throw new RuntimeException("로그인에 실패하였습니다.");
	}
	
	// mypage 비밀번호 변경
	public Optional<User> getPasswordById(int id, String curPassword) {
		System.out.println(id + "/ " + curPassword);
		Optional<User> user = urepo.findByIdAndPassword(id, curPassword);
		if(user.isPresent()) {
			return user;
		} else {
			throw new RuntimeException("비밀번호가 틀렸습니다.");
		}
	}
	
	public User saveUser(User user) {
		return urepo.save(user);
	}
	
	// 내가 쓴 글 불러오기
	public Page<Board> getUserBoardList(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        User sender = urepo.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저가 없습니다"));
        return brepo.findBySender(sender, pageable);
    }

}