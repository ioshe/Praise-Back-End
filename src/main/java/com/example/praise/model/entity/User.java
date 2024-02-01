package com.example.praise.model.entity;

import java.util.List;

import com.example.praise.model.dto.UserDto;

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
<<<<<<< Updated upstream
@ToString(exclude="board")
public class User {
	@Id
	private int id;	// autoIncreasement
	private String userName; // loginID
	private String passWord;
	private String realName;
	String className;
	String nickName;
=======
@ToString(exclude = {"sentBoards", "receivedBoards"}) // 순환참조 방지

@Builder
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	// autoIncreasement
	
	private String username; // loginID
	private String password;
	private String realname;
	private String classname;
	private String nickname;
>>>>>>> Stashed changes
	int userPoint;
	
	@OneToMany(mappedBy = "user")
	List<Board> boards;
	
	// DTO -> Entity
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setId(this.getId());
<<<<<<< Updated upstream
		dto.setUserName(this.getUserName());
		dto.setPassWord(this.getPassWord());
		dto.setRealName(this.getRealName());
		dto.setClassName(this.getClassName());
		dto.setNickName(this.getNickName());
=======
		dto.setUsername(this.getUsername());
		dto.setPassword(this.getPassword());
		dto.setRealname(this.getRealname());
		dto.setClassname(this.getClassname());
		dto.setNickname(this.getNickname());
>>>>>>> Stashed changes
		dto.setUserPoint(this.getUserPoint());
		return dto;
	}
}