package com.lqzj.web.interview;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author liuqian1
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        System.out.println(100>>>1);
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask sumTask = new SumTask(1000000000);

        ForkJoinTask<Long> joinTask = forkJoinPool.submit(sumTask);

        try {
            Long result = null;
            result = joinTask.get();
            System.out.println("result=" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum =+ i;
        }

        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }

        System.out.println("sum=" + sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;

    private long start;

    private long end;

    public SumTask(long end) {
        this(1, end);
    }

    public SumTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((end - start) <= THRESHOLD) {
            for (long i = start; i < end; i++) {
                sum += i;
            }
        } else {
            long mid = (start + end) >>> 1;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid, end);
            left.fork();
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}
