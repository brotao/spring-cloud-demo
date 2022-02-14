package com.brotao.sc.config.ftp;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;

/**
 * @author luotao
 * @className FtpConfigTest
 * @description FtpConfigTest
 * @date 2021-12-08 10:51
 */
/*

@SpringBootTest
class FtpConfigTest {

	@Autowired
	MyGateway gateway;

	@Autowired
	@Qualifier("remoteFileTemplate") RemoteFileTemplate template;

	@Autowired
	@Qualifier("sftpRemoteFileTemplate") RemoteFileTemplate sftpTemplate;

	@Test
	public void test() {
		gateway.sendToFtp(new File("C:\\Users\\luotao\\Desktop\\FXRATE_20210002.xlsx"));
	}

	@Test
	public void test2() {
		Message<File> message = MessageBuilder.withPayload(new File("C:\\Users\\luotao\\Desktop\\FXRATE_20210002.xlsx")).build();
		template.send(message, "qwer", null);
	}

	@Test
	public void test3() {
		Message<File> message = MessageBuilder.withPayload(new File("C:\\Users\\luotao\\Desktop\\FXRATE_20210002.xlsx")).build();
		sftpTemplate.send(message, "", null);
	}

}
*/
