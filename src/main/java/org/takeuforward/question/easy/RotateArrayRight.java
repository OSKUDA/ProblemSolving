package org.takeuforward.question.easy;

import java.util.Arrays;

/**
 * <h2>Rotate Array (Right Rotation)</h2>
 *
 * <p>
 * Given an integer array <code>nums</code>, rotate the array to the right by <code>k</code> steps.
 * After rotation, each element shifts to the right and elements at the end wrap around to the beginning.
 * </p>
 *
 * <p>
 * <b>LeetCode Reference:</b>
 * <a href="https://leetcode.com/problems/rotate-array/" target="_blank">
 * https://leetcode.com/problems/rotate-array/
 * </a>
 * </p>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input  : nums = [1,2,3,4,5,6,7], k = 3
 * Output : [5,6,7,1,2,3,4]
 *
 * Explanation:
 * rotate 1 step -> [7,1,2,3,4,5,6]
 * rotate 2 steps -> [6,7,1,2,3,4,5]
 * rotate 3 steps -> [5,6,7,1,2,3,4]
 * </pre>
 *
 * <hr>
 *
 * <h3>Approach 1: Brute Force Rotation</h3>
 *
 * <ul>
 * <li>Rotate the array one step at a time.</li>
 * <li>For each rotation, move the last element to the front.</li>
 * <li>Repeat this process <code>k</code> times.</li>
 * </ul>
 *
 * <pre>
 * [1,2,3,4,5]
 * rotate once -> [5,1,2,3,4]
 * rotate twice -> [4,5,1,2,3]
 * </pre>
 *
 * <b>Complexity</b>
 *
 * <ul>
 * <li>Time Complexity : O(k * n)</li>
 * <li>Space Complexity : O(1)</li>
 * </ul>
 *
 * <p>
 * This approach is simple but inefficient when <code>k</code> is large.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 2: Extra Array (Index Mapping)</h3>
 *
 * <ul>
 * <li>Create a temporary array.</li>
 * <li>Compute the new index using:</li>
 * </ul>
 *
 * <pre>
 * newIndex = (i + k) % n
 * </pre>
 *
 * <ul>
 * <li>Place each element at its rotated position.</li>
 * </ul>
 *
 * <pre>
 * nums = [1,2,3,4,5]
 * k = 2
 *
 * 1 -> index 2
 * 2 -> index 3
 * 3 -> index 4
 * 4 -> index 0
 * 5 -> index 1
 *
 * result -> [4,5,1,2,3]
 * </pre>
 *
 * <b>Complexity</b>
 *
 * <ul>
 * <li>Time Complexity : O(n)</li>
 * <li>Space Complexity : O(n)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 3: Reverse Algorithm (Optimal & Common Interview Solution)</h3>
 *
 * <p>
 * This method uses the property of reversing segments of the array.
 * </p>
 *
 * <b>Steps</b>
 *
 * <ol>
 * <li>Reverse the entire array.</li>
 * <li>Reverse the first <code>k</code> elements.</li>
 * <li>Reverse the remaining <code>n - k</code> elements.</li>
 * </ol>
 *
 * <pre>
 * nums = [1,2,3,4,5,6,7], k = 3
 *
 * Step 1: reverse entire array
 * [7,6,5,4,3,2,1]
 *
 * Step 2: reverse first k
 * [5,6,7,4,3,2,1]
 *
 * Step 3: reverse remaining
 * [5,6,7,1,2,3,4]
 * </pre>
 *
 * <b>Complexity</b>
 *
 * <ul>
 * <li>Time Complexity : O(n)</li>
 * <li>Space Complexity : O(1)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 4: Juggling Algorithm (Cycle Replacement)</h3>
 *
 * <p>
 * This method rotates the array using cycles determined by the
 * <b>Greatest Common Divisor (GCD)</b>.
 * </p>
 *
 * <p>
 * The number of independent cycles is:
 * </p>
 *
 * <pre>
 * gcd(n, k)
 * </pre>
 *
 * <p>
 * Each cycle shifts elements by <code>k</code> positions.
 * </p>
 *
 * <pre>
 * Example:
 *
 * nums = [1,2,3,4,5,6]
 * k = 2
 *
 * gcd(6,2) = 2 cycles
 *
 * Cycle 1:
 * 1 -> 3 -> 5 -> 1
 *
 * Cycle 2:
 * 2 -> 4 -> 6 -> 2
 * </pre>
 *
 * <b>Complexity</b>
 *
 * <ul>
 * <li>Time Complexity : O(n)</li>
 * <li>Space Complexity : O(1)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Important Optimization</h3>
 *
 * <pre>
 * k = k % n
 * </pre>
 *
 * <p>
 * If <code>k</code> is larger than the array length,
 * extra rotations are redundant.
 * </p>
 *
 * <pre>
 * n = 5, k = 12
 * effective rotation = 12 % 5 = 2
 * </pre>
 *
 * <hr>
 *
 * <h3>Interview Notes</h3>
 *
 * <ul>
 * <li>Always normalize <code>k</code> using <code>k % n</code>.</li>
 * <li>The expected optimal solution is the <b>reverse method</b>.</li>
 * <li>The <b>juggling algorithm</b> is mathematically elegant but less commonly implemented in interviews.</li>
 * <li>Understanding <b>cycle replacement</b> is useful for advanced array manipulation problems.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Comparison of Approaches</h3>
 *
 * <table border="1">
 * <tr>
 * <th>Approach</th>
 * <th>Time</th>
 * <th>Space</th>
 * </tr>
 * <tr>
 * <td>Brute Force Rotation</td>
 * <td>O(k * n)</td>
 * <td>O(1)</td>
 * </tr>
 * <tr>
 * <td>Extra Array</td>
 * <td>O(n)</td>
 * <td>O(n)</td>
 * </tr>
 * <tr>
 * <td>Reverse Algorithm</td>
 * <td>O(n)</td>
 * <td>O(1)</td>
 * </tr>
 * <tr>
 * <td>Juggling Algorithm</td>
 * <td>O(n)</td>
 * <td>O(1)</td>
 * </tr>
 * </table>
 */
public class RotateArrayRight {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int k = 8;
        System.out.println("Input arr : " + Arrays.toString(arr));
        rotate(arr, k);
        System.out.println("After " + k + " rotation : " + Arrays.toString(arr));

        arr = new int[]{64, 25, 12, 22, 11};
        k = 8;
        System.out.println("Input arr : " + Arrays.toString(arr));
        arr = rotate1(arr, k);
        System.out.println("After " + k + " rotation : " + Arrays.toString(arr));

        arr = new int[]{64, 25, 12, 22, 11};
        k = 8;
        System.out.println("Input arr : " + Arrays.toString(arr));
        rotate2(arr, k);
        System.out.println("After " + k + " rotation : " + Arrays.toString(arr));

        arr = new int[]{64, 25, 12, 22, 11};
        System.out.println("Input arr : " + Arrays.toString(arr));
        k = 8;
        rotate3(arr, k);
        System.out.println("After " + k + " rotation : " + Arrays.toString(arr));
    }

    /**
     * Time complexity : O (k * n)
     * Space complexity : O (1)
     */
    public static void rotate(int[] arr, int k) {
        for (int rotate = 0; rotate < k; rotate++) {
            int l = arr[arr.length - 1];
            for (int i = 0; i < arr.length; i++) {
                int temp = arr[i];
                arr[i] = l;
                l = temp;
            }
        }
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int[] rotate1(int[] arr, int k) {
        int[] results = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int rotate = (i + k) % arr.length;
            results[rotate] = arr[i];
        }
        return results;
    }

    /**
     * Step 1 : Reverse the array
     * Step 2 : Compute mid-pointer for partition
     * Step 3 : Reverse two half of the array around partition point
     * Note: It requires double pass
     * Time complexity : O(n)
     * Space complexity : O(1)`
     */
    public static void rotate2(int[] nums, int k) {
        // reverse the array
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            // swap
            swap(nums, start++, end--);
        }

        // partition at k and reverse each side
        start = 0;
        int mid = (k % nums.length) - 1;
        end = nums.length - 1;

        // reverse first half
        while (start < mid) {
            // swap
            swap(nums, start++, mid--);
        }

        mid = (k % nums.length);
        // reverse send half
        while (mid < end) {
            // swap
            swap(nums, mid++, end--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static void rotate3(int[] arr, int k) {
        // handle case when k > arr.length, minimized redundant cycles
        k = k % arr.length;
        // determine number of independent cycles
        int n = gcd(arr.length, k);

        // for each cycle, juggle
        for (int i = 0; i < n; i++) {
            int curr = i;
            int prev = arr[curr];
            do {
                int next = (curr + k) % arr.length;
                int temp = arr[next];
                arr[next] = prev;
                curr = next;
                prev = temp;
            } while (curr != i);
        }
    }

    /**
     * Time complexity : O(log(min(a,b))
     * Space complexity : O(1)
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // when b becomes 0, a is the GCD
    }

}
