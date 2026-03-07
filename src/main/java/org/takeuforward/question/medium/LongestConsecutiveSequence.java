package org.takeuforward.question.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <h2>Longest Consecutive Sequence</h2>
 *
 * <p>
 * Given an unsorted array of integers, find the length of the longest sequence
 * of consecutive numbers.
 * </p>
 *
 * <p>
 * A consecutive sequence consists of numbers that follow each other by exactly 1.
 * The numbers do not need to appear consecutively in the input array.
 * </p>
 *
 * <p>
 * LeetCode reference:
 * <a href="https://leetcode.com/problems/longest-consecutive-sequence/" target="_blank">
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * </a>
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input  : [100,4,200,1,3,2]
 * Output : 4
 *
 * Explanation:
 * Longest consecutive sequence is:
 * 1 → 2 → 3 → 4
 * Length = 4
 * </pre>
 *
 * <hr>
 *
 * <h3>Approach 1 : Sorting</h3>
 *
 * <p>
 * Sort the array and then scan through it while counting consecutive numbers.
 * Duplicate values are ignored.
 * </p>
 *
 * <b>Steps</b>
 * <ol>
 * <li>Sort the array.</li>
 * <li>Traverse the array and check if current element is previous + 1.</li>
 * <li>If duplicate appears, ignore it.</li>
 * <li>If sequence breaks, reset the counter.</li>
 * <li>Track the maximum sequence length.</li>
 * </ol>
 *
 * <b>Time Complexity</b>
 * <ul>
 * <li>Sorting → O(n log n)</li>
 * <li>Traversal → O(n)</li>
 * </ul>
 *
 * <b>Space Complexity</b>
 * <ul>
 * <li>O(log n) due to sorting recursion stack</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 2 : HashSet (Optimal)</h3>
 *
 * <p>
 * Store all numbers in a HashSet for O(1) lookup. Only start counting a sequence
 * when the current number is the beginning of a sequence.
 * </p>
 *
 * <p>
 * A number starts a sequence only if:
 * </p>
 *
 * <pre>
 * num - 1 does NOT exist in the set
 * </pre>
 *
 * <p>
 * From that number, keep checking:
 * </p>
 *
 * <pre>
 * num + 1, num + 2, num + 3 ...
 * </pre>
 *
 * <p>
 * until the sequence breaks.
 * </p>
 *
 * <b>Steps</b>
 * <ol>
 * <li>Insert all numbers into a HashSet.</li>
 * <li>Iterate through the set.</li>
 * <li>If (num - 1) does not exist, start a new sequence.</li>
 * <li>Count consecutive numbers using num + 1.</li>
 * <li>Track the maximum length.</li>
 * </ol>
 *
 * <b>Why this works</b>
 * <p>
 * This prevents recounting sequences multiple times and ensures each sequence
 * is explored only once.
 * </p>
 *
 * <b>Time Complexity</b>
 * <ul>
 * <li>Average → O(n)</li>
 * <li>Worst case → O(n²) due to hash collisions</li>
 * </ul>
 *
 * <b>Space Complexity</b>
 * <ul>
 * <li>O(n)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 3 : Boolean Range Array</h3>
 *
 * <p>
 * Determine the minimum and maximum values in the array and create a boolean
 * array to mark which numbers exist.
 * </p>
 *
 * <b>Steps</b>
 * <ol>
 * <li>Find minimum and maximum element.</li>
 * <li>Create boolean array of size (max - min + 1).</li>
 * <li>Mark observed numbers.</li>
 * <li>Scan the boolean array for longest consecutive true segment.</li>
 * </ol>
 *
 * <b>Time Complexity</b>
 * <ul>
 * <li>O(n)</li>
 * </ul>
 *
 * <b>Space Complexity</b>
 * <ul>
 * <li>O(range)</li>
 * </ul>
 *
 * <p>
 * ⚠️ This approach may cause memory issues if the range of numbers is very large.
 * </p>
 *
 * <hr>
 *
 * <h3>Interview Insight</h3>
 *
 * <p>
 * The optimal solution relies on the observation that a sequence should only
 * start from numbers where <b>(num - 1) does not exist</b>.
 * This ensures each sequence is counted exactly once.
 * </p>
 *
 * <pre>
 * Example:
 * Set = {100,4,200,1,3,2}
 *
 * 1 → start sequence
 * 2 → skip (1 exists)
 * 3 → skip (2 exists)
 * 4 → skip (3 exists)
 *
 * Sequence found:
 * 1 → 2 → 3 → 4
 * </pre>
 *
 * <hr>
 *
 * <h3>Edge Cases</h3>
 *
 * <ul>
 * <li>Empty array → return 0</li>
 * <li>Duplicate values</li>
 * <li>Negative numbers</li>
 * <li>Single element array</li>
 * </ul>
 *
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence1(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence2(arr));

        arr = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence1(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence2(arr));

        arr = new int[]{1, 0, 1, 2};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence1(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence2(arr));

        arr = new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence1(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence2(arr));

        arr = new int[]{9, 1, -3, 2, 4, 8, 3, -1, 6, -2, -4, 7};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence1(arr));
        System.out.println("Longest consecutive sequence : " + longestConsecutiveSequence2(arr));
    }

    /**
     * Time complexity : O(n * log(n))
     * Space complexity : O(log(n))
     */
    private static int longestConsecutiveSequence(int[] arr) {
        if (arr.length == 0) return 0;
        int count = 1;
        int maxCount = 1;

        Arrays.sort(arr); // O(n * log(n))

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1] - 1 || arr[i] == arr[i + 1]) {
                if (arr[i] != arr[i + 1]) {
                    count++;
                    maxCount = Math.max(count, maxCount);
                }
            } else {
                count = 1;
            }
        }
        return maxCount;
    }

    /**
     * Time complexity : O(n) average -> O(n^2) worst case due to hash collisions
     * Space complexity : O(n)
     */
    private static int longestConsecutiveSequence1(int[] arr) {
        if (arr.length == 0) return 0;
        int longest = 1;

        HashSet<Integer> integers = new HashSet<>();
        for (int i : arr) {
            integers.add(i);
        }

        for (int i : integers) {
            if (!integers.contains(i - 1)) {
                int count = 1;
                while (integers.contains(i + 1)) {
                    count++;
                    i += 1;
                }
                longest = Math.max(count, longest);
            }
        }

        return longest;
    }


    /**
     * Time complexity : O(3n) 3 pass
     * Space complexity : O(range)
     */
    private static int longestConsecutiveSequence2(int[] arr) {
        int min = arr[0];
        int max = arr[0];

        for (int i : arr) {
            if (i < min) {
                min = i;
            } else if (i > max) {
                max = i;
            }
        }

        int range = max - min + 1;
        boolean[] isObserved = new boolean[range];

        for (int i : arr) {
            int index = i - min;
            isObserved[index] = true;
        }

        int count = 0;
        int longest = 0;

        for (boolean _isObserved : isObserved) {
            if (_isObserved) {
                count++;
            } else {
                longest = Math.max(count, longest);
                count = 0;
            }
        }

        return Math.max(count, longest);
    }

}
