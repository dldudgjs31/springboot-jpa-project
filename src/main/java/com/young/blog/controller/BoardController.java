package com.young.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.blog.model.Board;
import com.young.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	@GetMapping({"","/"})
	//@ResponseBody
	public String index(Model model,
			@PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC) Pageable pageable
			) {
		model.addAttribute("boards",boardService.list(pageable));
		Page<Board> page=boardService.list(pageable);
		return "index";
		//return page;
	}
	
	//User권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm"; 
	}
	/**
	 * 글 상세보기
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/detail";
	}
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/updateForm";
	}
	
}
