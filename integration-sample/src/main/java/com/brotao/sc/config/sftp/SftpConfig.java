package com.brotao.sc.config.sftp;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

/**
 * @author luotao
 * @className SftpConfig
 * @description SftpConfig
 * @date 2021-12-08 15:22
 */

@Configuration
public class SftpConfig {

	@Bean
	public SessionFactory<LsEntry> sftpSessionFactory() {
		DefaultSftpSessionFactory sf = new DefaultSftpSessionFactory(true);
		sf.setHost("10.106.0.221");
		sf.setUser("root");
		sf.setPassword("Hundsun@1");
		sf.setAllowUnknownKeys(true);

		return new CachingSessionFactory<>(sf, 10);
	}

	@Bean("sftpRemoteFileTemplate")
	public RemoteFileTemplate remoteFileTemplate(SessionFactory<LsEntry> sftpSessionFactory) {
		SftpRemoteFileTemplate template = new SftpRemoteFileTemplate(sftpSessionFactory);
		template.setRemoteDirectoryExpression(new LiteralExpression("/"));
		template.setAutoCreateDirectory(true);
		return template;
	}

}
