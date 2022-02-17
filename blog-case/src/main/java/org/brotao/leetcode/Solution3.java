package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author luotao
 * @className Solution3
 * @description 无重复字符的最长子串
 * @date 2022-02-16 9:21
 */
@Slf4j
public class Solution3 {

	public static void main(String[] args) {
		String s = "abba";
		int res = lengthOfLongestSubstring(s);
		log.info(String.valueOf(res));
	}

	public static int lengthOfLongestSubstring(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int max = 0, start = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				start = Math.max(map.get(c)+1, start);
			}
			max = Math.max(max, i-start +1);
			map.put(c, i);
		}

		return max;
	}
}
