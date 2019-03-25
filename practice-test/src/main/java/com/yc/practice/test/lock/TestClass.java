package com.yc.practice.test.lock;

public class TestClass {

    public static void main(String[] args) {
        TestReenTrantLock lock = new TestReenTrantLock();
        int threadNum = 100;
        for (int i = 0; i < threadNum; i++) {
            TestThread thread = new TestThread("test-" + i, lock);
            thread.start();
        }
        try{
            Thread.sleep(10000000);
        }catch(Exception e){

        }

    }
}
