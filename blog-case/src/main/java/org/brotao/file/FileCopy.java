package org.brotao.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author luotao
 * @className FileCopy
 * @description TODO
 * @date 2021-12-13 17:00
 */
public class FileCopy {

	public static void main(String[] args) throws IOException {
		File srcFile = new File("C:\\Users\\luotao\\Desktop\\FP_BasicInfo_ZL_20201117.txt");
		File destFile = new File("E:\\data\\FP_BasicInfo_ZL_20201117.txt");

		FileUtils.copyFile(srcFile, destFile);

		File file = new File("C:\\Users\\luotao\\Desktop\\testfile.dat");
		Path path = file.toPath();
		long size = Files.size(path);

		long length = file.length();

		System.out.println(size + ";" + length);


	}
}
