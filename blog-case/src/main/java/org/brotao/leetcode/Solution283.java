package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Solution283 {
    public static void main(String[] args) {
        int[] nums = new int[] {0,1,0,3,12};
        moveZeros(nums);
        log.info(Arrays.toString(nums));
    }

    public static void moveZeros(int[] nums) {
        for (int i = 0, j = 0; i < nums.length ; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
