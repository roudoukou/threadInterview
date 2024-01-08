package com.atguigu.thread;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

enum CountryEnum {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        for (CountryEnum value : CountryEnum.values()) {
            if (value.getRetCode() == index) {
                return value;
            }
        }

        return null;
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国, 被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "秦帝国, 一统华夏");

        // 小复习
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }

    private static void closeDoor() throws InterruptedException {
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
齐国, 被灭
楚国, 被灭
燕国, 被灭
赵国, 被灭
魏国, 被灭
韩国, 被灭
main秦帝国, 一统华夏

ONE
1
齐

 */