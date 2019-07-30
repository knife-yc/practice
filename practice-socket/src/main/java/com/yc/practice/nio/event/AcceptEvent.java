package com.yc.practice.nio.event;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class AcceptEvent extends Event {
    private SelectionKey selectionKey;

    public AcceptEvent(Selector selector, SocketChannel socketChannel, SelectionKey selectionKey) {
        super(selector, socketChannel);
        this.selectionKey = selectionKey;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }
}
