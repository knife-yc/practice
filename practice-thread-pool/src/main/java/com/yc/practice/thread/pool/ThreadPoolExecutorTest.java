package com.yc.practice.thread.pool;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    @Test
    public void test() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20));

        int threadNum = 30;
        for (int i = 0; i < threadNum; i++) {
            TestThread t = new TestThread(i);
            executor.submit(t);
        }
        int activeCount = executor.getActiveCount();
        int largestPoolSize = executor.getLargestPoolSize();
        System.out.println("activeCount:" + activeCount + ",largestPoolSize:" + largestPoolSize);
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
