package org.brotao.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luotao
 * @className CompletableFutureCase
 * @description CompletableFuture使用
 * @date 2021-12-03 11:04
 */

@Slf4j
public class CompletableFutureCase {

	static ExecutorService pool = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS
			, new LinkedBlockingQueue<Runnable>()
			, (r) -> {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
	);
	static ExecutorService pool2 = Executors.newFixedThreadPool(1);


	public static void main(String[] args) throws Exception {

		List<CompletableFuture<String>> threadList = new ArrayList<>();
		AtomicInteger err = new AtomicInteger();

		CompletableFuture<String> thread1 = CompletableFuture.supplyAsync(() -> {
			log.info("A线程：{}，是否是守护线程: {}", Thread.currentThread().getName(), Thread.currentThread().isDaemon());
			return Thread.currentThread().getName();
		});
		thread1.whenCompleteAsync((s, e) -> {
			if (e != null) err.getAndIncrement();
		});

		CompletableFuture<String> thread2 = CompletableFuture.supplyAsync(() -> {
			for (int i = 0; i < 6; i++) {
				log.info("B线程：{}，是否是守护线程: {}, 计数：{}"
						, Thread.currentThread().getName()
						, Thread.currentThread().isDaemon()
						, i
				);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error("睡眠中断", e);
			}

			return Thread.currentThread().getName();
		}, pool);
		thread2.whenCompleteAsync((s, e) -> {
			if (e != null) err.getAndIncrement();
		});

/*		CompletableFuture<String> thread3 = CompletableFuture.supplyAsync(() -> {

			for (int i = 0; i < 2; i++) {
				log.info("C线程：{}，是否是守护线程: {}, 计数：{}"
						, Thread.currentThread().getName()
						, Thread.currentThread().isDaemon()
						, i
				);
			}
			return Thread.currentThread().getName();
		}, pool);
		thread3.whenCompleteAsync((s, e) -> {
			if (e != null) err.getAndIncrement();
		});*/

		CompletableFuture<String>[] results = new CompletableFuture[]{thread1, thread2};
		CompletableFuture<Void> voidFuture = CompletableFuture.allOf(results);

		voidFuture.get(20, TimeUnit.SECONDS);

//		pool.shutdown();

	}
}

