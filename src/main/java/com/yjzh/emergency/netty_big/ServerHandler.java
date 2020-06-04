package com.yjzh.emergency.netty_big;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<YuSocket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, YuSocket msg) throws Exception {
        System.err.println(new String(msg.getContent(),"utf-8"));
        System.err.println(new String(msg.getContent(),"utf-8").length());
        System.err.println(new String(msg.getContent(),"utf-8").getBytes().length);

    }
}