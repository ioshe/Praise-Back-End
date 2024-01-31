package com.example.praise.model.dto;


import com.example.praise.model.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private int no;
	private String title;
	private String content;
	private int sendId;
	private int receiveId;
	
	public Board toEntity() {
		Board board = new Board();
		board.setNo(this.getNo());
		board.setTitle(this.getTitle());
		board.setContent(this.getContent());
		board.setSendId(this.getSendId());
		board.setReceiveId(this.getReceiveId());
		return board;
	}
}