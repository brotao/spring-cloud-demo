package com.brotao.sc.config.ftp;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.outbound.FtpMessageHandler;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.io.File;

/**
 * @author luotao
 * @className FtpConfig
 * @description FtpConfig
 * @date 2021-12-08 10:24
 */
/*
@Configuration
public class FtpConfig {

	@Bean
	@ConfigurationProperties(prefix = "ftpconfig")
	public DefaultFtpSessionFactory ftpSessionFactory() {
		return new DefaultFtpSessionFactory();
	}

	@Bean
	public SessionFactory<FTPFile> cachingSessionFactory(DefaultFtpSessionFactory ftpSessionFactory) {
		return new CachingSessionFactory<FTPFile>(ftpSessionFactory);
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
*/
