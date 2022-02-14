package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author luotao
 * @className Solution704
 * @description
 * @date 2022-02-14 13:39
 */
@Slf4j
public class Solution704 {
	public static void main(String[] args) {
		int[] nums = new int[] {-1,0,3,5,9,12};
		int target = 9;
		int res = search(nums, target);
		log.info(String.valueOf(res));
	}

	public static int search(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		int mid = 0;
		while (low < high) {
			mid =  (high-low)/2 + low;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				low = mid +1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
