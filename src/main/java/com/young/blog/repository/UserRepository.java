package com.young.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.young.blog.model.User;

// user의 pk integer
//DAO 의 역할
//Spring 빈등록 된다. 따라서 @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//JPA 네이밍 전략
	// SELECT * FROM user WHERE username=?1 AND password=?2; 이런 쿼리가 내부적으로 동작함
	//User findByUsernameAndPassword(String username, String password);
	
	//다른 방법
	//@Query(value="SELECT * FROM user WHERE username=?1 AND password =?2", nativeQuery = true)
	//User login(String username, String password);
}
