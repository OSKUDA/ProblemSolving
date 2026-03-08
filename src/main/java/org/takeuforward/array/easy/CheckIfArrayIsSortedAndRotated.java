package org.takeuforward.array.easy;

import java.util.Arrays;

public class CheckIfArrayIsSortedAndRotated {
    public static void main(String[] args) {
        int[] arr = {5,2,3,4,1};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Is array sorted and rotated? : " + check(arr));
    }

    public static boolean check(int[] arr) {
        int breakPoint = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) breakPoint++;
        }

        if (breakPoint == 0) return true;
        if (breakPoint == 1) {
            if (arr[0] < arr[arr.length - 1]) return false;
            return true;
        }
        return false;
    }
}
