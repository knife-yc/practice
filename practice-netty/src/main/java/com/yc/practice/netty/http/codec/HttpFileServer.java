package com.yc.practice.netty.http.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HttpFileServer {
    public final static String DEFAULT_URL = "/practice-netty/src/main/java";

    public void run(final int port, final String url) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChildInitializer(url));
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        System.out.println("文件服务器启动，网址为：http://192.168.13.103:" + port + url);
        channelFuture.channel().closeFuture().sync();
    }

    public static void main(String[] args){
        try {
            new HttpFileServer().run(8080,DEFAULT_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
