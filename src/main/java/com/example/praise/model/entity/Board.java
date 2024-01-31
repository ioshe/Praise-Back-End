package com.example.praise.model.entity;

import com.example.praise.model.dto.BoardDto;

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
@ToString(exclude = {"recevieUser", "sendUser"}) // 순환참조 방지
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int no; // 글 번호
	private String title; // 글 제목
	private String content; // 글 내용
	private int receiveId; // 칭찬받는 유저 인덱스
	private int sendId; // 칭찬하는 유저 인덱스
	
	@ManyToOne
	@JoinColumn(name = "userId") // SELECT * FROM USER WHERE ID = ?;
	User recevieUser;
	User sendUser;
	
	public BoardDto toDto() {
		BoardDto boardDto = new BoardDto();
		boardDto.setNo(this.getNo());
		boardDto.setTitle(this.getTitle());
		boardDto.setContent(this.getContent());
		boardDto.setReceiveId(this.getRecevieUser().getId());
		boardDto.setSendId(this.getSendUser().getId());		
		return boardDto;
	}
}