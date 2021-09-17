package com.brotao.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Slf4j
@SpringBootTest
class CommonVFSServiceTest {

    @Resource
    private CommonVFSService service;

    @Test
    public void uploadFileToSftpServer() {
        final String srcPath = "file:///C:\\Users\\luotao\\Desktop\\qwert.pdf";
        final String descPath = "sftp://test1:test1@127.0.0.1/uploadFiles_sftp";

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

    @Test
    public void test2()  {
        FileSystemManager manager = null;
        try {
            manager = VFS.getManager();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
        FileObject fileObject;
        try {
            fileObject = manager.resolveFile(new File("C:\\Users\\luotao\\Desktop\\国际宏观库表逻辑及数据源解析-LST.pdf"),
                    "");
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
        log.info("123");

    }

    @Test
    public void test3() throws IOException {
        FTPSClient client = new FTPSClient();

        client.setAutodetectUTF8(true);
        client.setControlEncoding("UTF-8");
        client.connect("127.0.0.1", 21);
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.enterLocalPassiveMode();

        client.login("test1", "test1");

        log.info(client.printWorkingDirectory());

    }

    @Test
    public void test4() throws IOException {
        File.createTempFile("qwe.txt", "");
    }


}