package org.brotao.fullgc;

/**
 * @Author luotao
 * @Date 2022-07-02
 * @Description
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import groovy.lang.GroovyClassLoader;
public class Test2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        final String code = readAll("DemoHandlerAImpl.groovy");
        final AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Object object = parseClass(code);
                        System.out.println("COUNT2=" + count.incrementAndGet() + ", " + object.hashCode());
                    }
                }
            });
        }
    }
    static GroovyClassLoader classLoader = new GroovyClassLoader();
    public static Object parseClass(String code){
        classLoader = new GroovyClassLoader();
        return classLoader.parseClass(code);
    }
    public static String readAll(String logFile){
        try {
            InputStream ins = null;
            BufferedReader reader = null;
            try {
                ins = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(logFile).getPath());
                reader = new BufferedReader(new InputStreamReader(ins, "utf-8"));
                if (reader != null) {
                    String content = null;
                    StringBuilder sb = new StringBuilder();
                    while ((content = reader.readLine()) != null) {
                        sb.append(content).append("\n");
                    }
                    return sb.toString();
                }
            } finally {
                if (ins != null) {
                    try {
                        ins.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}