package com.yc.practice.nio.event;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public abstract class Event {

    private SocketChannel socketChannel;
    private Selector selector;

    public Event(Selector selector, SocketChannel socketChannel) {
        this.selector = selector;
        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public Selector getSelector() {
        return selector;
    }
}
