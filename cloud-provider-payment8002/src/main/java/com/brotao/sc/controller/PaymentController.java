package com.brotao.sc.controller;

import com.brotao.sc.common.CommonResult;
import com.brotao.sc.entities.Payment;
import com.brotao.sc.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String port;

	@PostMapping(value = "/payment/create")
	public CommonResult<Payment> create(@RequestBody Payment payment)
	{
		int result = paymentService.create(payment);
		log.info("*****插入结果："+result);

		if(result > 0)
		{
			return new CommonResult<>(200,port + "插入数据库成功:"+result);
		}else{
			return new CommonResult<>(444,port + "插入数据库失败",null);
		}
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
	{
		Payment payment = paymentService.getPaymentById(id);

		if(payment != null)
		{
			return new CommonResult<>(200,port + "查询成功", payment);
		}else{
			return new CommonResult<>(444,port + "没有对应记录,查询ID: "+id,null);
		}
	}
}
