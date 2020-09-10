package com.moralok.concurrency;

/**
 * 多线程一定快吗
 *
 * 循环次数-并发执行耗时-串行执行耗时
 * 1万-60+ms-1ms
 * 10万-60+ms-3ms
 * 1百万-60+ms-6ms
 * 1千万-60+ms-11ms
 * 1亿-100+ms-60+ms
 * 10亿-400+ms-600+ms
 *
 * 因为线程的创建和上下文切换的开销，直到循环次数打到10亿级别，并发执行的速度快于串行执行的速度
 *
 * @author moralok
 * @since 2020/9/10 2:12 下午
 */
public class ConcurrencyTest {

    private static final long count = 1000000000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency : " + time + "ms, b = " + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial : " + time + "ms, b = " + b + ", a = " + a);
    }
}
