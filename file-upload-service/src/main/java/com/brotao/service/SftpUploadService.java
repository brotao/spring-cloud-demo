package com.brotao.service;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class SftpUploadService {

	public void upload(InputStream in, String host, int port,
					   String username, String password, String path, String filename) throws JSchException, SftpException {
		Session session = null;
		ChannelSftp channelSftp = null;

		log.info("=====>preparing the host information for sftp.");

		JSch jSch = new JSch();
		session = jSch.getSession(username, host, port);
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		channelSftp = (ChannelSftp)session.openChannel("sftp");

		channelSftp.connect();
//		channelSftp.setFilenameEncoding("GBK");

		log.info("sftp channel connected");
		channelSftp.cd(path);
		channelSftp.put(in, filename);

		log.info("File transfered successfully to host.");

		channelSftp.exit();
		session.disconnect();

	}
}
