package org.brotao.schedule;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author luotao
 * @Date 2022-07-26 13:18
 * @Description
 */

@Slf4j
public class TimerTest {

    static Timer timer = new Timer();

    public static void main(String[] args) {
        log.info("程序启动");
        final AtomicInteger i = new AtomicInteger(1);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("i: {}", i.getAndIncrement());
            }
        }, 2000, 10000);
    }
}
