package org.takeuforward.stackAndQueue.easy;

import java.util.*;

public class NextGreaterElement {

    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        int[] result = nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(result));

        System.out.println("----------");

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        result = nextGreaterElement1(nums1, nums2);
        System.out.println(Arrays.toString(result));

    }

    /**
     * Time complexity : O(n * m) -> n:nums1.length, m:nums2.length
     * Space complexity : O(n)
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int val = nums1[i];

            // identify index of val in nums2
            int valIndex = 0;
            while (val != nums2[valIndex]) {
                valIndex++;
            }
            // fetch next greater
            int nextGreater = -1;
            for (int j = valIndex; j < nums2.length; j++) {
                if (val < nums2[j]) {
                    nextGreater = nums2[j];
                    break;
                }
            }
            results[i] = nextGreater;
        }

        return results;
    }

    /**
     * Time complexity : O(n + m) here, n:nums1.length, m:nums2.length
     * Space complexity : O(n + m + m) -> O(n + m)
     */
    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length];

        Map<Integer,Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            int val = nums2[i];

            while (!stack.isEmpty() && val >= stack.peek()) {
                stack.pop();
            }

            int nextGreaterElement = stack.isEmpty() ? -1 : stack.peek();
            map.put(val, nextGreaterElement);

            stack.push(val);
        }

        // build results
        for (int i = 0; i < nums1.length; i++) {
            int val = nums1[i];
            results[i] = map.get(val);
        }
        return results;
    }
}
