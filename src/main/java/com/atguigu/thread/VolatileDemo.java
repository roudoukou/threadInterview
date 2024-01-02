package com.atguigu.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    /**
     * 请注意, 此时number前面是加了volatile关键字修饰的, volatile不保证原子性
     */
    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成后, 再用main线程取的最终的结果值看是多少?
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type finally number value: " + myData.atomicInteger);
    }

    /**
     * volatile可以保证可见性, 及时通知其他线程, 朱物理内存的值已经被修改
     */
    private static void seeOKByVolatile() {
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
