package com.example.praise.model.dto;

import java.sql.Timestamp;

import com.example.praise.model.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private int boardId;
	private String title;
	private String content;
	
	private int views;
	private int likes;
	private boolean anonymous;
	private Timestamp registDate;
	
	private int senderId; // 칭찬하는 유저 ID
	private int receiverId; // 칭찬받는 유저 ID
		
	public Board toEntity() {
		Board board = new Board();
		board.setBoardId(this.getBoardId());
		board.setTitle(this.getTitle());
		board.setContent(this.getContent());
		board.setAnonymous(this.isAnonymous());
		board.setRegistDate(this.getRegistDate());
		return board;
	}
}