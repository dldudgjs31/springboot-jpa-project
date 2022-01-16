package com.young.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("save 호출 완료");
		
		//실제로 db에 insert를 하고 아래에서 return
		user.setRole(RoleType.USER);
		int result=userService.join(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK,result);
	}
}
