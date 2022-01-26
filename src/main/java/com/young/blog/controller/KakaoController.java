package com.young.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.blog.model.KakaoProfile;
import com.young.blog.model.LoginType;
import com.young.blog.model.OAuthToken;
import com.young.blog.model.User;
import com.young.blog.service.UserService;

@Controller
public class KakaoController {
	@Autowired
	private UserService userSerivce;
	@Autowired
	private AuthenticationManager manager;
	
	@Value("${cos.key}")
	private String coskey;
	
	@GetMapping("/auth/kakao/callback")
	// 데이터 반환하는 컨트롤러 설정
	public String kakaocallback(String code) {
		// post방식으로 key-value 데이터를 카카오쪽으로 요청해야함
		// RestTemplate 를 통해 요청 가능
		// Retrofit2 / OkHttp 등 다른 라이브러리도 존재
		RestTemplate rt = new RestTemplate();
		// header 값
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		// body 값
		params.add("grant_type", "authorization_code");
		params.add("client_id", "1bba6aafcad7c5f83d3e74b925ceb08a");
		params.add("redirect_uri", "http://localhost:8000/blog/auth/kakao/callback");
		params.add("code", code);

		// 바디와 헤더 정보를 가진 엔터티 생성
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// 응답받을 엔터디 생성후 요청하고 값받기
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// 응답값을 담는방법 json=>java object
		// Gson,Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			// 변수명이나 getter setter 유무에 따른 예외처리
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//
		System.out.println(oauthToken.getAccess_token());
		String key = oauthToken.getAccess_token();

		RestTemplate rt2 = new RestTemplate();
		/*
		 * rt2.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		 * rt2.setErrorHandler(new DefaultResponseErrorHandler() { public boolean
		 * hasError(ClientHttpResponse response) throws IOException { HttpStatus
		 * statusCode = response.getStatusCode(); return statusCode.series() ==
		 * HttpStatus.Series.SERVER_ERROR; } });
		 */
		// header 값

		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + key);
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// 해더값 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = new HttpEntity<>(headers2);
		// 응답받을 엔터디 생성후 요청하고 값받기
		ResponseEntity<String> response2 = null;
		response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, kakaoTokenRequest2, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
			// 변수명이나 getter setter 유무에 따른 예외처리
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// User 오브젝트 : username, password, email
		System.out.println("카카오 ID (번호)" + kakaoProfile.getId());
		System.out.println("카카오 email" + kakaoProfile.getKakao_account().getEmail());

		// 카카오 유저 네임 : 카카오 등록 email + _ + kakao.getID
		// 블로그 서버 이메일 :
		// 블로그 서버 패스워드 : coskey
		
		// 권한은 USER

		// 강제로 로그인 시키기
		User user = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
				.password(coskey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.loginType(LoginType.KAKAO)
				.build();
		// 가입자 혹은 비가입자 체크
		User originUser = userSerivce.findUser(user.getUsername());
		if (originUser.getUsername() == null) {
			userSerivce.join(user);
		}
		// 로그인 처리
		Authentication authentication = manager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), coskey));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";

	}

}
