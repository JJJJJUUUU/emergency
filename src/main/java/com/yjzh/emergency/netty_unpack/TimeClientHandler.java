package com.yjzh.emergency.netty_unpack;

import com.zaxxer.hikari.util.SuspendResumeLock;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/10 19:50
 **/
@Slf4j
public class TimeClientHandler extends SimpleChannelInboundHandler {
    private int counter;
    private byte[] req;


    public TimeClientHandler() {
        //这里介绍一下System.getProperty("line.separator") // 直线分隔符
        req = ("Query Time Order" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.warn("Unexpected exception from downstream:" + cause.getMessage());
        ctx.close();//释放资源
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        //客户端发送了100次数据,理论上服务器端应该收到100条数据。但实际上服务器只收到2条，很明显发生了粘包。
//        for (int i = 0; i < 100; i++) {
//            message = Unpooled.buffer(req.length);
//
//            message.writeBytes(req);
//
//            ctx.writeAndFlush(message);
//        }

        String s = "aaaaasds";
//        for (int i = 0; i < 100; i++) {
//            s += "张举我稀罕你张举我稀罕你张举我稀罕你张举我稀罕你张举我稀罕你";
//        }
        s += System.getProperty("line.separator");

        System.err.println(s);
        message = Unpooled.buffer(s.getBytes().length);
        message.writeBytes(s.getBytes());
        ctx.writeAndFlush(message);


    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        /**
         *  客户端会记录服务器发过来的消息数量，我们预期应改收到100条数据。
         *  但是实际上客户端只收到1条数据，这很正常，因为我们的服务器端只返回了2条数据，
         *  只所以客户端只收到1条数据，是因为服务器发过来的2条数据被粘包了。
         */

//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
//        System.out.println("Now is:" + body + "; the counter is:" + (++counter));


        String body = (String) msg;
        System.out.println("Now is:" + body + "; the counter is:" + (++counter));
    }

}