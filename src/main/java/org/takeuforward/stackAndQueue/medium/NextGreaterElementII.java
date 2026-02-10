package org.takeuforward.stackAndQueue.medium;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h2>LeetCode 503: Next Greater Element II</h2>
 * <p>
 *   <a href="https://leetcode.com/problems/next-greater-element-ii/">Problem Link</a>
 * </p>
 *
 * <h3>Problem</h3>
 * <ul>
 *   <li>Given a <b>circular array</b>, for each element find the <b>next greater element</b>.</li>
 *   <li>If no greater element exists, return <b>-1</b> for that position.</li>
 * </ul>
 *
 * <h3>Key Challenge</h3>
 * <ul>
 *   <li>The array is <b>circular</b>: after the last index, search continues from index 0.</li>
 * </ul>
 *
 * <h3>Naive Approach</h3>
 * <ul>
 *   <li>For each element, scan the rest of the array circularly.</li>
 *   <li><b>Time:</b> O(n<sup>2</sup>)</li>
 * </ul>
 *
 * <h3>Optimized Approach: Monotonic Stack</h3>
 * <ul>
 *   <li>Use a <b>monotonic decreasing stack</b> (top is the nearest greater candidate).</li>
 *   <li>Traverse from <b>right to left</b>.</li>
 *   <li>Simulate circular behavior by iterating from <b>(2n - 1) → 0</b>.</li>
 *   <li>Use modulo indexing: <code>nums[i % n]</code>.</li>
 * </ul>
 *
 * <h3>Stack Invariant</h3>
 * <ul>
 *   <li>Stack stores values in <b>strictly decreasing order</b> from top to bottom.</li>
 *   <li>The stack top represents the best <b>next greater</b> candidate.</li>
 * </ul>
 *
 * <h3>Algorithm Steps</h3>
 * <ol>
 *   <li>Loop <code>i</code> from <code>(2 * n - 1)</code> down to <code>0</code>.</li>
 *   <li>Let <code>val = nums[i % n]</code>.</li>
 *   <li>
 *     While stack is not empty and <code>stack.peek() &lt;= val</code>,
 *     pop (those elements can never be the next greater).
 *   </li>
 *   <li>
 *     If <code>i &lt; n</code>, store answer:
 *     <code>result[i] = stack.isEmpty() ? -1 : stack.peek()</code>
 *   </li>
 *   <li>Push <code>val</code> onto the stack.</li>
 * </ol>
 *
 * <h3>Why This Works</h3>
 * <ul>
 *   <li>Circular traversal is handled by modulo indexing.</li>
 *   <li>Each element is pushed and popped at most once ⇒ linear time.</li>
 * </ul>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time:</b> O(2n) ⇒ O(n)</li>
 *   <li><b>Space:</b> O(n) (stack + output array)</li>
 * </ul>
 *
 * <h3>Common Pitfalls</h3>
 * <ul>
 *   <li>Iterating only <code>n</code> times (misses circular effect).</li>
 *   <li>Forgetting <code>i % n</code> while reading values.</li>
 *   <li>Not maintaining the monotonic stack rule (<code>&lt;=</code> vs <code>&lt;</code> mistakes).</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <ul>
 *   <li>Say: <b>"monotonic decreasing stack + simulate circular array using 2n traversal"</b>.</li>
 *   <li>Mention: each element is processed at most twice (push/pop bound).</li>
 * </ul>
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        System.out.println("Input array : " + Arrays.toString(nums));
        int[] results = nextGreater(nums);
        System.out.println("Output array : " + Arrays.toString(results));

        System.out.println("Input array : " + Arrays.toString(nums));
        results = nextGreaterElement1(nums);
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

    /**
     * Time complexity : O(2n) => O(n)
     * Space complexity : O(n)
     * Note : Duplicate and attach the array hypothetically. Then calculate nextGreater from right -> left.
     */
    public static int[] nextGreaterElement1(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int[] results = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = (2 * nums.length) - 1; i >= 0; i--) {
            int val = nums[i % nums.length];
            while (!stack.isEmpty() && val >= stack.peek()) {
                stack.pop();
            }
            if (i < nums.length) {
                results[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(val);
        }

        return results;
    }
}
