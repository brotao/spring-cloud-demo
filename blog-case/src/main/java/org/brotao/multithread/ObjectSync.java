package org.brotao.multithread;

/**
 * @author luotao
 * @className ObjectSync
 * @description
 * @date 2022-03-02 15:22
 */
public class ObjectSync {
	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj = new Object();

		Thread t1 = new Thread(() -> {
			synchronized (obj1) {
				for (int i = 0; i < 10; i++) {
					System.out.println("t1: " + i);
				}
				try {
					obj1.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 10; i < 20; i++) {
					System.out.println("t1: " + i);
				}

			}
		});

		Thread t2= new Thread(() -> {
			synchronized (obj1) {
				for (int i = 0; i < 10; i++) {
					System.out.println("t2: " + i);
				}
				obj1.notify();
				for (int i = 10; i < 20; i++) {
					System.out.println("t2: " + i);
				}
			}

		});

		t1.start();
		Thread.sleep(1000);
		t2.start();
		t1.join();
		t2.join();

	}
}
