package org.takeuforward.heap.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <h2>Kth Largest Element in an Array</h2>
 *
 * <p>
 * LeetCode Problem:
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * </a>
 * </p>
 *
 * <h3>Problem Statement</h3>
 * <p>
 * Given an integer array <b>nums</b> and an integer <b>k</b>, return the
 * <b>kth largest element</b> in the array.
 * </p>
 *
 * <ul>
 *     <li>The kth largest element is the element that would appear at position
 *     <b>k</b> if the array were sorted in descending order.</li>
 *     <li>This is <b>NOT</b> the kth distinct element.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Key Observations</h3>
 *
 * <ul>
 *     <li>Sorting the array would solve the problem easily.</li>
 *     <li>But sorting takes <b>O(n log n)</b>, which is unnecessary.</li>
 *     <li>Better solutions use:
 *         <ul>
 *             <li>Heap (Priority Queue)</li>
 *             <li>QuickSelect (optimal expected solution)</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 1 — Max Heap (Heap Sort Idea)</h3>
 *
 * <p>
 * Build a <b>Max Heap</b> from the array. The root always contains the largest element.
 * Removing the root repeatedly gives elements in descending order.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Convert the array into a <b>Max Heap</b>.</li>
 *     <li>Remove the root element <b>k</b> times.</li>
 *     <li>The kth removed element is the answer.</li>
 * </ol>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Heap Build:</b> O(n)</li>
 *     <li><b>Remove k elements:</b> O(k log n)</li>
 *     <li><b>Total:</b> O(n + k log n)</li>
 *     <li><b>Space:</b> O(1) (in-place heap)</li>
 * </ul>
 *
 * <h4>Intuition</h4>
 *
 * <p>
 * The largest element is always at the root.
 * Removing the root repeatedly gives the next largest elements.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 2 — Min Heap of Size K (Better Heap Approach)</h3>
 *
 * <p>
 * Maintain a <b>Min Heap</b> that keeps track of the <b>k largest elements</b>.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Create a <b>Min Heap</b>.</li>
 *     <li>Insert elements until heap size becomes <b>k</b>.</li>
 *     <li>For every new element:
 *         <ul>
 *             <li>If element &gt; heap root → remove root and insert element.</li>
 *             <li>Otherwise ignore it.</li>
 *         </ul>
 *     </li>
 *     <li>The heap root always contains the <b>kth largest element</b>.</li>
 * </ol>
 *
 * <h4>Why This Works</h4>
 *
 * <p>
 * The heap stores only the <b>k largest elements seen so far</b>.
 * The smallest among them (heap root) is the kth largest element.
 * </p>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Insertion:</b> O(log k)</li>
 *     <li><b>Processing n elements:</b> O(n log k)</li>
 *     <li><b>Space:</b> O(k)</li>
 * </ul>
 *
 * <h4>Why This Is Efficient</h4>
 *
 * <p>
 * Instead of maintaining the entire heap of size <b>n</b>,
 * we maintain only <b>k elements</b>.
 * This significantly reduces operations when <b>k &lt;&lt; n</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 3 — PriorityQueue (Java Built-in Heap)</h3>
 *
 * <p>
 * Java's <b>PriorityQueue</b> is a <b>Min Heap by default</b>.
 * It can directly implement the previous approach.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Create a <b>PriorityQueue</b>.</li>
 *     <li>Maintain heap size ≤ k.</li>
 *     <li>If new element is greater than heap root, replace it.</li>
 * </ol>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Time:</b> O(n log k)</li>
 *     <li><b>Space:</b> O(k)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Optimal Approach — QuickSelect (To Study Later)</h3>
 *
 * <p>
 * QuickSelect is a partial quicksort algorithm that finds the kth element
 * without fully sorting the array.
 * </p>
 *
 * <h4>Idea</h4>
 *
 * <ul>
 *     <li>Choose a pivot.</li>
 *     <li>Partition array into elements greater and smaller than pivot.</li>
 *     <li>Check pivot position.</li>
 * </ul>
 *
 * <ul>
 *     <li>If pivot index == target → answer found</li>
 *     <li>If pivot index &lt; target → search right side</li>
 *     <li>If pivot index &gt; target → search left side</li>
 * </ul>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Average:</b> O(n)</li>
 *     <li><b>Worst case:</b> O(n²)</li>
 *     <li><b>Space:</b> O(1)</li>
 * </ul>
 *
 * <p>
 * This is the <b>most optimal expected solution</b> for the problem.
 * </p>
 *
 * <hr>
 *
 * <h3>Summary</h3>
 *
 * <table border="1">
 * <tr>
 *     <th>Approach</th>
 *     <th>Time Complexity</th>
 *     <th>Space</th>
 *     <th>Notes</th>
 * </tr>
 * <tr>
 *     <td>Sorting</td>
 *     <td>O(n log n)</td>
 *     <td>O(1)</td>
 *     <td>Simple but unnecessary work</td>
 * </tr>
 * <tr>
 *     <td>Max Heap</td>
 *     <td>O(n + k log n)</td>
 *     <td>O(1)</td>
 *     <td>Remove max k times</td>
 * </tr>
 * <tr>
 *     <td>Min Heap (size k)</td>
 *     <td>O(n log k)</td>
 *     <td>O(k)</td>
 *     <td>Most common interview solution</td>
 * </tr>
 * <tr>
 *     <td>QuickSelect</td>
 *     <td>O(n) average</td>
 *     <td>O(1)</td>
 *     <td>Optimal expected solution</td>
 * </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>Interview Takeaway</h3>
 *
 * <ul>
 *     <li>If heap is allowed → use <b>Min Heap of size k</b>.</li>
 *     <li>If optimal solution is required → use <b>QuickSelect</b>.</li>
 * </ul>
 *
 */
public class FindKthLargest {
    public static void main(String[] args) {
        int[] arr = {3,2,1,5,6,4};
        int k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + removeKthLargest(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + removeKthLargest(arr, k));

        arr = new int[]{3,2,1,5,6,4};
        k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + removeKthLargest1(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + removeKthLargest1(arr, k));

        arr = new int[]{3,2,1,5,6,4};
        k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + removeKthLargest2(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + removeKthLargest2(arr, k));
    }

    /**
     * Time complexity : O(n + k log(n))
     * Space complexity : O(1)
     */
    private static int removeKthLargest(int[] arr, int k) {
        // build max heap
        buildHeap(arr);

        int size = arr.length;
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = remove(arr, size--);
        }
        return result;
    }

    /**
     * Time complexity : O(n * (log(k) + log(k))) -> O(n * 2log(k)) -> O(n * log(k))
     * Space complexity : O(k)
     */
    private static int removeKthLargest1(int[] arr, int k) {
        ArrayList<Integer> minHeap = new ArrayList<>();
        for (int element : arr) {
            if (minHeap.size() >= k) {
                // check if element is bigger than min in heap
                if (element > minHeap.get(0)) {
                    remove(minHeap);
                    insert(minHeap, element);
                }
            } else {
                insert(minHeap, element);
            }
        }
        return minHeap.isEmpty() ? -1 : minHeap.get(0);
    }

    /**
     * Time complexity : O(n * log(k))
     * Space complexity : O(k)
     */
    private static int removeKthLargest2(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int element : arr) {
            if (priorityQueue.size() >= k) {
                // check if element is bigger than min in pq
                if (element > priorityQueue.peek()) {
                    priorityQueue.poll();
                    priorityQueue.offer(element);
                }
            } else {
                priorityQueue.offer(element);
            }
        }
        return priorityQueue.peek();
    }

    private static int remove(ArrayList<Integer> arr) {
        int removed = arr.get(0);
        int last = arr.remove(arr.size() - 1);
        if (!arr.isEmpty()) {
            arr.set(0, last);
            heapDown(arr, 0);
        }
        return removed;
    }


    private static void insert(ArrayList<Integer> arr, int e) {
        arr.add(e);
        heapUp(arr, arr.size() - 1);
    }

    private static void heapUp(ArrayList<Integer> arr, int i) {
        int p = parent(i);
        if (p >= 0 && arr.get(i).compareTo(arr.get(p)) < 0) {
            swap(arr, p, i);
            heapUp(arr, p);
        }
    }

    private static void heapDown(ArrayList<Integer> arr, int i) {
        int min = i;
        int l = left(i);
        int r = right(i);
        if (l < arr.size() && arr.get(l).compareTo(arr.get(min)) < 0) {
            min = l;
        }
        if (r < arr.size() && arr.get(r).compareTo(arr.get(min)) < 0) {
            min = r;
        }
        if (min != i) {
            swap(arr, min, i);
            heapDown(arr, min);
        }
    }

    private static int remove(int[] arr, int size) {
        int remove = arr[0];
        int last = arr[size - 1];
        if (size > 0) {
            arr[0] = last;
            size--;
            downHeap(arr, 0, size);
        }
        return remove;
    }
    private static void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            downHeap(arr, i, arr.length);
        }
    }

    private static void downHeap(int[] arr, int i, int size) {
        int max = i;
        int l = left(i);
        int r = right(i);
        if (l < size && arr[l] > arr[max]) {
            max = l;
        }
        if (r < size && arr[r] > arr[max]) {
            max = r;
        }
        if (max != i) {
            swap(arr, max, i);
            downHeap(arr, max, size);
        }
    }
    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap(ArrayList<Integer> arr, int i, int j) {
        int t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }
}
