package com.yc.practice.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;


    public TimeClientHandler() {
        byte[] request = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(request.length);
        firstMessage.writeBytes(request);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] response = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(response);
        String responseBody = new String(response, "UTF-8");
        System.out.println("now is:" + responseBody);
        //关闭连接
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Unexcepted exception from downsttream:" + cause.getMessage());
        ctx.close();
    }
}
