package org.takeuforward.array.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithZeroSum {

    public static void main(String[] args) {
        int[] arr = {9, -3, 3, -1, 6, -5};
        System.out.println("Input array : " + Arrays.toString(arr));
        int longest = longest(arr);
        System.out.println("Output : " + longest);
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int longest(int[] arr) {
        int k = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int prefix = 0;
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            if (prefix == 0) {
                maxLength = Math.max(maxLength, i + 1);
            } else { // look in memory
                if (map.containsKey(prefix - k)) {
                    maxLength = Math.max(maxLength, i - map.get(prefix - k));
                }
            }

            // if same preFix found, ignore, we want the longest length
            if (!map.containsKey(prefix)) {
                map.put(prefix, i);
            }
        }
        return maxLength;
    }


}
