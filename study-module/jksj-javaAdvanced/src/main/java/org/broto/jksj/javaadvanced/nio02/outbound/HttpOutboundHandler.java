package org.broto.jksj.javaadvanced.nio02.outbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.ExecutorService;


@Slf4j
public class HttpOutboundHandler {

    private CloseableHttpAsyncClient httpClient;
    private ExecutorService proxyService;

    public HttpOutboundHandler() {

        IOReactorConfig config = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setRcvBufSize(32 * 1024)
                .build();

        httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(config)
                .setKeepAliveStrategy(((response, context) -> 6000))
                .build();
        httpClient.start();
    }

    public void handler(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        final String url = "http://www.baidu.com";
        final HttpGet httpGet = new HttpGet(url);
        // 设置请求头

        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                try {
                    handleResponse(fullHttpRequest, ctx, result);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }

            @Override
            public void failed(Exception ex) {

            }

            @Override
            public void cancelled() {

            }
        });


    }

    private void handleResponse(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpResponse result) throws Exception {
        FullHttpResponse response = null;
        try {

            byte[] body = EntityUtils.toByteArray(result.getEntity());

            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));

            // 设置响应头




        } catch (Exception e) {
            exceptionCaught(ctx, e);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
//            ctx.close();
        }

    }

    private void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        log.error("请求后端服务出现异常", e);
        ctx.close();
    }
}
