package com.yc.practice.netty.codec.linecodec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter = 0;

    byte[] request;

    public TimeClientHandler() {
        String body = "QUERY TIME ORDER" + System.getProperty("line.separator");
        request = body.getBytes();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf bufMessage = null;
        for (int i = 0; i < 100; i++) {
            bufMessage = Unpooled.buffer(request.length);
            bufMessage.writeBytes(request);
            ctx.writeAndFlush(bufMessage);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {

            String responseBody = (String) msg;
            System.out.println("now is:" + responseBody + "   " + ++counter);
            //关闭连接
//        ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Unexcepted exception from downsttream:" + cause.getMessage());
        ctx.close();
    }
}
