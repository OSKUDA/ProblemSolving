package org.takeuforward.question.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Subarray Sum Equals K</h2>
 *
 * <p>
 * Given an integer array <b>nums</b> and an integer <b>k</b>, return the total number
 * of contiguous subarrays whose sum equals <b>k</b>.
 * </p>
 *
 * <p>
 * A <b>subarray</b> is a contiguous sequence of elements within an array.
 * </p>
 *
 * <p>
 * LeetCode reference:
 * <a href="https://leetcode.com/problems/subarray-sum-equals-k/" target="_blank">
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * </a>
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input  : nums = [1,1,1], k = 2
 * Output : 2
 *
 * Explanation:
 *
 * Subarrays with sum = 2
 * [1,1]  (index 0..1)
 * [1,1]  (index 1..2)
 * </pre>
 *
 * <hr>
 *
 * <h3>Key Idea : Prefix Sum + HashMap</h3>
 *
 * <p>
 * The core mathematical identity used in this problem:
 * </p>
 *
 * <pre>
 * sum(i..j) = prefix[j] - prefix[i-1]
 * </pre>
 *
 * <p>
 * If the subarray sum equals <b>k</b>, then:
 * </p>
 *
 * <pre>
 * prefix[j] - prefix[i-1] = k
 * </pre>
 *
 * Rearranging:
 *
 * <pre>
 * prefix[i-1] = prefix[j] - k
 * </pre>
 *
 * <p>
 * Therefore, while iterating through the array, we check whether
 * <b>(prefixSum - k)</b> has already appeared before.
 * If it has, it means there exists a subarray ending at the current index
 * whose sum equals <b>k</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Algorithm</h3>
 *
 * <ol>
 * <li>Maintain a running prefix sum.</li>
 * <li>Use a HashMap to store the frequency of each prefix sum encountered.</li>
 * <li>If (prefixSum - k) exists in the map, it indicates one or more valid subarrays.</li>
 * <li>Add the frequency of (prefixSum - k) to the result count.</li>
 * <li>Update the frequency of the current prefix sum in the map.</li>
 * </ol>
 *
 * <hr>
 *
 * <h3>Why Initialize map.put(0,1)?</h3>
 *
 * <p>
 * This handles cases where a valid subarray starts from index <b>0</b>.
 * </p>
 *
 * <pre>
 * Example:
 * nums = [3]
 * k = 3
 *
 * prefix = 3
 * prefix - k = 0
 *
 * Since 0 already exists in the map,
 * the subarray [3] is correctly counted.
 * </pre>
 *
 * <hr>
 *
 * <h3>Time Complexity</h3>
 *
 * <ul>
 * <li>Single pass through array → <b>O(n)</b></li>
 * <li>HashMap lookup and insertion → average <b>O(1)</b></li>
 * </ul>
 *
 * <p>
 * Total Time Complexity: <b>O(n)</b>
 * </p>
 *
 * <hr>
 *
 * <h3>Space Complexity</h3>
 *
 * <ul>
 * <li>HashMap stores prefix sums → <b>O(n)</b></li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Important Observations</h3>
 *
 * <ul>
 * <li>Works with negative numbers.</li>
 * <li>Sliding window cannot solve this problem due to negative values.</li>
 * <li>Prefix sum + hashmap is the standard pattern for such problems.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Visualization</h3>
 *
 * <pre>
 * nums = [1,1,1]
 * k = 2
 *
 * prefix sums:
 *
 * index   value   prefix
 * 0       1       1
 * 1       1       2
 * 2       1       3
 *
 * When prefix = 2
 * prefix - k = 0 → found in map → count++
 *
 * When prefix = 3
 * prefix - k = 1 → found in map → count++
 *
 * Result = 2
 * </pre>
 *
 */
public class CountSubArrayWithGivenSumK {
    public static void main(String[] args) {
        int[] arr = {1,1,1};
        int k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        int count = count(arr, k);
        System.out.println("Output : " + count);
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int count(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefix = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            if (map.containsKey(prefix - k)) {
                count += map.get(prefix - k);
            }

            // save the freq
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }
}
