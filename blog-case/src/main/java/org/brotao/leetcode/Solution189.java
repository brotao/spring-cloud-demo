package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author luotao
 * @className Solution189
 * @description
 * @date 2022-02-14 16:14
 */

@Slf4j
public class Solution189 {
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,4,5,6,7};
		int k = 1;
		rotate(nums, k);
		log.info(Arrays.toString(nums));
	}
	public static void rotate(int[] nums, int k) {
		int len = nums.length-1;
		int k2 = nums.length - k;
		int next = len;
		int tmp = nums[next];

		do {
			int  a = (next + k2 )%nums.length;
			if (a < 0) a = a + nums.length;
			nums[next] = nums[a];
			next = a;
		} while (next != (len-1));

		nums[next] = tmp;
	}
}
