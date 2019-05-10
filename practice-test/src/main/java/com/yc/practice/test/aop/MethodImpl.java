package com.yc.practice.test.aop;

public class MethodImpl implements MethodDao {
    public void sayHello(String str) {
        System.out.println("hello world,"+str);
    }
}
