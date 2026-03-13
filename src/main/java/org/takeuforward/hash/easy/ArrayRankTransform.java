package org.takeuforward.hash.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <h2>Rank Transform of an Array</h2>
 *
 * <p>
 * LeetCode Problem:
 * <a href="https://leetcode.com/problems/rank-transform-of-an-array/description/">
 * https://leetcode.com/problems/rank-transform-of-an-array/description/
 * </a>
 * </p>
 *
 * <h3>Problem Statement</h3>
 *
 * <p>
 * Given an integer array <b>arr</b>, replace each element with its
 * <b>rank</b>.
 * </p>
 *
 * <p>
 * The rank represents the position of the element in the sorted order
 * of unique values.
 * </p>
 *
 * <ul>
 *     <li>Rank starts from <b>1</b>.</li>
 *     <li>Larger value → larger rank.</li>
 *     <li>Equal values must have the <b>same rank</b>.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input  : [40, 10, 10, 30]
 * Sorted : [10, 10, 30, 40]
 *
 * Unique values with rank:
 * 10 -> 1
 * 30 -> 2
 * 40 -> 3
 *
 * Output : [3, 1, 1, 2]
 * </pre>
 *
 * <hr>
 *
 * <h3>Key Observation</h3>
 *
 * <p>
 * Rank depends only on the <b>relative sorted order of unique elements</b>.
 * </p>
 *
 * <p>
 * So the problem can be solved in 3 steps:
 * </p>
 *
 * <ol>
 *     <li>Copy the array.</li>
 *     <li>Sort the copied array.</li>
 *     <li>Assign ranks to unique values and store them in a map.</li>
 * </ol>
 *
 * <p>
 * Finally, replace every original element with its mapped rank.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach — Sorting + HashMap</h3>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Create a copy of the input array.</li>
 *     <li>Sort the copied array.</li>
 *     <li>Traverse the sorted array:
 *         <ul>
 *             <li>If the element has not been ranked yet, assign the next rank.</li>
 *             <li>Store the mapping in a <b>HashMap&lt;Integer, Integer&gt;</b>.</li>
 *         </ul>
 *     </li>
 *     <li>Traverse the original array and replace each element using the map.</li>
 * </ol>
 *
 * <hr>
 *
 * <h3>Why This Works</h3>
 *
 * <ul>
 *     <li>Sorting arranges numbers in increasing order.</li>
 *     <li>Only unique values need new ranks.</li>
 *     <li>Duplicate elements reuse the same rank from the map.</li>
 * </ul>
 *
 * <p>
 * This ensures:
 * </p>
 *
 * <ul>
 *     <li>Smaller values get smaller ranks.</li>
 *     <li>Equal values get equal ranks.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Complexity Analysis</h3>
 *
 * <ul>
 *     <li>
 *         <b>Copy array:</b> O(n)
 *     </li>
 *     <li>
 *         <b>Sort copied array:</b> O(n log n)
 *     </li>
 *     <li>
 *         <b>Build rank map:</b> O(n)
 *     </li>
 *     <li>
 *         <b>Transform original array:</b> O(n)
 *     </li>
 * </ul>
 *
 * <pre>
 * Time Complexity  : O(n log n)
 * Space Complexity : O(n)
 * </pre>
 *
 * <p>
 * Sorting dominates the total time complexity.
 * </p>
 *
 * <hr>
 *
 * <h3>Dry Run</h3>
 *
 * <pre>
 * arr  = [40, 10, 10, 30]
 * temp = [40, 10, 10, 30]
 *
 * after sorting temp:
 * [10, 10, 30, 40]
 *
 * rankMap:
 * 10 -> 1
 * 30 -> 2
 * 40 -> 3
 *
 * transform arr:
 * 40 -> 3
 * 10 -> 1
 * 10 -> 1
 * 30 -> 2
 *
 * result:
 * [3, 1, 1, 2]
 * </pre>
 *
 * <hr>
 *
 * <h3>Edge Cases</h3>
 *
 * <ul>
 *     <li>Empty array → return empty array.</li>
 *     <li>Single element → rank is always 1.</li>
 *     <li>Duplicate values → same rank.</li>
 *     <li>Negative values → handled naturally after sorting.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Pattern Recognition</h3>
 *
 * <p>
 * This problem is a simple form of <b>coordinate compression</b>.
 * </p>
 *
 * <p>
 * Whenever a problem asks to replace values by their relative sorted order,
 * think of:
 * </p>
 *
 * <ul>
 *     <li><b>Sorting</b></li>
 *     <li><b>Unique element ranking</b></li>
 *     <li><b>HashMap for fast lookup</b></li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Interview Takeaway</h3>
 *
 * <ul>
 *     <li>Use a sorted copy to preserve the original array order.</li>
 *     <li>Use a map to assign rank only once per unique element.</li>
 *     <li>This is a classic <b>sorting + hashing</b> problem.</li>
 * </ul>
 *
 */
public class ArrayRankTransform {
    public static void main(String[] args) {
        int[] arr = {40,10,10,30};
        System.out.println("Input : " + Arrays.toString(arr));
        int[] results = rank(arr);
        System.out.println("Output : " + Arrays.toString(results));
    }

    /**
     * Time complexity : O(nlog(n) + n + n)
     * Space complexity : O(n + n) -> O(n)
     */
    private static int[] rank(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temp);

        HashMap<Integer, Integer> rank = new HashMap<>();
        int currRank = 1;
        for (int element : temp) {
            if (!rank.containsKey(element)) {
                rank.put(element, currRank);
                currRank++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rank.get(arr[i]);
        }
        return arr;
    }
}
