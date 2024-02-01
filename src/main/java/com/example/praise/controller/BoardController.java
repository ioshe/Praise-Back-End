package com.example.praise.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@GetMapping("/like")
	public String likes(@RequestParam int boardId) {
		System.out.println(111);
		return "board/detail?boardId=3";
	}
}