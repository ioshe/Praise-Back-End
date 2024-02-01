package com.example.praise.model.entity;

import java.util.List;

import com.example.praise.model.dto.UserDto;
import com.example.praise.model.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="board")
@Builder
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;	// autoIncreasement
	private String username; // loginID
	private String password;
	private String realName;
	String className;
	String nickName;
	int userPoint;
	 
    // 이 사용자가 보낸 모든 칭찬 게시물
    @OneToMany(mappedBy = "sender")
    private List<Board> sentBoards;
    // 이 사용자가 받은 모든 칭찬 게시물
    @OneToMany(mappedBy = "receiver")
    private List<Board> receivedBoards;
    
    
	// Entity -> Dto
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setId(this.getUserId());
		dto.setUserName(this.getUsername());
		dto.setPassWord(this.getPassword());
		dto.setRealName(this.getRealName());
		dto.setClassName(this.getClassName());
		dto.setNickName(this.getNickName());
		dto.setUserPoint(this.getUserPoint());
		return dto;
	}
}