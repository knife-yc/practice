package com.yc.practice.netty.http.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ChildInitializer extends ChannelInitializer<SocketChannel> {

    private String url;

    public ChildInitializer(String url) {
        this.url = url;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("http-decoder", new HttpRequestDecoder())
                //将多个消息转换成但以的FullHttpRequest或者FullHttpResponse,因为Http解码器在每个HTTP消息中会生成多个消息对象，
                // 1、HttpRequest/HttpResponse，2、HttpContent，3、LastHttpContent
                .addLast("http-aggregator", new HttpObjectAggregator(65536))
                .addLast("http-encoder", new HttpResponseEncoder())
                //支持异步发送大码流同时不占过多的内存，防止发生内存溢出错误
                .addLast("http-chunked", new ChunkedWriteHandler())
                .addLast("fileServerHandler", new HttpFileServerHandler(url));
    }
}
