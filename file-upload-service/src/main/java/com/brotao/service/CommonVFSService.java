package com.brotao.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;
import java.net.URI;

@Slf4j
@Service
public class CommonVFSService {

    public void uploadFileToSftpServer(String srcPath, String destPath) throws FileSystemException {
        FileSystemManager manager = VFS.getManager();

        FileObject srcFO = manager.resolveFile(srcPath);


        FileObject destFO = manager.resolveFile(destPath + "/" + srcFO.getPath().getFileName().toString(), sftpServerOptions());


        if (!destFO.exists()) {
            destFO.copyFrom(srcFO, new FileDepthSelector());
        }

        log.info("srcFO: {}", srcFO);
        log.info("destFO: {}", destFO);

    }

    @Async
    public ListenableFuture<String> asyncUploadFileToSftpServer(String srcPath, String destPath) throws FileSystemException {

        try (
                FileSystemManager manager = VFS.getManager();
                FileObject srcFO = manager.resolveFile(srcPath);
                FileObject destFO = manager.resolveFile(destPath + "/" + srcFO.getPath().getFileName().toString(), sftpServerOptions())
        ) {
            log.info("destPath: {}", destPath + "/" + srcFO.getPath().getFileName().toString());

            if (!destFO.exists()) {
                destFO.copyFrom(srcFO, new FileDepthSelector());
            }

            log.info("srcFO: {}", srcFO);
            log.info("destFO: {}", destFO);
        }
//        catch (FileSystemException e) {
//            log.error("异常: ", e);
//        }

        return new AsyncResult<>("success");

    }


    private FileSystemOptions sftpServerOptions() {
        FileSystemOptions opts = new FileSystemOptions();

        SftpFileSystemConfigBuilder instance = SftpFileSystemConfigBuilder.getInstance();
        try {
            instance.setStrictHostKeyChecking(opts, "no");
            instance.setUserDirIsRoot(opts, true);
            instance.setConnectTimeoutMillis(opts, 5000);
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
        return opts;
    }
}
