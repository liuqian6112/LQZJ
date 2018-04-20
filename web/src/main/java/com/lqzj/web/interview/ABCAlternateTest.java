package com.lqzj.web.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuqian1
 */
public class ABCAlternateTest {

    public static void main(String[] args) {
        // Condition是一个多线程间协调通信的工具类

        ABCAlternateDemo demo = new ABCAlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.loopA(i);
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.loopB(i);
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.loopC(i);
                }
            }
        }, "C").start();
    }
}

class ABCAlternateDemo {
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int totalGroup) {

        lock.lock();
        try {
            if (number != 1) {
                condition1.await();
            }
            System.out.print(Thread.currentThread().getName());

            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int totalGroup) {

        lock.lock();
        try {
            if (number != 2) {
                condition2.await();
            }
            System.out.print(Thread.currentThread().getName());

            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int totalGroup) {

        lock.lock();
        try {
            if (number != 3) {
                condition3.await();
            }
            System.out.print(Thread.currentThread().getName());

            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
