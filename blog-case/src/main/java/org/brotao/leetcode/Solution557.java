package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luotao
 * @className Solution557
 * @description
 * @date 2022-02-15 10:25
 */
@Slf4j
public class Solution557 {
	public static void main(String[] args) {
		String s = "Let's take LeetCode contest";
		log.info(reverseWords(s));
	}

	public static String reverseWords(String s) {
		String[] s1 = s.split(" ");
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String s2 : s1) {
			char[] chars = s2.toCharArray();
			reverseCharArray(chars);
			if (!first) {
				sb.append(" ");
			}
			sb.append(chars);
			first = false;
		}
		return sb.toString();
	}

	private static void reverseCharArray(char[] chars) {
		int l = 0;
		int r = chars.length-1;
		while (l < r) {
			char tmp = chars[l];
			chars[l++] = chars[r];
			chars[r--] = tmp;
		}
	}
}
