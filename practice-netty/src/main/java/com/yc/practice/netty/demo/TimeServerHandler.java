package com.yc.practice.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] request = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(request);
        String requestBody = new String(request, "UTF-8");
        System.out.println("the time server receive order:" + requestBody);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(requestBody) ?
                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
