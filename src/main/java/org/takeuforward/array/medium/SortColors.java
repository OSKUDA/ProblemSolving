package org.takeuforward.array.medium;

import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        System.out.println("Input arr : " + Arrays.toString(arr));
        sortColors(arr);
        System.out.println("Sorted arr : " + Arrays.toString(arr));
        arr = new int[]{2,0,2,1,1,0};
        sortColors1(arr);
        System.out.println("Sorted arr : " + Arrays.toString(arr));
    }

    /**
     * Time complexity : O(2N) two pass
     * Space complexity : O(1)
     */
    private static void sortColors(int[] arr) {
        int zero = 0, one = 0, two = 0;
        for (int element : arr) {
            if (element == 0)
                zero++;
            else if (element == 1)
                one++;
            else
                two++;
        }
        int curr = 0;
        while (zero != 0) {
            arr[curr++] = 0;
            zero--;
        }
        while (one != 0) {
            arr[curr++] = 1;
            one--;
        }
        while (two != 0) {
            arr[curr++] = 2;
            two--;
        }
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    private static void sortColors1(int[] arr) {
        int left = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, left++, mid++);
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high--);
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
