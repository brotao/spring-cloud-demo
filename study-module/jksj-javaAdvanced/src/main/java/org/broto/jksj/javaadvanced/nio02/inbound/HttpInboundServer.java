package org.broto.jksj.javaadvanced.nio02.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class HttpInboundServer {

    private int port;

    public HttpInboundServer() {
        this.port = 10000;
    }
    public HttpInboundServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(16);

        ServerBootstrap server = new ServerBootstrap();
        server.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                // TODO
                .childHandler(null)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_RCVBUF, 32*1024)
                .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            Channel channel = server.bind(port).sync().channel();
            log.info("开启netty http 服务器，监听地址和端口为 {}:{}" , "http://localhost", port);
            channel.closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }


    }
}
