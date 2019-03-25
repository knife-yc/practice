package com.yc.practice.socket.tcp.executor;

import java.util.concurrent.ThreadFactory;

public class FixedThreadFactory implements ThreadFactory {

    private String factoryName;

    public FixedThreadFactory(String factoryName) {
        this.factoryName = factoryName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, factoryName);
        return thread;
    }
}
