package org.takeuforward.array.easy;

import java.util.Arrays;

public class MissingNumber {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Missing number : " + missingNumber(arr));
        arr = new int[]{0, 1, 2, 3, 4};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Missing number : " + missingNumber1(arr));
    }


    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static int missingNumber(int[] arr) {
        // compute the sum of natural number up to arr.length
        int sum = (arr.length * (arr.length + 1)) / 2;
        for (int integer : arr) {
            sum = sum - integer;
        }
        return sum;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Logic :
     *         xor1 = (arr[0] ^ arr[1] ^ .... ^ arr[arr.length - 1]) => (0 ^ 1 ^ 2 ^ 3 ^ 4)
     *         xor2 = (0 ^ 1 ^ 2 ^ ..... ^ arr.length) => (0 ^ 1 ^ 2 ^ 3 ^ 4 ^ 5)
     *         missing number = xor1 ^ xor2
     *                        = (0 ^ 1 ^ 2 ^ 3 ^ 4) ^ (0 ^ 1 ^ 2 ^ 3 ^ 4 ^ 5)
     *                        = (0 ^ 0) ^ (1 ^ 1) ^ (2 ^ 2) ^ (3 ^ 3) ^ (4 ^ 4) ^ (5)   // associative property
     *                        = (0) ^ (0) ^ (0) ^ (0) ^ (0) ^ (5)                       // self-inverse property
     *                        = (0 ^ 0 ^ 0 ^ 0 ^ 0) ^ (5)                               // associative property
     *                        = (0) ^ (5)                                               // self-inverse property
     *                        = (0 ^ 5)                                                 // associative property
     *                        = (5)                                                     // identity element property
     */
    public static int missingNumber1(int[] arr) {
        int xor1 = 0;
        int xor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            xor1 = xor1 ^ arr[i];
            xor2 = xor2 ^ i;
        }
        // consider arr.length in xor2
        xor2 = xor2 ^ arr.length;
        return xor1 ^ xor2;
    }
}
