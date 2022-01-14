package com.young.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.young.blog.model.User;

// user의 pk integer
//DAO 의 역할
//Spring 빈등록 된다. 따라서 @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
