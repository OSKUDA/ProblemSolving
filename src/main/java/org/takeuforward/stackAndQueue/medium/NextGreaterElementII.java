package org.takeuforward.stackAndQueue.medium;

import java.util.Arrays;

public class NextGreaterElementII {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        System.out.println("Input array : " + Arrays.toString(nums));
        int[] results = nextGreater(nums);
        System.out.println("Output array : " + Arrays.toString(results));
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(n)
     */
    public static int[] nextGreater(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int[] results = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int nextGreater = -1;
            int j = i + 1;
            if (j >= nums.length) j = 0;
            while (i != j) {
                if (nums[j] > value) {
                    nextGreater = nums[j];
                    break;
                }
                j++;
                if (j >= nums.length) j = 0;
            }
            results[i] = nextGreater;
        }
        return results;
    }
}
