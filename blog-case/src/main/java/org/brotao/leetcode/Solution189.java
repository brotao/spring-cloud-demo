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
		int k = 3;
		rotate(nums, k);
		log.info(Arrays.toString(nums));
	}
	public static void rotate(int[] nums, int k) {
		int length = nums.length;
		int k2 = k % length;


//		int idx = 0;
//		int tmp = nums[idx];
//		int next = ((idx - k2) < 0 ?  (idx - k2 + length) : (idx - k2)) % length;
//		nums[idx] = nums[next];
//		idx = next;

		int gcd = gcd(length, k2);
		for (int i = 0; i < gcd; i++) {
			int i2 = i;
			int tmp = nums[i];
			int next = ((i2 - k2) < 0 ?  (i2 - k2 + length) : (i2 - k2)) % length;

			do {
				nums[i2] = nums[next];
				i2 = next;
				next = ((i2 - k2) < 0 ?  (i2 - k2 + length) : (i2 - k2)) % length;
			}while (next != i);
			nums[i2] = tmp;
		}




	}

	private static int gcd(int a, int b) {
		return b > 0 ? gcd(b, a % b) : a;
	}
}
