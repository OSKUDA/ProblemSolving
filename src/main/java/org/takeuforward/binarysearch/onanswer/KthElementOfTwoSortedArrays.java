package org.takeuforward.binarysearch.onanswer;

import java.util.Arrays;

public class KthElementOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k = 5;
        System.out.println("Array1 : " + Arrays.toString(arr1));
        System.out.println("Array2 : " + Arrays.toString(arr2));
        System.out.println(k + "th element is : " + find(arr1, arr2, k));
        System.out.println(k + "th element is : " + find1(arr1, arr2, k));

        arr1 = new int[]{100, 112, 256, 349, 770};
        arr2 = new int[]{72, 86, 113, 119, 265, 445, 892};
        k = 7;
        System.out.println("Array1 : " + Arrays.toString(arr1));
        System.out.println("Array2 : " + Arrays.toString(arr2));
        System.out.println(k + "th element is : " + find(arr1, arr2, k));
        System.out.println(k + "th element is : " + find1(arr1, arr2, k));
    }

    /**
     * Time complexity : O(m + n)
     * Space complexity : O(m + n)
     */
    public static int find(int[] arr1, int[] arr2, int k) {
        int left = 0;
        int right = 0;
        int curr = 0;
        int[] result = new int[arr1.length + arr2.length];
        while (left < arr1.length && right < arr2.length) {
            if (arr1[left] < arr2[right]) {
                result[curr++] = arr1[left++];
            } else {
                result[curr++] = arr2[right++];
            }
        }

        // remaining left
        while (left < arr1.length) {
            result[curr++] = arr1[left++];
        }
        // remaining right
        while (right < arr2.length) {
            result[curr++] = arr2[right++];
        }

        return result[k - 1];
    }

    /**
     * Time complexity : O(m + n)
     * Space complexity : O(1)
     */
    public static int find1(int[] arr1, int[] arr2, int k) {
        int left = 0;
        int right = 0;
        int count = 0;

        while (left < arr1.length && right < arr2.length) {
            int val;
            if (arr1[left] < arr2[right]) {
                val = arr1[left++];
            } else {
                val = arr2[right++];
            }
            if (count == k - 1) return val;
            count++;
        }

        return -1;
    }
}
