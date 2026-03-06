package org.takeuforward.basic.math;
import java.util.ArrayList;

public class BasicMathQuestions {

    public static void main(String[] args) {
        countDigits();
        countDigitsOptimal();
        reverseDigitsOfaNumber();
        checkPalindrome();
        findGCF();
        System.out.println(findGCFEuclidean(2, 9));
        findGCFEuclideanOptimal();
        isArmstrongNumber();
        fetchAllDivisors();
        fetchAllDivisorsOptimal();
        isPrime();
        printSumOfNNaturalNumbers();
    }

    /**
     * Brute force approach
     * O(log10(N))
     */
    public static void countDigits() {
        int n = 501;
        int count = 0;
        while (n > 0) {
            n = n / 10;
            count++;
        }
        System.out.println("For given input " + n + " count of digit is: " + count);
    }

    /**
     * Optimal solution
     * Uses log10(n) + 1 to calculate the number of digits in given number n
     * General formula => 10^(d-1) <= n < 10^d, apply log on both side gives d <= log10(n) + 1
     * O(1)
     */
    public static void countDigitsOptimal() {
        int n = 50112311;
        int d = (int) Math.log10(n) + 1;
        System.out.println("For given input " + n + " count of digit is: " + d);
    }

    /**
     * Optimal solution
     * O(log10(n) + 1)
     */
    public static void reverseDigitsOfaNumber() {
        int n = 12345;
        int result = 0;
        int currentPlace = 10;
        while (n > 0) {
            result = (result * currentPlace) + (n % 10);
            n /= 10;
        }
        System.out.println(result);
    }

    /**
     * Optimal solution
     * O(log10(n) + 1)
     */
    public static void checkPalindrome() {
        int n = 4004;
        int original = n;
        int result = 0;
        while (n > 0) {
            result = (result * 10) + (n % 10);
            n /= 10;
        }
        if (result == original) {
            System.out.println("Palindrome!");
        } else {
            System.out.println("Not palindrome!");
        }
    }

    /**
     * O(min(n1,n2))
     * Number of iteration : (min/2) - 2 - 1 => (min/2 - 1)
     */
    public static void findGCF() {
        int n1 = 9;
        int n2 = 12;

        int min = Math.min(n1, n2);
        int max = Math.max(n1, n2);
        int result = 1;

        // check if lower number is our gcf
        if (max % min == 0) {
            System.out.println("GCF is " + min);
            return;
        }

        for (int i = 2; i <= min / 2; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                result = Math.max(result, i);
            }
        }
        System.out.println("GCF is " + result);
    }

    /**
     * Time complexity O(log(min(n,m))
     * Space complexity o(log(min(n,m))
     */
    public static int findGCFEuclidean(int n, int m) {
        int remainder = n % m;
        if (remainder == 0) {
            return m;
        }
        return findGCFEuclidean(m, remainder);
    }

    public static void findGCFEuclideanOptimal() {
        int a = 2;
        int b = 9;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println("GCF is : " + a);
    }

    /**
     * Time complexity: O(log10(n) + 1)
     */
    public static void isArmstrongNumber() {
        int n = 153;

        // find number of digits
        int d = (int) (Math.log10(n) + 1);
        int result = 0;

        int temp = n;
        while (temp > 0) {
            int value = temp % 10;
            result += (int) Math.pow(value, d);
            temp = temp / 10;
        }

        if (result == n) {
            System.out.println("is a armstrong number");
        } else {
            System.out.println("is not an armstrong number");
        }
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static void fetchAllDivisors() {
        int n = 36;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) result.add(i);
        }
        result.add(n);

        System.out.println(result);
    }

    /**
     * Time complexity: O(sqrt(n))
     * Space complexity: O(2*sqrt(n))
     */
    public static void fetchAllDivisorsOptimal() {
        int n = 36;
        ArrayList<Integer> result = new ArrayList<>();

        int sqrtN = (int) Math.sqrt(36);

        for (int i = 1; i <= sqrtN; i++) {
            if (n % i == 0) {
                result.add(i);
                if (i != n / i) {
                    result.add(n / i);
                }
            }
        }
        System.out.println(result);
    }

    /**
     * Time complexity: O(sqrt(n))
     * Space complexity: O(1)
     */
    public static void isPrime() {
        int n = 11;
        int sqrtN = (int) Math.sqrt(n);
        boolean isPrime = true;
        for (int i = 2; i <= sqrtN; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            System.out.println("It is a prime!");
        } else {
            System.out.println("It is not a prime!");
        }
    }

    public static void printSumOfNNaturalNumbers() {
        int n = 10;
        int sum = (n * (n + 1))/2;
        System.out.println("Sum of first "+n+" Natural numbers is: "+sum);
    }
}
