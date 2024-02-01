package com.example.praise.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.praise.model.dto.BoardDto;
import com.example.praise.model.entity.Board;
import com.example.praise.service.BoardService;
import com.example.praise.service.UserService;

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
	
	// 글 삭제하는 기능
	@DeleteMapping("/detail")
	public String deleteBoard(@RequestParam int boardId){
		// 글 삭제 머서드
		return "redirect:/board/list";
	}
	
	// 좋아요 기능 
	@PatchMapping("/detail")
	public void likes(@RequestParam int likes) {
		//자바스크립트? 아니라면 int return
	}
	
	//목록으로 돌아가는 기능
	@GetMapping("/back")
	public String backList() {
		//현재 작성중인것을 저장할거냐를 물어볼까?
		return "redirect:/board";
	}


	
	// 단일 글 조회하기
	@GetMapping("/detail")
	public String detail(@RequestParam int board_id, Model model) {
		try {
			//Board board = service.detailBoard(board_id);
			//model.addAttribute("board",board);
			return "board/detail";
		}catch(RuntimeException e) {
			return "board/list";
		}
	}
	

	
}