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
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread thread = new Thread(futureTask, "AA");
        thread.start();

        System.out.println(Thread.currentThread().getName());
        int result01 = 100;

        while (!futureTask.isDone()) {
        }

        // 要求获得 Callable 线程的计算结果, 如果没有计算完成就要去强求, 会导致堵塞, 值得计算完成
        int result02 = futureTask.get();

        System.out.println(result01 + result02);
    }
}
