package com.yc.practice.netty.codec.linecodec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientLineCodecDemo {

    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group);
            bs.option(ChannelOption.TCP_NODELAY, true);
            bs.channel(NioSocketChannel.class);
            bs.handler(new ClientChannelHandler());
            ChannelFuture cf = bs.connect(host, port).sync();
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new ClientLineCodecDemo().connect(port, "127.0.0.1");
    }
}
