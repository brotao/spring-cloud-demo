package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author luotao
 * @className Solution977
 * @description
 * @date 2022-02-14 15:38
 */
@Slf4j
public class Solution977 {
	public static void main(String[] args) {
		int[] nums = new int[] {-5,-3,-2,-1};
		log.info(Arrays.toString(new Solution977().sortedSquares(nums)));
	}

	public int[] sortedSquares(int[] nums) {

		int len = nums.length;
		int bound = len;
		int[] newNums = new int[len];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				bound = i;
				break;
			}
		}
		// 计算平方
		for (int i = 0; i < nums.length; i++) {
			nums[i] = nums[i] * nums[i];
		}

		int i = bound - 1, j = bound, k = 0;
		while (i >= 0 && j < len) {
			if (nums[i] < nums[j]) {
				newNums[k++] = nums[i--];
			} else {
				newNums[k++] = nums[j++];
			}
		}
		while ( i>=0) {
			newNums[k++] = nums[i--];
		}

		while ( j < len) {
			newNums[k++] = nums[j++];
		}

		return newNums;
	}
}
