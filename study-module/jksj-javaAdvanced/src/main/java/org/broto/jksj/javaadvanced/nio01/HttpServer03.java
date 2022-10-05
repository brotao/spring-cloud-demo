
package org.broto.jksj.javaadvanced.nio01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author luotao
 * @Date 5/10/2022
 * @Description
 */

public class HttpServer03 {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10000);
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            Socket accept = serverSocket.accept();
            EXECUTOR_SERVICE.execute(() -> {
                service(accept);
            });
        }
    }

    private static void service(Socket accept) {
        
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);
            printWriter.println("Http/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello, nio");
            printWriter.close();
            accept.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
