package com.atguigu.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // 获取电脑CPU核数
        // System.out.println(Runtime.getRuntime().availableProcessors());

        // ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池5个处理线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); // 一池N个处理线程
        try {
            // 模拟10个用户来办理业务, 每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 10; i++) {
                // submit() execute() 之间区别就是返回值不同, 一个是 Future, 一个是 void
                // threadPool.submit(() -> {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });

/*
                // 假设 newCachedThreadPool 当中的, 每个线程间隔一段时间才开始执行下一个任务
                // newCachedThreadPool 只会有一个线程工作
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
*/

            }
        } finally {
            threadPool.shutdown();
        }
    }
}






















