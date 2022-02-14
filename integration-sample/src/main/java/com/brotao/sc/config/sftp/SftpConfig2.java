package com.brotao.sc.config.sftp;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luotao
 * @className SftpConfig
 * @description SftpConfig
 * @date 2021-12-08 15:22
 */

@Component
@ConfigurationProperties(prefix = "app.inner-server")
public class SftpConfig2 {

	List<DefaultSftpSessionFactory> sftps;

//	@Bean
	public DefaultSftpSessionFactory defaultSftpSessionFactory() {
		DefaultSftpSessionFactory sf = new DefaultSftpSessionFactory(true);
		sf.setHost("10.106.0.221");
		sf.setUser("root");
		sf.setPassword("Hundsun@1");
		sf.setAllowUnknownKeys(true);


		return sf;
//		return new CachingSessionFactory<>(sf, 10);
	}

//	@Bean("sftpRemoteFileTemplate")
	public RemoteFileTemplate remoteFileTemplate(SessionFactory<LsEntry> sftpSessionFactory) {
		SftpRemoteFileTemplate template = new SftpRemoteFileTemplate(sftpSessionFactory);
		template.setRemoteDirectoryExpression(new LiteralExpression("/"));
		template.setAutoCreateDirectory(true);
		template.setTemporaryFileSuffix("");
		template.setUseTemporaryFileName(false);
		return template;
	}

	@Bean
	public List<SessionFactory<LsEntry>> sftpSessionFactorys() {
		List<SessionFactory<LsEntry>> list = new ArrayList<>();
		for (DefaultSftpSessionFactory sftpFactory : sftps) {
			list.add(new CachingSessionFactory<>(sftpFactory, 5));
		}
		return list;
	}

	@Bean("sftpRemoteFileTemplates")
	public List<RemoteFileTemplate> remoteFileTemplate(List<SessionFactory<LsEntry>> sftpSessionFactorys) {
		Assert.notEmpty(sftpSessionFactorys, "sftpSessionFactorys can not be empty");

		List<RemoteFileTemplate> list = new ArrayList<>();

		for (SessionFactory<LsEntry> sftpSessionFactory : sftpSessionFactorys) {
			SftpRemoteFileTemplate template = new SftpRemoteFileTemplate(sftpSessionFactory);
			template.setRemoteDirectoryExpression(new LiteralExpression("/"));
			template.setAutoCreateDirectory(true);
			list.add(template);
		}

		return list;
	}


	public List<DefaultSftpSessionFactory> getSftps() {
		return sftps;
	}

	public void setSftps(List<DefaultSftpSessionFactory> sftps) {
		this.sftps = sftps;
	}
}
