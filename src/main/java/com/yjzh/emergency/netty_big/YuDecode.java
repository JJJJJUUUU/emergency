package com.yjzh.emergency.netty_big;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class YuDecode extends ByteToMessageDecoder {
    private final int LENGTH = 4 + 4 ;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //head 4字节
        //length 4字节
        if(in.readableBytes() >= LENGTH){
            //符合协议
            if(in.readableBytes() > 10000000){
                System.out.println("超过协议定义大小" + in.readableBytes());
                //限制数据大小
                in.skipBytes(in.readableBytes());
            }
            int begin;
            while(true){
                //开始的位置
                begin = in.readerIndex();
                //标记
                in.markReaderIndex();
                if(in.readInt() == 824){
                    //每次读4个字节 读到了包头 符合要求
                    System.out.println("读到了包头");
                    break;
                }
                //没读到包头重置读取位置
                in.resetReaderIndex();
                //略过一个字节
                in.readByte();
                if(in.readableBytes() < LENGTH){
                    System.out.println("没有读到包头");
                    return;
                }
            }
            //消息长度
            int contentLength = in.readInt();
            if(in.readableBytes() < contentLength){
                System.out.println("消息的内容长度没有达到预期设定的长度，还原指针重新读");
                //消息的内容长度没有达到预期设定的长度，还原指针重新读
                in.readerIndex(begin);
                return;
            }
            byte[] content = new byte[contentLength];
            in.readBytes(content);
            YuSocket protocol = new YuSocket();
            protocol.setHead(824);
            protocol.setContent(content);
            protocol.setContentLength(contentLength);
            System.out.println(contentLength);
            out.add(protocol);
        }
        System.out.println("不符合协议内容");
    }
}