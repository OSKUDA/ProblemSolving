package org.takeuforward.linkedlist.hard;

/**
 * <b>Rotate Linked List</b>
 * <br>
 * LeetCode 61:
 * <a href="https://leetcode.com/problems/rotate-list/" target="_blank">
 * https://leetcode.com/problems/rotate-list/
 * </a>
 *
 * <h3>Problem</h3>
 * Rotate a singly linked list to the right by <code>k</code> places.
 *
 * <pre>
 * Example:
 * Input :  1 -> 2 -> 3 -> 4 -> 5, k = 2
 * Output:  4 -> 5 -> 1 -> 2 -> 3
 * </pre>
 *
 * <h3>Core Idea (Link & Cut Technique)</h3>
 * Instead of repeatedly rotating the list, we:
 * <ul>
 *   <li>Find the length of the list and the tail node</li>
 *   <li>Connect the tail to the head to form a circular list</li>
 *   <li>Break the circle at the correct position to get the rotated list</li>
 * </ul>
 *
 * <h3>Step-by-Step Approach</h3>
 * <ol>
 *   <li>Traverse the list once to compute length (<code>n</code>) and track the tail</li>
 *   <li>Normalize rotations using <code>k = k % n</code></li>
 *   <li>Connect <code>tail.next = head</code> to make the list circular</li>
 *   <li>Find the cut position at index <code>n - k</code></li>
 *   <li>Break the circular link to form the rotated list</li>
 * </ol>
 *
 * <h3>Why This Works</h3>
 * <ul>
 *   <li>Rotating right by <code>k</code> means the last <code>k</code> nodes move to the front</li>
 *   <li>Making the list circular allows rotation to be done by a single cut</li>
 *   <li>Avoids multiple reversals or extra data structures</li>
 * </ul>
 *
 * <h3>Edge Cases Handled</h3>
 * <ul>
 *   <li>Empty list</li>
 *   <li>Single-node list</li>
 *   <li><code>k == 0</code></li>
 *   <li><code>k >= length of list</code></li>
 * </ul>
 *
 * <h3>Time & Space Complexity</h3>
 * <ul>
 *   <li><b>Time:</b> O(n) — single traversal + cut</li>
 *   <li><b>Space:</b> O(1) — in-place pointer manipulation</li>
 * </ul>
 *
 * <h3>Interview Notes</h3>
 * <ul>
 *   <li>This is the optimal and most preferred solution</li>
 *   <li>Clearly explain the circular linking step — it’s the key insight</li>
 *   <li>Much cleaner and safer than reverse-based approaches</li>
 * </ul>
 */
public class RotateLL {

    public static void main(String[] args) {
        // 1 -> 2 -> 3 -> 4
        Node head = buildLL(1, 2);
        printLL(head);

        head = rotateLL(head, 2);
        printLL(head);

    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public static Node rotateLL(Node head, int k) {
        if (head == null || head.next == null) return head;
        if (k == 0) return head;

        // find length
        Node curr = head;
        Node tail = null;
        int length = 0;
        while (curr != null) {
            length++;
            tail = curr;
            curr = curr.next;
        }

        // attach tail to head
        tail.next = head;

        int cutOffNodeIndex = length - (k % length);

        curr = head;
        Node prev = tail;
        int count = 0;

        while (curr != null) {
            if (count == cutOffNodeIndex) {
                // break the link
                prev.next = null;
                head = curr;
                break;
            }
            count++;
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    public static Node buildLL(int... values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node prev = head;
        for (int i = 1; i < values.length; i++) {
            Node node = new Node(values[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }

    public static void printLL(Node head) {
        Node curr = head;
        System.out.print("List : ");
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? "->" : " "));
            curr = curr.next;
        }
        System.out.println();
    }

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
