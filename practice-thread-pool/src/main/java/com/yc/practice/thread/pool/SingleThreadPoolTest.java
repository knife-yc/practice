package com.yc.practice.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SingleThreadPoolTest {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int size = 10000;
        for(int i = 0;i < size;i++){
            TestThread testThread = new TestThread(i);
            executorService.execute(testThread);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
