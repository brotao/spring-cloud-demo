package com.brotao.test;

import com.brotao.enums.DefaultOptionsEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.junit.jupiter.api.Test;

import java.io.File;

@Slf4j
public class uploadFileToServerTest {
    @Test
    // 上传文件至 ftp服务器
    public void test1() throws Exception {
        String srcPath = "C:\\Users\\luota\\Desktop\\并发.zip";
        String destPath = "ftp://test1:test1@127.0.0.1:21/ftpUpload";

        File srcFile = new File(srcPath);

        FileSystemManager manager = VFS.getManager();

        FileObject srcFileObject = manager.resolveFile(srcPath);
        FileObject destDirObject = manager.resolveFile(destPath, DefaultOptionsEnum.FTP.getOptions());
        FileObject destFileObject = manager.resolveFile(destDirObject, srcFile.getName());

        destFileObject.copyFrom(srcFileObject, Selectors.SELECT_SELF);

        log.info("上传成功");
    }

    @Test
    // 上传文件至 sftp服务器
    public void test2() throws Exception {
        String srcPath = "C:\\Users\\luotao\\Desktop\\并发2.zip";
        String destPath = "sftp://test1:test1@127.0.0.1:22/sftpUpload";

        File srcFile = new File(srcPath);

        FileSystemManager manager = VFS.getManager();

        FileObject srcFileObject = manager.resolveFile(srcPath);
        FileObject destDirObject = manager.resolveFile(destPath, DefaultOptionsEnum.SFTP.getOptions());
        FileObject destFileObject = manager.resolveFile(destDirObject, srcFile.getName());

        destFileObject.copyFrom(srcFileObject, Selectors.SELECT_SELF);

        log.info("上传成功");
    }

    @Test
    // 上传文件至 ftps服务器
    public void test3() throws Exception {
        String srcPath = "C:\\Users\\luotao\\Desktop\\并发3.zip";
        String destPath = "ftps://test1:test1@127.0.0.1:21/ftpsUpload";

        File srcFile = new File(srcPath);

        FileSystemManager manager = VFS.getManager();

        FileObject srcFileObject = manager.resolveFile(srcPath);
        FileObject destDirObject = manager.resolveFile(destPath, DefaultOptionsEnum.FTPS.getOptions());
        FileObject destFileObject = manager.resolveFile(destDirObject, srcFile.getName());

        destFileObject.copyFrom(srcFileObject, Selectors.SELECT_SELF);

        log.info("上传成功");
    }
}
