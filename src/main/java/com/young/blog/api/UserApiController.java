package com.young.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.young.blog.dto.ResponseDto;
import com.young.blog.model.RoleType;
import com.young.blog.model.User;
import com.young.blog.service.UserService;

@RestController
public class UserApiController {
	
	//DI 개념
	@Autowired
	private UserService userService;
	
	//@Autowired
	//private HttpSession session;

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("save 호출 완료");
		
		//실제로 db에 insert를 하고 아래에서 return
		user.setRole(RoleType.USER);
		int result=userService.join(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
	}
	
	/**
	 * 전통적인 로그인 방식
	 * @param user
	 * @return
	 */
	/*
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		System.out.println("UserApiController: login 호출 됨");
		User principal=userService.login(user);
		
		//세션을 만들어줌
		if(principal!=null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}*/

}
