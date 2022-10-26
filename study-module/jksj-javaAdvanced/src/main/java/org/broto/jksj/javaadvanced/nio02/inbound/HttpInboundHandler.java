package org.broto.jksj.javaadvanced.nio02.inbound;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.broto.jksj.javaadvanced.nio02.outbound.HttpOutboundHandler;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private HttpOutboundHandler outboundHandler;

    public HttpInboundHandler() {
        outboundHandler = new HttpOutboundHandler();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;

            // todo 出站 filter
            outboundHandler.handler(fullHttpRequest, ctx);

        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


}
