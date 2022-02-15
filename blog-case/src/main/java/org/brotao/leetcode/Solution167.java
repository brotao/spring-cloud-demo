package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luotao
 * @className Solution167
 * @description
 * @date 2022-02-15 9:26
 */
@Slf4j
public class Solution167 {

	public static void main(String[] args) {
		int[] numbers = new int[]{2,3,4};
		int target = 6;
		log.info(Arrays.toString(twoSum(numbers, target)));

	}

	public static int[] twoSum(int[] numbers, int target) {
		int l = 0, h = numbers.length-1;
		while (l < h) {
			int sum = numbers[l] + numbers[h];
			if (sum == target) {
				return new int[] {l + 1, h + 1};
			} else if (sum < target) {
				l ++;
			} else  {
				h ++;
			}
		}
		return new int[] {-1, -1};
	}

	public static int[] twoSumI(int[] numbers, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < numbers.length; i++) {
			Integer integer = map.get(target-numbers[i]);
			if (integer == null) {
				map.put(numbers[i], i);
			} else {
				return new int[]{integer+1, i+1};
			}
		}

		return new int[0];
	}
}
