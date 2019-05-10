package com.yc.practice.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        MethodDao methodDao = new MethodImpl();
        InvocationHandler handler = new DynamicProxy(methodDao);
        //第一个参数是类加载器，与handler相同;
        //第二个是参数对象实现的接口,如果没有的话需要使用cdlib
        //第三个参数是InvocationHandler.
        //该类返回的其实是MethodImpl类
        methodDao = (MethodDao) Proxy.newProxyInstance(handler.getClass().getClassLoader(), methodDao.getClass().getInterfaces(), handler);
        methodDao.sayHello("test");



    }
}
