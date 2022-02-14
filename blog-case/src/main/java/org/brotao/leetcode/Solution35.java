package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luotao
 * @className Solution35
 * @description
 * @date 2022-02-14 14:13
 */
@Slf4j
public class Solution35 {
	public static void main(String[] args) {
		int[] nums = new int[]{1, 3, 5, 6};
		int target = 0;
		int res = new Solution35().searchInsert(nums, target);
		log.info(String.valueOf(res));
	}

	public int searchInsert(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		boolean shift = true;
		int mid = 0;
		while (l <= r) {
			mid = l + (r - l) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				l = mid + 1;
				shift = true;
			} else {
				r = mid - 1;
				shift = false;
			}

		}
		return l;
		/*if (l >=0 && l <= (nums.length-1)) {
			return shift ? l : l+1;
		}
		if (l==0) {
			return 0;
		} else {
			return l + 1;
		}*/

	}
}
