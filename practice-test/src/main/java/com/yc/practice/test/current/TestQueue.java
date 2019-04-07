package com.yc.practice.test.current;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestQueue {

    private Queue<String> queue;

    public TestQueue(int size){
        queue = new ArrayBlockingQueue<String>(size);

//        queue = new LinkedBlockingQueue<String>(size);
    }

    public void put(String str){
        queue.offer(str);
    }
}
