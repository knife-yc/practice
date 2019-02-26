package com.yc.practice.socket.tcp;

import java.io.IOException;
import java.io.InputStream;

public class FixedClassLoader extends ClassLoader {



    //破坏了双亲委派模式，因为这里可以自定义任何加载方式
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return dealFindClass(name);
    }
    //没有破坏双亲委派模式，因为类加载调用的是loadClass方法，
    // loadClass方法中无法加载类是才调用findClass方法加载类
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return dealFindClass(name);
    }

    private Class<?> dealFindClass(String name) throws ClassNotFoundException{
        String fileName = name.substring(name.lastIndexOf(".") +1)+".class";
        InputStream is = this.getClass().getResourceAsStream(fileName);
        Class<?> clazz = null;
        try {
            if(is == null){//由于所有的类都是继承自java.lang.Object类，所有在加载任何一个类是都需要加载Object.class类
                return super.loadClass(name);
            }
            int fileByteLength = is.available();
            byte[] bytes = new byte[fileByteLength];
            is.read(bytes);
            clazz = this.defineClass(name,bytes,0,fileByteLength);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
