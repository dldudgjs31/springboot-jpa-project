package com.young.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.young.blog.model.RoleType;
import com.young.blog.model.User;
import com.young.blog.repository.UserRepository;

@RestController // html이 아닌 data를 리턴해주는 컨트롤러
public class DummyController {
	
	@Autowired // 위에 컨트롤러가 컴포넌트 스캔될시 등록된다. // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER); //enum으로 저장한 유저 roletype을 기입해준다.
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다";
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		/*
		User user=userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				return new User();
			}
		}); // 리턴타입이 Optional임 // orElseGet 은 혹여 없는 데이터인 경우 뒤에 부분 실행됨 (빈객체를 불러옴 =>null 방지)
		*/
		// illegalArgumentException을 발생시켜 메시지와 전달
		User user= userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 없습니다.id:"+id);
			}
		});
		/* 람다식을 사용하면 짧게 코딩 가능해진다.
		User user= userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("해당유저는 없습니다.id:"+id);
			}
		});
		*/
		
		//user 객체는 자바 오브젝트이기때문에 변환해서 보내줘야한다.
		//json타입으로 변환하여 전송
		//스프링 부트에서는 MessageConverter가 응답시 자동 응답한다.
		//만일 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서 
		// user오브젝트를 json으로 자동 변환하여 브라우저에 던져준다.
		return user;
	}
}
