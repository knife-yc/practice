package com.yc.practice.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CachedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int size = 10000;
        try {
            for (int i = 0; i < size; i++) {
                TestThread testThread = new TestThread(i);
//                Thread.sleep(1000);
                executorService.execute(testThread);
            }

            Thread.sleep(10000);
            int activeCount = ((ThreadPoolExecutor) executorService).getLargestPoolSize();
            System.out.println(activeCount);
            Thread.sleep(40000);
            activeCount = ((ThreadPoolExecutor) executorService).getActiveCount();
            System.out.println(activeCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
