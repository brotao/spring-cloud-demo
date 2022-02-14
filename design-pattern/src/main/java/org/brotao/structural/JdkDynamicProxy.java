package org.brotao.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @author luotao
 * @className JdkDynamicProxy
 * @description jdk
 * @date 2021-12-20 15:48
 */


interface Leader {
	void meeting();

	int evaluate(String name);
}

class Boss implements Leader {
	@Override
	public void meeting() {
		System.out.println("领导早上开会");
	}

	@Override
	public int evaluate(String name) {
		int score = new Random(System.currentTimeMillis()).nextInt(20) + 80;
		System.out.println(String.format("领导给%s的考评为%s分", name, score));
		return score;
	}
}

class WorkInvocationHandler implements InvocationHandler {

	private Object object;

//	private Leader object;

	public WorkInvocationHandler(Leader object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("object: " + object.getClass().getSimpleName());
		System.out.println("proxy: " + proxy.getClass().getSimpleName());

		if ("meeting".equals(method.getName())) {
			System.out.println("代理先准备会议材料...");
			return method.invoke(object, args);
		} else if ("evaluate".equals(method.getName())) {
			if (args[0] instanceof String) {
				if ("James".equals(args[0])) {
					System.out.println("James 犯过错误，所以考评分数较低...");
					return 70;
				}
			}
			return method.invoke(object, args);
		}
		return null;
	}
}

public class JdkDynamicProxy {
	public static void main(String[] args) {
		Leader leader  = new Boss();
		Object o = Proxy.newProxyInstance(JdkDynamicProxy.class.getClassLoader(), new Class[]{Leader.class}, new WorkInvocationHandler(leader));
		Leader o1 = (Leader) o;
		o1.meeting();
		o1.evaluate("Joy");
		o1.evaluate("James");

	}
}
