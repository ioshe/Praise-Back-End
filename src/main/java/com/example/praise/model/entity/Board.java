package com.example.praise.model.entity;

import java.sql.Timestamp;

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
@ToString(exclude = {"sender", "receiver"}) // 순환참조 방지
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int boardId; // 글 번호
	private String title; // 글 제목
	private String content; // 글 내용
	private int views;
	private int likes;
	private boolean anonymous;
	
	
	private Timestamp registDate;

	@ManyToOne
    @JoinColumn(name = "sendId") 
    private User sender;
    // 칭찬을 받는 사용자
    @ManyToOne
    @JoinColumn(name = "receiveId")
    private User receiver;
	
	public BoardDto toDto() {
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardId(this.getBoardId());
		boardDto.setTitle(this.getTitle());
		boardDto.setContent(this.getContent());
		
		boardDto.setViews(this.getViews());
		boardDto.setLikes(this.getLikes());
		boardDto.setAnonymous(this.isAnonymous());
		
		boardDto.setRegistDate(this.getRegistDate());
		
		boardDto.setSenderId(this.getSender().getId());
		boardDto.setReceiverId(this.getReceiver().getId());
		return boardDto;
	}
}