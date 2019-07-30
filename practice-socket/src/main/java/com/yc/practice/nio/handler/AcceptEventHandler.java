package com.yc.practice.nio.handler;

import com.yc.practice.nio.Buffers;
import com.yc.practice.nio.event.AcceptEvent;
import com.yc.practice.nio.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class AcceptEventHandler extends AbstractEventHandler<Boolean> {

    private final Logger logger = LoggerFactory.getLogger(AcceptEventHandler.class);

    public AcceptEventHandler(Event event) {
        super(event);
    }


    @Override
    public Boolean execute() throws Exception{
        SocketChannel socketChannel = event.getSocketChannel();
        Selector selector = event.getSelector();
        try {
            if(socketChannel == null){
                return false;
            }
            socketChannel.getRemoteAddress();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ, new Buffers(1024 * 1024, 1024 * 1024));
            logger.info("client connect success,remote address:" + socketChannel.getRemoteAddress().toString());
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
}
