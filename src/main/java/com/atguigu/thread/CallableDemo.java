package com.atguigu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 两个线程 , 一个 main 线程, 一个是 AA futureTask 线程
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());

        Thread thread1 = new Thread(futureTask1, "AA");
        Thread thread2 = new Thread(futureTask2, "BB"); // 需要执行几次Callable, 那就创建几个futureTask
        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName());
        int result01 = 100;

        while (!futureTask1.isDone()) {
        }

        // 要求获得 Callable 线程的计算结果, 如果没有计算完成就要去强求, 会导致堵塞, 值得计算完成
        int result02 = futureTask1.get();

        System.out.println(result01 + result02);
    }
}
