package org.brotao.structural;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author luotao
 * @className CglibDynamicProxy
 * @description
 * @date 2021-12-20 16:05
 */


class LeaderMethodInterceptor implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		if ("meeting".equals(method.getName())) {
			System.out.println("代理先准备会议材料...");
			return methodProxy.invokeSuper(o, objects);
		} else if ("evaluate".equals(method.getName())) {
			if(objects[0] instanceof String) {
				if ("James".equals(objects[0])) {
					System.out.println("James 犯过错误，所以考评分数较低...");
					return 70;
				}
			}
			return methodProxy.invokeSuper(o, objects);
		}
		return null;
	}
}

public class CglibDynamicProxy {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Boss.class);
		enhancer.setCallback(new LeaderMethodInterceptor());
		Object o = enhancer.create();
		Leader o1 = (Leader) o;
		o1.meeting();
		o1.evaluate("Joy");
		o1.evaluate("James");
	}
}
