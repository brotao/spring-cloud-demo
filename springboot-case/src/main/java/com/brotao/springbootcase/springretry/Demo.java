package com.brotao.springbootcase.springretry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author luotao
 * @className Demo
 * @description springRetry 用例
 * @date 2022-03-03 14:36
 */

//@SpringBootApplication
//@EnableRetry
public class Demo {

	public static void main(String[] args) {
		String basePackage = "com.brotao.springbootcase.springretry";
		ApplicationContext context= new AnnotationConfigApplicationContext(basePackage);
		RemoteService remoteService = context.getBean("remoteService", RemoteService.class);
		try {
			remoteService.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//@Service
class RemoteService {
	/**
	 * @Retryable注解
	 * 被注解的方法发生异常时会重试
	 * value:指定发生的异常进行重试
	 * include:和value一样，默认空，当exclude也为空时，所有异常都重试
	 * exclude:指定异常不重试，默认空，当include也为空时，所有异常都重试
	 * maxAttemps:重试次数，默认3
	 * backoff:重试补偿机制，默认没有
	 *
	 * @Backoff注解
	 * delay:指定延迟后重试
	 * multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒
	 * @throws Exception
	 */
	@Retryable(value= {RemoteAccessException.class},maxAttempts = 3,backoff = @Backoff(delay = 5000l,multiplier = 1))
	public void call() throws Exception {
		System.out.println("do something...");
		throw new RemoteAccessException("RPC调用异常");
	}

	/**
	 * @Recover
	 * 当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
	 * @param e
	 */
	@Recover
	public void recover(RemoteAccessException e) {
		System.out.println(e.getMessage());
	}
}