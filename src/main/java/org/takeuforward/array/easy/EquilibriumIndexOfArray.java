package org.takeuforward.array.easy;

import java.util.Arrays;

public class EquilibriumIndexOfArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3 , 2 ,1};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Equilibrium index : " + equilibriumIndexOfArray1(arr));
        System.out.println("Equilibrium index : " + equilibriumIndexOfArray(arr));
    }

    /**
     * Time complexity : O(n) 2 - pass
     * Space complexity : O(1)
     */
    private static int equilibriumIndexOfArray(int[] arr){
        if (arr.length <= 1) return -1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        for (int i = 1; i < arr.length - 1; i++) {
            // left sum = right sum
            if (arr[i - 1] == (arr[arr.length - 1] - arr[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Time complexity : O(n) 2-pass
     * Space complexity : O(1)
     */
    private static int equilibriumIndexOfArray1(int[] arr) {
        int leftSum = 0;
        int rightSum = 0;
        // calculate rightSum
        for (int i = 0; i < arr.length; i++) {
            rightSum += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            rightSum = rightSum - arr[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += arr[i];
        }
        return -1;
    }
}
