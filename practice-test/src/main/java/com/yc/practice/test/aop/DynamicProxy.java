package com.yc.practice.test.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private Object object;//被代理类

    public DynamicProxy(Object object) {
        this.object = object;
    }

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     *实现invoke方法,在方法执行之后添加操作
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=====方法执行前=======");
        method.invoke(object, args);
        System.out.println("=====方法执行后=======");
        return null;
    }
}
