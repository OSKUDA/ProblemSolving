package org.takeuforward.heap.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

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
 *
 * <p>
 * Given an integer array <b>nums</b> and an integer <b>k</b>, return the
 * <b>kth largest element</b> in the array.
 * </p>
 *
 * <ul>
 *     <li>The kth largest element is the element at index <b>k-1</b> in a
 *     <b>descending sorted array</b>.</li>
 *     <li>This is <b>NOT</b> the kth distinct element.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Key Observations</h3>
 *
 * <ul>
 *     <li>Sorting the array solves the problem but costs <b>O(n log n)</b>.</li>
 *     <li>The problem only asks for one element, so full sorting is unnecessary.</li>
 *     <li>Better approaches focus on <b>partial ordering</b>.</li>
 * </ul>
 *
 * <p><b>Common strategies:</b></p>
 *
 * <ul>
 *     <li>Heap / Priority Queue</li>
 *     <li>QuickSelect (selection algorithm)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 1 — Max Heap (Heap Sort Idea)</h3>
 *
 * <p>
 * Convert the array into a <b>Max Heap</b>.
 * The root always contains the largest element.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Build a max heap from the array.</li>
 *     <li>Remove the root element <b>k</b> times.</li>
 *     <li>The kth removed element is the answer.</li>
 * </ol>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Heap construction:</b> O(n)</li>
 *     <li><b>k removals:</b> O(k log n)</li>
 *     <li><b>Total:</b> O(n + k log n)</li>
 *     <li><b>Space:</b> O(1)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 2 — Min Heap of Size K</h3>
 *
 * <p>
 * Maintain a <b>Min Heap</b> containing the <b>k largest elements seen so far</b>.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Insert elements until heap size becomes <b>k</b>.</li>
 *     <li>For every new element:
 *         <ul>
 *             <li>If element &gt; heap root → remove root and insert element.</li>
 *             <li>Otherwise ignore it.</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * <p>
 * The heap root always represents the <b>kth largest element</b>.
 * </p>
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
 * <h3>Approach 3 — Java PriorityQueue</h3>
 *
 * <p>
 * Java's <b>PriorityQueue</b> implements a <b>Min Heap</b>.
 * This provides a direct implementation of the previous approach.
 * </p>
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
 * <h3>Approach 4 — QuickSelect (Random Pivot + Lomuto Partition)</h3>
 *
 * <p>
 * QuickSelect is derived from the <b>Quicksort partition algorithm</b>.
 * Instead of sorting the whole array, it places one element into its
 * correct sorted position and searches only one side.
 * </p>
 *
 * <h4>Step 1 — Convert kth Largest → kth Smallest</h4>
 *
 * <pre>
 * targetIndex = n - k
 * </pre>
 *
 * Example:
 *
 * <pre>
 * nums = [3,2,1,5,6,4]
 * k = 2
 *
 * sorted ascending
 * [1,2,3,4,5,6]
 *
 * targetIndex = 6 - 2 = 4
 * answer = 5
 * </pre>
 *
 * <h4>Lomuto Partition</h4>
 *
 * <pre>
 * elements < pivot | pivot | elements >= pivot
 * </pre>
 *
 * Random pivot helps avoid adversarial inputs.
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Average:</b> O(n)</li>
 *     <li><b>Worst:</b> O(n²)</li>
 * </ul>
 *
 * <p>
 * Downside: Lomuto partition performs many swaps.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 5 — QuickSelect (Mid Pivot + Hoare Partition)</h3>
 *
 * <p>
 * This is an optimized QuickSelect variant that uses:
 * </p>
 *
 * <ul>
 *     <li><b>Middle element as pivot</b></li>
 *     <li><b>Hoare partition scheme</b></li>
 * </ul>
 *
 * <p>
 * Hoare partition reduces swaps and improves constant factors.
 * </p>
 *
 * <h4>Hoare Partition Idea</h4>
 *
 * Two pointers scan from both ends:
 *
 * <pre>
 * i → move right while arr[i] < pivot
 * j → move left while arr[j] > pivot
 *
 * swap(arr[i], arr[j])
 * </pre>
 *
 * This continues until pointers cross.
 *
 * <h4>Why Hoare Partition Is Faster</h4>
 *
 * <ul>
 *     <li>Fewer swaps than Lomuto partition</li>
 *     <li>No need to move pivot to the end</li>
 *     <li>Better cache performance</li>
 * </ul>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li><b>Average:</b> O(n)</li>
 *     <li><b>Worst:</b> O(n²)</li>
 *     <li><b>Space:</b> O(1)</li>
 * </ul>
 *
 * <p>
 * In practice this version is often the <b>fastest QuickSelect implementation</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Partition Schemes Comparison</h3>
 *
 * <table border="1">
 * <tr>
 *     <th>Partition Type</th>
 *     <th>Swaps</th>
 *     <th>Pivot Placement</th>
 *     <th>Typical Usage</th>
 * </tr>
 * <tr>
 *     <td>Lomuto</td>
 *     <td>More swaps</td>
 *     <td>Pivot moved to end</td>
 *     <td>Simpler implementation</td>
 * </tr>
 * <tr>
 *     <td>Hoare</td>
 *     <td>Fewer swaps</td>
 *     <td>Pivot not moved</td>
 *     <td>Faster in practice</td>
 * </tr>
 * </table>
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
 * </tr>
 * <tr>
 *     <td>Sorting</td>
 *     <td>O(n log n)</td>
 *     <td>O(1)</td>
 * </tr>
 * <tr>
 *     <td>Max Heap</td>
 *     <td>O(n + k log n)</td>
 *     <td>O(1)</td>
 * </tr>
 * <tr>
 *     <td>Min Heap (size k)</td>
 *     <td>O(n log k)</td>
 *     <td>O(k)</td>
 * </tr>
 * <tr>
 *     <td>QuickSelect (Lomuto)</td>
 *     <td>O(n) avg</td>
 *     <td>O(1)</td>
 * </tr>
 * <tr>
 *     <td>QuickSelect (Hoare)</td>
 *     <td>O(n) avg</td>
 *     <td>O(1)</td>
 * </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>Interview Takeaways</h3>
 *
 * <ul>
 *     <li>Min Heap is the easiest interview solution.</li>
 *     <li>QuickSelect gives optimal expected time.</li>
 *     <li>Random pivot prevents adversarial inputs.</li>
 *     <li>Hoare partition improves performance by reducing swaps.</li>
 * </ul>
 *
 */
public class FindKthLargest {
    public static void main(String[] args) {
        int[] arr = {3,2,1,5,6,4};
        int k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest(arr, k));

        arr = new int[]{3,2,1,5,6,4};
        k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest1(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest1(arr, k));

        arr = new int[]{3,2,1,5,6,4};
        k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest2(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest2(arr, k));

        arr = new int[]{3,2,1,5,6,4};
        k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest3(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest3(arr, k));

        arr = new int[]{3,2,1,5,6,4};
        k = 2;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest4(arr, k));

        arr = new int[]{3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("K : " + k);
        System.out.println("Kth largest is : " + findKthLargest4(arr, k));
    }

    /**
     * Time complexity : O(n + k log(n))
     * Space complexity : O(1)
     */
    private static int findKthLargest(int[] arr, int k) {
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
    private static int findKthLargest1(int[] arr, int k) {
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
    private static int findKthLargest2(int[] arr, int k) {
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

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Note : random pivot and lomuto partition
     */
    private static int findKthLargest3(int[] arr, int k) {
        int target = arr.length - k;
        int left = 0;
        int right = arr.length - 1;
        while (true) {
            int pivotIndex = partition(arr, left, right);

            if (pivotIndex == target) return arr[pivotIndex];

            if (pivotIndex > target) {
                // search left
                right = pivotIndex - 1;
            } else {
                // search right
                left = pivotIndex + 1;
            }
        }
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * Note : Uses mid-pivot and hoare partitioning
     * It's relatively faster because of less swap()
     */
    private static int findKthLargest4(int[] arr, int k) {
        int target = arr.length - k;

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int pivot = arr[left + (right - left) / 2];

            int i = left;
            int j = right;

            while (i <= j) {

                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }

            if (target <= j) {
                right = j;
            }
            else if (target >= i) {
                left = i;
            }
            else {
                return arr[target];
            }
        }

        return -1;
    }


    private static final Random RANDOM = new Random();

    private static int partition(int[] arr, int left, int right) {
        // pick random pivot index
        int pivotIndex = left + RANDOM.nextInt(right - left + 1);

        // move pivot to end
        swap(arr, pivotIndex, right);

        int pivot = arr[right];
        int partition = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, partition);
                partition++;
            }
        }
        // put pivot in its correct place
        swap(arr, partition, right);
        return partition;
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
