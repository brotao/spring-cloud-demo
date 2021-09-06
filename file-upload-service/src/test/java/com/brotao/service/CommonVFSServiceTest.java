package com.brotao.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Async
class CommonVFSServiceTest {

    @Resource
    private CommonVFSService service;

    @Test
    void uploadFileToSftpServer() {
        final String srcPath = "file:///D:\\luota\\Downloads\\bbb_sunflower_1080p_30fps_normal.mp4";
        final String descPath = "sftp://luotao:111111@192.168.2.177/.";

        try {
            service.uploadFileToSftpServer(srcPath, descPath);
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
    }

 /*   @Test
    void uploadFileToSftpServer2() throws InterruptedException {
        final String srcPath = "file:///D:\\luota\\Downloads\\P020201015600148925528.doc";
        final String descPath = "sftp://luotao:111111@192.168.2.177/.";

        ListenableFuture<String> res = service.asyncUploadFileToSftpServer(srcPath, descPath);
        res.addCallback((result -> log.info("异步返回值： {}", result)), (aa -> {
        }));

//            String s = res.get();
//            log.info("异步返回值： {}", s);

        log.info("程序执行完成");
        Thread.sleep(50000);

    }*/
}