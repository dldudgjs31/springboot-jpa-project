package com.young.blog.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.blog.model.OAuthToken;

@Controller
public class KakaoController {


	@GetMapping("/auth/kakao/callback")
	 //데이터 반환하는 컨트롤러 설정
	public @ResponseBody String kakaocallback(String code) {
		//post방식으로 key-value 데이터를 카카오쪽으로 요청해야함
		//RestTemplate 를 통해 요청 가능
		//Retrofit2 / OkHttp 등 다른 라이브러리도 존재
		RestTemplate rt =new RestTemplate();
		//header 값
		HttpHeaders headers =new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		// body 값
		params.add("grant_type", "authorization_code");
		params.add("client_id", "1bba6aafcad7c5f83d3e74b925ceb08a");
		params.add("redirect_uri", "http://localhost:8000/blog/auth/kakao/callback");
		params.add("code",code);
		
		//바디와 헤더 정보를 가진 엔터티 생성
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		
		//응답받을 엔터디 생성후 요청하고 값받기
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		
		//응답값을 담는방법 json=>java object
		//Gson,Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken =null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			//변수명이나 getter setter 유무에 따른 예외처리
		} catch (JsonMappingException e) {
			e.printStackTrace();
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//
		System.out.println(oauthToken.getAccess_token());
		
		RestTemplate rt2 =new RestTemplate();
		//header 값
		HttpHeaders headers2 =new HttpHeaders();
		headers.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		//해더값 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 =
				new HttpEntity<>(headers2);
		
		//응답받을 엔터디 생성후 요청하고 값받기
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoTokenRequest2,
				String.class
				);
		
		return response2.getBody();
		
	}
	
}
