package org.takeuforward.array.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RearrangeArrayElementBySign {
    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -5, 2, -4};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Rearranged arr : " + Arrays.toString(rearrange(arr)));
        arr = new int[]{3, 1, -2, -5, 2, -4};
        System.out.println("Rearranged arr : " + Arrays.toString(rearrange1(arr)));
        arr = new int[]{3, 1, -2, -5, 2, -4};
        System.out.println("Rearranged arr : " + Arrays.toString(rearrange2(arr)));
    }

    /**
     * Time complexity : O(2N) 2 pass
     * Space complexity : O(N) N/2 positive + N/2 negative
     */
    private static int[] rearrange(int[] arr) {

        Queue<Integer> positiveQueue = new LinkedList<>();
        Queue<Integer> negativeQueue = new LinkedList<>();

        for (int e : arr) {
            if (e < 0) {
                negativeQueue.add(e);
            } else {
                positiveQueue.add(e);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = positiveQueue.remove();
            } else {
                arr[i] = negativeQueue.remove();
            }
        }
        return arr;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static int[] rearrange1(int[] arr) {
        int[] result = new int[arr.length];
        int p = -1, n = -1;
        boolean fetchPositive = true;
        for (int i = 0; i < arr.length; i++) {
            if (fetchPositive) {
                p = nextPositive(arr, p);
                result[i] = arr[p];
            } else {
                n = nextNegative(arr, n);
                result[i] = arr[n];
            }
            fetchPositive = !fetchPositive;
        }
        return result;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static int[] rearrange2 (int[] arr) {
        int[] result = new int[arr.length];
        int p = 0, n = 1;
        for (int j : arr) {
            if (j < 0) {
                result[n] = j;
                n += 2;
            } else {
                result[p] = j;
                p += 2;
            }
        }
        return result;
    }

    private static int nextPositive(int[] arr, int p) {
        while (p + 1 < arr.length) {
            if (arr[++p] >= 0) {
                return p;
            }
        }
        return 0;
    }

    private static int nextNegative(int[] arr, int n) {
        while (n + 1 < arr.length) {
            if (arr[++n] < 0) {
                return n;
            }
        }
        return 0;
    }
}
