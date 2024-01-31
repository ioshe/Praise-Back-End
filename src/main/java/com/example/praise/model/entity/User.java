package com.example.praise.model.entity;

import java.util.List;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="board")
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
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setId(this.getId());
		dto.setUserName(this.getUserName());
		dto.setPassWord(this.getPassWord());
		dto.setRealName(this.getRealName());
		dto.setClassName(this.getClassName());
		dto.setNickName(this.getNickName());
		dto.setUserPoint(this.getUserPoint());
		return dto;
	}
}