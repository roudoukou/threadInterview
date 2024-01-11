package com.atguigu.jvm;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println();
        myWeakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);    // {1=HashMap}

        key = null;
        System.out.println(map);    // {1=HashMap}

        System.gc();
        System.out.println(map);    // {1=HashMap}
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key, value);
        System.out.println(map);    // {2=WeakHashMap}

        key = null; // key这个键引用不再使用了, 并且之后垃圾回收了, 所以被自动移除了
        System.out.println(map);    // {2=WeakHashMap}

        System.gc();
        System.out.println(map);    // {}

    }
}
