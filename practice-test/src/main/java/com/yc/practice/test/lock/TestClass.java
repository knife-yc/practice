package com.yc.practice.test.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

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

    @Test
    public void testLockSupport(){
        DataManager dm = new DataManager();
        try {
            int size = 3;
            List<Thread> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Thread t = new TestThreadForLockSupport(dm, i);
                list.add(t);
                t.start();
            }

            Thread.sleep(1000);
            for(int i = 0;i < size;i++){
                Thread t = list.get(i);
                LockSupport.unpark(t);
                Thread.sleep(1000);
            }
//            LockSupport.unpark(t);
//            System.out.println("main running............");

//        TestThread t = new TestThread();
//        t.start();
//        t.interrupt();
//        System.out.println(Thread.interrupted());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
