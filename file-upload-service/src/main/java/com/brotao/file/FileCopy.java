package com.brotao.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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


	}
}
