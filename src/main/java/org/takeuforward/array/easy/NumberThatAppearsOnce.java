package org.takeuforward.array.easy;

import java.util.Arrays;

public class NumberThatAppearsOnce {
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Number that appears once : " + numberThatAppearsOnce(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Logic : xor  = 4 ^ 1 ^ 2 ^ 1 ^ 2
     *              = (4) ^ (1 ^ 1) ^ (2 ^ 2)   // associative property
     *              = (4) ^ 0 ^ 0               // self-inverse property
     *              = 4                         // identity property
     */
    private static int numberThatAppearsOnce(int[] arr) {
        int xor = 0;
        for (int element : arr) {
            xor = xor ^ element;
        }
        return xor;
    }
}
