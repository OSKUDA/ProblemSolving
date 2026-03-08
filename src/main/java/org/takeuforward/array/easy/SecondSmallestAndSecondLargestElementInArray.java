package org.takeuforward.array.easy;

import java.util.Arrays;

/**
 * Time complexity : O(2N)
 * Space complexity : O(1)
 */
public class SecondSmallestAndSecondLargestElementInArray {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Input arr : " + Arrays.toString(arr));

        int secondSmallestElementInArray = findSecondSmallestElementInArray(arr);
        int secondLargestElementInArray = findSecondLargestElementInArray(arr);
        System.out.println("Second smallest : " + secondSmallestElementInArray);
        System.out.println("Second largest : " + secondLargestElementInArray);
    }

    public static int findSecondLargestElementInArray(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static int findSecondSmallestElementInArray(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] > smallest && arr[i] < secondSmallest) {
                secondSmallest = arr[i];
            }
        }
        return secondSmallest;
    }
}
