package com.lqzj.web.interview;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuqian1
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        //CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行

        CountDownLatch countDownLatch = new CountDownLatch(10);

        LatchDemo latchDemo = new LatchDemo(countDownLatch);

        long l = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(latchDemo).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - l) );
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }

        } finally {
            latch.countDown();
        }
    }
}
