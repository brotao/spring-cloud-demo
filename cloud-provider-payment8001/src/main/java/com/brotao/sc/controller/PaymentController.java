package com.brotao.sc.controller;

import com.brotao.sc.common.CommonResult;
import com.brotao.sc.entities.Payment;
import com.brotao.sc.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String port;

	@Resource
	private DiscoveryClient discoveryClient;

	@PostMapping(value = "/payment/create")
	public CommonResult<Payment> create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		log.info("*****插入结果：" + result);

		if (result > 0) {
			return new CommonResult<>(200, port + "插入数据库成功:" + result);
		} else {
			return new CommonResult<>(444, port + "插入数据库失败", null);
		}
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);

		if (payment != null) {
			return new CommonResult<>(200, port + "查询成功", payment);
		} else {
			return new CommonResult<>(444, port + "没有对应记录,查询ID: " + id, null);
		}
	}

	@GetMapping(value = "payment/discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();

		log.info(">>>>>>>>elements:");
		services.forEach(log::info);
		log.info("<<<<<<<<");

		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

		for (ServiceInstance instance : instances) {
			log.info("serviceId: {}, host: {}, port: {}, uri: {}",
					instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
		}

		return this.discoveryClient;
	}

	@GetMapping(value = "/payment/lb")
	public String getPaymentLB() {
		return port;
	}

	@GetMapping(value = "/payment/feign/timeout")
	public String paymentFeignTimeout() {
		// 业务逻辑处理正确，但是需要耗费3秒钟
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return port;
	}
}
