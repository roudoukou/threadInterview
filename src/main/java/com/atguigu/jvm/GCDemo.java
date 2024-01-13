package com.atguigu.jvm;

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {
        String str = "atguigu";

        try {
            while (true) {
                // str += str + new Random(111111) + new Random(222222);
                // str.intern();
                System.out.println(new Random().nextInt(10000));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // java.lang.OutOfMemoryError: Java heap space
    }
}
