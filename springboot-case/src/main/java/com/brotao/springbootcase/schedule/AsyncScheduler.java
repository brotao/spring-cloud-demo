package com.brotao.springbootcase.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author luotao
 * @Date 2022-07-04 13:50
 * @Description 自定义调度 线程池
 */

@Component
@Slf4j
public class AsyncScheduler {

    @Scheduled(cron = "0/5 * * ? * MON-FRI")
    @Async("scheduledExecutor")
    public void test1() throws InterruptedException {
        log.info("AsyncScheduler.test1()");
        Thread.sleep(2000);
    }
    @Scheduled(cron = "0/5 * * ? * MON-FRI")
    @Async("scheduledExecutor")
    public void test2() {
        log.info("AsyncScheduler.test2()");
        LockSupport.parkNanos(10*1000*1000L);
    }

    @Scheduled(cron = "0/5 * * ? * MON-FRI")
    @Async("scheduledExecutor")
    public void test3() {
        log.info("AsyncScheduler.test3()");
    }




}
