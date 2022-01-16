package com.young.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.blog.model.User;
import com.young.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해 bean에 등록해준다. IOC 해줌
@Service
public class UserService {
	@Autowired 
	private UserRepository userRepository;
	
	//회원가입 전체의 서비스가 하나의 트랜잭션으로 묶이게됨.
	@Transactional
	public int join(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("userService: 회원가입"+e.getStackTrace());
		}
		return -1;
	}
}
