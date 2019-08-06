package com.yc.practice.netty.demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new TimeServerHandler());
    }
}
