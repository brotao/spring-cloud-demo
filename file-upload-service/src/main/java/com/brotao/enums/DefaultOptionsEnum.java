package com.brotao.enums;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.KeyManagerUtils;
import org.apache.commons.net.util.TrustManagerUtils;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystem;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftp.FtpFileType;
import org.apache.commons.vfs2.provider.ftps.FtpsDataChannelProtectionLevel;
import org.apache.commons.vfs2.provider.ftps.FtpsFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftps.FtpsMode;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 保存各种传输协议的配置
 */
public enum DefaultOptionsEnum {
	FTP(new FileSystemOptions(), FtpFileSystemConfigBuilder.getInstance())
	,FTPS(new FileSystemOptions(), FtpsFileSystemConfigBuilder.getInstance())
	,SFTP(new FileSystemOptions(), SftpFileSystemConfigBuilder.getInstance())
	;

	private FileSystemOptions opts;

	private final Logger log = LoggerFactory.getLogger(DefaultOptionsEnum.class);

	private DefaultOptionsEnum(FileSystemOptions opts, FtpFileSystemConfigBuilder builder) {
		builder.setControlEncoding(opts, "UTF-8");
		builder.setConnectTimeout(opts, 5000);
		builder.setUserDirIsRoot(opts, true);


		this.opts = opts;
	}

	private DefaultOptionsEnum(FileSystemOptions opts, FtpsFileSystemConfigBuilder builder) {
		builder.setControlEncoding(opts, "UTF-8");
		builder.setUserDirIsRoot(opts, true);
//		builder.setConnectTimeout(opts, 5000);
		builder.setPassiveMode(opts, true);
		builder.setFileType(opts, FtpFileType.BINARY);
		builder.setAutodetectUtf8(opts, true);
		builder.setFtpsMode(opts, FtpsMode.EXPLICIT);
		builder.setDataChannelProtectionLevel(opts, FtpsDataChannelProtectionLevel.P);
//		builder.setTrustManager(opts, TrustManagerUtils.getValidateServerCertificateTrustManager());
//		builder.setKeyManager(opts, KeyManagerUtils.);

		this.opts = opts;
	}

	private DefaultOptionsEnum(FileSystemOptions opts, SftpFileSystemConfigBuilder builder) {
		try {
			builder.setStrictHostKeyChecking(opts, "no");
		} catch (FileSystemException e) {
			log.error("初始化sftp默认配置错误", e);
		}
		builder.setUserDirIsRoot(opts, true);
		builder.setConnectTimeoutMillis(opts, 60000);
		builder.setPreferredAuthentications(opts, "password");
		builder.setDisableDetectExecChannel(opts, true);
		this.opts = opts;
	}


	public FileSystemOptions getOptions() {
		return opts;
	}

}
