package com.atguigu.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t sendEmail()");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();
    private void get() {
        // 多把锁没有关系, 上了几把锁, 就需要解锁几把锁
        lock.lock();
        lock.lock();
        lock.lock();
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
            lock.unlock();
            lock.unlock();
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set()");
        } finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println();
            System.out.println();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t3 = new Thread(phone, "t3");
        t3.start();

        Thread t4 = new Thread(phone, "t4");
        t4.start();

    }
}
