package com.atguigu.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        // synchronousQueue 生产一个阻塞等待消费一个, 并不会存储第二个, 只有第一个消费掉了, 才会开始生产第二个
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                synchronousQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                synchronousQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t get" + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t get" + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t get" + synchronousQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "BBB").start();
    }
}

/*
运行结果:
AAA	 put 1
BBB	 get1
AAA	 put 2
BBB	 get2
AAA	 put 3
BBB	 get3
 */