package com.brotao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FileUploadApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(FileUploadApplication.class, args);
	}
}
