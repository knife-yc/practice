package com.yc.practice.nio.event;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadEvent extends Event {

    private SelectionKey selectionKey;

    public ReadEvent(Selector selector, SocketChannel socketChannel, SelectionKey selectionKey) {
        super(selector, socketChannel);
        this.selectionKey = selectionKey;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }
}
