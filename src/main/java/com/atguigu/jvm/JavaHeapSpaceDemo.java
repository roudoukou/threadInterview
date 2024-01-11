package com.atguigu.jvm;

import java.util.Random;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        /*String str = "atguigu";

        while (true) {
            str += str + new Random(111111) + new Random(222222);
            str.intern();
        }*/

        // java.lang.OutOfMemoryError: Java heap space

        // -Xmx10m -Xms10m
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
