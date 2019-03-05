package com.yc.practice.thread.pool;

public class TestThread implements Runnable {

    private int i;
    public TestThread(int i){
        this.i = i;
    }
    public void run(){
        try{
            Thread.sleep(60000);
            String threadName = Thread.currentThread().getName();
            System.out.println("threadName:"+threadName+",index:"+i+",currentTimeMS:"+System.currentTimeMillis());
        }catch(Exception e){

        }

    }
}
