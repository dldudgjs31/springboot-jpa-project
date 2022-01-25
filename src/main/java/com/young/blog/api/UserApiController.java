package com.young.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
		//여기서는 트랜잭션이 종료되기때문에 DB값은 변경 완료됨
		//세션값은 변경되지 않음 => 이로 인해 변경이후에 로그아웃을 하지 않으면 변경되지 않는 걸 확인할 수 있음
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
