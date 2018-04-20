package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class Test2 {

    static class DeepClass {
        static int i = 0;
        static {
            if (true) {
                while (i < 10) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    System.out.println(Thread.currentThread() + " init");

                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeepClass test2 = new DeepClass();
                System.out.println(Thread.currentThread() + " end");
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
