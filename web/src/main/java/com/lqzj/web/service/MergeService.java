package com.lqzj.web.service;

import com.google.common.collect.Maps;
import com.lqzj.common.thread.ThreadPool;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class MergeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MergeService.class);

    // merge超时时间为30秒
    private static final int MERGE_TIME_OUT_IN_SECONDS = 30;

    // 存放merge结果
    private final Map<Integer, String> mergeResults = Maps.newHashMap();

    // 处理merge时用的锁
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Autowired
    private ThreadPool threadPool;

    @Value("${merge.salt}")
    private String mergeSalt;

    public String mergeNum(Integer num) {
        return mergeNum(num, MERGE_TIME_OUT_IN_SECONDS);
    }

    // 处理merge
    public String mergeNum(Integer num, long timeout) {
        // 先从缓存中获取merge结果
        try {
            readLock.lock();

            String result = mergeResults.get(num);

            if (!StringUtils.isEmpty(result)) {
                return result;
            }
        } finally {
            readLock.unlock();
        }

        String mergeResult = "";
        Future<String> future = null;

        try {
            future = threadPool.submit(() -> merge(num));
            mergeResult = future.get(MERGE_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
        } catch (TimeoutException exception) {
            LOGGER.error("merge time out : ", exception);
        } catch (Exception exception) {
            LOGGER.error("merge failed: ", exception);
        } finally {
            // 结束线程的运行
            if (future != null && !future.isDone()) {
                future.cancel(true);
            }
        }

        if (!StringUtils.isEmpty(mergeResult)) {
            // 缓存merge结果
            try {
                writeLock.lock();

                mergeResults.put(num, mergeResult);
            } finally {
                writeLock.unlock();
            }
        }

        return mergeResult;
    }

    private String merge(Integer num) {
        try {
            // 模拟处理耗时
            Thread.sleep(10 * 1000L);
            return randomSalt() + " : " + num;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String randomSalt() {
        List<String> salts = Arrays.asList(mergeSalt.split(","));

        return salts.get(RandomUtils.nextInt(salts.size()));
    }

}
