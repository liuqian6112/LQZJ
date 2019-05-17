package com.lqzj.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * @author qianliu86
 */
public class ZookeeperExample {
    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 50000, watchedEvent -> {
        });

//        zk.create("/root", "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        byte[] data = zk.getData("/root", true, null);
        System.out.println(new String(data));

//        zk.create("/root/user", "userdata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zk.create("/root/info", "infodata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zk.create("/root/zoo", "zoodata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

//        zk.setData("/root/info", "infodatabak".getBytes(), -1);

        List<String> children = zk.getChildren("/root", true);
        children.forEach(s -> {
            byte[] data1 = new byte[0];
            try {
                data1 = zk.getData("/root/" + s, true, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(new String(data1));
        });

//        zk.delete("/root/zoo", -1);
//        zk.delete("/root/user", -1);
//        zk.delete("/root/info", -1);
//        zk.delete("/root", -1);
    }
}
