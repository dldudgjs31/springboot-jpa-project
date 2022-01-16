package com.young.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
}
