package com.yc.practice.nio.handler;

import com.yc.practice.nio.Buffers;
import com.yc.practice.nio.event.Event;
import com.yc.practice.nio.event.WriteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class WriteEventHandler extends AbstractEventHandler<Boolean> {

    private final Logger logger = LoggerFactory.getLogger(WriteEventHandler.class);

    private long createTimeMs;

    public WriteEventHandler(Event event) {

        super(event);
        this.createTimeMs = System.currentTimeMillis();
    }

    @Override
    public Boolean execute() throws Exception {
        Selector selector = event.getSelector();
        SocketChannel socketChannel = event.getSocketChannel();
        SelectionKey selectionKey = ((WriteEvent) event).getSelectionKey();
        try {

            Buffers buffers = (Buffers) selectionKey.attachment();

            ByteBuffer writeBuffer = buffers.getWriteBuffer();
            writeBuffer.clear();
            String response = "server response,timeMS:" + createTimeMs;
            writeBuffer.put(response.getBytes());
            writeBuffer.flip();

            int len = 0;
            while (writeBuffer.hasRemaining()) {
                len = socketChannel.write(writeBuffer);
                if (len == 0) {
                    break;
                }
            }

            if (!writeBuffer.hasRemaining()) {
                socketChannel.register(selector, SelectionKey.OP_READ, buffers);
            }
            writeBuffer.compact();
        } catch (Exception e) {
            throw e;
        }

        return null;
    }
}
