package com.brotao.controller;

import com.brotao.service.FtpUploadService;
import com.brotao.service.SftpUploadService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class FileUploadController {

	@Resource
	private FtpUploadService service;

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
		final String path = ".";
		String filename = file[0].getOriginalFilename();
		String sysCharset = System.getProperty("file.encoding");
		log.info(sysCharset);

		filename = new String(filename.getBytes(sysCharset), "UTF-8");

		service.upload(file[0].getInputStream(), host, port, username, password, path,filename);

		return new ResponseEntity<>("upload file", HttpStatus.OK);
	}


}
