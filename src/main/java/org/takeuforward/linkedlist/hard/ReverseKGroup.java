package org.takeuforward.linkedlist.hard;

import java.util.Stack;

/**
 * LeetCode 25: Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Problem:
 * - Given a singly linked list, reverse the nodes of the list k at a time.
 * - If the number of nodes is not a multiple of k, leave the remaining nodes as-is.
 *
 * ---------------------------------------------------------------------
 * Approach 1: Stack-based reversal (reverseGroup)
 *
 * Idea:
 * - Traverse the list and push nodes into a stack.
 * - Once stack size reaches k, pop all nodes to reverse their order.
 * - Reconnect the reversed group to the remaining list.
 *
 * Key points:
 * - Stack temporarily holds k nodes.
 * - Links of original list are broken to avoid cycles.
 * - Simple to reason about, but uses extra space.
 *
 * Time Complexity:
 * - O(n)
 *   Each node is pushed and popped exactly once.
 *
 * Space Complexity:
 * - O(k)
 *   Stack holds at most k nodes at any time.
 *
 * Interview Note:
 * - Correct but not optimal due to extra space usage.
 *
 * ---------------------------------------------------------------------
 * Approach 2: In-place reversal using pointers (reverseGroup1)
 *
 * Idea:
 * - Use a dummy node to simplify edge cases.
 * - Traverse the list and reverse nodes in-place for every group of size k.
 * - Reverse is done using a helper method that reverses a sublist [start, end).
 *
 * Core technique:
 * - Identify group boundaries using a counter.
 * - Reverse exactly k nodes at a time.
 * - Reconnect the reversed group back to the list.
 *
 * Helper reverse(start, end):
 * - Reverses nodes from 'start' up to (but not including) 'end'.
 * - Uses constant space and pointer manipulation.
 *
 * Time Complexity:
 * - O(n)
 *   Each node is visited and rewired once.
 *
 * Space Complexity:
 * - O(1)
 *   In-place reversal (excluding recursion stack, which is not used here).
 *
 * Why this is optimal:
 * - No extra data structures.
 * - Single pass over the list.
 * - Clean pointer manipulation.
 *
 * Interview Takeaway:
 * - Always present this solution after a stack-based approach.
 * - Shows strong understanding of linked list pointer mechanics.
 *
 * ---------------------------------------------------------------------
 * Common Pitfalls:
 * - Forgetting to reconnect the reversed group to the remaining list.
 * - Incorrect handling when k > length of list.
 * - Not using a dummy node, leading to complex head handling.
 */
public class ReverseKGroup {

    public static void main(String[] args) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
        Node head = buildLL(1,2,3,4,5,6,7);
        printLL(head);
        head = reverseGroup(head, 3);
        printLL(head);

        head = buildLL(1,2,3,4,5,6,7);
        printLL(head);
        head = reverseGroup1(head, 3);
        printLL(head);
    }


    /**
     * Time complexity : O(n + ((n/k)*k)) => O(n + n) => O(2n) => O(n)
     * Space complexity : O(k)
     */
    public static Node reverseGroup(Node head, int k) {
        if (head == null || head.next == null) return head;

        Node dummy = new Node(0, null);
        Node prev = dummy;
        Node curr = head;

        Stack<Node> nodes = new Stack<>();
        while (curr != null) {
            // push to stack
            nodes.push(curr);
            curr = curr.next;

            if (nodes.size() >= k) {
                // do reverse
                while (!nodes.isEmpty()) {
                    Node pop = nodes.pop();
                    prev.next = pop;
                    prev = pop;
                }
                // wire reversed nodes to remaining list
                prev.next = curr;
            }
        }
        return dummy.next == null ? head : dummy.next;
    }

    /**
     * Time complexity : O(n + n) => O(2n) => O(n)
     * Space complexity : O(1)
     */
    public static Node reverseGroup1(Node head, int k) {
        if (head == null || head.next == null) return head;

        Node dummy = new Node(0, head);
        Node groupPrev = dummy;
        Node curr = head;

        int count = 1;
        while (curr != null) {
            curr = curr.next;

            if (count >= k) {
                // reverse
                Node temp = groupPrev.next;
                groupPrev.next = reverse(groupPrev.next, curr);
                groupPrev = temp;
                count = 0;
            }

            count++;
        }

        return dummy.next == null ? head : dummy.next;
    }

    private static Node reverse(Node start, Node end) {
        Node curr = start;
        Node prev = end;
        while (curr != end) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
        System.out.print("List : ");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? "->" : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }
}
