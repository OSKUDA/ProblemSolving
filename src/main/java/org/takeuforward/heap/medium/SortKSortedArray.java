package org.takeuforward.heap.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <h2>Sort a K-Sorted (Nearly Sorted) Array</h2>
 *
 * <p>
 * In a <b>k-sorted array</b>, every element is at most <b>k positions away</b>
 * from its correct position in the fully sorted array.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * Sorted Array:
 * [2,3,5,6,8,9,10]
 *
 * K-sorted Array (k = 3):
 * [6,5,3,2,8,10,9]
 * </pre>
 *
 * Each element may move at most <b>k positions left or right</b>.
 *
 * <hr>
 *
 * <h3>Key Observation</h3>
 *
 * <p>
 * The correct element for index <b>i</b> must be within the range:
 * </p>
 *
 * <pre>
 * [i, i + k]
 * </pre>
 *
 * Therefore, the smallest element among the next <b>k + 1</b> elements
 * must belong at position <b>i</b>.
 *
 * <p>
 * This allows us to use a <b>Min Heap of size k + 1</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 1 — Brute Force (Merge Sort)</h3>
 *
 * <p>
 * Simply sort the entire array using a general-purpose sorting algorithm
 * such as <b>Merge Sort</b>.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Apply merge sort on the array.</li>
 *     <li>The array becomes fully sorted.</li>
 * </ol>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Time:</b> O(n log n)</li>
 *     <li><b>Space:</b> O(n)</li>
 * </ul>
 *
 * <p>
 * This ignores the special <b>k-sorted property</b>, so it is not optimal.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 2 — Min Heap (Optimal)</h3>
 *
 * <p>
 * Since the correct element for index <b>i</b> lies within the next
 * <b>k + 1 elements</b>, we maintain a <b>Min Heap of size k + 1</b>.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Insert the first <b>k + 1</b> elements into the Min Heap.</li>
 *     <li>For each remaining element:
 *         <ul>
 *             <li>Remove the minimum element from the heap.</li>
 *             <li>Place it at the current array position.</li>
 *             <li>Add the next array element to the heap.</li>
 *         </ul>
 *     </li>
 *     <li>After processing all elements, remove the remaining heap elements.</li>
 * </ol>
 *
 * <h4>Example</h4>
 *
 * <pre>
 * Input:
 * [6,5,3,2,8,10,9], k = 3
 *
 * Heap initially:
 * [6,5,3,2]
 *
 * Output after processing:
 * [2,3,5,6,8,9,10]
 * </pre>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Heap size:</b> k + 1</li>
 *     <li><b>Insert:</b> O(log k)</li>
 *     <li><b>Remove:</b> O(log k)</li>
 * </ul>
 *
 * <p>
 * Total complexity:
 * </p>
 *
 * <pre>
 * Time  : O(n log k)
 * Space : O(k)
 * </pre>
 *
 * <p>
 * This is significantly faster than full sorting when <b>k ≪ n</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Why Heap Size is k + 1</h3>
 *
 * <p>
 * The correct element for index <b>i</b> could be anywhere between:
 * </p>
 *
 * <pre>
 * i → i + k
 * </pre>
 *
 * Therefore we must consider:
 *
 * <pre>
 * k + 1 elements
 * </pre>
 *
 * <hr>
 *
 * <h3>Comparison of Approaches</h3>
 *
 * <table border="1">
 * <tr>
 *     <th>Approach</th>
 *     <th>Time Complexity</th>
 *     <th>Space</th>
 * </tr>
 * <tr>
 *     <td>Merge Sort</td>
 *     <td>O(n log n)</td>
 *     <td>O(n)</td>
 * </tr>
 * <tr>
 *     <td>Min Heap</td>
 *     <td>O(n log k)</td>
 *     <td>O(k)</td>
 * </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>When is Heap Much Better?</h3>
 *
 * <p>
 * If:
 * </p>
 *
 * <pre>
 * k << n
 * </pre>
 *
 * Example:
 *
 * <pre>
 * n = 1,000,000
 * k = 10
 *
 * Heap solution:
 * O(n log 10)
 *
 * vs
 *
 * Merge sort:
 * O(n log n)
 * </pre>
 *
 * Heap is significantly faster.
 *
 * <hr>
 *
 * <h3>Interview Takeaways</h3>
 *
 * <ul>
 *     <li>This is a classic <b>Heap pattern problem</b>.</li>
 *     <li>Recognize problems involving:
 *         <ul>
 *             <li>Nearly sorted arrays</li>
 *             <li>Top K elements</li>
 *             <li>Streaming smallest/largest values</li>
 *         </ul>
 *     </li>
 *     <li>The key insight is using a <b>Min Heap of size k + 1</b>.</li>
 * </ul>
 *
 */
public class SortKSortedArray {
    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;
        System.out.println("Input : " + Arrays.toString(arr));
        sort(arr, k);
        System.out.println("Output : " + Arrays.toString(arr));

        arr = new int[]{6, 5, 3, 2, 8, 10, 9};
        k = 3;
        System.out.println("Input : " + Arrays.toString(arr));
        sort1(arr, k);
        System.out.println("Output : " + Arrays.toString(arr));

        arr = new int[]{6, 5, 3, 2, 8, 10, 9};
        k = 3;
        System.out.println("Input : " + Arrays.toString(arr));
        sort2(arr, k);
        System.out.println("Output : " + Arrays.toString(arr));
    }

    /**
     * Time complexity : O(n * log(n))
     * Space complexity : O(n)
     */
    private static void sort(int[] arr, int k) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Time complexity : O(n * (log(k) + log(k)) -> O(n * log(k))
     * Space complexity : O(k + 1) -> O(k)
     */
    private static void sort1(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int curr = 0;
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k + 1) {
                pq.offer(arr[i]);
            } else {
                arr[curr++] = pq.poll();
                pq.offer(arr[i]);
            }
        }
        // populate rest of the element from pq
        while (!pq.isEmpty()) {
            arr[curr++] = pq.poll();
        }
    }

    /**
     * Time complexity : O(n * (log(k) + log(k)) -> O(n * log(k))
     * Space complexity : O(k + 1) -> O(k)
     * Note : Same but readable
     */
    private static void sort2(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int curr = 0;

        for (int i = 0; i <= k; i++) {
            pq.offer(arr[i]);
        }

        for (int i = k + 1; i < arr.length; i++) {
            arr[curr++] = pq.poll();
            pq.offer(arr[i]);
        }

        // populate rest of the element from pq
        while (!pq.isEmpty()) {
            arr[curr++] = pq.poll();
        }
    }

    /**
     * Time complexity : O(n * log(n))
     * Space complexity : O(n)
     */
    private static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + ((high - low) / 2);
        // left partition
        mergeSort(arr, low, mid);
        // right partition
        mergeSort(arr, mid + 1, high);

        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] result = new int[arr.length];
        int curr = low;
        int left = low;
        int right = mid + 1;

        while (left <= mid && right <= high) {
            if (arr[left] <  arr[right]) {
                result[curr++] = arr[left++];
            } else {
                result[curr++] = arr[right++];
            }
        }
        // remaining left
        while (left <= mid) {
            result[curr++] = arr[left++];
        }
        // remaining right
        while (right <= high) {
            result[curr++] = arr[right++];
        }
        // copy result to main arr
        for (int i = low; i <= high; i++) {
            arr[i] = result[i];
        }
    }

}
