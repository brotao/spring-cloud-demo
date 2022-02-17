package org.brotao.leetcode;

import jdk.nashorn.internal.ir.WhileNode;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luotao
 * @className Solution567
 * @description 字符串的排列
 * @date 2022-02-16 10:17
 */
@Slf4j
public class Solution567 {
	public static void main(String[] args) {
		String s1 = "abb";
		String s2 = "ababab";
//		boolean b = checkInclusionI(s1, s2);
		boolean b = checkInclusion(s1, s2);
		log.info(String.valueOf(b));
	}

	public static boolean checkInclusion(String s1, String s2) {
		int len1 = s1.length(), len2 = s2.length();
		if (len1 > len2) {
			return false;
		}

		int[] cnt = new int[26];
		for (int i = 0; i < len1; i++) {
			++cnt[s1.charAt(i)-'a'];
		}

		int left = 0;
		int i = 0;
		for (i = 0; i < len2; i++) {
			char c = s2.charAt(i);
			cnt[c-'a']--;
			while (cnt[c-'a'] < 0) {
				cnt[s2.charAt(left)-'a']++;
				left ++;
			}
			if ((i - left + 1) == len1 ) {
				return true;
			}
		}
		return false;

	}

	public static boolean checkInclusionI(String s1, String s2) {
		int n = s1.length(), m = s2.length();
		if (n > m) {
			return false;
		}
		int[] cnt = new int[26];
		for (int i = 0; i < n; ++i) {
			--cnt[s1.charAt(i) - 'a'];
		}
		int left = 0;
		for (int right = 0; right < m; ++right) {
			int x = s2.charAt(right) - 'a';
			++cnt[x];
			while (cnt[x] > 0) {
				--cnt[s2.charAt(left) - 'a'];
				++left;
			}
			if (right - left + 1 == n) {
				return true;
			}
		}
		return false;
	}
}
