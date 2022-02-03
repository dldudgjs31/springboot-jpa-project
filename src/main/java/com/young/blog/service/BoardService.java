package com.young.blog.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.blog.model.Board;
import com.young.blog.model.Reply;
import com.young.blog.model.User;
import com.young.blog.repository.BoardRepository;
import com.young.blog.repository.ReplyRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	/**
	 * 댓글 쓰기
	 * @param user
	 * @param boardId
	 * @param requestreply
	 */
	@Transactional
	public void writeReply(User user, int boardId, Reply requestreply){
		requestreply.setUser(user);
		Board board=boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 스기 실패 : id 찾을 수 없음");
		});

		requestreply.setBoard(board);
		replyRepository.save(requestreply);
	}


	/**
	 * 글쓰기
	 * @param board
	 * @param user
	 */
	@Transactional
	public void write(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	//페이지로 호출하면 리턴타입 page로 호출
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	/**
	 * 글 상세보기
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Board detail(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 : 아이디 찾을수 없음");
		});
	}
	/**
	 * 글 삭제
	 * @param id
	 */
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void update(int id, Board requestBoard) {
		Board board=boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 수정 실패 : 아이디를 찾을 수 없음");
		});
		// 해당 함수로 종료시에 트랜잭션 종료시(서비스 종료시) 트랜잭션이 종료됨 
		//이때 더티체킹하여 자동 업데이트
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}
}
