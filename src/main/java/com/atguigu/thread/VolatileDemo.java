package com.atguigu.thread;

import java.util.concurrent.TimeUnit;

class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tcome in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (myData.number == 0) {
            // main线程就一直在这里等待循环, 知道number值不再等于零
        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
