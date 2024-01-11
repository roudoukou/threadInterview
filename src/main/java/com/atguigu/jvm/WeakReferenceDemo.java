package com.atguigu.jvm;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);                     // java.lang.Object@1540e19d
        System.out.println(weakReference.get());    // java.lang.Object@1540e19d

        o1 = null;
        System.gc();

        System.out.println(o1);                     // null
        System.out.println(weakReference.get());    // null
    }
}
