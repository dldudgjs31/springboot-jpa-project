package com.young.blog.service;


import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.blog.model.User;
import com.young.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해 bean에 등록해준다. IOC 해줌
@Service
public class UserService {
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encode;
	

	//회원가입 전체의 서비스가 하나의 트랜잭션으로 묶이게됨.
	@Transactional
	public int join(User user) {
		try {
			// password 암호화 처리
			String rawPassword=user.getPassword();
			String encPassword = encode.encode(rawPassword);
			user.setPassword(encPassword);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("userService: 회원가입"+e.getStackTrace());
		}
		return -1;
	}
	/**
	 * 로그인 기능
	 * @param user
	 * @return
	 */
	/*
	@Transactional(readOnly = true) // select을 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}*/
	
	@Transactional
	public void update(User user) {
		//수정시에는 영속성 컨텍스트라는 곳에 user 오브젝트를 영속화하고 user 오브젝트를 수정
		// select해서 user오브젝트를 db로부터 가져와 영속화하기
		User persistance= userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword=user.getPassword();
		String encPassword= encode.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//회원수정 함수 종료시 서비스 종료 => 트랜잭션 종료 and commit
		

	}
}
