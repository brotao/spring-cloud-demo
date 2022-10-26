package org.broto.jksj.javaadvanced.nio02;


import lombok.extern.slf4j.Slf4j;
import org.broto.jksj.javaadvanced.nio02.inbound.HttpInboundServer;

@Slf4j
public class NettyServerApplication {

    public static void main(String[] args) {
        try {
            new HttpInboundServer(10000).run();
        } catch (Exception e) {
            log.error("服务启动异常", e);
        }
    }
}
