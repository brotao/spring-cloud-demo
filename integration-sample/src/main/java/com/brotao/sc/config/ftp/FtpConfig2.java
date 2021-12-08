package com.brotao.sc.config.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.outbound.FtpMessageHandler;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

/**
 * @author luotao
 * @className FtpConfig
 * @description FtpConfig
 * @date 2021-12-08 10:24
 */
@Configuration
public class FtpConfig2 {

	@Bean
	public SessionFactory<FTPFile> ftpSessionFactory() {
		DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
		sf.setHost("10.106.0.221");
		sf.setPort(21);
		sf.setUsername("luotao");
		sf.setPassword("123456");
		sf.setControlEncoding("UTF-8");
		sf.setFileType(FTP.BINARY_FILE_TYPE);
		sf.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
//		sf.setTestSession(true);
		return new CachingSessionFactory<FTPFile>(sf, 10);
	}

	@Bean
	public RemoteFileTemplate remoteFileTemplate(SessionFactory<FTPFile> ftpSessionFactory) {
		FtpRemoteFileTemplate template = new FtpRemoteFileTemplate(ftpSessionFactory);
		template.setRemoteDirectoryExpression(new LiteralExpression(""));
		template.setAutoCreateDirectory(true);
		return template;
	}

	@Bean
	@ServiceActivator(inputChannel = "ftpChannel")
	public MessageHandler handler(SessionFactory<FTPFile> cachingSessionFactory) {
		FtpMessageHandler handler = new FtpMessageHandler(cachingSessionFactory);
		handler.setRemoteDirectoryExpressionString("headers['remote-target-dir']"
		);
		handler.setFileNameGenerator(new FileNameGenerator() {
			@Override
			public String generateFileName(Message<?> message) {
				return "handlerContent.test";
			}
		});
		return handler;
	}
}
