package com.yc.practice.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReenTrantLock {

    Lock lock = new ReentrantLock();
    private String s = "lock getted...";

    public String get(){
        try{
            lock.lock();
//            Thread.sleep(10000);
            return s;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

}
