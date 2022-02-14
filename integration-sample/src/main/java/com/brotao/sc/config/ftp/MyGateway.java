package com.brotao.sc.config.ftp;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author luotao
 * @className MyGateway
 * @description MyGateway
 * @date 2021-12-08 13:25
 */
//@MessagingGateway
//@Component
public interface MyGateway {
	@Gateway(requestChannel = "toFtpChannel")
	void sendToFtp(File file);
}
