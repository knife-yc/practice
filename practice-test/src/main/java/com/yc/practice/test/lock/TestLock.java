package com.yc.practice.test.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await(){
        try{
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void signal(){
        condition.signal();
    }

    @Test
    public void testMoveBit(){
        int c = 10;
        c = c >>> 16;
        System.out.println(c);
    }
}
