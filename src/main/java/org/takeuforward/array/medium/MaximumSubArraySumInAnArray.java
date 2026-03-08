package org.takeuforward.array.medium;

import java.util.Arrays;

public class MaximumSubArraySumInAnArray {
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 4, -1, 2, -2, 3, 4, -1, 2};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("MaxSum : " + maxSum(arr));
        System.out.println("MaxSum : " + maxSum1(arr));
        System.out.println("MaxSum : " + maxSum2(arr));
        System.out.println("MaxSum : " + maxSum3(arr));
        System.out.println("MaxSum : " + maxSum4(arr));
    }

    /**
     * Time complexity : O(n^3)
     * Space complexity : O(1)
     */
    private static int maxSum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    private static int maxSum1(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    private static int maxSum2(int[] arr) {
        int maxEnding = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEnding = Math.max(maxEnding + arr[i], arr[i]);
            maxSum = Math.max(maxEnding, maxSum);
        }
        return maxSum;
    }

    /**
     * Kadane's Algorithm
     * Time complexity : O(n)
     * Space complexity : O(1)
     * If the sum of current sub array is negative, start a new sub array
     */
    private static int maxSum3(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i : arr) {
            currSum += i;
            if (currSum > maxSum) {
                maxSum = currSum;
            }
            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * Kadane's Algorithm
     * Time complexity : O(n)
     * Space complexity : O(1)
     * If the sum of current sub array is negative, start a new sub array
     */
    private static int maxSum4(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        int start = 0;
        int ansStart = 0, ansEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (currSum == 0) start = i;
            currSum += arr[i];
            if (currSum > maxSum) {
                maxSum = currSum;
                ansStart = start;
                ansEnd = i;
            }
            if (currSum < 0) {
                currSum = 0;
            }
        }
        System.out.println("Max sub-array : ");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
        return maxSum;
    }

}
