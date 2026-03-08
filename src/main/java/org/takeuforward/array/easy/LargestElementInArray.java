package org.takeuforward.array.easy;

import java.util.Arrays;

public class LargestElementInArray {

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Largest element in array : " + findLargestElementInArray(arr));
    }

    /**
     * Brute force
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static int findLargestElementInArray(int[] arr) {
        if (arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

}
