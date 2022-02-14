package com.brotao.sc.config.ftp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;
import java.util.List;

/**
 * @author luotao
 * @className FtpConfigTest
 * @description FtpConfigTest
 * @date 2021-12-08 10:51
 */

@SpringBootTest
class FtpConfigTest {


	@Autowired
	@Qualifier("sftpRemoteFileTemplates") List<RemoteFileTemplate> templates;

	@Test
	public void test() {
		File file = new File("C:\\Users\\luotao\\Desktop\\FXRATE_20210002.xlsx");
		Message<File> message = MessageBuilder.withPayload(file).build();
		String res;
		for (RemoteFileTemplate template : templates) {
			template.setFileNameGenerator(msg -> file.getName());
			res = template.send(message, "/root/sftp", null);
			System.out.println("上传文件成功: " + res);
		}
	}


}
