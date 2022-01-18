package com.young.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.young.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
