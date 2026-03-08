package org.takeuforward.array.medium;

import java.util.Arrays;

/**
 * <h2>Find Smallest Balanced Index</h2>
 *
 * <p>
 * Given an integer array <code>nums</code>, find the smallest index <code>i</code>
 * such that:
 * </p>
 * <p>
 * LeetCode reference:
 * <a href="https://leetcode.com/problems/find-the-smallest-balanced-index/" target="_blank">
 * https://leetcode.com/problems/find-the-smallest-balanced-index/
 * </a>
 * </p>
 * <pre>
 * sum(nums[0 .. i-1]) == product(nums[i .. n-1])
 * </pre>
 *
 * <p>
 * Return the smallest such index, or <code>-1</code> if no valid index exists.
 * </p>
 *
 * <hr>
 *
 * <h3>Core Idea</h3>
 *
 * <p>
 * We need to compare:
 * </p>
 *
 * <ul>
 *   <li><b>Left side:</b> prefix sum</li>
 *   <li><b>Right side:</b> suffix product</li>
 * </ul>
 *
 * <p>
 * Instead of recomputing both for every index:
 * </p>
 *
 * <ul>
 *   <li>Compute total sum once.</li>
 *   <li>Traverse from right to left.</li>
 *   <li>Maintain a running suffix product.</li>
 *   <li>Update prefix sum by subtracting current element.</li>
 * </ul>
 *
 * <p>
 * At index <code>i</code>:
 * </p>
 *
 * <pre>
 * prefixSum = sum(nums[0 .. i-1])
 * suffixProduct = product(nums[i+1 .. n-1]) before multiplying current
 * </pre>
 *
 * <p>
 * After subtracting <code>nums[i]</code> from total sum, we can check whether
 * the prefix sum equals the suffix product built so far.
 * </p>
 *
 * <hr>
 *
 * <h3>Algorithm</h3>
 *
 * <ol>
 *   <li>Compute total sum of the array.</li>
 *   <li>Initialize <code>suffixProduct = 1</code>.</li>
 *   <li>Traverse from right to left.</li>
 *   <li>At each index:
 *     <ul>
 *       <li>Remove current element from prefix sum.</li>
 *       <li>Check if <code>prefixSum == suffixProduct</code>.</li>
 *       <li>If yes, return current index.</li>
 *       <li>Otherwise multiply current element into suffix product.</li>
 *     </ul>
 *   </li>
 * </ol>
 *
 * <hr>
 *
 * <h3>Why Right-to-Left Traversal?</h3>
 *
 * <p>
 * Because suffix product naturally grows from the end of the array.
 * By moving from right to left:
 * </p>
 *
 * <ul>
 *   <li>prefix sum can be updated by subtracting the current value</li>
 *   <li>suffix product can be built incrementally</li>
 * </ul>
 *
 * <p>
 * This gives an O(n) solution.
 * </p>
 *
 * <hr>
 *
 * <h3>Monotonic Observation (Optimization Insight)</h3>
 *
 * <p>
 * Constraints guarantee:
 * </p>
 *
 * <pre>
 * 1 <= nums[i]
 * </pre>
 *
 * <p>
 * Therefore:
 * </p>
 *
 * <ul>
 *   <li><b>prefixSum</b> strictly decreases as we move left</li>
 *   <li><b>suffixProduct</b> never decreases as we multiply positive numbers</li>
 * </ul>
 *
 * <p>
 * So once:
 * </p>
 *
 * <pre>
 * suffixProduct > prefixSum
 * </pre>
 *
 * <p>
 * equality can never happen later.
 * This enables an <b>early exit</b> optimization.
 * </p>
 *
 * <hr>
 *
 * <h3>Overflow Concern</h3>
 *
 * <p>
 * Since:
 * </p>
 *
 * <pre>
 * nums[i] can be as large as 10^9
 * </pre>
 *
 * <p>
 * suffix product may overflow even a <code>long</code>.
 * So before multiplying:
 * </p>
 *
 * <pre>
 * if (suffixProduct > prefixSum / nums[i]) return -1;
 * </pre>
 *
 * <p>
 * This safely detects when product would exceed prefix sum without overflow.
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input : [2, 8, 2, 2, 5]
 *
 * totalSum = 19
 *
 * Traverse from right:
 *
 * i = 4
 * prefixSum = 14
 * suffixProduct = 1
 *
 * i = 3
 * prefixSum = 12
 * suffixProduct = 5
 *
 * i = 2
 * prefixSum = 10
 * suffixProduct = 10
 *
 * Balanced index = 2
 * </pre>
 *
 * <hr>
 *
 * <h3>Time Complexity</h3>
 *
 * <ul>
 *   <li>One pass to compute sum → O(n)</li>
 *   <li>One pass from right to left → O(n)</li>
 * </ul>
 *
 * <p>
 * <b>Total Time Complexity:</b> O(n)
 * </p>
 *
 * <hr>
 *
 * <h3>Space Complexity</h3>
 *
 * <p>
 * Only a few variables are used.
 * </p>
 *
 * <p>
 * <b>Space Complexity:</b> O(1)
 * </p>
 *
 * <hr>
 *
 * <h3>Interview Notes</h3>
 *
 * <ul>
 *   <li>This is a good example of combining <b>prefix sum</b> and <b>suffix product</b>.</li>
 *   <li>The monotonic behavior of positive numbers allows early exit.</li>
 *   <li>Always discuss overflow when product grows rapidly.</li>
 * </ul>
 */
public class FindSmallestBalancedIndex {

    public static void main(String[] args) {
        int[] arr = {2,8,2,2,5};
        System.out.println("Input arr : " + Arrays.toString(arr));
        int smallestBalancedIndex = smallestBalancedIndex(arr);
        System.out.println("Output : " + smallestBalancedIndex);
    }


    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Trick : need to care for suffixProduct overflow. if current suffixProduct is bigger than prefix
     * we will never find the answer on our left.
     */
    public static int smallestBalancedIndex(int[] nums) {
        long totalSum = 0;
        for (int n : nums)
            totalSum += n;

        long prefixSum = totalSum;
        long suffixProduct = 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            prefixSum -= nums[i];

            if (prefixSum == suffixProduct)
                return i;

            // overflow-safe multiplication guard
            if (suffixProduct > prefixSum / nums[i])
                return -1;

            suffixProduct *= nums[i];
        }

        return -1;
    }
}
