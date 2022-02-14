package org.brotao.bug;

import org.apache.logging.log4j.core.lookup.JndiLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;

import java.io.IOException;

/**
 * @author luotao
 * @className Log4j2ZeroDay
 * @description CVE-2021-44228
 * @date 2021-12-10 15:56
 */
public class Log4j2ZeroDay {

	public static void main(String[] args) throws IOException, InterruptedException {
		java.lang.Runtime.getRuntime().exec("calc");
	}
}
