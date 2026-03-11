package org.takeuforward.heap.easy;

import java.util.Arrays;

/**
 * <h2>Convert Min Heap to Max Heap</h2>
 *
 * <p>
 * Given an array that represents a <b>Min Heap</b>, convert it into a
 * <b>Max Heap</b> in-place.
 * </p>
 *
 * <hr>
 *
 * <h3>Heap Definitions</h3>
 *
 * <b>Min Heap Property</b>
 * <pre>
 * parent <= left child
 * parent <= right child
 * </pre>
 *
 * <b>Max Heap Property</b>
 * <pre>
 * parent >= left child
 * parent >= right child
 * </pre>
 *
 * <p>
 * In a Max Heap, the <b>largest element is always stored at the root</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Array Representation of Heap</h3>
 *
 * <p>
 * A heap is a <b>complete binary tree</b>, which allows it to be stored in
 * an array without explicit tree nodes.
 * </p>
 *
 * <pre>
 * Index relationships (0-based indexing):
 *
 * left child  = 2 * i + 1
 * right child = 2 * i + 2
 * parent      = (i - 1) / 2
 * </pre>
 *
 * <hr>
 *
 * <h3>Key Idea</h3>
 *
 * <p>
 * To convert a Min Heap to a Max Heap, we rebuild the heap using the
 * <b>bottom-up heap construction approach</b>.
 * </p>
 *
 * <p>
 * Starting from the <b>last non-leaf node</b>, we apply <b>heapify-down</b>
 * (also called <i>sift-down</i>) to ensure that the subtree rooted at that
 * node satisfies the Max Heap property.
 * </p>
 *
 * <pre>
 * last non-leaf index = n/2 - 1
 * </pre>
 *
 * <p>
 * We iterate backwards from this index to the root.
 * </p>
 *
 * <pre>
 * for (i = n/2 - 1; i >= 0; i--)
 *     heapifyDown(i)
 * </pre>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input (Min Heap):
 * [10, 20, 30, 21, 23]
 *
 * Tree:
 *
 *        10
 *      /    \
 *    20      30
 *   /  \
 * 21   23
 *
 * After Conversion (Max Heap):
 *
 *        30
 *      /    \
 *    23      10
 *   /  \
 * 21   20
 *
 * Array Representation:
 * [30, 23, 10, 21, 20]
 * </pre>
 *
 * <hr>
 *
 * <h3>Heapify Down Process</h3>
 *
 * <ol>
 * <li>Compare the current node with its children.</li>
 * <li>Select the larger child.</li>
 * <li>If the child is greater than the parent, swap them.</li>
 * <li>Continue heapifying recursively.</li>
 * </ol>
 *
 * <pre>
 * while node < largerChild
 *     swap(node, largerChild)
 * </pre>
 *
 * <hr>
 *
 * <h3>Time Complexity</h3>
 *
 * <ul>
 * <li>Heapify operation → O(log n)</li>
 * <li>Applied to roughly n/2 nodes</li>
 * </ul>
 *
 * <p>
 * However, due to the structure of a complete binary tree,
 * the total cost of bottom-up heap construction is:
 * </p>
 *
 * <pre>
 * O(n)
 * </pre>
 *
 * <p>
 * This is because most nodes are near the leaves and require
 * very little heapify work.
 * </p>
 *
 * <hr>
 *
 * <h3>Space Complexity</h3>
 *
 * <pre>
 * O(1)
 * </pre>
 *
 * <p>
 * The transformation is done <b>in-place</b> without using
 * additional data structures.
 * </p>
 *
 * <hr>
 *
 * <h3>Key Takeaways</h3>
 *
 * <ul>
 * <li>Heap can be efficiently stored in an array.</li>
 * <li>Only non-leaf nodes need heapify operations.</li>
 * <li>Bottom-up heap construction builds a heap in <b>O(n)</b> time.</li>
 * <li>This approach is used in <b>Heap Sort</b> and many priority queue algorithms.</li>
 * </ul>
 *
 */
public class ConvertMinHeapToMaxHeap {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 21, 23};
        System.out.println("Input : " + Arrays.toString(arr));
        System.out.println("Output : " + Arrays.toString(buildHeap(arr)));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    private static int[] buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            downHeap(arr, i);
        }
        return arr;
    }


    /**
     * Time complexity : O(log(n))
     */
    private static void downHeap(int[] arr, int i) {
        int max = i;
        int l = left(i);
        int r = right(i);
        if (l < arr.length && arr[l] > arr[i]) {
            max = l;
        }
        if (r < arr.length && arr[r] > arr[i]) {
            max = r;
        }
        if (max != i) {
            swap(arr, i, max);
            downHeap(arr, max);
        }
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
