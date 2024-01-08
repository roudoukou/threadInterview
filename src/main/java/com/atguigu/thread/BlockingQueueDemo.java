package com.atguigu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        // List<String> list = new ArrayList<>();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 抛出异常组 add element remove
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // java.lang.IllegalStateException: Queue full
        // System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // java.util.NoSuchElementException
        // System.out.println(blockingQueue.remove());

    }
}
