package com.atguigu.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完晚自习, 离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "班长最后关门走人");
    }
}

/*
1-CountDownLatch:未使用CountDownLatch
1	上完晚自习, 离开教室
5	上完晚自习, 离开教室
4	上完晚自习, 离开教室
main班长最后关门走人
3	上完晚自习, 离开教室
2	上完晚自习, 离开教室
6	上完晚自习, 离开教室

2-CountDownLatch:使用CountDownLatch
2	上完晚自习, 离开教室
1	上完晚自习, 离开教室
3	上完晚自习, 离开教室
5	上完晚自习, 离开教室
4	上完晚自习, 离开教室
6	上完晚自习, 离开教室
main班长最后关门走人

3-CountDownLatch: 使用CountDownLatch & 枚举类的应用
 */