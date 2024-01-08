package com.atguigu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });
        for(int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println("收集到第:" + Thread.currentThread().getName() + "\t龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}

/*
运行结果:
收集到第:4	龙珠
收集到第:7	龙珠
收集到第:1	龙珠
收集到第:3	龙珠
收集到第:5	龙珠
收集到第:2	龙珠
收集到第:6	龙珠
召唤神龙
 */