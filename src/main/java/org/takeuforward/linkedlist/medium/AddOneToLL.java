package org.takeuforward.linkedlist.medium;

import java.util.Stack;

/**
 * Problem:
 * - Given a non-negative number represented as a singly linked list,
 *   where each node contains a single digit (most significant digit first),
 *   add 1 to the number and return the resulting linked list.
 *
 * Example:
 * - 4 -> 5 -> 6  =>  4 -> 5 -> 7
 * - 9 -> 9 -> 9  =>  1 -> 0 -> 0 -> 0
 *
 * Key Challenge:
 * - Addition starts from the tail, but singly linked lists can only be
 *   traversed forward.
 *
 * --------------------------------------------------------------------
 * APPROACH 1: Stack-based (addOne)
 * --------------------------------------------------------------------
 * Idea:
 * - Push all digits onto a stack.
 * - Pop digits from the stack to simulate addition from the least
 *   significant digit.
 * - Handle carry and update nodes.
 *
 * Time Complexity: O(n)  (multiple passes)
 * Space Complexity: O(n) (stack)
 *
 * Pros:
 * - Straightforward and intuitive.
 *
 * Cons:
 * - Uses extra memory.
 *
 * --------------------------------------------------------------------
 * APPROACH 2: Reverse + Add + Reverse (addOne1)
 * --------------------------------------------------------------------
 * Idea:
 * - Reverse the linked list.
 * - Add 1 like a normal number with carry propagation.
 * - Reverse the list back to restore original order.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Pros:
 * - Space-optimal.
 * - Common interview solution.
 *
 * Cons:
 * - Requires modifying the list twice.
 *
 * --------------------------------------------------------------------
 * APPROACH 3: Recursive Carry Propagation (addOne2) ⭐
 * --------------------------------------------------------------------
 * Idea:
 * - Use recursion to reach the tail.
 * - Add 1 at the end and propagate carry backward.
 * - If carry remains after processing head, prepend a new node.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) (recursion stack)
 *
 * Why this is elegant:
 * - Naturally simulates digit-by-digit addition from right to left.
 * - No explicit reversal or extra data structures.
 *
 * Interview Insight:
 * - Best conceptual solution.
 * - Reverse-based solution is space-optimal.
 * - Recursive solution shows strong problem-solving skills.
 *
 * Final Takeaway:
 * - Know all three approaches.
 * - Prefer reverse-based for optimal space.
 * - Use recursion to demonstrate deeper understanding.
 */
public class AddOneToLL {

    public static void main(String[] args) {
        // 4 -> 5 -> 6
        Node head = buildLL(4,5,6);
        printLL(head);

        head = addOne2(head);
        printLL(head);

        head = buildLL(9,9,9);
        printLL(head);

        head = addOne2(head);
        printLL(head);

    }

    /**
     * Time complexity : O(n) 3-pass
     * Space complexity : O(n)
     */
    public static Node addOne(Node head) {
        if (head == null) return null;
        Stack<Integer> integers = new Stack<>();

        Node curr = head;
        while (curr != null) {
            integers.push(curr.data);
            curr = curr.next;
        }

        curr = head;
        int carryOver = 1;
        while (carryOver > 0 || curr != null) {
            int currVal = integers.isEmpty() ? 0 : integers.pop();
            int value = currVal + carryOver;
            if (value > 9) {
                curr.data = value % 10;
                carryOver  = value / 10;
            } else {
                curr.data = value;
                carryOver = 0;
            }
            if (carryOver > 0 && curr.next == null) {
                curr.next = new Node(0, null);
            }
            curr = curr.next;
        }

        return reverse(head);
    }

    /**
     * Time complexity : O(n) 3 pass
     * Space complexity : O(1)
     */
    public static Node addOne1(Node head) {
        if (head == null) return null;

        head = reverse(head);
        int carryForward = 1;

        Node curr = head;
        while (curr != null && carryForward > 0) {
            int val = curr.data + carryForward;

            if (val > 9) {
                curr.data = val % 10;
                carryForward = val / 10;
            } else {
                curr.data = val;
                carryForward = 0;
            }

            if (carryForward > 0 && curr.next == null) {
                Node node = new Node(0, null);
                curr.next = node;
            }

            curr = curr.next;
        }
        return reverse(head);
    }

    /**
     * Time complexity : O(n) 1 pass
     * Space complexity : O(n) recursion stack
     */
    public static Node addOne2(Node head) {
        if (head == null) return null;

        int carry = add(head);
        if (carry > 0) {
            head = new Node(carry, head);
        }
        return head;
    }

    public static int add(Node node) {
        if (node == null) {
            return 1;
        }
        int carryOver = add(node.next);
        int val = node.data + carryOver;
        if (val > 9) {
            node.data = val % 10;
            carryOver = val / 10;
        } else {
            node.data = val;
            carryOver = 0;
        }
        return carryOver;
    }

    public static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
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
    }
}
