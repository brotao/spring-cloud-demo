package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Solution733 {
    public static void main(String[] args) {
        int[][] image ={{1,1,1},{1,1,0},{1,0,1}};
        int sr = 1, sc = 1;
        int newColor= 2;

        image = floodFill(image, sr, sc, newColor);
        for (int i = 0; i < image.length; i++) {
            log.info(Arrays.toString(image[i]));
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        floodFill(image, sr, sc, newColor, image[sr][sc]);

        return image;
    }

    public static void floodFill(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image.length) {
            return ;
        }

        if (image[sr][sc] != oldColor) {
            return;
        }

        floodFill(image, sr-1, sc, newColor, image[sr][sc]);
        floodFill(image, sr, sc-1, newColor, image[sr][sc]);
        floodFill(image, sr+1, sc, newColor, image[sr][sc]);
        floodFill(image, sr, sc+1, newColor, image[sr][sc]);
        if (image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
        }

    }
}
