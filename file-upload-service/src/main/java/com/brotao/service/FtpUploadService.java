package com.brotao.service;

import com.jcraft.jsch.*;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FtpUploadService {

	public void upload(InputStream in, String host, int port,
					   String username, String password, String path, String filename) throws JSchException, SftpException, IOException {

		DefaultFtpSessionFactory ftpFactory = new DefaultFtpSessionFactory();
		ftpFactory.setHost(host);
		ftpFactory.setPort(port);
		ftpFactory.setUsername(username);
		ftpFactory.setPassword(password);
		ftpFactory.setControlEncoding("UTF-8");

		FtpSession session = ftpFactory.getSession();
		session.mkdir(path);
		session.write(in, path + "/" + filename);
	}

}
