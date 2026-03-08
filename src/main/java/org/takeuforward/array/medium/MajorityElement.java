package org.takeuforward.array.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {2,2,1,1,1,2,2};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Majority arr : " + majorityElement(arr));
        System.out.println("Majority arr : " + majorityElement1(arr));
    }

    /**
     * Time complexity : O(2N)
     * Space complexity : O(N)
     */
    private static int majorityElement(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i : arr) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        int max = Integer.MIN_VALUE;
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() >= max) {
                max = entry.getValue();
                value = entry.getKey();
            }
        }
        return value;
    }

    /**
     * Boyer-Moore majority voting algorithm
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    private static int majorityElement1(int[] arr) {
        int candidate = 0, vote = 0;
        for (int e : arr) {
            if (vote == 0) {
                candidate = e;
                vote = 1;
            } else {
                if (candidate == e) {
                    vote++;
                } else {
                    vote--;
                }
            }
        }
        return candidate;
    }
}
