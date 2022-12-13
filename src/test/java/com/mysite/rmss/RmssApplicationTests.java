package com.mysite.rmss;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class RmssApplicationTests {

	@Test
	void contextLoads() {
		String url = "/shop/sExSeXbO";
		url = URLEncoder.encode(url, StandardCharsets.UTF_8);
		System.out.println(url);
	}

}
