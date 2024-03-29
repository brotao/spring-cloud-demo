package org.broto.jksj.javaadvanced.nettystudy.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author luotao
 * @Date 7/10/2022
 * @Description
 */

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf time = (ByteBuf) msg;
        try {
            long l = (time.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(l));
            ctx.close();
        } finally {
            time.release();    
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
