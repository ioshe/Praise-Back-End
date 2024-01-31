package com.example.likeBoard.model.entity;

import com.example.likeBoard.model.dto.BoardDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = {"recevie_User", "send_User"}) // 순환참조 방지

public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int no; // 글 번호
	private String title; // 글 제목
	private String content; // 글 내용
	private int receive_Id; // 칭찬받는 유저 인덱스
	private int send_Id; // 칭찬하는 유저 인덱스
	
	@ManyToOne
	@JoinColumn(name = "user_id") // SELECT * FROM USER WHERE ID = ?;
	User recevie_User;
	User send_User;
	
	public BoardDto toDto() {
		BoardDto boardDto = new BoardDto();
		boardDto.setNo(this.getNo());
		boardDto.setTitle(this.getTitle());
		boardDto.setContent(this.getContent());
		boardDto.setReceive_Id(this.getRecevie_User().getId());
		boardDto.setSend_Id(this.getSend_User().getId());		
		return boardDto;
	}
}