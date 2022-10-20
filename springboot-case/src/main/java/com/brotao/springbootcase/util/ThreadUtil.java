package com.brotao.springbootcase.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author luotao
 * @Date 2022-06-28 10:38
 * @Description 线程工具类
 */

@Slf4j
public class ThreadUtil {
    // CPU数量
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // 空闲存活时间，单位秒
    public static final int KEEP_ALIVE_SECONDS = 30;
    // 有界队列大小
    public static final int QUEUE_SIZE = 1000;
    // IO密集型线程池核心线程大小
    public static final int IO_MAX = Math.max(2, CPU_COUNT * 2);


    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return ThreadPoolExecutorLazyHolder.EXECUTOR;
    }

    public static class CustomThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String threadTag;

        public CustomThreadFactory(String tag) {
            SecurityManager sm = System.getSecurityManager();
            group = (sm != null) ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            threadTag = "customPool-" + poolNumber.getAndIncrement() + "-" + tag + "-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, threadTag + threadNumber.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

    private static class ThreadPoolExecutorLazyHolder {
        // IO密集型
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
                IO_MAX,
                IO_MAX,
                KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_SIZE),
                new CustomThreadFactory("io")
        );

        static {
            // JVM关闭时停止线程池的钩子
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    log.info("准备销毁executorService");
                    EXECUTOR.shutdown();
                    if (!EXECUTOR.awaitTermination(300, TimeUnit.SECONDS)) {
                        EXECUTOR.shutdownNow();
                    }
                } catch (Exception e) {
                    log.error("关闭线程池异常", e);
                }
            }, "Shutdown-ThreadPoolExecutor"));
        }
    }

}
