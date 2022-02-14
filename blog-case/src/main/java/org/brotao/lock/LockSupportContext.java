package org.brotao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author luotao
 * @className LockSupportContext
 * @description
 * @date 2022-01-12 17:51
 */

public class LockSupportContext {
	private AtomicBoolean lockFlag = new AtomicBoolean(false);
	private Thread lockThread = null;
	public boolean lock() {
		lockThread = Thread.currentThread();
		lockFlag.set(true);
		LockSupport.park(this);
		while (!lockFlag.compareAndSet(false, true)) {
			// 无限加锁（如考虑非可控的中断，可更改为if，只加一次锁）
			LockSupport.park(this);
			if (lockThread.isInterrupted()) {
				// 解锁失败
				return false;
			}
		}
		// 解锁成功
		return true;
	}
	public void unlock() {
		lockFlag.set(false);
		LockSupport.unpark(lockThread);
	}

	public static void main(String[] args) throws Exception {
		LockSupportContext lockSupportContext = new LockSupportContext();
		Thread thread = new Thread(() -> {
			System.out.println("子线程-加锁开始");
			boolean lockFlag = lockSupportContext.lock();
			System.out.println("子线程-加锁结束，解锁结果：" + lockFlag);
		});
		thread.start();
		Thread.sleep(TimeUnit.SECONDS.toMillis(2));

		System.out.println("主线程解锁");
		lockSupportContext.unlock();
	}
}