package com.atguigu.jvm;

public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
/*
$ jps
# 啥也没有配置
$ jinfo -flag UseSerialGC 1234
-XX:-UseSerialGC
$ jinfo -flag UseParallelGC 1234
-XX:+UseParallelGC

$ jps
# 配置 `-XX:+UseSerialGC` 之后
$ jinfo -flag UseSerialGC 1234
-XX:+UseSerialGC
$ jinfo -flag UseParallelGC 1234
-XX:-UseParallelGC



 */