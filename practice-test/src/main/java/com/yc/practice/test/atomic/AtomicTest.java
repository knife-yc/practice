package com.yc.practice.test.atomic;

import org.junit.Test;

public class AtomicTest {

    private volatile int value;

    @Test
    public void test(){
        AtomicTest b = new AtomicTest();
        b.init(false);
        System.out.println(b.getValue());
    }

    public void init(boolean flag){
        value = flag ? 1 : 0;//等同于下面的代码
//        int v = flag ? 1 : 0;
//        value = v;
    }

    public int getValue(){
        return value;
    }
}
