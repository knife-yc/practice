package com.yc.practice.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataManager {

    private Lock lock = new ReentrantLock();

    public void testMethod(){
        try{
            lock.lock();
            System.out.println("get lock.......");
            Thread.sleep(10000);
            System.out.println("after sleep........");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

}
