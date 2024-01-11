package com.atguigu.jvm;

public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object(); // 这样定义的默认就是强引用
        Object obj2 = obj1; // obj2 引用赋值
        obj1 = null; // 置空
        System.gc();
        System.out.println(obj2); // obj2 强引用, 不会被垃圾回收 java.lang.Object@1540e19d
    }
}
