package com.brotao.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class MainTest {

	@Test
	public void test() throws IOException, URISyntaxException {
		String str = "jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//		URL url = new URL(str);
		URI uri = new URI(str);
		log.info("123");
		uri.getHost();
	}
}
