package org.takeuforward.array.easy;

import java.util.Arrays;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        System.out.println("Input arr : " + Arrays.toString(arr));
        moveZeroes(arr);
        System.out.println("Output arr : " + Arrays.toString(arr));

        arr = new int[]{0,1,0,3,12};
        System.out.println("Input arr : " + Arrays.toString(arr));
        moveZeroes1(arr);
        System.out.println("Output arr : " + Arrays.toString(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static void moveZeroes(int[] arr) {
        int firstZero = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && firstZero == -1) {
                firstZero = i;
            } else if (arr[i] != 0 && firstZero < i && firstZero != -1) {
                swap(arr, firstZero, i);
                firstZero++;
            }
        }
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static void moveZeroes1(int[] arr) {
        int firstZero = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[firstZero] != 0) firstZero++;
            if (arr[firstZero] == 0 && arr[i] != 0) {
                arr[firstZero++] = arr[i];
                arr[i] = 0;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
