package org.takeuforward.array.medium;

import java.util.ArrayList;

public class LeadersInAnArray {
    public static void main(String[] args) {
        int[] arr = {10, 22, 12, 3, 0, 6};
        System.out.println(leadersInArray(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    private static ArrayList<Integer> leadersInArray(int[] arr) {
        int maxElement = Integer.MIN_VALUE;
        ArrayList<Integer> leaders = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= maxElement) {
                leaders.add(arr[i]);
            }
            maxElement = Math.max(arr[i], maxElement);
        }
        return leaders;
    }

}
