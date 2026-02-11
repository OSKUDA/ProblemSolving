package org.takeuforward.stackAndQueue.easy;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h2>Next Smaller Element (to the Right)</h2>
 *
 * <p>
 * Given an integer array, for each element find the <b>next smaller element
 * on its right</b>. If no such element exists, return <code>-1</code>.
 * </p>
 *
 * <h3>Key Insight</h3>
 * <ul>
 *   <li>This is a classic <b>Monotonic Stack</b> problem.</li>
 *   <li>We maintain a <b>monotonically increasing stack</b>.</li>
 *   <li>Traversal is done from <b>right to left</b>.</li>
 * </ul>
 *
 * <h3>Why Right to Left?</h3>
 * <p>
 * The "next" smaller element must lie to the right.
 * By traversing from right → left, we already know the candidates
 * for the next smaller element when processing the current index.
 * </p>
 *
 * <h3>Algorithm</h3>
 * <ol>
 *   <li>Initialize an empty stack.</li>
 *   <li>Traverse the array from right to left.</li>
 *   <li>While stack is not empty and stack top ≥ current value, pop.</li>
 *   <li>The stack top (if exists) is the next smaller element.</li>
 *   <li>Push current element onto the stack.</li>
 * </ol>
 *
 * <h3>Monotonic Stack Invariant</h3>
 * <p>
 * The stack always stores elements in <b>strictly increasing order</b>
 * from bottom to top.
 * </p>
 *
 * <h3>Example</h3>
 * <pre>
 * Input:  [10, 9, 8, 7]
 * Output: [-1, -1, -1, -1]
 *
 * Input:  [4, 8, 5, 2, 25]
 * Output: [2, 5, 2, -1, -1]
 * </pre>
 *
 * <h3>Time Complexity</h3>
 * <ul>
 *   <li><b>O(n)</b> — each element is pushed and popped at most once.</li>
 * </ul>
 *
 * <h3>Space Complexity</h3>
 * <ul>
 *   <li><b>O(n)</b> — stack can hold up to n elements.</li>
 * </ul>
 *
 * <h3>Common Pitfalls</h3>
 * <ul>
 *   <li>Using <code>&lt;</code> instead of <code>&lt;=</code> in pop condition
 *       (breaks correctness for duplicates).</li>
 *   <li>Traversing left-to-right instead of right-to-left.</li>
 * </ul>
 *
 * <h3>Related Problems</h3>
 * <ul>
 *   <li>Next Greater Element</li>
 *   <li>Previous Smaller Element</li>
 *   <li>Largest Rectangle in Histogram</li>
 *   <li>Stock Span Problem</li>
 * </ul>
 */
public class NextSmallestElement {
    public static void main(String[] args) {
        int[] nums = {10, 9, 8, 7};
        System.out.println("Input array : " + Arrays.toString(nums));
        int[] results = nextSmallestElement(nums);
        System.out.println("Output array : " + Arrays.toString(results));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int[] nextSmallestElement(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        int[] results = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];
            while (!stack.isEmpty() && val <= stack.peek()) {
                stack.pop();
            }
            results[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(val);
        }
        return results;
    }
}
