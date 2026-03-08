package org.takeuforward.array.medium;

import java.util.Arrays;

public class StockBuyAndSell {
    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Max profit : " + maxProfit(arr));
        System.out.println("Max profit : " + maxProfit1(arr));

    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    private static int maxProfit(int[] arr) {
        int max = 0;
        for (int start = 0; start < arr.length - 1; start++) {
            for (int end = start + 1; end < arr.length; end++) {
                int profit = arr[end] - arr[start];
                max = Math.max(max, profit);
            }
        }
        return max;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Note : Keep track of min value found so far.
     */
    private static int maxProfit1(int[] arr) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int j : arr) {
            if (min > j) {
                min = j;
            }
            int profit = j - min;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }


}
