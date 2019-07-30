package com.yc.practice.nio.handler;

import com.sun.org.apache.xpath.internal.operations.String;
import com.yc.practice.nio.Buffers;
import com.yc.practice.nio.event.Event;
import com.yc.practice.nio.event.ReadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ReadEventHandler extends AbstractEventHandler<Boolean> {

    private final Logger logger = LoggerFactory.getLogger(ReadEventHandler.class);


    public ReadEventHandler(Event event) {
        super(event);
    }

    @Override
    public Boolean execute() throws Exception{
        Selector selector = event.getSelector();
        SocketChannel socketChannel = event.getSocketChannel();
        SelectionKey selectionKey = ((ReadEvent) event).getSelectionKey();
        try {
            Buffers buffers = (Buffers) selectionKey.attachment();

            ByteBuffer readBuffer = buffers.getReadBuffer();
            socketChannel.read(readBuffer);
            readBuffer.flip();
            Charset charset = Charset.forName("utf8");

            CharBuffer charBuffer = charset.decode(readBuffer);
            StringBuilder builder = new StringBuilder();
            builder.append(charBuffer.array());
            logger.info("execute:" + builder.toString());

            readBuffer.clear();
            socketChannel.register(selector, SelectionKey.OP_WRITE, buffers);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
