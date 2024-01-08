package com.atguigu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目: synchronized 和 Lock有什么区别? 用新的 lock 有什么好处? 你举例说说
 * 1 原石构成
 * synchronized是关键字属于JVM层面
 *     monitorenter (底层是通过monitor对象来完成, 其实wait/notify等方法也依赖于monitor对象只有在同步块或方法中才能调wait/notify等方法)
 *     monitorexit
 * Lock是具体类(java.util.concurrent.locks.Lock)是api层面的锁
 *
 * 2 使用方法
 *     synchronized 不需要用户去手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 *     ReentrantLock则需要用户去手动释放锁若没有主动释放锁，就有可能导致出现死锁现象。
 *     需要lock()和unlock()方法虎合try/finally 语何块来完成。
 *
 * 3 等待是否可中断
 * synchronized不可中断，除非抛出导常或青正常运行完成
 * ReentrantLock 可中断，
 *     1.设置超时方法 tryLock(lona timeout， TimeUnit unit)
 *     2.LockInterruptibly()放代码块中，调用interrupt() 方法可中断
 *
 * 4 加锁是否公平
 * synchronized非公平锁
 * ReentrantLock两青都可以，默认非公平锁，构造方法可以传入boolean，true 为公平锁，faLse 为非公平锁
 *
 * 5 锁绑定多个条件Condition
 * synchronized没有
 * ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 *
 *
 *
 *
 * 题目: 多线程之间按顺序调用, 实现 A -> B -> C 三个线程启动, 要求如下
 * AA 打印 5 次, BB 打印 10次 , CC 打印 15 次
 * 紧接着
 * AA 打印 5 次, BB 打印 10次 , CC 打印 15 次
 * ...
 * 来 10 轮
 */

class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}
