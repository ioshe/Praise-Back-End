package com.example.praise.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.praise.model.dto.BoardDto;
import com.example.praise.model.entity.Board;
import com.example.praise.service.BoardService;
import com.example.praise.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService bService;
	private UserService uService;
	
	public BoardController(BoardService bService, UserService uService) {
		this.bService = bService;
		this.uService = uService;
	}
	
	// 글 작성하기로 이동
	@GetMapping("/regist") 
	public String registForm(Model model) {
	    Map<Integer, String> realnames = uService.getAllRealNames();
	    model.addAttribute("realnames", realnames);
	    return "board/registboard";
	}
	
	// 글 작성하는 기능
	@PostMapping("/regist")
	public String registBoard(@ModelAttribute BoardDto board) {
		bService.writeBoard(board);
		log.info("info -- id: {} create new content", board.getSenderId());
		return "redirect:/board/list";
	}
	
	// 전체 글 조회하기
	@GetMapping("/list")
	public String list(@RequestParam(required = false, defaultValue = "1") Integer page, Model model) {
		page--;
		Page<Board> pageInfo = bService.listBoard(page);
		model.addAttribute("pageInfo", pageInfo);
		System.out.println(pageInfo);
		return "board/list";
	}
	
	// 단일 글 조회하기
	@GetMapping("/detail")
	public String detail(@RequestParam int boardId, Model model) {
		try {
			Board board = bService.detailBoard(boardId);
			bService.updateViews(boardId);
			model.addAttribute("board",board);
			return "board/detail";
		}catch(RuntimeException e) {
			return "board/list";
		}
	}
	
	// 삭제하는 메서드  /delete?boardId=3
	@GetMapping("/delete")
	public String delete(@RequestParam int boardId) {
		bService.deleteBoard(boardId);
		return "redirect:/board/list";  // /WEB-INF/view/list.jsp
	}
	
	// 좋아요 기능 
	@PostMapping("/like")
	public ResponseEntity<String> toggleLike(@RequestBody Map<String, Object> requestBody, HttpSession session) {
	    // 요청 바디에서 필요한 값들을 추출합니다.
//		int boardId = Integer.parseInt((String) requestBody.get("boardId"));
//	    int senderId = Integer.parseInt((String) requestBody.get("senderId"));
//		int currentUserId = Integer.parseInt((String) requestBody.get("currentUserId"));
//		int boardId = (Integer) requestBody.get("boardId");
//	    int senderId = (Integer) requestBody.get("senderId");
//		int currentUserId = (Integer) requestBody.get("currentUserId");
		Object boardIdObj = requestBody.get("boardId");
		int boardId = boardIdObj instanceof Integer ? (Integer) boardIdObj : Integer.parseInt(boardIdObj.toString());

		Object senderIdObj = requestBody.get("senderId");
		int senderId = senderIdObj instanceof Integer ? (Integer) senderIdObj : Integer.parseInt(senderIdObj.toString());

		Object currentUserIdObj = requestBody.get("currentUserId");
		int currentUserId = currentUserIdObj instanceof Integer ? (Integer) currentUserIdObj : Integer.parseInt(currentUserIdObj.toString());

		
	    // 좋아요 상태를 토글하고, 현재 좋아요 수를 반환하는 로직
	    int currentLikeCount = bService.toggleLike(boardId, session, senderId, currentUserId);

	    // 현재 좋아요 수를 JSON 형식으로 응답합니다.
	    return ResponseEntity.ok("{\"likeCount\":" + currentLikeCount + "}");
	}

	//@GetMapping("/like")
//	public ResponseEntity<String> toggleLike(@RequestParam int boardId,HttpSession session) {
//	    // 좋아요 상태를 토글하고, 현재 좋아요 수를 반환하는 로직
//		int currentLikeCount = bService.toggleLike(boardId,session);
//	    return ResponseEntity.ok("{\"likeCount\":" + currentLikeCount + "}");
//	}
}