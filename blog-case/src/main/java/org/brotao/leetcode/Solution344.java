package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author luotao
 * @className Solution344
 * @description
 * @date 2022-02-15 10:12
 */
@Slf4j
public class Solution344 {
	public static void main(String[] args) {
		char[] s = new char[]{'h','e','l','l','o'};
		reverseString(s);
		log.info(Arrays.toString(s));
	}

	public static void reverseString(char[] s) {
		int l = 0, r = s.length-1;
		while (l < r) {
			char tmp = s[l];
			s[l++] = s[r];
			s[r--] = tmp;
		}
	}
}
