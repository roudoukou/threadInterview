package com.atguigu.jvm;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    /**
     * 内存够用的时候就保留, 不够用就回收
     */
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);                     // java.lang.Object@1540e19d
        System.out.println(softReference.get());    // java.lang.Object@1540e19d

        o1 = null;
        System.gc();

        System.out.println(o1);                     // null
        System.out.println(softReference.get());    // java.lang.Object@1540e19d
    }

    /**
     * JVM 配置, 故意产生大对象并配置小的内存, 让它内存不够用了导致OOM, 看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);                     // java.lang.Object@1540e19d
        System.out.println(softReference.get());    // java.lang.Object@1540e19d

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            throw new RuntimeException(e);
        } finally {
            System.out.println(o1);                     // null
            System.out.println(softReference.get());    // null
        }
    }

    public static void main(String[] args) {
        // softRef_Memory_Enough();

        softRef_Memory_NotEnough();
    }
}
