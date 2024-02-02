package com.example.praise.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.praise.model.dto.BoardDto;
import com.example.praise.model.entity.Board;
import com.example.praise.model.entity.User;
import com.example.praise.repository.BoardRepository;
import com.example.praise.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class BoardService {
	private BoardRepository brepo;
	private UserRepository urepo;
	
	@Autowired //생성자를 주입받는다. 자기가 알아서 서비스클래스의 들어오는 컨트럴러를 통해 받은 값을 가각ㄱ의 레포지토리의 값에 전달하도록 자기가 등록한 객체를 활용할 수 있도록 한다.
	public BoardService(BoardRepository brepo,UserRepository urepo){
		this.brepo = brepo;
		this.urepo = urepo;
	}
	@Transactional
	public void writeBoard(BoardDto dto) {
		System.out.println(dto);
		
		int senderId = dto.getSenderId();
		int receiverId = dto.getReceiverId();
		
		User sender = urepo.getReferenceById(senderId);
		User receiver = urepo.getReferenceById(receiverId);
		
		Board board = dto.toEntity();
		board.setViews(0);
		board.setLikes(0);
		
		Date currentDate = new Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		
	    board.setRegistDate(timestamp);
	    
	    board.setSender(sender);
		board.setReceiver(receiver);
		
		brepo.saveAndFlush(board);
	}
	
	public Page<Board> listBoard(int page){
		Pageable pageable = PageRequest.of(page, 10, Direction.DESC, "boardId");
		Page<Board> pageInfo = brepo.findAll(pageable);
		return pageInfo;
	}
	
	public Board detailBoard(int boardId) {
		// 값이 없는 경우에 에러를 제시하기 위해 
		Optional<Board> option = brepo.findById(boardId); // isPresent() 
		if (option.isPresent()) {
			return option.get();
		}else {
			throw new RuntimeException(boardId + "라는 글은 없습니다");
		}
	}
	
	public void deleteBoard(int boardId) {
		brepo.deleteById(boardId);
	}
	
	public void updateViews(int boardId) {
		Board board = brepo.getReferenceById(boardId);
		int currentViews = board.getViews() + 1;
		board.setViews(currentViews);
		brepo.saveAndFlush(board);
	}
	
	public int toggleLike(int boardId,HttpSession session) {
		Board board = brepo.getReferenceById(boardId);
		int currentLikes = board.getLikes();
		Integer like = (Integer) session.getAttribute(String.valueOf(boardId)+"Like");
		if ((like == null) || (like == 0)){
			session.setAttribute(String.valueOf(boardId)+"Like", 1);
			currentLikes += 1;
		}
		else {
			session.setAttribute(String.valueOf(boardId)+"Like",0);
			currentLikes -=1;
		}
		board.setLikes(currentLikes);
		brepo.saveAndFlush(board);
		return currentLikes;
	}
}