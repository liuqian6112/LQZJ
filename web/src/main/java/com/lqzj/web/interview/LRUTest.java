package com.lqzj.web.interview;

/**
 *  @author liuqian1 
 */
public class LRUTest {
    public static void main(String[] args) throws Exception {
        // 1. LRU(least recently used)最近最少使用

        // 2. LFU(least frequently used)根据数据访问频率淘汰数据

        // 3. LRU-K最近使用k次，解决缓存污染，两个队列，维护访问数据历史

        // 4. 2Q(two queues)一个FIFO(first input first output)队列，一个LRU队列

        // 5. MQ(multi queues)维护多个队列，不同队列有不同的访问优先级，核心思想：优先缓存访问次数多的数据

        // 总结：
        //     1.命中率：LRU-2 > MQ > 2Q > LRU
        //     2.复杂率：LRU-2 > MQ > 2Q > LRU
        //     3.代价：LRU-2 > MQ > 2Q > LRU

    }

    public void get() {
        System.out.println("get success");
    }
}
