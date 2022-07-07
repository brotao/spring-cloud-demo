package org.brotao.multithread;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author luotao
 * @Date 2022-05-14
 * @Description
 * 
 * 100 10
 * 1 3 5 6 9 2 4 6 0 7
 * 1
 * 
 * 100 10
 * 95 96 97 98 99 101 102 103 104 105
 */


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int n = sc.nextInt();
        int[] array = new int[n+1];

        int i = 0;
        for (; i < n; i++) {
            array[i] = sc.nextInt();
        }
        array[i] = h;
        
        int j = 0;
        for (j=0 ; j < array.length; j++) {
            if (array[j] <= array[i] && j >= i) {
                int tmp = array[i];
                array[j] = array[i];
                array[i] = tmp;
                i = j;
            } else if (array[j] > array[i] && j < i) {
                int tmp = array[i];
                array[j] = array[i];
                array[i] = tmp;
                i = j;
            }
        }
        sort(array, 0, i-1);
        sort(array, i+1, n+1);
        
        int[] res = new int[n];
        int pos = 0;
        
        int x=i-1, y = i+1;
        for (; x >=0 && y < n+1; x--, y++) {
            res[pos++]=array[x];
            res[pos++]=array[y];
        }
        
        while (x >=0 ) {
            res[pos++]=array[x--];
        }

        while (y <n+1 ) {
            res[pos++]=array[y++];
        }

        for (int i1 = 0; i1 < res.length; i1++) {
            if (i1 == 0) {
                System.out.print(res[i1]);
            } else {
                System.out.print(" " + res[i1]);
            }
        }
        System.out.println();
        
    }

    public static void main3(String[] args) {
        int[] arr = new int[] {1,3,6,5,2,7,9};
        sort(arr, 0, 3);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr, int begin, int end) {
        for (int i = 0; i < end-begin; i++) {
            for (int j = begin; j <= end-1; j++) {
                if (arr[j]> arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
    }
}
