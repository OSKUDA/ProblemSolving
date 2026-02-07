package org.takeuforward.stackAndQueue.easy;

import java.util.*;

/**
 * LeetCode 496: Next Greater Element I
 * https://leetcode.com/problems/next-greater-element-i/
 *
 * Problem:
 * - Given two arrays nums1 and nums2 where nums1 is a subset of nums2.
 * - For each element in nums1, find the next greater element to its right in nums2.
 * - If no such element exists, return -1.
 *
 * ---------------------------------------------------------
 * Approach 1: Brute Force
 * ---------------------------------------------------------
 * Idea:
 * - For each element in nums1:
 *   1. Find its index in nums2.
 *   2. Scan to the right in nums2 to find the first greater element.
 *
 * Steps:
 * - Outer loop over nums1
 * - Inner scan over nums2
 *
 * Time Complexity:
 * - O(n * m)
 *   where n = nums1.length, m = nums2.length
 *
 * Space Complexity:
 * - O(n) for result array
 *
 * Drawback:
 * - Inefficient due to repeated scans.
 *
 * ---------------------------------------------------------
 * Approach 2: Monotonic Decreasing Stack (Optimal)
 * ---------------------------------------------------------
 * Key Insight:
 * - Precompute the next greater element for every value in nums2.
 * - Use a monotonic decreasing stack to process nums2 in one pass.
 *
 * Monotonic Stack Rule:
 * - Stack stores elements in decreasing order (top is smallest).
 * - While current element >= stack.peek(), pop the stack.
 * - The stack top after popping is the next greater element.
 *
 * Steps:
 * 1. Traverse nums2 from right to left.
 * 2. Use a stack to maintain decreasing order.
 * 3. Store (value -> next greater) mapping in a HashMap.
 * 4. Build result for nums1 using the map.
 *
 * Why it works:
 * - Each element is pushed and popped at most once.
 * - Avoids repeated scanning.
 *
 * Time Complexity:
 * - O(n + m)
 *   - O(m) to process nums2
 *   - O(n) to build result for nums1
 *
 * Space Complexity:
 * - O(n + m)
 *   - HashMap: O(m)
 *   - Stack: O(m)
 *   - Result array: O(n)
 *
 * Interview Notes:
 * - This is a classic monotonic stack problem.
 * - Pattern applies to:
 *   - Next Greater Element
 *   - Daily Temperatures
 *   - Stock Span
 *   - Largest Rectangle in Histogram
 *
 * Pattern Recognition:
 * - "Next / Previous Greater / Smaller" → Monotonic Stack
 */
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
