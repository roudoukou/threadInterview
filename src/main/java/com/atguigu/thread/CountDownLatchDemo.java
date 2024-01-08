package com.atguigu.thread;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完晚自习, 离开教室");
            }, String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "班长最后关门走人");
    }
}

/*
未使用 CountDownLatch
1	上完晚自习, 离开教室
5	上完晚自习, 离开教室
4	上完晚自习, 离开教室
main班长最后关门走人
3	上完晚自习, 离开教室
2	上完晚自习, 离开教室
6	上完晚自习, 离开教室
 */