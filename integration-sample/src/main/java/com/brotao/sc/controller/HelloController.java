package com.brotao.sc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luotao
 * @className HelloController
 * @description HelloController
 * @date 2021-12-08 10:22
 */
@RestController
@Slf4j
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
