package com.yc.practice.test.lambda;

public class RunableDemo {

    public void taskHandler(Runnable handler){
        System.out.println("RunableDemo,taskHandler method executed...");
    }

    public void handle(){
        System.out.println("handle method executed...");
    }

    public static void main(String[] args){
        RunableDemo demo = new RunableDemo();
        demo.taskHandler(() -> demo.handle());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
