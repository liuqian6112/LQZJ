package com.lqzj.core.helper;

public class ProduceConsume {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack(); //建造一个装馒头的框
        Producer producer = new Producer(ss); //新建一个生产者，使之持有框
        Consumer consumer = new Consumer(ss); //新建一个消费者，使之持有同一个框
        Thread producerThread = new Thread(producer); // 新建一个生产者线程
        Thread consumerThread = new Thread(consumer); // 新建一个消费者线程
        producerThread.start(); //开始生产者线程
        consumerThread.start(); //开始消费者线程
    }
}

// 馒头类
class StreamBread {
    // 编号
    private int id;

    public StreamBread(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "streamBread: " + id;
    }
}

// 装馒头的框，栈结构
class SyncStack {
    private int index = 0;
    StreamBread[] stb = new StreamBread[6];

    // 放入框中，相当于人栈
    public synchronized void push(StreamBread sb) {
        while (index == stb.length) { // 框满了，即栈满
            try {
                this.wait();//让当前线程等待
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程，即生产者线程
        stb[index] = sb;
        this.index++;
    }

    // 从框中拿出，相当于出栈
    public synchronized StreamBread pop() {
        while (index == 0) { // 框空了，即栈空
            try {
                this.wait();//让当前线程等待
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程，即消费者线程
        this.index--;
        return stb[index];
    }
}

//生产者
class Producer implements Runnable {
    private SyncStack ss = null;

    public Producer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        //开始生产馒头
        for (int i = 0; i < 20 ; i++) {
            StreamBread sb = new StreamBread(i);
            ss.push(sb);
            System.out.println("生产了 " + sb);
            try {
                Thread.sleep(10L);  //每生产一个馒头，睡10毫秒
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

//消费者
class Consumer implements Runnable {
    private SyncStack ss = null;

    public Consumer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        //开始消费馒头
        for (int i = 0; i < 20; i++) {
            StreamBread sb = ss.pop();
            System.out.println("消费了 " + sb);
            try {
                Thread.sleep(100L); //每消费一个，睡100毫秒，即消费一个，生产多个
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
