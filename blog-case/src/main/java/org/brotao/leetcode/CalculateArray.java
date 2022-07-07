package org.brotao.leetcode;

import java.util.Arrays;


/**
 * calculateArray 根据 a 数组计算 b 数组
 * a -> [1, 2, 3, 4, 5]
 * b -> [120, 60, 40, 30, 24]
 * 1. 不能使用除法
 * 2. 时间 O(n) 空间 O(1)
 * 3. b[i]=(a[0] * a[1] * ... a[n-1]) / a[i]
 * @date 22:13 2022/4/6
 */
public class CalculateArray {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        int[] b = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < a.length; i++) {
            int prevI = i-1 < 0 ? 1 : b[i-1];
            b[i] = a[i] * prevI;
        }

        for (int j = a.length-1; j >=0; j--) {
            int nextJ = j+1 >= a.length ? 1 : a[j+1];
            a[j] = a[j] * nextJ;
        }

        for (int j = a.length-1; j >=0; j--) {
            int next = j+1 >= a.length ? 1 : a[j+1];
            int prev = j -1 <0 ? 1 : b[j-1];
            b[j] = next * prev;
        }

        System.out.println(Arrays.toString(b));
    }
}
