package com.yc.practice.thread.pool;

public class TestThread implements Runnable {

    private int i;
    public TestThread(int i){
        this.i = i;
    }
    public void run(){
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName:"+threadName+",index:"+i+",currentTimeMS:"+System.currentTimeMillis());
    }
}
