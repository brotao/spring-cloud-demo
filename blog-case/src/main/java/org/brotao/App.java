package org.brotao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();

		Thread thread2 = new Thread(() -> {
			System.out.println("==================Thread 2:");
//			LockSupport.unpark(mainThread);
			mainThread.interrupt();
		});

		thread2.start();

		System.out.println("============= mainThread: ");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		LockSupport.park();

		System.out.println((byte)2000L);

	}
}
