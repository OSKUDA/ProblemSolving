package org.takeuforward.stackAndQueue.medium;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h2>Sum of Subarray Minimums (LeetCode 907)</h2>
 *
 * <p>
 * Given an integer array {@code arr}, return the sum of the minimum value of every subarray.
 * Because the answer can be very large, return it modulo {@code 1_000_000_007}.
 * </p>
 *
 * <p><b>Key Idea (Contribution Technique):</b></p>
 * <ul>
 *   <li>Instead of enumerating all subarrays, count how many subarrays consider {@code arr[i]} as their minimum.</li>
 *   <li>If {@code arr[i]} is the minimum for {@code count} subarrays, its contribution is {@code count * arr[i]}.</li>
 * </ul>
 *
 * <p><b>How to Count Subarrays Where {@code arr[i]} is Minimum:</b></p>
 * <ul>
 *   <li>Find the <b>Previous Smaller (or Equal)</b> element index on the left: {@code PSEE[i]}</li>
 *   <li>Find the <b>Next Strictly Smaller</b> element index on the right: {@code NSE[i]}</li>
 * </ul>
 *
 * <p>
 * Then:
 * </p>
 * <ul>
 *   <li>{@code left = i - PSEE[i]}  (number of choices for left boundary)</li>
 *   <li>{@code right = NSE[i] - i}  (number of choices for right boundary)</li>
 *   <li>{@code count = left * right}</li>
 *   <li>{@code contribution = count * arr[i]}</li>
 * </ul>
 *
 * <p><b>Why do we use different comparisons for left and right?</b></p>
 * <ul>
 *   <li><b>Right side (NSE):</b> use {@code >=} while popping so we stop at a strictly smaller element.</li>
 *   <li><b>Left side (PSEE):</b> use {@code >} while popping so we allow equal values on the left.</li>
 *   <li>This asymmetry prevents <b>double-counting</b> when duplicates exist.</li>
 * </ul>
 *
 * <p><b>ASCII intuition:</b></p>
 * <pre>
 * arr:  [3, 1, 2, 4]
 * idx:   0  1  2  3
 *
 * For i = 2 (value = 2):
 *   PSEE[2] = 1  (value 1)
 *   NSE[2]  = 4  (no smaller on right)
 *
 *   left  = 2 - 1 = 1
 *   right = 4 - 2 = 2
 *   count = 1 * 2 = 2 subarrays where 2 is minimum:
 *     [2], [2,4]
 * </pre>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li><b>Time:</b> O(n) — each index is pushed/popped at most once in each monotonic stack pass.</li>
 *   <li><b>Space:</b> O(n) — arrays for NSE/PSEE + stack.</li>
 * </ul>
 *
 * <p><b>Common Pitfalls:</b></p>
 * <ul>
 *   <li>Wrong boundary defaults:
 *     <ul>
 *       <li>{@code PSEE[i] = -1} when no smaller/equal exists on left</li>
 *       <li>{@code NSE[i] = n} when no smaller exists on right</li>
 *     </ul>
 *   </li>
 *   <li>Wrong tie-handling (duplicates) leading to over/under counting.</li>
 *   <li>Integer overflow: use {@code long} for {@code left * right * arr[i]}.</li>
 * </ul>
 *
 * <p><b>Reference:</b>
 * <a href="https://leetcode.com/problems/sum-of-subarray-minimums/">LeetCode 907 - Sum of Subarray Minimums</a>
 * </p>
 */
public class SumOfSubArrayMinimum {
    public static void main(String[] args) {
        int[] arr = new int[]{3,1,2,4};
        System.out.println("Input array : " + Arrays.toString(arr));
        int result = min(arr);
        System.out.println("Output : " + result);

        arr = new int[]{3,1,2,4};
        System.out.println("Input array : " + Arrays.toString(arr));
        result = min1(arr);
        System.out.println("Output : " + result);

        arr = new int[]{3,1,2,4};
        System.out.println("Input array : " + Arrays.toString(arr));
        result = min2(arr);
        System.out.println("Output : " + result);


    }

    /**
     * Time complexity : O(n^3)
     * Space complexity : O(1)
     */
    public static int min(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        long minTotal = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                // find min in the sub-array
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    if (arr[k] < min) min = arr[k];
                }
                minTotal += min;
            }
        }

        return (int) (minTotal % (Math.pow(10,9) + 7));
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    public static int min1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        long minTotal = 0;
        for (int i = 0; i < arr.length; i++) {
            long minSoFar = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                // find min
                if (arr[j] < minSoFar) {
                    minSoFar = arr[j];
                }
                minTotal += minSoFar;
            }
        }

        return (int) (minTotal % (Math.pow(10,9) + 7));
    }

    public static int min2(int[] arr) {
        int[] nsei = nextSmallestElementIndex(arr);
        int[] pseei = previousSmallestOrEqualElementIndex(arr);
        long total = 0;

        for (int i = 0; i < arr.length; i++) {
            int left = i - pseei[i];
            int right = nsei[i] - i;
            long contributing = (long) left * right * arr[i];
            total = (total + contributing) % 1_000_000_007;
        }
        return (int) total;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static int[] nextSmallestElementIndex(int[] arr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (arr[stack.peek()] >= arr[i])) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = arr.length;
            } else {
                result[i] = stack.peek();
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static int[] previousSmallestOrEqualElementIndex(int[] arr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] results = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && (arr[stack.peek()] > arr[i])) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                results[i] = -1;
            } else {
                results[i] = stack.peek();
            }
            stack.push(i);
        }
        return results;
    }
}
