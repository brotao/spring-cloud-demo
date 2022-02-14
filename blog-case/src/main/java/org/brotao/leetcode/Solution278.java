package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luotao
 * @className Solution278
 * @description
 * @date 2022-02-14 13:54
 */
@Slf4j
public class Solution278 {

	private static int badVersion = 0;

	public static void main(String[] args) {
		int n = 5, bad = 4;
		badVersion = bad;
		int res = firstBadVersion(n);
		log.info(String.valueOf(res));

	}

	public static int firstBadVersion(int n) {
		int left = 1, right = n;
		while (left < right) { // 循环直至区间左右端点相同
			int mid = left + (right - left) / 2; // 防止计算时溢出
			if (isBadVersion(mid)) {
				right = mid; // 答案在区间 [left, mid] 中
			} else {
				left = mid + 1; // 答案在区间 [mid+1, right] 中
			}
		}
		// 此时有 left == right，区间缩为一个点，即为答案
		return left;
	}

	public static boolean isBadVersion(int v) {
		return v >= badVersion;
	}
}
