package org.takeuforward.stackAndQueue.hard;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h2>Largest Rectangle in Histogram</h2>
 *
 * <p>
 * Given an array of integers <code>heights</code> representing the height of bars in a histogram,
 * where the width of each bar is <b>1</b>, return the area of the largest rectangle that can be
 * formed within the histogram.
 * </p>
 *
 * <p>
 * LeetCode reference:
 * <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/description/" target="_blank">
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * </a>
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input  : [2,1,5,6,2,3]
 * Output : 10
 *
 * Explanation:
 *
 * Histogram bars:
 *
 *      6
 *      █
 *      █
 *  5   █
 *  █   █
 *  █   █       3
 *  █   █   2   █
 *  █   █   █   █
 *  2   1   5   6   2   3
 *
 * Largest rectangle:
 *
 * heights = [5,6]
 * width   = 2
 * height  = 5
 *
 * area = 5 × 2 = 10
 * </pre>
 *
 * <hr>
 *
 * <h3>Approach 1 : Brute Force</h3>
 *
 * <p>
 * For every bar, treat it as the minimum height of a rectangle and expand
 * both left and right until a smaller height is encountered.
 * </p>
 *
 * <b>Steps</b>
 *
 * <ol>
 * <li>Pick each bar as the current height.</li>
 * <li>Expand left while bars are greater than or equal to this height.</li>
 * <li>Expand right while bars are greater than or equal to this height.</li>
 * <li>Compute width of the rectangle.</li>
 * <li>Area = height × width.</li>
 * </ol>
 *
 * <b>Time Complexity</b>
 *
 * <ul>
 * <li>For each bar we may scan the whole array → <b>O(n²)</b></li>
 * </ul>
 *
 * <b>Space Complexity</b>
 *
 * <ul>
 * <li><b>O(1)</b></li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 2 : Monotonic Stack (Optimal)</h3>
 *
 * <p>
 * Instead of expanding left and right for every bar, we precompute the boundaries
 * where a smaller element exists.
 * </p>
 *
 * <p>
 * For each index <code>i</code> we compute:
 * </p>
 *
 * <ul>
 * <li><b>PSE</b> → Previous Smaller Element Index</li>
 * <li><b>NSE</b> → Next Smaller Element Index</li>
 * </ul>
 *
 * <p>
 * These indices define the maximum width for which the current bar
 * can act as the minimum height.
 * </p>
 *
 * <hr>
 *
 * <h3>Rectangle Width Calculation</h3>
 *
 * <pre>
 * width = NSE[i] - PSE[i] - 1
 * </pre>
 *
 * <p>
 * The <code>-1</code> is needed because the smaller elements themselves
 * cannot be included in the rectangle.
 * </p>
 *
 * <pre>
 * Example:
 *
 * heights = [2,1,5,6,2,3]
 *                  ↑
 *                 5
 *
 * PSE = index of 1
 * NSE = index of 2
 *
 * width = 4 - 1 - 1 = 2
 * area  = 5 × 2 = 10
 * </pre>
 *
 * <hr>
 *
 * <h3>Monotonic Stack Insight</h3>
 *
 * <p>
 * A <b>monotonically increasing stack</b> is used to efficiently find the nearest
 * smaller element.
 * </p>
 *
 * <ul>
 * <li>When traversing left → right we compute <b>PSE</b>.</li>
 * <li>When traversing right → left we compute <b>NSE</b>.</li>
 * </ul>
 *
 * <p>
 * While processing each element:
 * </p>
 *
 * <pre>
 * while stack not empty AND height[stack.top] ≥ current height
 *     pop stack
 * </pre>
 *
 * <p>
 * This ensures the stack maintains increasing heights.
 * </p>
 *
 * <hr>
 *
 * <h3>Why This Works</h3>
 *
 * <p>
 * Each bar acts as the <b>minimum height</b> for a rectangle extending
 * between its nearest smaller elements on both sides.
 * </p>
 *
 * <p>
 * Using a stack ensures that each element is pushed and popped at most once,
 * resulting in linear complexity.
 * </p>
 *
 * <hr>
 *
 * <h3>Time Complexity</h3>
 *
 * <ul>
 * <li>PSE computation → O(n)</li>
 * <li>NSE computation → O(n)</li>
 * <li>Area calculation → O(n)</li>
 * </ul>
 *
 * <p>
 * <b>Total Time Complexity : O(n)</b>
 * </p>
 *
 * <hr>
 *
 * <h3>Space Complexity</h3>
 *
 * <ul>
 * <li>Stack for PSE → O(n)</li>
 * <li>Stack for NSE → O(n)</li>
 * <li>Index arrays → O(n)</li>
 * </ul>
 *
 * <p>
 * <b>Total Space Complexity : O(n)</b>
 * </p>
 *
 * <hr>
 *
 * <h3>Interview Notes</h3>
 *
 * <ul>
 * <li>This is a classic <b>monotonic stack problem</b>.</li>
 * <li>The key trick is identifying that each bar determines a rectangle
 * where it is the smallest height.</li>
 * <li>The nearest smaller elements define the rectangle boundaries.</li>
 * <li>This technique is reused in many problems like:
 *   <ul>
 *     <li>Sum of Subarray Minimums</li>
 *     <li>Maximal Rectangle</li>
 *     <li>Next Greater Element</li>
 *   </ul>
 * </li>
 * </ul>
 *
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println("Input arr : " + Arrays.toString(heights));
        System.out.println("Output maxArea : " + maxArea(heights));
        System.out.println("Output maxArea : " + maxArea1(heights));
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    public static int maxArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) { // O(n)
            int height = heights[i];
            int width = 1;
            // find left supporting width
            int curr = i - 1;
            while (curr >= 0) { // O(n/2)
                if (heights[curr] < height) {
                    break;
                }
                width++;
                curr--;
            }
            // find right supporting width
            curr = i + 1;
            while (curr < heights.length) { // O(n/2)
                if (heights[curr] < height) {
                    break;
                }
                width++;
                curr++;
            }
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }

    /**
     * Time complexity : O(3n)
     * Space complexity : O(2n)
     */
    public static int maxArea1(int[] heights) {
        int maxArea = 0;
        int[] psei = psei(heights);
        int[] nsei = nsei(heights);

        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int width = (i - psei[i]) + (nsei[i] - i) - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static int[] psei(int[] heights) {
        int[] results = new int[heights.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
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

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static int[] nsei(int[] heights) {
        int[] results = new int[heights.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                results[i] = heights.length;
            } else {
                results[i] = stack.peek();
            }
            stack.push(i);
        }
        return results;
    }

}
