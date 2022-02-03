package com.young.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.young.blog.auth.PrincipalDetail;
import com.young.blog.dto.ResponseDto;
import com.young.blog.model.Board;
import com.young.blog.model.Reply;
import com.young.blog.model.RoleType;
import com.young.blog.model.User;
import com.young.blog.service.BoardService;
import com.young.blog.service.UserService;

@RestController
public class BoardApiController {
	
	//DI 개념
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		// title, content + user 정보
		boardService.write(board,principal.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.update(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {

		boardService.writeReply(principal.getUser(),boardId,reply);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);


	}
}
