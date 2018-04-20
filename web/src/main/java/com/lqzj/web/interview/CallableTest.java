package com.lqzj.web.interview;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liuqian1
 */
public class CallableTest {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();

        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);

        new Thread(futureTask).start();

        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class CallableDemo implements Callable {
    @Override
    public Integer call() throws Exception {
        // 计算 0~100 的和
        int sum = 0;

        for(int i=0; i<=100; i++){
            sum += i;
        }

        return sum;
    }
}
