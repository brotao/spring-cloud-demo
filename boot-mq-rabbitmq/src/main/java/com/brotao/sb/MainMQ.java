package com.brotao.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.brotao")
public class MainMQ {
	public static void main(String[] args) {
		SpringApplication.run(MainMQ.class, args);
	}
}
