package org.takeuforward.basic.recursion;

import java.util.Arrays;

public class BasicRecursion {
    public static void main(String[] args) {
        printNameNTimes(0, 5, "Oskar");
        print1ToN(1, 5);
        print1ToNBacktracking(5, 5);
        printNTo1(5, 5);
        printNTo1Backtracking(1, 5);
        printSumOfNNaturalNumbers(10, 0);
        System.out.println("Sum of N natural numbers is: " + printSumOfNNaturalNumbersFunctional(10));
        System.out.println("Factorial of N is: " + printFactorialOfANumber(3));

        int[] arr = {1,2,3,4,5};
        int[] result = new int[arr.length];
        reverseArray(0, result.length - 1, arr, result);
        System.out.println(Arrays.toString(result));

        reverseArrayInPlace(0, arr.length - 1, arr);
        System.out.println(Arrays.toString(arr));

        String s = "ABCDCBA";
        System.out.println("isPalindrome: "+isPalindrome(0, s.length() - 1, s));

        printFibonacciNumbers();

        System.out.println("5th Fibonacci number: "+printNthFibonacciNumber(5));

        int n = 5;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        System.out.println(n + "th Fibonacci number : " + printNthFibonacciNumberMemoization(n, memo));
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void printNameNTimes(int count, int n, String name) {
        if (count >= n) {
            return;
        }
        System.out.println(name);
        printNameNTimes(++count, n, name);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void print1ToN(int count, int n) {
        if (count >= n) {
            return;
        }
        System.out.println(count++);
        print1ToN(count, n);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void print1ToNBacktracking(int count, int n) {
        if (count < 1) {
            return;
        }
        print1ToNBacktracking(count - 1, n);
        System.out.println(count);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void printNTo1(int count, int n) {
        if (count < 1) {
            return;
        }
        System.out.println(count);
        printNTo1(--count, n);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void printNTo1Backtracking(int count, int n) {
        if (count > n) {
            return;
        }
        printNTo1Backtracking(count + 1, n);
        System.out.println(count);
    }

    /**
     * Parameterized approach
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void printSumOfNNaturalNumbers(int n, int sum) {
        if (n < 1) {
            System.out.println("Sum of N natural number is: " + sum);
            return;
        }
        printSumOfNNaturalNumbers(n - 1, sum+=n);
    }

    /**
     * Functional approach
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int printSumOfNNaturalNumbersFunctional(int n) {
        if (n <= 1) {
            return n;
        }
        return n + printSumOfNNaturalNumbersFunctional(n - 1);
    }

    /**
     * Functional approach
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int printFactorialOfANumber(int n) {
        if (n == 0) {
            return 1;
        }
        return n * printFactorialOfANumber(n - 1);
    }

    /**
     * Parameterized approach
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void reverseArray(int curr, int i, int[] arr, int[] result) {
        if (curr >= arr.length) {
            return;
        }
        result[i] = arr[curr];
        reverseArray(++curr, --i, arr, result);
    }

    /**
     * Parameterized approach, similar to two pointer swap
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void reverseArrayInPlace(int start, int end, int[] arr) {
        if (start > end) {
            return;
        }
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArrayInPlace(++start, --end, arr);
    }

    /**
     * Time complexity: O(len(s))
     * Space complexity: O(len(s))
     */
    public static boolean isPalindrome(int start, int end, String s) {
        if (start >= end) {
            return true;
        }
        if (s.charAt(start) == s.charAt(end)) {
            return isPalindrome(++start, --end, s);
        } else {
            return false;
        }
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static void printFibonacciNumbers() {
        int n = 50;
        int a = 0;
        int b = 1;
        System.out.println();
        System.out.print(a + ", " + b + ", ");
        for (int i = 2; i < n; i++) {
            int c = a + b;
            System.out.print(c + ", ");
            a = b;
            b = c;
        }
        System.out.println();
    }

    /**
     * Time complexity: O(2^n)
     * Space complexity: O(n)
     * @return
     */
    public static int printNthFibonacciNumber(int n) {
        if (n <= 1) {
            return n;
        }
        return printNthFibonacciNumber(n - 1) + printNthFibonacciNumber(n - 2);
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int printNthFibonacciNumberMemoization(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }

        // already calculated
        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = printNthFibonacciNumberMemoization(n - 2, memo) + printNthFibonacciNumberMemoization(n - 1, memo);

        return memo[n];
    }

}
