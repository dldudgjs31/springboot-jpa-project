package com.young.blog.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.young.blog.dto.ResponseDto;

@ControllerAdvice //모든 exception 발생시 해당 컨트럴러 실행 // aop 개념
@RestController
public class GlobalExceptionHandler {
	
	
	/**
	 * exception 발생시에 실행시킬 컨트롤러를 지정한다.
	 * @ExceptionHandler 를 기입하여 지정한다. value= <exception 명>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	/**
	 * 회원가입시 이미 존재하는 회원일시 발생하는 예외처리
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value=ConstraintViolationException.class)
	public ResponseDto<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}
