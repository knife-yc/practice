package com.yc.practice.nio;

import com.yc.practice.nio.event.AcceptEvent;
import com.yc.practice.nio.event.ReadEvent;
import com.yc.practice.nio.event.WriteEvent;
import com.yc.practice.nio.handler.AcceptEventHandler;
import com.yc.practice.nio.handler.ReadEventHandler;
import com.yc.practice.nio.handler.WriteEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private final static Logger logger = LoggerFactory.getLogger(NioServer.class);

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private int port;


    public NioServer(int port) {
        this.port = port;
    }

    public void init() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();

            SocketAddress socketAddress = new InetSocketAddress(port);
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("server started,socketAddress:" + socketAddress);
        } catch (Exception e) {
            logger.error("NioServer,init Exception,port:" + port, e);
        }
    }

    public void run() {
        if (selector == null) {
            logger.error("start error,selector is null,port:" + port);
            return;
        }
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    try {
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            AcceptEvent acceptEvent = new AcceptEvent(selector, socketChannel, selectionKey);
                            AcceptEventHandler acceptEventHandler = new AcceptEventHandler(acceptEvent);
                            acceptEventHandler.execute();
                        }

                        if (selectionKey.isReadable()) {

                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ReadEvent readEvent = new ReadEvent(selector, socketChannel, selectionKey);
                            ReadEventHandler readEventHandler = new ReadEventHandler(readEvent);
                            readEventHandler.execute();
                        }

                        if (selectionKey.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            WriteEvent writeEvent = new WriteEvent(selector, socketChannel, selectionKey);
                            WriteEventHandler writeEventHandler = new WriteEventHandler(writeEvent);
                            writeEventHandler.execute();
                        }
                    } catch (Exception e) {
                        logger.error("NioServer,connect Exception.", e);
                        selectionKey.cancel();
                        selectionKey.channel().close();
                    }
                }
            } catch (Exception e) {
                logger.error("run Exception,port:" + port, e);
            }
        }
    }


    public static void main(String[] args) {
        NioServer nioServer = new NioServer(8080);
        nioServer.init();
        nioServer.run();
    }


}
