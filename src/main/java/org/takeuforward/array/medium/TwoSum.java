package org.takeuforward.array.medium;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int k = 18;
        System.out.println("Input arr : " + Arrays.toString(arr));
        twoSum(arr, k);
        twoSum1(arr, k);
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    private static void twoSum(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i != j) {
                    if (arr[i] + arr[j] == k) {
                        System.out.println("TwoSum : " + arr[i] + " + " + arr[j] + " = " + k);
                        return;
                    }
                }
            }
        }
        System.out.println("NotFound");
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static void twoSum1(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = k - arr[i];
            if (map.containsKey(arr[i])){
                System.out.println("TwoSum : " + arr[i] + " + " + arr[map.get(arr[i])] + " = " + k);
            } else {
                map.put(complement, i);
            }
        }
        System.out.println("NotFound");
    }


}
