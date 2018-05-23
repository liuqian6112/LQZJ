package com.lqzj.web.interview;

import java.util.concurrent.CyclicBarrier;

/**
 * @author liuqian1
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程" + Thread.currentThread().getName());
            }
        });
        System.out.println(cyclicBarrier.getNumberWaiting());
        for (int i = 0; i < 5; i++) {
            new WriterTest(cyclicBarrier).start();
        }
    }
}

class WriterTest extends Thread {
    private CyclicBarrier cyclicBarrier;

    public WriterTest(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据");
        try {
            Thread.sleep(3000L);
            System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程");
            cyclicBarrier.await();
            System.out.println("所有线程处理完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
