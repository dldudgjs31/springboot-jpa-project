package com.young.blog;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void hash_enc() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println(encPassword);
	}
}
