package com.brotao.controller;

import com.brotao.service.CommonVFSService;
import com.brotao.service.FtpUploadService;
import com.brotao.service.SftpUploadService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@Slf4j
public class FileUploadController {

	@Resource
	private FtpUploadService service;

    @Resource
    private CommonVFSService vfsService;

	@RequestMapping(value = "/check")
	public ResponseEntity<String> check() {
		return new ResponseEntity<>("I AM OK", HttpStatus.OK);
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile[] file,
										 @RequestParam("protocol") String protocol,
										 @RequestParam Map<String, String> params) throws IOException, JSchException, SftpException {
		log.info("123123");

		final String host = "10.106.0.221";
		final int port = 21;
		final String username = "pureuser";
		final String password = "123456";
		final String path = "./test";
		String filename = file[0].getOriginalFilename();
//		String sysCharset = System.getProperty("file.encoding");
//		log.info(sysCharset);
//
//		filename = new String(filename.getBytes(sysCharset), "UTF-8");

        filename = new String(filename.getBytes(), StandardCharsets.UTF_8);

        service.upload(file[0].getInputStream(), host, port, username, password, path, filename);

		return new ResponseEntity<>("upload file", HttpStatus.OK);
	}

    @GetMapping("/asyncUpload")
    public String asyncUpload() throws FileSystemException {
        final String srcPath = "file:///D:/luota/Downloads/qwertyui.epub";
        final String descPath = "sftp://luotao:111111@192.168.2.177/.";

        ListenableFuture<String> res = vfsService.asyncUploadFileToSftpServer(srcPath, descPath);
        res.addCallback((result -> log.info("异步成功返回值： {}", result)), (aa -> log.info("异步失败返回值： {}", aa)));

//            String s = res.get();
//            log.info("异步返回值： {}", s);

        
        return "程序执行完成";
    }


}
