package com.lqzj.common.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ThreadPool {
    // 核心线程数，会一直存活，即使没有任务处理
    private static final int CORE_POOL_SIZE = 10;

    // 最大线程数，当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，
    // 直到线程数量达到maxPoolSize。如果线程数已等于maxPoolSize，且任务队列已满，
    // 则已超出线程池的处理能力，线程池会拒绝处理任务而抛出异常
    private static final int MAXIMUM_POOL_SIZE = 200;

    // 当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize
    private static final int KEEP_ALIVE_TIME = 1;// 1分钟

    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadPool() {
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public <T> Future<T> submit(Callable<T> task) {
        return threadPoolExecutor.submit(task);
    }
}
