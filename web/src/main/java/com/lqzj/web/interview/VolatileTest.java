package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class VolatileTest {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            synchronized(threadDemo) {
                if (threadDemo.isFlag()) {
                    System.out.println("########");
                    break;
                }
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private boolean flag = false;

//    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println(" flag = " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
