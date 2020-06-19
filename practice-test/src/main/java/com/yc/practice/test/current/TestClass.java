package com.yc.practice.test.current;

import org.junit.Test;

public class TestClass {

    @Test
    public void test(){

        int size = 10000000;
        TestQueue testQueue = new TestQueue(size);
        long startTime = System.currentTimeMillis();
        for(int i = 0;i < size;i++){
            testQueue.put(i+"");
        }
        long endTime = System.currentTimeMillis();

        System.out.println("usingTimeMS:"+(endTime - startTime));
    }
}
