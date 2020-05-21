package com.yc.practice.test.cglib;

public class ClassProxyTest {

    public static void main(String[] args) {

        //单步调试时IDEA会调用被代理类的toString()方法，代理ClassA类会代理该类的所有方法（包括toString和hashCode），因此会重复输出
        ClassA a = ClassProxy.getProxyInstance(ClassA.class,"test","1.0.0");
        a.callMethod();
        //接口调用时会通过代理的intercept方法触发
        a.test("test");
    }
}
