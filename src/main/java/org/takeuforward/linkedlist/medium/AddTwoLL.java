package org.takeuforward.linkedlist.medium;

/**
 * LeetCode 2: Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Problem Recap:
 * - Two non-empty linked lists represent two non-negative integers.
 * - Digits are stored in REVERSE order.
 *   Example: 2 -> 4 -> 3 represents 342
 * - Each node contains a single digit (0–9).
 * - Add the two numbers and return the sum as a linked list
 *   (also in reverse order).
 *
 * Key Insight:
 * - Since digits are already in reverse order, we can add them
 *   directly from head to tail (like elementary addition).
 * - No need to reverse the lists.
 *
 * Approach (Elementary Addition with Carry):
 * - Use a dummy node to simplify result list construction.
 * - Traverse both lists simultaneously.
 * - At each step:
 *   1) Read digit from l1 (or 0 if null)
 *   2) Read digit from l2 (or 0 if null)
 *   3) sum = digit1 + digit2 + carry
 *   4) current digit = sum % 10
 *   5) carry = sum / 10
 * - Continue until both lists are exhausted.
 * - If carry remains, append a new node.
 *
 * Why Dummy Node?
 * - Avoids special handling for the head node.
 * - Makes pointer movement uniform and cleaner.
 *
 * Edge Cases Handled:
 * - Different length lists
 * - Carry propagation at the end (e.g., 9 -> 9 + 1)
 * - One list becoming null earlier than the other
 *
 * Time Complexity:
 * - O(max(m, n)) where m and n are lengths of the lists
 *
 * Space Complexity:
 * - O(max(m, n)) for the result list
 * - No extra auxiliary data structures used
 */
public class AddTwoLL {

    public static void main(String[] args) {
        // 2 -> 4 -> 3
        Node headOne = buildLL(2, 4, 3);
        printLL(headOne);
        // 5 -> 6 -> 4
        Node headTwo = buildLL(5,6,4);
        printLL(headTwo);

        Node result = addLL(headOne, headTwo);
        printLL(result);
    }

    /**
     * Time complexity : O(max(m,n))
     * Space complexity : O(max(m,n))
     */
    public static Node addLL(Node l1, Node l2) {

        Node resultDummy = new Node(-1, null);
        Node result = resultDummy;
        int carryOver = 0;

        while (l1 != null || l2 != null) {
            if (result.next == null) {
                result.next = new Node(0);
                result = result.next;
            }
            int val1 = l1 != null ? l1.data : 0;
            int val2 = l2 != null ? l2.data : 0;
            int val = carryOver + val1 + val2;

            result.data = val % 10;
            carryOver = val / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carryOver > 0) {
            result.next = new Node(0);
            result = result.next;
            result.data = carryOver;
        }

        return resultDummy.next;
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
    }
}
