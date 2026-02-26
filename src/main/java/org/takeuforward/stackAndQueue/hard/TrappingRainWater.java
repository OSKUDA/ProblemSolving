package org.takeuforward.stackAndQueue.hard;

import java.util.Arrays;

/**
 * <h2>LeetCode 42: Trapping Rain Water</h2>
 * <p>
 * <a href="https://leetcode.com/problems/trapping-rain-water/">
 * https://leetcode.com/problems/trapping-rain-water/
 * </a>
 * </p>
 *
 * <h3>Problem Summary</h3>
 * <ul>
 *   <li>Given an array <b>height[]</b> representing elevation bars.</li>
 *   <li>Each bar has width = 1.</li>
 *   <li>Return the total amount of water that can be trapped after raining.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Core Insight</h3>
 * <p>
 * Water trapped at index <b>i</b> depends on:
 * </p>
 *
 * <pre>
 * water[i] = min(maxLeft[i], maxRight[i]) - height[i]
 * </pre>
 *
 * <ul>
 *   <li><b>maxLeft[i]</b> → maximum height to the left of i</li>
 *   <li><b>maxRight[i]</b> → maximum height to the right of i</li>
 *   <li>Water level is limited by the shorter boundary.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 1: Prefix & Suffix Maximum Arrays (DP Approach)</h3>
 *
 * <h4>Steps</h4>
 * <ol>
 *   <li>Build <b>maxLeft[]</b> array.</li>
 *   <li>Build <b>maxRight[]</b> array.</li>
 *   <li>For each index, compute trapped water using formula.</li>
 * </ol>
 *
 * <h4>Time Complexity</h4>
 * <ul>
 *   <li>O(n) → Three passes over the array.</li>
 * </ul>
 *
 * <h4>Space Complexity</h4>
 * <ul>
 *   <li>O(n) → Two auxiliary arrays (maxLeft, maxRight).</li>
 * </ul>
 *
 * <h4>Why It Works</h4>
 * <p>
 * For each position, the amount of water is bounded by the smaller of the tallest walls on both sides.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 2: Two-Pointer Optimization (Space Optimized)</h3>
 *
 * <h4>Key Observation</h4>
 * <ul>
 *   <li>If height[left] ≤ height[right], then trapped water depends only on <b>leftMax</b>.</li>
 *   <li>If height[right] < height[left], then trapped water depends only on <b>rightMax</b>.</li>
 * </ul>
 *
 * <h4>Algorithm</h4>
 * <ol>
 *   <li>Initialize two pointers: <b>left</b> and <b>right</b>.</li>
 *   <li>Maintain <b>leftMax</b> and <b>rightMax</b>.</li>
 *   <li>Move the smaller height pointer inward.</li>
 *   <li>Accumulate trapped water accordingly.</li>
 * </ol>
 *
 * <h4>Time Complexity</h4>
 * <ul>
 *   <li>O(n) → Each pointer moves at most n times.</li>
 * </ul>
 *
 * <h4>Space Complexity</h4>
 * <ul>
 *   <li>O(1) → No extra arrays used.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Why Two-Pointer Works (Important Interview Insight)</h3>
 *
 * <p>
 * When height[left] ≤ height[right], we are guaranteed that:
 * </p>
 *
 * <pre>
 * min(maxLeft, maxRight) = maxLeft
 * </pre>
 *
 * <p>
 * Because right boundary is already tall enough to trap water from the left side.
 * This removes the need to explicitly compute maxRight[].
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input : [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Input : [4,2,0,3,2,5]
 * Output: 9
 * </pre>
 *
 * <hr>
 *
 * <h3>Common Pitfalls</h3>
 * <ul>
 *   <li>Forgetting to ensure trapped water is non-negative.</li>
 *   <li>Incorrect pointer movement order in two-pointer approach.</li>
 *   <li>Not understanding why smaller boundary determines water level.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Related Problems</h3>
 * <ul>
 *   <li>Container With Most Water</li>
 *   <li>Largest Rectangle in Histogram</li>
 *   <li>Daily Temperatures</li>
 *   <li>Next Greater Element</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Always first explain the DP solution clearly.
 * Then optimize to the two-pointer solution to demonstrate deeper understanding.
 * </p>
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Input arr : " + Arrays.toString(height));
        int result = trap(height);
        System.out.println("Output : " + result);

        height = new int[]{4,2,0,3,2,5};
        System.out.println("Input arr : " + Arrays.toString(height));
        result = trap(height);
        System.out.println("Output : " + result);

        height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};;
        System.out.println("Input arr : " + Arrays.toString(height));
        result = trap1(height);
        System.out.println("Output : " + result);

        height = new int[]{4,2,0,3,2,5};
        System.out.println("Input arr : " + Arrays.toString(height));
        result = trap1(height);
        System.out.println("Output : " + result);
    }

    /**
     * Time complexity : O(n) - 3 pass
     * Space complexity : O(n)
     * Trick : Math.min(MaxLeft[i],MaxRight[i]) - height[i]
     */
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            maxLeft[i] = max;
            if (max < height[i]) {
                max = height[i];
            }
        }

        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            maxRight[i] = max;
            if (max < height[i]) {
                max = height[i];
            }
        }

        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int trapped = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (trapped > 0) water+= trapped;
        }
        return water;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Note : 2-pointer and smallest height minus curr height determines the water trapped
     */
    public static int trap1(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left <= right) {
            // if left height is equal or smaller
            if (height[left] <= height[right]) {
                // water trapped depends on leftMax
                if (leftMax <= height[left]) {
                    // we've found new maxLeft
                    leftMax = height[left];
                } else {
                    // water is trapped in height[left]
                    water += leftMax - height[left];
                }
                // move left forward
                left++;
            } else { // right height is smaller
                // water trapped depends on rightMax
                if (rightMax <= height[right]) {
                    // we've found new maxRight
                    rightMax = height[right];
                } else {
                    // water is trapped in height[right]
                    water += rightMax - height[right];
                }
                // move right backward
                right--;
            }
        }
        return water;
    }
}
