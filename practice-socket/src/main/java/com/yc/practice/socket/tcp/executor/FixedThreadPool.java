package com.yc.practice.socket.tcp.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool {

    private boolean closed;
    private ThreadPoolExecutor pool;
    private FixedThreadPoolConfig fixedThreadPoolConfig;

    public FixedThreadPool(FixedThreadPoolConfig fixedThreadPoolConfig) {
        closed = false;
        this.fixedThreadPoolConfig = fixedThreadPoolConfig;
        int coreSize = fixedThreadPoolConfig.getCoreSize();
        int maxSize = fixedThreadPoolConfig.getMaxSize();
        long keepAliveTime = fixedThreadPoolConfig.getKeepAliveTime();
        String poolName = fixedThreadPoolConfig.getPoolName();
        FixedThreadFactory factory = new FixedThreadFactory(poolName);
        int queueSize = fixedThreadPoolConfig.getQueueSize();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(queueSize);
        pool = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.SECONDS, workQueue, factory);
    }

    //异步线程池，没有返回值
    public void addTask(Runnable thread){
        pool.submit(thread);
    }

    public void close() {
        if (closed == true) {
            return;
        }
        if (pool != null) {
            pool.shutdown();
        }
    }
}
