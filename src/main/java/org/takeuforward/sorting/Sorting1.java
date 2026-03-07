package org.takeuforward.sorting;

import java.util.Arrays;

public class Sorting1 {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);

        arr = new int[]{64, 25, 12, 22, 11};
        bubbleSort(arr);

        arr = new int[]{64, 25, 12, 22, 11};
        insertionSort(arr);
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public static void selectionSort(int[] arr) {
        System.out.println("input arr: " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] <= arr[minIndex]) {
                    minIndex = j;
                }
            }
            // swap
            int curr = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = curr;
        }
        System.out.println("sorted arr: " + Arrays.toString(arr));
    }

    /**
     * Time complexity: O(n^2)
     * Best case: O(n)
     * Space complexity: O(1)
     */
    public static void bubbleSort(int[] arr) {
        System.out.println("input arr: " + Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSwap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    isSwap = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!isSwap) {
                System.out.println("No swap. Early exit at i index: " + i);
                return;
            }
        }
        System.out.println("sorted arr: " + Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        System.out.println("input arr: " + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int curr = i;

            // shift elements to the right
            while (curr > 0 && temp < arr[curr - 1]) {
                arr[curr] = arr[curr - 1];
                curr--;
            }

            // insert element at correct position
            arr[curr] = temp;
        }

        System.out.println("sorted arr: " + Arrays.toString(arr));
    }
}
