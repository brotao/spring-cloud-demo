package com.brotao.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

@Slf4j
public class MainTest {

	@Test
	public void test() {
		log.info(Charset.defaultCharset().displayName());
	}
}
