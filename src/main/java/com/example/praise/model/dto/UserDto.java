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
	
	private int id;	// autoIncreasement
	private String userName; // loginID
	private String passWord;
	private String realName;
	String className;
	String nickName;
	int userPoint;
	private List<Board> boards;
	
	// Dto -> Entity
	public User toEntity() {
		User user = new User();
		user.setUserId(this.getId());
		user.setUsername(this.getUserName());
		user.setPassword(this.getPassWord());
		user.setRealName(this.getRealName());
		user.setClassName(this.getClassName());
		user.setNickName(this.getNickName());
		user.setUserPoint(this.getUserPoint());
		return user;
	}
	
}