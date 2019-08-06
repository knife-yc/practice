package com.yc.practice.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientDemo {

    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            //设置客户端NIO线程组
            bs.group(group);
            //设置创建的channel为NioSocketChannel，对应SocketChannel
            bs.channel(NioSocketChannel.class);
            //设置客户端的处理器，用于处理网络io事件
            bs.handler(new ClientChannelHandler());
            //发起异步连接操作
            ChannelFuture cf = bs.connect(host, port).sync();
            //等待客户端关闭连接
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new ClientDemo().connect(port, "127.0.0.1");
    }
}
