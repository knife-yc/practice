package com.yc.practice.test.lock;

public class TestThread extends Thread {

    private String threadName;
    private TestReenTrantLock testReenTrantLock;

    public TestThread(String threadName, TestReenTrantLock testReenTrantLock) {
        this.threadName = threadName;
        this.testReenTrantLock = testReenTrantLock;
    }

    @Override
    public void run() {
        String s = testReenTrantLock.get();
        System.out.println("i get the lock,threadName:" + threadName + ",s:" + s);
    }
}
