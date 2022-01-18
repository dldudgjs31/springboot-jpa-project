package com.young.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.young.blog.model.User;
//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 
//UserDetails 타입의 오브젝트를 스프링 스큐리티의 고유한 세션 저장소에 저장해준다.
public class PrincipalDetail implements UserDetails{
	private User user;
	/**
	 * 생성자와 getter setter 생성
	 * @param user
	 */
	public PrincipalDetail(User user) {
		this.user=user;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	/**
	 * 계정의 권한 리턴
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole(); // ROLE_USER // 앞에 ROLE_ 을 꼭 붙여야 인식함
			}
		});
		return collectors;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	/**
	 * 계정 만료 여부 리턴 
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 계정 잠금 여부 리턴
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 비밀번호 만료 여부
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 계정 활성화 인지 리턴 (true 활성화)
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
