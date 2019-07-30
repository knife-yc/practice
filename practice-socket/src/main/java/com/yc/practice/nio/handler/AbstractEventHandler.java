package com.yc.practice.nio.handler;

import com.yc.practice.nio.event.Event;

public abstract class AbstractEventHandler<T>{

    Event event;

    public AbstractEventHandler(Event event) {
        this.event = event;
    }


//    @Override
//    public void run() {
//        execute();
//    }

    public abstract T execute() throws Exception;

}
