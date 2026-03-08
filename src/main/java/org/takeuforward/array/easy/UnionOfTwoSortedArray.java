package org.takeuforward.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class UnionOfTwoSortedArray {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 3, 4, 4, 5, 7};

        System.out.println("Input arr1 : " + Arrays.toString(arr1));
        System.out.println("Input arr2 : " + Arrays.toString(arr2));
        System.out.println("Output arr : " + Arrays.toString(union2(arr1, arr2)));
    }

    public static ArrayList<Integer> union(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int e : arr1) {
            set.add(e);
        }
        for (int e : arr2) {
            set.add(e);
        }
        for (int e : set) {
            result.add(e);
        }
        return result;
    }

    /**
     * Time complexity : O(n+m)
     * Space complexity : O(n+m)
     */
    public static int[] union2(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = -1;
        int[] result = new int[arr1.length + arr2.length];

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                // i is smaller or equal
                if (k > -1 && result[k] == arr1[i]) {
                    // element already exists
                    i++;
                } else {
                    // insert to result, increment k, i
                    result[++k] = arr1[i++];
                }
            } else {
                // j is smaller
                if (k > -1 && result[k] == arr2[j]) {
                    // element already exists
                    j++;
                } else {
                    // insert to result, increment k, j
                    result[++k] = arr2[j++];
                }
            }
        }

        // exhaust arr1
        while (i < arr1.length) {
            if (k > -1 && result[k] == arr1[i]) {
                // element already exists
                i++;
            } else {
                // insert to result, increment k, i
                result[++k] = arr1[i++];
            }
        }

        // exhaust arr2
        while (j < arr2.length) {
            if (k > -1 && result[k] == arr2[j]) {
                // element already exists
                j++;
            } else {
                // insert to result, increment k, j
                result[++k] = arr2[j++];
            }
        }
        return result;
    }
}
