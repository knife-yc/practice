package com.yc.practice.test.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ClassProxy<T> implements MethodInterceptor {

    private Object proxy;
    private Class<T> consumer;
    private String serviceName;
    private String version;

    public ClassProxy(Class<T> consumer, String serviceName, String version) {
        this.consumer = consumer;
        this.serviceName = serviceName;
        this.version = version;
    }

    public static <T> T getProxyInstance(Class<T> consumer, String serviceName, String version) {
        return (T) new ClassProxy(consumer, serviceName, version).createProxy();
    }


    public T createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AbstractClass.class);
        enhancer.setCallback(this);
        enhancer.setClassLoader(Thread.currentThread().getContextClassLoader());
        enhancer.setInterfaces(new Class[]{consumer});
        Class[] types = new Class[]{String.class, String.class};
        Object[] args = new Object[]{serviceName, version};
        proxy = enhancer.create(types, args);
        return (T) proxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("method invoked......method:" + method.getName() + ",threadName:" + Thread.currentThread().getName());
        //我们一般使用methodProxy.invokeSuper(obj,args)方法。这个很好理解，就是执行原始类的方法。
        // 还有一个方法methodProxy.invoke(obj,args)，这是执行生成子类的方法。
        //如果传入的obj就是子类的话，会发生内存溢出，因为子类的方法不停地进入intercept方法，而这个方法又去调用子类的方法，两个方法直接循环调用了。
        //可以在这里实现第三方服务的调用
        methodProxy.invokeSuper(o, args);
        return null;
    }
}
