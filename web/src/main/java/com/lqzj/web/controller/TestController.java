package com.lqzj.web.controller;

import com.lqzj.database.config.redis.DistributedLock;
import com.lqzj.web.interview.ProxyTestService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * @author liuqian1
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProxyTestService proxyTestService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/set")
    public void set() throws InterruptedException {
        // 获取锁
        String s = UUID.randomUUID().toString();
        String key = "key1";
        boolean flag;
        // 也可调用DistributedLock.lock，类似atomicLong的cas，不过没有随机休眠
        // 在高并发下，多个线程竞争同一个资源造成大量线程占用cpu进行充实操作
        do {
            flag = DistributedLock.tryLock(jedisPool, key, s, 10000);
            // 随机休眠
            long nextLong = RandomUtils.nextLong(1, 50);
            Thread.sleep(nextLong);
        } while (!flag);

        // 执行逻辑
        proxyTestService.set();
        Thread.sleep(10000L);
        // 释放锁
        DistributedLock.unLock(jedisPool, key, s);
    }

}
