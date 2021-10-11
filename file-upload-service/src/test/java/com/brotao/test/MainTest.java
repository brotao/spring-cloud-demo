package com.brotao.test;

import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
public class MainTest {

	@Test
	public void test() {
		log.info(Charset.defaultCharset().displayName());
	}

	@Test
	public void test2() throws URISyntaxException {
		URI uri = new URI("ftp", "luotao:123@456", "127.0.0.1", 22,
				"\teset", null, null);
		log.info("uri: {}", uri.toString());
	}

	@Test
	public void test3() {
		Date date = new Date(342946132868L);
		log.info(date.toString());

		Boolean a = true;
		Boolean b = true;
		Boolean c = new Boolean(true);
		Boolean d = null;

		if (a == b ) {
			log.info("aaa");
		}

		if (a.equals(c)) {
			log.info("ccc");
		}

		if (Boolean.TRUE.equals(d)) {
			log.info("ddd");
		}

		if (Boolean.TRUE.equals(a)) {
			log.info("aaaddd");
		}
	}
}
