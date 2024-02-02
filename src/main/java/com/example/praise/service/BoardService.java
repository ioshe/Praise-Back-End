package com.example.praise.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
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
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(dto);
		
		int senderId = dto.getSenderId();
		int receiverId = dto.getReceiverId();
		
		User sender = urepo.getReferenceById(senderId);
		User receiver = urepo.getReferenceById(receiverId);
		
		point(sender,50); // 글쓴이는 50점을 point로 받는다.
		point(receiver,100); // 칭찬을 받은 사람은 100점을 point로 받는다.
		
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
	
	// ResponseEntity<String> toggleLike 에서 사용
	public int toggleLike(int boardId,HttpSession session,int senderId,int currentUserId) {
		Board board = brepo.getReferenceById(boardId); //현재 board의 Id를 가져온다.
		int currentLikes = board.getLikes(); //현재 board의 like를 가져온다.
		
		Integer like = (Integer) session.getAttribute(String.valueOf(boardId)+"Like"); //null값을 방지하기 위해 Integer를 사용하였고 session에 Like를 사용한 기록이 있는지 살핀다.
		if ((like == null) || (like == 0)){ //Like 가 null이라면 최초의 요청이므로 +1, 0이라면 안누른 상태이니 +1
			point(senderId,10); // 글작성자에게 좋아요시 10점
			point(currentUserId,5); // 글 좋아요 누른 사람에게 5점
			session.setAttribute(String.valueOf(boardId)+"Like", 1);
			currentLikes += 1;
		}
		else { 
			point(senderId,-10); // 글작성자에게 좋아요시 -10점
			point(currentUserId,-5); // 글 좋아요 누른 사람에게 -5점
			session.setAttribute(String.valueOf(boardId)+"Like",0);
			currentLikes -=1;
		}
		
		board.setLikes(currentLikes);
		brepo.saveAndFlush(board);
		return currentLikes;
	}

	public void point(int userId, int pointUp) { //userId로 줄 때 point를 up 하는 것
		User user = urepo.getReferenceById(userId); 
		int temp = user.getUserPoint()+pointUp;
		System.out.println("#################3");
		System.out.println(user.getUserPoint());
		System.out.println(pointUp);
		System.out.println(temp);
		user.setUserPoint(temp);
		System.out.println(user);
		//user.setUserPoint(user.getUserPoint()+pointUp);
		urepo.save(user);
	}
	public void point(User user, int pointUp) { //User 객체로 줄 때 point를 up 하는 것
		user.setUserPoint(user.getUserPoint()+pointUp);
		urepo.save(user);
	}
	
}