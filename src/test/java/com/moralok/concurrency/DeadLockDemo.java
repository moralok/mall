package com.moralok.concurrency;

/**
 * 演示死锁
 *
 * Thread Dump
 *
 * "Thread-0@653" prio=5 tid=0xd nid=NA waiting for monitor entry
 *   java.lang.Thread.State: BLOCKED
 * 	 blocks Thread-1@654
 * 	 waiting for Thread-1@654 to release lock on <0x293> (a java.lang.String)
 * 	  at com.moralok.concurrency.DeadLockDemo.lambda$deadLock$0(DeadLockDemo.java:26)
 * 	  - locked <0x294> (a java.lang.String)
 * 	  at com.moralok.concurrency.DeadLockDemo$$Lambda$1.1887813102.run(Unknown Source:-1)
 * 	  at java.lang.Thread.run(Thread.java:748)
 *
 * @author moralok
 * @since 2020/9/10 2:40 下午
 */
public class DeadLockDemo {

    private static final String A = "A";

    private static final String B = "B";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println(1);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println(2);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
