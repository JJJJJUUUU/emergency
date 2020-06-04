package com.yjzh.emergency.netty_big;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author yujian
 */
public class YuEncode extends MessageToByteEncoder<YuSocket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, YuSocket msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getHead());
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
    }
}