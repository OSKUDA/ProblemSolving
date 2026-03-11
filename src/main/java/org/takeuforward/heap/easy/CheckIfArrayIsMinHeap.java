package org.takeuforward.heap.easy;

import java.util.Arrays;

/**
 * <h2>Check if an Array Represents a Min Heap</h2>
 *
 * <p>
 * Given an array representing a binary tree in <b>level-order form</b>,
 * determine whether it satisfies the <b>Min Heap property</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Min Heap Definition</h3>
 *
 * <p>
 * A <b>Min Heap</b> is a <b>complete binary tree</b> where:
 * </p>
 *
 * <pre>
 * parent <= left child
 * parent <= right child
 * </pre>
 *
 * <p>
 * This means the <b>smallest element is always stored at the root</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Array Representation of Heap</h3>
 *
 * <p>
 * Because a heap is a <b>complete binary tree</b>, it can be stored efficiently
 * inside an array.
 * </p>
 *
 * <pre>
 * Index relationships (0-based indexing):
 *
 * Left child  = 2*i + 1
 * Right child = 2*i + 2
 * Parent      = (i - 1) / 2
 * </pre>
 *
 * <p>
 * Using these formulas, we can check whether the heap property holds
 * for every parent node.
 * </p>
 *
 * <hr>
 *
 * <h3>Algorithm</h3>
 *
 * <ol>
 * <li>Iterate through every element of the array.</li>
 * <li>Compute the indices of the left and right children.</li>
 * <li>If a child exists and violates the Min Heap property, return false.</li>
 * <li>If no violations are found, the array represents a valid Min Heap.</li>
 * </ol>
 *
 * <pre>
 * for each index i
 *     if left child exists and arr[left] < arr[i]
 *         return false
 *
 *     if right child exists and arr[right] < arr[i]
 *         return false
 *
 * return true
 * </pre>
 *
 * <hr>
 *
 * <h3>Example 1 (Valid Min Heap)</h3>
 *
 * <pre>
 * Input:
 * [10, 20, 30, 21, 23]
 *
 * Tree Representation:
 *
 *        10
 *      /    \
 *    20      30
 *   /  \
 * 21   23
 *
 * All parents are smaller than children → Valid Min Heap
 * </pre>
 *
 * <hr>
 *
 * <h3>Example 2 (Invalid Min Heap)</h3>
 *
 * <pre>
 * Input:
 * [10, 20, 30, 25, 15]
 *
 * Tree Representation:
 *
 *        10
 *      /    \
 *    20      30
 *   /  \
 * 25   15
 *
 * Violation:
 * 20 > 15
 *
 * Therefore → Not a Min Heap
 * </pre>
 *
 * <hr>
 *
 * <h3>Time Complexity</h3>
 *
 * <ul>
 * <li>We inspect each element once → <b>O(n)</b></li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Space Complexity</h3>
 *
 * <ul>
 * <li>No extra space used → <b>O(1)</b></li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Optimization Insight</h3>
 *
 * <p>
 * Only the <b>non-leaf nodes</b> need to be checked.
 * Leaf nodes automatically satisfy the heap property because
 * they do not have children.
 * </p>
 *
 * <p>
 * In an array-based heap, leaf nodes start from:
 * </p>
 *
 * <pre>
 * index = n / 2
 * </pre>
 *
 * <p>
 * So the loop can also run from:
 * </p>
 *
 * <pre>
 * for (int i = 0; i <= n/2 - 1; i++)
 * </pre>
 *
 * This slightly reduces unnecessary checks.
 * </p>
 *
 * <hr>
 *
 * <h3>Key Takeaways</h3>
 *
 * <ul>
 * <li>Heap structure can be validated using simple index relationships.</li>
 * <li>Min Heap requires <code>parent <= children</code>.</li>
 * <li>Leaf nodes always satisfy heap property.</li>
 * <li>Checking all nodes takes linear time.</li>
 * </ul>
 *
 */
public class CheckIfArrayIsMinHeap {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 21, 23};
        System.out.println("Input : " + Arrays.toString(arr));
        System.out.println("Is MinHeap? " + isMinHeap(arr));

        arr = new int[]{10, 20, 30, 25, 15};
        System.out.println("Input : " + Arrays.toString(arr));
        System.out.println("Is MinHeap? " + isMinHeap(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static boolean isMinHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // check left
            if (left(i) < arr.length && arr[left(i)] < arr[i]) {
                return false;
            }
            // check right
            if (right(i) < arr.length && arr[right(i)] < arr[i]) {
                return false;
            }
        }
        return true;
    }


    private static int left(int i) {
        return i * 2 + 1;
    }

    private static int right(int i) {
        return i * 2 + 2;
    }



}
