package com.brotao.sb.controller;

import com.brotao.sb.service.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProducerController {
	@Autowired
	private Producer producer;

	@GetMapping("/produce/{msg}")
	public void produceMsg(@PathVariable("msg") String msg) {
		producer.produce(msg);
	}
}
