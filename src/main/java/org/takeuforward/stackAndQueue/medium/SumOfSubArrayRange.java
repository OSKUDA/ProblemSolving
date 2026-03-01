package org.takeuforward.stackAndQueue.medium;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h2>LeetCode 2104: Sum of Subarray Ranges</h2>
 * <p>
 * <a href="https://leetcode.com/problems/sum-of-subarray-ranges/">LeetCode Link</a>
 * </p>
 *
 * <h3>Problem</h3>
 * <p>
 * For every subarray, define its <b>range</b> as:
 * <code>(max element in subarray - min element in subarray)</code>.
 * Return the sum of ranges over all subarrays.
 * </p>
 *
 * <h3>Key Insight (Interview Core)</h3>
 * <p>
 * Instead of computing (max - min) for each subarray directly:
 * </p>
 * <ul>
 *   <li><b>Sum of subarray ranges</b> = (Sum of all subarray maximums) - (Sum of all subarray minimums)</li>
 * </ul>
 *
 * <h3>Brute Force (for intuition)</h3>
 * <ul>
 *   <li>Fix start <code>i</code>, extend end <code>j</code>, maintain min/max incrementally.</li>
 *   <li><b>Time:</b> O(n^2)</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 *
 * <h3>Optimal Approach (Contribution Technique + Monotonic Stack)</h3>
 * <p>
 * Each element <code>nums[i]</code> contributes to many subarrays as:
 * </p>
 * <ul>
 *   <li>a <b>minimum</b> in some subarrays</li>
 *   <li>a <b>maximum</b> in some subarrays</li>
 * </ul>
 *
 * <p>
 * If we can count how many subarrays take <code>nums[i]</code> as the minimum (or maximum),
 * we can sum contributions directly.
 * </p>
 *
 * <h4>Contribution Formula</h4>
 * <p>
 * Let:
 * </p>
 * <ul>
 *   <li><code>left = i - prevIndex</code></li>
 *   <li><code>right = nextIndex - i</code></li>
 * </ul>
 *
 * <p>
 * Then #subarrays where <code>nums[i]</code> is the chosen min/max:
 * </p>
 * <pre>
 * count = left * right
 * contribution = count * nums[i]
 * </pre>
 *
 * <h4>How we get boundaries (indices)</h4>
 *
 * <p><b>For Minimum Contribution:</b></p>
 * <ul>
 *   <li><b>PSEE</b>: Previous Smaller or Equal Element Index</li>
 *   <li><b>NSE</b>: Next Smaller Element Index</li>
 * </ul>
 *
 * <p><b>For Maximum Contribution:</b></p>
 * <ul>
 *   <li><b>PGEE</b>: Previous Greater or Equal Element Index</li>
 *   <li><b>NGE</b>: Next Greater Element Index</li>
 * </ul>
 *
 * <h3>Duplicate Handling (Very Important)</h3>
 * <p>
 * To avoid double-counting when duplicates exist, we break ties consistently:
 * </p>
 *
 * <p><b>For Minimums:</b></p>
 * <ul>
 *   <li><b>Left boundary</b> keeps equals (pop while <code>&gt;</code>)</li>
 *   <li><b>Right boundary</b> removes equals (pop while <code>&gt;=</code>)</li>
 * </ul>
 *
 * <p><b>For Maximums:</b></p>
 * <ul>
 *   <li><b>Left boundary</b> keeps equals (pop while <code>&lt;</code>)</li>
 *   <li><b>Right boundary</b> removes equals (pop while <code>&lt;=</code>)</li>
 * </ul>
 *
 * <h3>Why stacks are O(n) (Amortized)</h3>
 * <p>
 * Each index is pushed once and popped at most once in each monotonic-stack pass,
 * so every helper method runs in O(n).
 * </p>
 *
 * <h3>Overall Complexity</h3>
 * <ul>
 *   <li><b>Time:</b> O(n) (4 stack passes + 1 final pass)</li>
 *   <li><b>Space:</b> O(n) (4 index arrays + stack)</li>
 * </ul>
 *
 * <h3>Common Pitfalls</h3>
 * <ul>
 *   <li>Wrong tie-breaking (<code>&gt;</code> vs <code>&gt;=</code>, <code>&lt;</code> vs <code>&lt;=</code>) → duplicates get counted incorrectly</li>
 *   <li>Overflow: use <code>long</code> for <code>left * right * nums[i]</code></li>
 *   <li>For boundary arrays, use <code>-1</code> for "no previous" and <code>n</code> for "no next"</li>
 * </ul>
 *
 * <h3>Final Formula Used</h3>
 * <pre>
 * sumRanges = sumMaxContribution - sumMinContribution
 * </pre>
 */
public class SumOfSubArrayRange {
    public static void main(String[] args) {
        int[] arr = new int[]{-31372,86677,70463,37727,-91683,-41347,-90576,-82174,-84198,-5148,-12591,-34156,49770,9666,-77075,-57678,-31101,-47531,-86306,-91337,-89507,-24917,-87692,-39171,98075,17787,-42549,34352,-70752,71832,70055,-1026,3784,2190,-36669,959,50619,97129,77088,54049,51707,72052,59230,-96834,-14048,-9619,84853,99362,69885,74086,-28737,23060,-63323,13156,-72998,94336,-75409,58266,-86800,-54564,80773,40687,-47207,43609,-56556,21192,-48024,-58907,1629,-65561,-68397,31862,-2201,-34966,43542,-59201,-3637,-21936,-93559,49435,23249,-54299,70508,-90795,-3620,-33894,43927,10208,-7390,86931,48175,81859,95058,-16614,38066,-99361,63621,-99285,-47111,29933,73901,60455,46586,-84117,35256,-89853,33383,-91662,82979,-48835,-93877,-80929,-98904,-47773,69451,85183,-14449,-51496,75765,35062,12456,35254,-16363,80792,-3414,9244,62961,-52057,56344,-50277,-26870,-63323,54993,75596,-93637,-78526,-3058,-30560,82233,-50795,-5290,-641,-83040,13524,86725,23735,29280,43938,-43995,-8992,-83717,-62090,74538,58682,-56550,-8638,61528,-87974};
        System.out.println("Input array : " + Arrays.toString(arr));
        long result = range(arr);
        System.out.println("Output : " + result);

        arr = new int[]{1,2,3};
        System.out.println("Input array : " + Arrays.toString(arr));
        result = range1(arr);
        System.out.println("Output : " + result);
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    public static long range(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                }
                if (max < nums[j]) {
                    max = nums[j];
                }
                total += (max - min);
            }
        }
        return total;
    }

    /**
     * Time complexity : O(9n) => O(n)
     * Space complexity : O(8n) => O(n)
     */
    public static long range1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] pseei = previousSmallestOrEqualElementIndex(nums);
        int[] nsei = nextSmallestElementIndex(nums);
        int[] pgeei = previousGreatestOrEqualElementIndex(nums);
        int[] ngei = nextGreatestElementIndex(nums);

        long totalMinContribution = 0L;
        long totalMaxContribution = 0L;

        for (int i = 0; i < nums.length; i++) {
            // calculate min contribution
            int left = i - pseei[i];
            int right = nsei[i] - i;
            totalMinContribution += (long) left * right * nums[i];

            // calculate max contribution
            left = i - pgeei[i];
            right = ngei[i] - i;
            totalMaxContribution += (long) left * right * nums[i];
        }
        return totalMaxContribution - totalMinContribution;
    }

    /**
     * Time complexity : O(2n)
     * Space complexity : O(2n)
     */
    private static int[] nextSmallestElementIndex(int[] arr) {
        int[] result = new int[arr.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (arr[stack.peek()] >= arr[i])) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? arr.length : stack.peek();
            stack.push(i);
        }
        return result;
    }

    /**
     * Time complexity : O(2n)
     * Space complexity : O(2n)
     */
    private static int[] previousSmallestOrEqualElementIndex(int[] arr) {
        int[] results = new int[arr.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && (arr[stack.peek()] > arr[i])) {
                stack.pop();
            }
            results[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return results;
    }

    /**
     * Time complexity : O(2n)
     * Space complexity : O(2n)
     */
    private static int[] nextGreatestElementIndex(int[] arr) {
        int[] results = new int[arr.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (arr[stack.peek()] <= arr[i])) {
                stack.pop();
            }
            results[i] = stack.isEmpty() ? arr.length : stack.peek();
            stack.push(i);
        }
        return results;
    }

    /**
     * Time complexity : O(2n)
     * Space complexity : O(2n)
     */
    private static int[] previousGreatestOrEqualElementIndex(int[] arr) {
        int[] results = new int[arr.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && (arr[stack.peek()] < arr[i])) {
                stack.pop();
            }
            results[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return results;
    }
}
