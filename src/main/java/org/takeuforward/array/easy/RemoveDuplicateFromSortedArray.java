package org.takeuforward.array.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <h2>Remove Duplicates from Sorted Array</h2>
 *
 * <p>
 * Given a <b>sorted</b> integer array, remove duplicates <b>in-place</b>
 * such that each unique element appears only once.
 * Return the count of unique elements.
 * </p>
 *
 * <h3>Key Observation</h3>
 * <ul>
 *   <li>The array is already <b>sorted</b>.</li>
 *   <li>So duplicates always appear <b>next to each other</b>.</li>
 *   <li>This allows a simple <b>two-pointer</b> solution in O(1) extra space.</li>
 * </ul>
 *
 * <h3>Approach 1: Using HashSet</h3>
 * <ul>
 *   <li>Traverse the array and store distinct values in a HashSet.</li>
 *   <li>Overwrite the original array with unique values.</li>
 *   <li>Works, but uses extra memory.</li>
 * </ul>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Time: O(n)</li>
 *   <li>Space: O(n)</li>
 * </ul>
 *
 * <h3>Approach 2: Track Previous Element</h3>
 * <ul>
 *   <li>Maintain a variable <code>prevElement</code> to track the last unique value.</li>
 *   <li>Whenever the current value differs from <code>prevElement</code>, store <code>prevElement</code> in the result position.</li>
 *   <li>Finally, place the last remaining unique value.</li>
 * </ul>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Time: O(n)</li>
 *   <li>Space: O(1)</li>
 * </ul>
 *
 * <h3>Approach 3: Two Pointer Technique (Optimal)</h3>
 * <ul>
 *   <li>Use pointer <code>i</code> to scan the array.</li>
 *   <li>Use pointer <code>curr</code> to place the next unique element.</li>
 *   <li>If <code>nums[i] != nums[i - 1]</code>, it is a new unique value.</li>
 *   <li>Store it at <code>nums[curr]</code> and increment <code>curr</code>.</li>
 * </ul>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Time: O(n)</li>
 *   <li>Space: O(1)</li>
 * </ul>
 *
 * <h3>Why Two Pointers Work</h3>
 * <ul>
 *   <li>Because the array is sorted, equal values are adjacent.</li>
 *   <li>We only need to compare each element with the previous one.</li>
 *   <li>No shifting of entire subarrays is required.</li>
 * </ul>
 *
 * <h3>Example</h3>
 * <pre>
 * Input : [1, 1, 2, 3, 4, 5]
 * Output length: 5
 * Modified array: [1, 2, 3, 4, 5, ...]
 * </pre>
 *
 * <h3>Interview Notes</h3>
 * <ul>
 *   <li>HashSet solution is valid but not optimal because it uses extra space.</li>
 *   <li>The expected interview solution is the <b>two-pointer in-place approach</b>.</li>
 *   <li>Always mention that only the first <code>k</code> elements matter after modification.</li>
 * </ul>
 *
 * <h3>Common Pitfalls</h3>
 * <ul>
 *   <li>Forgetting to handle empty array case.</li>
 *   <li>Comparing against wrong index while overwriting values.</li>
 *   <li>Printing the whole array instead of only the first returned length.</li>
 * </ul>
 */
public class RemoveDuplicateFromSortedArray {
    public static void main(String[] args) {
        int[] arr = {1,1,2,3,4,5};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Distinct integer array size: " + removeDuplicate1(arr));
        System.out.println("Array after removing duplicate : " + Arrays.toString(arr));

        arr = new int[]{1,1,2,3,4,5};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Distinct integer array size: " + removeDuplicate2(arr));
        System.out.println("Array after removing duplicate : " + Arrays.toString(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int removeDuplicate(int[] arr) {
        if (arr.length == 1) return 1;
        HashSet<Integer> distinctIntegers = new HashSet<>();
        int curr = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!distinctIntegers.contains(arr[i])) {
                distinctIntegers.add(arr[i]);
                curr++;
                arr[curr] = arr[i];
            }
        }
        return curr + 1;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static int removeDuplicate1(int[] nums) {
        int curr = -1;
        int prevElement = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prevElement != nums[i]) {
                nums[++curr] = prevElement;
                prevElement = nums[i];
            }
        }
        // insert remaining prevElement
        nums[++curr] = prevElement;
        return curr + 1;
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static int removeDuplicate2(int[] nums) {
        if (nums.length == 0) return 0;
        int curr = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[curr++] = nums[i];
            }
        }
        return curr;
    }

}
