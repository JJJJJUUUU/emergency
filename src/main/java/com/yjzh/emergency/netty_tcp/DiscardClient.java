package com.yjzh.emergency.netty_tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/10 13:52
 **/
public class DiscardClient {
    /* Server Ip */
    public static String HOST = "127.0.0.1";
    /* Server Port */
    public static int PORT = 8080;

    public static Bootstrap bootstrap = getBootstrap();
    public static Channel channel = getChannel(HOST, PORT);

    // 初始化 `Bootstrap`
    public static final Bootstrap getBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {

                // 每个 `Channel` 都关联一个 `ChannelPipeline`
                /* 发送和接收的 `object`通过`ObjectDecoder` `ObjectEncoder`进行加解密
                 * 注：对应`object`类,必须实现`Serializable`接口
                 *
                 * `netty`框架本身自带了很多`Encode`和`DeCode`
                 *  例如：字符串的 `StringDecoder` `StringEncoder`
                 */

                ChannelPipeline pipeline = ch.pipeline();
                //pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                //pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                //pipeline.addLast(new ObjectEncoder());
                //pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                pipeline.addLast("handler", new DiscardClientHandler());
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    // 建立连接
    public static final Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            System.out.println("连接Server(IP{},PORT{})失败");
            return null;
        }
        return channel;
    }

    // 向服务器发送消息
    public static void sendMsg(Object msg) throws Exception {
        if (channel != null) {
            channel.writeAndFlush(msg).sync();
        } else {
            System.out.println("消息发送失败,连接尚未建立!");
        }
    }

    public static void main(String[] args) throws Exception {
        sendMsg("我爱你");
    }


}
