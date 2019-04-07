package com.yc.practice.test.lock;

public class ConditionAwaitThread extends Thread {
    private DataManager dm;
    private String name;

    public ConditionAwaitThread(DataManager dm,String name){
        this.dm = dm;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("before await............");
        dm.testWait();
        System.out.println("after await............");
    }
}
