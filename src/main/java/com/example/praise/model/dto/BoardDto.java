package com.example.likeBoard.model.dto;

import com.example.likeBoard.model.entity.Board;

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
	private int send_Id;
	private int receive_Id;
	
	public Board toEntity() {
		Board board = new Board();
		board.setNo(this.getNo());
		board.setTitle(this.getTitle());
		board.setContent(this.getContent());
		board.setSend_Id(this.getSend_Id());
		board.setReceive_Id(this.getReceive_Id());
		return board;
	}
}