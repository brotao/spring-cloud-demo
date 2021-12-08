package com.brotao.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * @author luotao
 * @className MainApp
 * @description MainApp
 * @date 2021-12-08 9:41
 */

@SpringBootApplication
@IntegrationComponentScan
public class MainApp {
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
