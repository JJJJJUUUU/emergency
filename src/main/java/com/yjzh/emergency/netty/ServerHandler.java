package com.yjzh.emergency.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/9 16:49
 **/
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
         //System.err.println(byteBuf.toString(CharsetUtil.UTF_8));
         System.err.println(msg);

        ChannelFuture future = channelHandlerContext.write(Unpooled.copiedBuffer("welcome client!", CharsetUtil.UTF_8));

        //System.err.println(future);
    }


}
