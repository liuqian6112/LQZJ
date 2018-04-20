package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class Singleton {

    private Singleton() {
    }

    // 1.饿汉模式
//    private static Singleton singleton = new Singleton();
//
//    public static Singleton getSingleton() {
//        return singleton;
//    }

    // 2.饱汉模式(线程不安全)
//    private static Singleton singleton;
//
//    public static Singleton getSingleton() {
//        if (singleton == null) {
//            singleton = new Singleton();
//        }
//        return singleton;
//    }

    // 3.双检锁(存在指令重排序)
//    private static Singleton singleton;
//
//    public static Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//
//        return singleton;
//    }

    // 4.双检锁(线程安全)
//    private static volatile Singleton singleton;
//
//    public static Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//
//        return singleton;
//    }

    //5.饱汉模式（线程安全）
//    private static Singleton singleton;
//
//    public static synchronized Singleton getSingleton() {
//        if (singleton == null) {
//            singleton = new Singleton();
//        }
//        return singleton;
//    }

    //6.静态内部类
//    private static class SingletonHolder {
//        private static final Singleton SINGLETON = new Singleton();
//    }
//
//    public static Singleton getSingleton() {
//        return SingletonHolder.SINGLETON;
//    }

    //7.枚举
    private enum SingletonHolder {
        SINGLETON;

        private Singleton singleton;

        SingletonHolder() {
            singleton = new Singleton();
        }

        public Singleton getSingleton() {
            return singleton;
        }
    }

    public static Singleton getSingleton() {
        return SingletonHolder.SINGLETON.getSingleton();
    }

}
