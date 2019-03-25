package com.yc.practice.test.thread;

public class TestThread extends Thread {

    private String threadName;

    public TestThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("thread running.......threadName:" + threadName);
//            try {
//                Thread.sleep(990);
//            } catch (InterruptedException e) {
//                System.out.println("run InterruptedException.threadName:"+threadName+","+e);
//                break;
//            }
        }


    }
}
