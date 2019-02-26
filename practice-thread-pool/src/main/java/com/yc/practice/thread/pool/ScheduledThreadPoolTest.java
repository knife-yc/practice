package com.yc.practice.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args){
        Executors.newFixedThreadPool()
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        int size = 1;
        for(int i = 0;i < size;i++){
            TestThread testThread = new TestThread(i);
            executorService.scheduleAtFixedRate(testThread,1000,10000, TimeUnit.MILLISECONDS);
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
