package org.takeuforward.array.easy;


import java.util.HashMap;

public class LongestSubArrayWithGivenSumK {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 1, 9};
        System.out.println("Number of all possible subsets : " + numberOfAllPossibleCombination(arr));
        System.out.println("LongestSubArrayWithGivenSumK : " + longestSubArrayWithGivenSumK(arr, 10));
        System.out.println("LongestSubArrayWithGivenSumK1 : " + longestSubArrayWithGivenSumK1(arr, 10));
        System.out.println("LongestSubArrayWithGivenSumK2 : " + longestSubArrayWithGivenSumK2(arr, 10));
        System.out.println("LongestSubArrayWithGivenSumK3 : " + longestSubArrayWithGivenSumK3(arr, 10));
    }

    /**
     * Time complexity : O(n^3)
     * Space complexity : O(1)
     */
    private static int longestSubArrayWithGivenSumK(int[] arr, int K) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                // sum i..j
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum == K) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    /**
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     */
    private static int longestSubArrayWithGivenSumK1(int[] arr, int K) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            // sum arr[i...j]
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == K) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    /**
     * Time complexity : O(n) in average
     * Space complexity : O(n)
     * Note : if hashing results in lots of collisions, the best case complexity changes to O(n^2)
     * due to O(n) lookup and insert operation on HashMap. However, modern java switches from linked-list
     * to balanced tree (red-black tree) for buckets ensuring O(log(n)) complexity. So, finally O(n*log(n))
     * can also be considered as worse case
     */
    private static int longestSubArrayWithGivenSumK2(int[] arr, int k) {
        int prefixSum = 0, len = 0;
        HashMap<Integer, Integer> memoryBank = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // check if prefixSum equals k
            if (prefixSum == k) {
                len = Math.max(len, i + 1);
            } else if (memoryBank.containsKey(prefixSum - k)) { // check in bank
                int j = memoryBank.get(prefixSum - k);
                len = Math.max(len, i - j);
            }

            // memorize the prefixSum for current index
            if (!memoryBank.containsKey(prefixSum)) {
                memoryBank.put(prefixSum, i);
            }
        }
        return len;
    }

    /**
     * Time complexity : O(2n)
     * Space complexity : O(1)
     * Note : Outer loop ensures, right visits all element. At most, left will visit all element in inner loop.
     * Expanding and shrinking sliding window using 2 pointer approach.
     */
    private static int longestSubArrayWithGivenSumK3(int[] arr, int k) {
        int left = 0; int right = 0; int sum = 0;
        int length = 0;
        while (right < arr.length) {
            sum += arr[right];

            // shrink
            while (sum > k && left <= right) {
                sum -= arr[left++];
            }

            // check
            if (sum == k) length = Math.max(length, right - left + 1);

            // expand
            right++;
        }

        return length;
    }

    private static int numberOfAllPossibleCombination(int[] arr) {
        int sum = 0;
        for (int i = 1; i <= arr.length; i++) {
            sum += numberOfCombination(arr, i);
        }
        return sum;
    }

    private static int numberOfCombination(int[] arr, int k) {
        return calculateFactorial(arr.length) / (calculateFactorial(k) * calculateFactorial(arr.length - k));
    }

    private static int calculateFactorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * calculateFactorial(n - 1);
    }


}
