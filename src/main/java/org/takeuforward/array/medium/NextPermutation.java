package org.takeuforward.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NextPermutation {
    public static void main(String[] args) {
        String data = "ABC";
        System.out.println(findPermutations(data));

        int[] arr = {7, 3, 2, 5, 0};
        List<int[]> results = findPermutation(arr);
        results.forEach(
                result -> System.out.println(Arrays.toString(result))
        );

        arr = new int[]{7, 3, 2, 5, 0};
        System.out.println("next permutation : "  + Arrays.toString(findNextPermutation(arr)));

        arr = new int[]{7, 3, 2, 5, 0};;
        System.out.println("next permutation : " + Arrays.toString(findNextPermutation1(arr)));
    }

    /**
     * Time complexity : O(3N) 3 - pass
     * Space complexity : O(1)
     */
    private static int[] findNextPermutation1(int[] arr) {
        // 1. identify breaking point
        int breakPoint = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                breakPoint = i;
                break;
            }
        }

        // 2. if breakpoint is not found, reverse the whole array
        if (breakPoint == -1) {
            int start = 0;
            int end = arr.length - 1;
            while (start <= end) {
                swap(arr, start++, end--);
            }
        } else {
            // 3. find 2nd largest and swap
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] > arr[breakPoint]) {
                    swap(arr, i, breakPoint);
                    break;
                }
            }

            // 4. reverse the right half
            int end = arr.length - 1;
            int start = breakPoint + 1;
            while (start <= end) {
                swap(arr, start++, end--);
            }
        }
        return arr;
    }

    private static int[] findNextPermutation(int[] arr) {
        List<int[]> permutations = findPermutation(arr);

        AtomicInteger foundIndex = new AtomicInteger(-1);
        permutations.forEach(
                permutation -> {
                    if(Arrays.compare(arr, permutation) == 0) {
                        foundIndex.set(permutations.indexOf(permutation));
                    }
                }
        );
        if (foundIndex.get() + 1 >= permutations.size()) {
            foundIndex.set(-1);
        }
        return permutations.get(foundIndex.get() + 1);
    }

    private static List<int[]> findPermutation(int[] arr) {

        // store the final answer
        ArrayList<int[]> ans = new ArrayList<>();

        recurPermute(0, arr, ans);

        // sort lexicographically
        ans.sort((a, b) -> {
            int len = Math.min(a.length, b.length);
            for (int i = 0; i < len; i++) {
                if (a[i] != b[i]) {
                    return Integer.compare(a[i], b[i]);
                }
            }
            return Integer.compare(a.length, b.length);
        });

        return ans;
    }

    /**
     * Time complexity : O(n * n!)
     * Space complexity : O(n * n!)
     */
    private static void recurPermute(int index, int[] arr, List<int[]> ans) {

        // base case
        if (index == arr.length) {
            ans.add(Arrays.copyOf(arr, arr.length));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            // swap the current index with all
            // possible indices and recur
            swap(arr, index, i);
            recurPermute(index + 1, arr, ans);
            swap(arr, index, i);
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * Recurrence relation : f(n) = n * (f(n-1) + O(1)) + O(n) => n * f(n - 1) + O(n)
     * Time complexity : O(n * n!)
     * Space complexity : O(n * n!)
     */
    private static List<String> findPermutations(String s) {

        // store the final answer
        List<String> ans = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(s);

        recurPermute(0, stringBuilder, ans);

        Collections.sort(ans);
        return ans;
    }

    private static void recurPermute(int index, StringBuilder stringBuilder, List<String> ans) { // f(n)

        // base case
        if (index == stringBuilder.length()) {
            ans.add(stringBuilder.toString());          // n
            return;
        }

        // swap the current index with all
        // possible indices and recur
        for (int i = index; i < stringBuilder.length(); i++) {  // n
            swap(stringBuilder, index, i);                              // 1
            recurPermute(index + 1, stringBuilder, ans);          // f(n-1)
            swap(stringBuilder, index, i);                              // 1
        }
    }

    private static void swap(StringBuilder stringBuilder, int i, int j) {
        char temp = stringBuilder.charAt(i);
        stringBuilder.setCharAt(i, stringBuilder.charAt(j));
        stringBuilder.setCharAt(j, temp);
    }


}
