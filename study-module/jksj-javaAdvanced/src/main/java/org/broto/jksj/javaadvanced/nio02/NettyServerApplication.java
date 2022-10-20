package org.broto.jksj.javaadvanced.nio02;


import lombok.extern.slf4j.Slf4j;
import org.broto.jksj.javaadvanced.nio02.inbound.HttpInboundServer;

@Slf4j
public class NettyServerApplication {

    public static void main(String[] args) {
        new HttpInboundServer(10000).run();
    }
}
