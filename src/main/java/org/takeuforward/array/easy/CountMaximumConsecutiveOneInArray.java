package org.takeuforward.array.easy;

import java.util.Arrays;

public class CountMaximumConsecutiveOneInArray {
    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 1, 1, 1, 0, 1, 1};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Maximum consecutive ones : " + count(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    private static int count(int[] arr) {
        int result = 0;
        int counter = 0;
        for (int element : arr) {
            if (element == 1) {
                counter++;
                if (counter > result) result = counter;
            } else {
                counter = 0;
            }
        }
        return result;
    }

}
