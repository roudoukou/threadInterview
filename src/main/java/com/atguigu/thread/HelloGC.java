package com.atguigu.thread;

public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC");
        // byte[] bytes = new byte[50 * 1024 * 1024];

        Thread.sleep(Integer.MAX_VALUE);
        /*
        long totalMemory = Runtime.getRuntime().totalMemory(); // 返回Java 虚拟机中的内存总量
        long maxMemory = Runtime.getRuntime().maxMemory(); // 返回 Java 虚拟机试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");
        */
    }
}
