package org.brotao.multithread;


import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class SynchronizedLock {

    static class Count {
        int num;
        public synchronized void increase() {
            num++;
        };
        public int getNum() {
            return num;
        }
    }

    public static void main(String[] args) throws Exception {
        // 第一步

        Count countTemp = new Count();
        System.out.println("无状态(001):" + ClassLayout.parseInstance(countTemp).toPrintable());

        /*
         * jvm默认延时4s自动开启偏向锁，可通过 -XX:BiasedLockingStartupDelay=0 取消延时
         * 如果不要偏向锁，可通过 -XX：-UseBiasedLocking = false 来设置
         * */
        Thread.sleep(5000);


        // 第二步
        Count count = new Count();
        System.out.println("启用偏向锁(101):" + ClassLayout.parseInstance(count).toPrintable());
        // 第三步
        for (int i = 0; i < 2; i++) {
            synchronized (count) {
                System.out.println("偏向锁(101)(带线程id):" + ClassLayout.parseInstance(count).toPrintable());
            }
            System.out.println("偏向锁释放(101)(带线程id):" + ClassLayout.parseInstance(count).toPrintable());
        }

// 第四步
        new Thread(() -> {
            synchronized (count) {
                System.out.println("轻量级锁(00):" + ClassLayout.parseInstance(count).toPrintable());
                try {
                    System.out.println("睡眠3秒钟");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("轻量(00)->重量(10)" + ClassLayout.parseInstance(count).toPrintable());
            }
        }).start();

        Thread.sleep(1000);
        // 第五步
        new Thread(() -> {
            synchronized (count) {
                System.out.println("重量级锁(10):" + ClassLayout.parseInstance(count).toPrintable());
            }
        }).start();


    }
}
