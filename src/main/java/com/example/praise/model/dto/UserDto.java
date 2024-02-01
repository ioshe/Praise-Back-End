package com.example.praise.model.dto;

import java.util.List;

import com.example.praise.model.entity.Board;
import com.example.praise.model.entity.User;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private int id;
	private String username;
	private String password;
	private String realname;
	private String classname;
	private String nickname;
	private int userPoint;
	
	private List<Board> sentBoards;
	private List<Board> receivedBoards;
	
	public User toEntity() {
		User user = new User();
		user.setId(this.getId());
		user.setPassword(this.getPassword());
		user.setRealname(this.getRealname());
		user.setClassname(this.getClassname());
		user.setNickname(this.getNickname());
		user.setUserPoint(this.getUserPoint());
		return user;
	}
}