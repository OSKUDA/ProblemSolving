package org.takeuforward.heap.easy;

import java.util.ArrayList;
/**
 * <h2>Heap Implementation (Max Heap)</h2>
 *
 * <p>
 * This class implements a <b>Max Heap</b> using an <code>ArrayList</code>.
 * A heap is a specialized <b>complete binary tree</b> that satisfies the
 * <b>heap property</b>.
 * </p>
 *
 * <ul>
 * <li><b>Max Heap</b> → Parent node is always greater than or equal to its children.</li>
 * <li><b>Min Heap</b> → Parent node is always smaller than or equal to its children.</li>
 * </ul>
 *
 * <p>
 * In this implementation we use a <b>Max Heap</b>, therefore the largest element
 * is always stored at the root (index 0).
 * </p>
 *
 * <hr>
 *
 * <h3>Heap Representation</h3>
 *
 * <p>
 * Instead of explicitly storing nodes with pointers, the heap is stored
 * inside an <code>ArrayList</code>.
 * This works because a heap is a <b>complete binary tree</b>.
 * </p>
 *
 * <pre>
 * Example Heap Tree
 *
 *         16
 *       /    \
 *     14      10
 *    /  \    /  \
 *   9    8   7    3
 *
 * Array Representation
 *
 * [16, 14, 10, 9, 8, 7, 3]
 * </pre>
 *
 * <hr>
 *
 * <h3>Index Relationships (0-based indexing)</h3>
 *
 * <pre>
 * Parent Index  = (i - 1) / 2
 * Left Child    = 2*i + 1
 * Right Child   = 2*i + 2
 * </pre>
 *
 * <p>
 * These formulas allow us to traverse the binary tree structure
 * directly from the array without storing explicit tree nodes.
 * </p>
 *
 * <hr>
 *
 * <h3>Core Operations</h3>
 *
 * <h4>1. Insert</h4>
 *
 * <p>
 * Steps:
 * </p>
 *
 * <ol>
 * <li>Add the element at the end of the array.</li>
 * <li>Restore heap property using <b>heapify-up</b>.</li>
 * </ol>
 *
 * <pre>
 * Insert 20
 *
 * Before:
 * [16, 14, 10, 9]
 *
 * After insertion:
 * [16, 14, 10, 9, 20]
 *
 * Heapify Up:
 *
 *        20
 *       /  \
 *     16   10
 *    /
 *   9
 * </pre>
 *
 * <p>
 * <b>Time Complexity:</b> O(log n)
 * </p>
 *
 * <hr>
 *
 * <h4>2. Remove (Extract Max)</h4>
 *
 * <p>
 * Steps:
 * </p>
 *
 * <ol>
 * <li>Remove the root element.</li>
 * <li>Replace root with the last element.</li>
 * <li>Restore heap property using <b>heapify-down</b>.</li>
 * </ol>
 *
 * <pre>
 * Example
 *
 * Before:
 * [16, 14, 10, 9, 8]
 *
 * Remove root (16)
 *
 * Replace with last element:
 * [8, 14, 10, 9]
 *
 * Heapify Down:
 *
 * [14, 9, 10, 8]
 * </pre>
 *
 * <p>
 * <b>Time Complexity:</b> O(log n)
 * </p>
 *
 * <hr>
 *
 * <h4>3. Heapify Up</h4>
 *
 * <p>
 * Used after insertion.
 * The inserted element is compared with its parent and swapped if necessary.
 * </p>
 *
 * <pre>
 * while element > parent
 *     swap(element, parent)
 * </pre>
 *
 * <p>
 * This operation moves the element <b>up the tree</b> until the heap property
 * is restored.
 * </p>
 *
 * <hr>
 *
 * <h4>4. Heapify Down</h4>
 *
 * <p>
 * Used after removing the root.
 * The root element is compared with its children and swapped with the
 * larger child until the heap property is restored.
 * </p>
 *
 * <pre>
 * while node < largerChild
 *     swap(node, largerChild)
 * </pre>
 *
 * <p>
 * This operation moves the element <b>down the tree</b>.
 * </p>
 *
 * <hr>
 *
 * <h3>Heap Sort Using Heap</h3>
 *
 * <p>
 * Heap sort repeatedly removes the maximum element from the heap.
 * Each removal returns the largest remaining element.
 * </p>
 *
 * <pre>
 * Heap:
 * [16, 14, 10, 9, 8]
 *
 * Remove sequence:
 * 16 → 14 → 10 → 9 → 8
 * </pre>
 *
 * <p>
 * This produces a <b>descending sorted order</b> when using a max heap.
 * </p>
 *
 * <p>
 * <b>Time Complexity:</b> O(n log n)
 * </p>
 *
 * <hr>
 *
 * <h3>Time Complexity Summary</h3>
 *
 * <table border="1">
 * <tr>
 * <th>Operation</th>
 * <th>Time Complexity</th>
 * </tr>
 * <tr>
 * <td>Insert</td>
 * <td>O(log n)</td>
 * </tr>
 * <tr>
 * <td>Remove Root</td>
 * <td>O(log n)</td>
 * </tr>
 * <tr>
 * <td>Peek Root</td>
 * <td>O(1)</td>
 * </tr>
 * <tr>
 * <td>Heap Sort</td>
 * <td>O(n log n)</td>
 * </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>Key Insights</h3>
 *
 * <ul>
 * <li>Heap is a <b>complete binary tree</b>.</li>
 * <li>It can be efficiently stored in an array.</li>
 * <li>Heap guarantees <b>partial ordering</b>, not full sorting.</li>
 * <li>Max Heap gives fast access to the <b>largest element</b>.</li>
 * <li>Min Heap gives fast access to the <b>smallest element</b>.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Applications of Heap</h3>
 *
 * <ul>
 * <li>Priority Queue implementation</li>
 * <li>Heap Sort</li>
 * <li>Kth largest / smallest element problems</li>
 * <li>Top-K frequent elements</li>
 * <li>Graph algorithms (Dijkstra, Prim)</li>
 * <li>Merging K sorted lists</li>
 * </ul>
 *
 */
public class HeapImpl {
    public static void main(String[] args) throws Exception {
        int[] arr = {4,3,2,10,14,8,7,9,16};
        Heap<Integer> heap = new Heap<>();
        heap.print();
        for (int element : arr) {
            heap.insert(element);
        }
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();

        ArrayList<Integer> heapsort = heap.heapsort();
        System.out.println("HeapSort : " + heapsort);
    }

    private static class Heap<T extends Comparable<T>> {

        private ArrayList<T> list;

        public Heap() {
            list = new ArrayList<>();
        }

        public void insert(T element) {
            // insert at end
            list.add(element);
            // Heapify up
            upHeap(list.size() - 1);
        }

        public T remove() throws Exception {
            if (list.isEmpty()) {
                throw new Exception("Removing from an empty list");
            }
            // remove root
            T remove = list.get(0);
            // remove last and replace
            T last = list.remove(list.size() - 1);
            if (!list.isEmpty()) {
                list.set(0, last);
                downHeap(0);
            }
            return remove;
        }

        private void upHeap(int i) {
            if (i == 0) {
                return;
            }
            int p = parent(i);
            if (list.get(i).compareTo(list.get(p)) > 0) {
                swap(i, p);
                upHeap(p);
            }
        }

        private void downHeap(int i) {
            int min = i;
            int l = left(i);
            int r = right(i);

            if (l < list.size() && list.get(l).compareTo(list.get(min)) > 0) {
                min = l;
            }
            if (r < list.size() && list.get(r).compareTo(list.get(min)) > 0) {
                min = r;
            }
            if (min != i) {
                swap(min, i);
                downHeap(min);
            }
        }

        public ArrayList<T> heapsort() throws Exception {
            ArrayList<T> data = new ArrayList<>();

            while (!list.isEmpty()) {
                data.add(remove());
            }
            return data;
        }

        public void print() {
            System.out.println("Heap : " + list);
        }

        private void swap(int i, int j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        private int left(int index) {
            return (index * 2 + 1);
        }

        private int right(int index) {
            return (index * 2 + 2);
        }

        private int parent(int index) {
            return (index - 1) / 2;
        }
    }

}


