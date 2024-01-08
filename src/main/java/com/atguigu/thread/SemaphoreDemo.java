package com.atguigu.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // 模拟3个停车位

        for(int i = 1; i <= 6; i++) { // 模拟6部汽车
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开车位");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
/*
运行结果:
1	抢到车位
3	抢到车位
2	抢到车位
3	停车3秒后离开车位
2	停车3秒后离开车位
1	停车3秒后离开车位
4	抢到车位
5	抢到车位
6	抢到车位
4	停车3秒后离开车位
6	停车3秒后离开车位
5	停车3秒后离开车位
 */