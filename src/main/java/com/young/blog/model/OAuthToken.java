package com.young.blog.model;

import lombok.Data;

@Data
public class OAuthToken {
	private String token_type;
	private String access_token;
	private int expires_in;
	private String scope;
	private String refresh_token;
	private int refresh_token_expires_in;
	/*
	 * token_type	String	토큰 타입, bearer로 고정	O
	   access_token	String	갱신된 사용자 액세스 토큰 값	O
	   expires_in	Integer	액세스 토큰 만료 시간(초)	O
	   refresh_token	String	갱신된 사용자 리프레시 토큰 값, 기존 리프레시 토큰의 유효기간이 1개월 미만인 경우에만 갱신	X
	   refresh_token_expires_in	Integer	리프레시 토큰 만료 시간(초)	X

	 * */
}
