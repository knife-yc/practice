package com.yc.practice.test.lock;

import java.util.concurrent.locks.LockSupport;
//测试多个线程使用同一个对象进行park
public class TestThreadForLockSupport extends Thread{
    private DataManager dm;
    private int index;
    public TestThreadForLockSupport(DataManager dm,int index){
        this.dm = dm;
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("before  park ...............index:"+index);
        LockSupport.park(dm);
        System.out.println("after  park ...............index:"+index);
    }
}
