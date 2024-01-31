package com.example.likeBoard.model.entity;

import java.util.List;

import com.example.likeBoard.model.entity.User;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private int id;	// autoIncreasement
	private String userName; // loginID
	private String passWord;
	private String realName;
	String className;
	String nickName;
	int userPoint;
	
	@OneToMany(mappedBy = "user")
	List<Board> boards;
	
	// DTO -> Entity
	public User toDto() {
		User user = new User();
		user.setId(this.getId());
		user.setUserName(this.getUserName());
		user.setPassWord(this.getPassWord());
		user.setRealName(this.getRealName());
		user.setClassName(this.getClassName());
		user.setNickName(this.getNickName());
		user.setUserPoint(this.getUserPoint());
		return user;
	}
}