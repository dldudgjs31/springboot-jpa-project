package com.young.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.young.blog.model.User;
import com.young.blog.repository.UserRepository;

@Service // 빈등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	/**
	 * 스프링이 로그인 요청을 가로챌때 username,passwrod 변수 2개를 가로채는데
	 * password 부분 처리는 알아서 함
	 * username이 db에 있는지만 확인
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User pricipal= userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
		});
		return new PrincipalDetail(pricipal); // 시큐리티 세션에 유저 정보 저장
	}
	
}
