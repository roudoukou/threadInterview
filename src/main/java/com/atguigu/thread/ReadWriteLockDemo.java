package com.atguigu.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成: " + result);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void clear() {
        map.clear();
    }

}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache mycache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int finalInt = i;
            new Thread(() -> {
                mycache.put(finalInt + "", finalInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int finalInt = i;
            new Thread(() -> {
                mycache.get(finalInt + "");
            }, String.valueOf(i)).start();
        }

    }
}

/*
未加锁 运行结果:
很明显, 5在写入过程中被4打断了, 这并不是我们想要的
理想情况 , 应该要5写入完成, 才能给下一个线程写入
2	 正在写入: 2
5	 正在写入: 5
4	 正在写入: 4
1	 正在写入: 1
3	 正在写入: 3
1	 正在读取
2	 正在读取
3	 正在读取
4	 正在读取
5	 正在读取
3	 写入完成
5	 写入完成
1	 读取完成: 1
1	 写入完成
2	 写入完成
5	 读取完成: 5
4	 读取完成: null
2	 读取完成: null
4	 写入完成
3	 读取完成: 3


添加读写锁之后, 线程1写入完成之后, 其他的线程才能够进来接着写
1	 正在写入: 1
1	 写入完成
4	 正在写入: 4
4	 写入完成
2	 正在写入: 2
2	 写入完成
3	 正在写入: 3
3	 写入完成
5	 正在写入: 5
5	 写入完成
1	 正在读取
2	 正在读取
3	 正在读取
4	 正在读取
5	 正在读取
5	 读取完成: 5
4	 读取完成: 4
3	 读取完成: 3
1	 读取完成: 1
2	 读取完成: 2
 */