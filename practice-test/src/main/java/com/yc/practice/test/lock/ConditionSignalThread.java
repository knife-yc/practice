package com.yc.practice.test.lock;

public class ConditionSignalThread extends Thread {

    private DataManager dm;
    private String name;

    public ConditionSignalThread(DataManager dm,String name){
        this.dm = dm;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("before signal............");
        dm.testSignal();
        System.out.println("after signal............");
    }
}
