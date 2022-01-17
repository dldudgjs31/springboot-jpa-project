package com.young.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration //빈등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 / IOC 관
@EnableWebSecurity  // 시큐리티 필터 추가 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겟다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	/**
	 * password 암호화 처리
	 * @return
	 */
	@Bean //IOC 처리
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화(테스트시 걸어두는게 좋음)
			.authorizeRequests()
				.antMatchers("/auth/**","/js/**","/css/**","/") // /auth/ 이하 접근 허용
				.permitAll()
				.anyRequest()
				.authenticated()
			.and() // auth이외의 경로 접근시 로그인 페이지로 이동
				.formLogin()
				.loginPage("/auth/loginForm");
	}

}
