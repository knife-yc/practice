package com.yc.practice.test.lock;

import java.util.concurrent.locks.LockSupport;

public class TestThreadForLockSupport extends Thread{
    private DataManager dm;
    private int index;
    public TestThreadForLockSupport(DataManager dm,int index){
        this.dm = dm;
        this.index = index;
    }

    @Override
    public void run() {
//        dm.testMethod();
        System.out.println("before  park ...............index:"+index);
        LockSupport.park(dm);
        System.out.println("after  park ...............index:"+index);
//        System.out.println("before  park ...............");
//        LockSupport.park();
//        System.out.println(Thread.interrupted());
//        System.out.println("after  park ...............");
//        System.out.println(Thread.interrupted());
    }
}
