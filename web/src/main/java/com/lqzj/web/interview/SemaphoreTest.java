package com.lqzj.web.interview;

import java.util.concurrent.Semaphore;

/**
 * @author liuqian1
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 8; i++) {
            new WorkerTest(i, semaphore).start();
        }
    }
}

class WorkerTest extends Thread {
    private int num;
    private Semaphore semaphore;

    public WorkerTest(int num, Semaphore semaphore) {
        this.num = num;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("员工" + num + "正在操作这个机器。。。。。。。。");
            Thread.sleep(3000L);
            System.out.println("员工" + num + "操作完成");
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
