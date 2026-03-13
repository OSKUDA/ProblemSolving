package org.takeuforward.heap.hard;

import java.util.ArrayList;

/**
 * <h2>Merge K Sorted Lists</h2>
 *
 * <p>
 * LeetCode Problem:
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/description/">
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * </a>
 * </p>
 *
 * <h3>Problem Statement</h3>
 *
 * <p>
 * You are given an array of <b>k sorted linked lists</b>. Each linked list
 * is sorted in ascending order.
 * </p>
 *
 * <p>
 * Merge all the linked lists into <b>one sorted linked list</b> and return its head.
 * </p>
 *
 * <pre>
 * Example
 *
 * Input:
 * lists = [
 *   1 → 4 → 5
 *   1 → 3 → 4
 *   2 → 6
 * ]
 *
 * Output:
 * 1 → 1 → 2 → 3 → 4 → 4 → 5 → 6
 * </pre>
 *
 * Each list is individually sorted, but we must merge them while preserving
 * the overall sorted order.  [oai_citation:0‡LeetCode](https://leetcode.com/problems/merge-k-sorted-lists/?utm_source=chatgpt.com)
 *
 * <hr>
 *
 * <h3>Key Idea</h3>
 *
 * <p>
 * At any moment, the next smallest element must be among the
 * <b>current heads of the k lists</b>.
 * </p>
 *
 * <p>
 * Therefore we only need to efficiently determine the
 * <b>minimum among k elements repeatedly</b>.
 * </p>
 *
 * <p>
 * A <b>Min Heap (Priority Queue)</b> is perfect for this.
 * </p>
 *
 * <hr>
 *
 * <h3>Approach 1 — Brute Force (Scan All List Heads)</h3>
 *
 * <p>
 * At each step:
 * </p>
 *
 * <ol>
 *     <li>Scan all k list heads.</li>
 *     <li>Pick the minimum node.</li>
 *     <li>Append it to the result list.</li>
 *     <li>Move that list's pointer forward.</li>
 * </ol>
 *
 * <h4>Example Iteration</h4>
 *
 * <pre>
 * Heads: [1,1,2]
 * pick → 1
 *
 * Heads: [4,1,2]
 * pick → 1
 *
 * Heads: [4,3,2]
 * pick → 2
 * </pre>
 *
 * <h4>Complexity</h4>
 *
 * <ul>
 *     <li>Each node selection scans <b>k lists</b>.</li>
 *     <li>Total nodes = <b>N</b>.</li>
 * </ul>
 *
 * <pre>
 * Time  : O(N * k)
 * Space : O(1)
 * </pre>
 *
 * <hr>
 *
 * <h3>Approach 2 — Min Heap (Optimal)</h3>
 *
 * <p>
 * Maintain a <b>Min Heap</b> containing the current head node of
 * each list.
 * </p>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Insert the first node of each non-null list into the heap.</li>
 *     <li>Extract the smallest node from the heap.</li>
 *     <li>Append it to the result list.</li>
 *     <li>If the extracted node has a next node, insert it into the heap.</li>
 *     <li>Repeat until the heap becomes empty.</li>
 * </ol>
 *
 * <h4>Visualization</h4>
 *
 * <pre>
 * Heap: [1,1,2]
 *
 * pop → 1
 * push → 4
 *
 * Heap: [1,2,4]
 *
 * pop → 1
 * push → 3
 *
 * Heap: [2,3,4]
 * </pre>
 *
 * The heap always provides the globally smallest node among all lists.  [oai_citation:1‡AlgoMonster](https://algo.monster/liteproblems/23?utm_source=chatgpt.com)
 *
 * <hr>
 *
 * <h3>Complexity Analysis</h3>
 *
 * Let:
 *
 * <pre>
 * k = number of lists
 * N = total number of nodes across all lists
 * </pre>
 *
 * Heap operations:
 *
 * <ul>
 *     <li>Initial heap build → O(k log k)</li>
 *     <li>For each node:
 *         <ul>
 *             <li>poll → O(log k)</li>
 *             <li>insert next node → O(log k)</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <pre>
 * Time complexity:
 *
 * O(k log k + N log k)
 * → O((k + N) log k)
 * → O(N log k) since N >> k
 *
 * Space complexity:
 *
 * O(k)
 * </pre>
 *
 * Using a heap reduces the repeated search among k elements,
 * improving the naive solution significantly.  [oai_citation:2‡LeetCode](https://leetcode.doocs.org/en/lc/23/?utm_source=chatgpt.com)
 *
 * <hr>
 *
 * <h3>Alternative Approach — Divide & Conquer</h3>
 *
 * <p>
 * Similar to <b>Merge Sort</b>:
 * </p>
 *
 * <ol>
 *     <li>Merge lists in pairs.</li>
 *     <li>Repeat until only one list remains.</li>
 * </ol>
 *
 * Example:
 *
 * <pre>
 * lists = [L1, L2, L3, L4]
 *
 * Step 1:
 * merge(L1,L2)
 * merge(L3,L4)
 *
 * Step 2:
 * merge(result1, result2)
 * </pre>
 *
 * <pre>
 * Time complexity : O(N log k)
 * Space complexity : O(log k)
 * </pre>
 *
 * This is essentially a <b>k-way merge algorithm</b>.  [oai_citation:3‡Wikipedia](https://en.wikipedia.org/wiki/K-way_merge_algorithm?utm_source=chatgpt.com)
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
 *     <td>Scan k heads</td>
 *     <td>O(N * k)</td>
 *     <td>O(1)</td>
 * </tr>
 * <tr>
 *     <td>Min Heap</td>
 *     <td>O(N log k)</td>
 *     <td>O(k)</td>
 * </tr>
 * <tr>
 *     <td>Divide & Conquer</td>
 *     <td>O(N log k)</td>
 *     <td>O(log k)</td>
 * </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>Interview Takeaways</h3>
 *
 * <ul>
 *     <li>This is a classic <b>K-way merge</b> problem.</li>
 *     <li>Recognize patterns involving:
 *         <ul>
 *             <li>Merging multiple sorted sequences</li>
 *             <li>Repeated minimum extraction</li>
 *             <li>Priority queue / heap usage</li>
 *         </ul>
 *     </li>
 *     <li>The optimal solution uses a <b>Min Heap of size k</b>.</li>
 * </ul>
 *
 */
public class MergeKSortedLL {

    public static void main(String[] args) {
        // 1 -> 4 -> 5
        ListNode head1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        // 1 -> 3 -> 4
        ListNode head2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        // 2 -> 6
        ListNode head3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = new ListNode[]{head1, head2, head3};
        for (ListNode list : lists) {
            printLL(list);
        }

        ListNode result = merge(lists);
        System.out.println("Output : ");
        printLL(result);

        // 1 -> 4 -> 5
        head1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        // 1 -> 3 -> 4
        head2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        // 2 -> 6
        head3 = new ListNode(2, new ListNode(6));

        lists = new ListNode[]{head1, head2, head3};
        for (ListNode list : lists) {
            printLL(list);
        }

        result = merge1(lists);
        System.out.println("Output : ");
        printLL(result);

    }

    /**
     * Time complexity : O(N * k) here, N is total number of nodes, k is number of lists
     * Space complexity : O(1)
     */
    private static ListNode merge(ListNode[] lists) {
        ListNode dummy = new ListNode(-1, null);
        ListNode curr = dummy;

        while (true) {
            int minAt = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    minAt = i;
                    min = lists[i].val;
                }
            }
            if (minAt == -1) {
                break;
            }
            ListNode minNode = lists[minAt];
            lists[minAt] = lists[minAt].next;
            minNode.next = null;

            curr.next = minNode;
            curr = minNode;
        }
        return dummy.next;
    }

    /**
     * Time complexity : O(k * log(k) + n * log(k)), here k is number of lists, n is number of nodes
     *                      -> O((k + n) * log(k)) -> O(n * log(k)) as k << n
     * Space complexity : O(k)
     */
    private static ListNode merge1(ListNode[] lists) {
        ArrayList<ListNode> heap = new ArrayList<>();
        // build min-heap
        for (ListNode list : lists) {
            if (list != null) {
                insert(heap, list);
            }
        }

        ListNode dummy = new ListNode(-1 , null);
        ListNode curr = dummy;
        while (!heap.isEmpty()) {
            curr.next = remove(heap);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static void insert(ArrayList<ListNode> arr, ListNode node) {
        arr.add(node);
        upHeap(arr, arr.size() - 1);
    }

    private static ListNode remove(ArrayList<ListNode> arr) {
        ListNode removedNode = arr.get(0);
        arr.set(0, arr.get(0).next);
        removedNode.next = null;
        if (arr.get(0) == null) {
            ListNode lastNode = arr.remove(arr.size() - 1);
            if (!arr.isEmpty()) {
                arr.set(0, lastNode);
                downHeap(arr, 0);
            }
        } else {
            downHeap(arr, 0);
        }
        return removedNode;
    }

    private static void downHeap(ArrayList<ListNode> arr, int i) {
        int min = i;
        int l = left(i);
        int r = right(i);
        if (l < arr.size() && arr.get(l).val < arr.get(min).val) {
            min = l;
        }
        if (r < arr.size() && arr.get(r).val < arr.get(min).val) {
            min = r;
        }
        if (min != i) {
            swap(arr, min, i);
            downHeap(arr, min);
        }
    }
    private static void upHeap(ArrayList<ListNode> arr, int i) {
        int p = parent(i);
        if (p >= 0 && arr.get(p).val > arr.get(i).val) {
            swap(arr, p, i);
            upHeap(arr, p);
        }
    }

    private static void swap(ArrayList<ListNode> arr, int i, int j) {
        ListNode t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static void printLL(ListNode head) {
        System.out.println("List : ");
        while (head != null) {
            System.out.print(head.val + (head.next != null ? "->" : ""));
            head = head.next;
        }
        System.out.println();
    }

    private static class ListNode implements Comparable<ListNode>{
        int val;
        ListNode next;

        public ListNode(int val) {this.val = val;}
        public ListNode(int val, ListNode next) {this.val = val; this.next = next;}

        @Override
        public String toString() {
            return String.valueOf(val);
        }

        @Override
        public int compareTo(ListNode o) {
            return Integer.compare(this.val, o.val);
        }
    }
}
