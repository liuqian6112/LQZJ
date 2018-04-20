package com.lqzj.web.interview;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author liuqian1
 */
public class CompareAndSwapTest {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int oldValue = cas.get();
                    int nextInt = RandomUtils.nextInt(0, 10);
                    boolean b = cas.compareAndSet(oldValue, nextInt);
                    System.out.println("newInt:" + nextInt + " === " + b);
                }
            }).start();

        }

        while (true) {
            if (Thread.activeCount() <= 1) {
                System.out.println(cas.get());
                break;
            }
        }
    }

}

class CompareAndSwap {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;

        if (oldValue == expectedValue) {
            value = newValue;
        }

        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {

        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
