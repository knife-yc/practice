package com.yc.practice.test.lock;

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

}
