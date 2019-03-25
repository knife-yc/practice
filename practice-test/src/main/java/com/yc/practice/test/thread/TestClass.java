package com.yc.practice.test.thread;

import org.junit.Test;

import java.util.Map;

public class TestClass {

    @Test
    public void test(){
        TestThread t = new TestThread("test");
        t.start();

        try {
            Thread.sleep(1000);
            Map<Thread, StackTraceElement[]> maps1 = Thread.getAllStackTraces();
            System.out.println(maps1.size());
            t.interrupt();
            System.out.println("thread is set to interrupt");
            Thread.sleep(10000);
            notify();

            Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
            System.out.println(maps.size());
            System.out.println("ended.................");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
