package org.takeuforward.stackAndQueue.learning;

/**
 * Min Stack implementation using a Singly Linked List.
 *
 * LeetCode: https://leetcode.com/problems/min-stack/
 *
 * ---------------------------------------------------
 * Problem:
 * Design a stack that supports the following operations in O(1) time:
 * - push(x)
 * - pop()
 * - top()
 * - getMin()
 *
 * ---------------------------------------------------
 * Core Idea:
 * Instead of using an extra stack, each node stores:
 * 1) its own value
 * 2) the minimum value of the stack *up to that node*
 *
 * This way:
 * - The current minimum is always available at the top node
 * - No traversal or auxiliary data structure is needed
 *
 * ---------------------------------------------------
 * Node Structure:
 * Node {
 *   int val;   // actual stack value
 *   int min;   // minimum value till this node
 *   Node next; // pointer to previous stack node
 * }
 *
 * ---------------------------------------------------
 * Push Logic:
 * - Maintain a global `min`
 * - On push(x):
 *     min = min(min, x)
 *     create a new node with (x, min)
 *     make it the new top
 *
 * ---------------------------------------------------
 * Pop Logic:
 * - Remove the top node
 * - Restore `min` from the new top's stored min
 * - If stack becomes empty, reset min to Integer.MAX_VALUE
 *
 * ---------------------------------------------------
 * Why this works:
 * - Each node remembers the minimum till that point
 * - When nodes are popped, the correct minimum is restored automatically
 *
 * ---------------------------------------------------
 * Time Complexity:
 * - push   : O(1)
 * - pop    : O(1)
 * - peek   : O(1)
 * - getMin : O(1)
 *
 * Space Complexity:
 * - O(n), where n is the number of elements in the stack
 * - Each node stores constant extra information (min)
 *
 * ---------------------------------------------------
 * Interview Notes:
 * - Cleaner than the 2-stack approach
 * - Easier to explain than value-encoding tricks
 * - Very safe and readable solution
 * - Strong choice for LLD / production discussion
 */
public class MinStackUsingLL {

    Node tos = null;
    int min = Integer.MAX_VALUE;

    /**
     * Time complexity : O(1)
     */
    public void push(int x) {
        if (x < min) min = x;
        if (tos == null) {
            tos = new Node(x, min, null);
        } else {
            tos = new Node(x, min, tos);
        }
    }

    /**
     * Time complexity : O(1)
     */
    public void pop() {
        Node next = tos.next;
        tos.next = null;
        tos = next;
        min = tos == null ? Integer.MAX_VALUE : tos.min;
    }

    /**
     * Time complexity : O(1)
     */
    public int peek() {
        return tos.val;
    }

    /**
     * Time complexity : O(1)
     */
    public int min() {
        return min;
    }

    /**
     * Time complexity : O(1)
     */
    public boolean isEmpty() {
        return tos == null;
    }

    /**
     * Time complexity : O(n)
     */
    public void printLL() {
        Node curr = tos;
        System.out.print("List : ");
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? "->" : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinStackUsingLL minStack = new MinStackUsingLL();
        minStack.push(1);
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.push(2);
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.push(3);
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.push(0);
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.push(4);
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.push(-1);
        minStack.printLL();
        System.out.println("Min is " + minStack.min());

        minStack.pop();
        minStack.printLL();
        System.out.println("Min is " + minStack.min());

        minStack.pop();
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.pop();
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.pop();
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.pop();
        minStack.printLL();
        System.out.println("Min is " + minStack.min());
        minStack.pop();
        minStack.printLL();
        System.out.println("Min is " + minStack.min());


    }

    public static class Node {
        int val;
        int min;
        Node next;
        Node(int val, int min, Node next) {this.val = val; this.min = min; this.next = next;}
    }
}
