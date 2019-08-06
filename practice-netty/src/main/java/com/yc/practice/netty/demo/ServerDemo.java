package com.yc.practice.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerDemo {

    public void bind(int port) throws Exception {
        //服务端NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap sbs = new ServerBootstrap();
            //ublic ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup):
            //parentGroup作为网络连接处理器的线程组（Reactor线程组）
            //childGroup作为网络读写的线程组
            sbs.group(bossGroup, workerGroup);
            //设置创建的channel为NioServerSocketChannel，对应ServerSocketChannel
            sbs.channel(NioServerSocketChannel.class);
            //设置服务端最大连接数为1024，基于tcp三次握手，
            // 客户端发起请求且服务端发送确认之后服务端将此连接放入syncQueue，
            // 当收到客户端的确认之后放入acceptQueue，
            // 两个queue的大小之和就是backlog设置的值
            sbs.option(ChannelOption.SO_BACKLOG, 1024);

            sbs.childHandler(new ServerChildChannelHandler());
            //绑定端口，同步等待成功
            ChannelFuture channelFuture = sbs.bind(port).sync();
            //等待服务端监听端口关闭，使用同步的方式阻塞，等待服务端链路关闭后推出main方法
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new ServerDemo().bind(port);
    }
}
