package org.takeuforward.stackAndQueue.learning;

import java.util.Stack;

/**
 * Implement Queue using Two Stacks
 *
 * LeetCode 232:
 * <a href="https://leetcode.com/problems/implement-queue-using-stacks">
 * https://leetcode.com/problems/implement-queue-using-stacks
 * </a>
 *
 * --------------------------------------------------
 * Core Idea:
 * A queue is FIFO, but a stack is LIFO.
 * Using two stacks, we can reverse order twice to simulate FIFO behavior.
 *
 * Two Standard Approaches:
 *
 * --------------------------------------------------
 * Approach 1: Costly Push, Cheap Pop
 *
 * push(x):
 * - Move all elements from primary -> secondary
 * - Push x into primary
 * - Move everything back from secondary -> primary
 *
 * Result:
 * - primary stack always maintains queue order
 * - Top of primary = front of queue
 *
 * Time Complexity:
 * - push: O(n)
 * - pop : O(1)
 * - peek: O(1)
 *
 * Space Complexity:
 * - O(n) for stacks
 *
 * --------------------------------------------------
 * Approach 2: Cheap Push, Amortized Cheap Pop (Used in push1 / pop1)
 *
 * push1(x):
 * - Simply push into primary stack
 * - Time: O(1)
 *
 * pop1():
 * - If secondary is NOT empty → pop from secondary
 * - Else:
 *     - Move all elements from primary -> secondary
 *     - Pop from secondary
 *
 * Why Amortized O(1)?
 * - Each element:
 *     - is pushed once
 *     - moved from primary -> secondary once
 *     - popped once
 * - Total operations for n elements = O(n)
 * - Average per operation = O(1)
 *
 * Time Complexity:
 * - push1: O(1)
 * - pop1 : O(n) worst-case, Amortized O(1)
 *
 * Space Complexity:
 * - O(n)
 *
 * --------------------------------------------------
 * Interview Notes:
 * - Always mention "amortized O(1)" for pop in Approach 2
 * - Explain that expensive transfers happen rarely
 * - This is a classic amortized analysis problem
 *
 * When to use which:
 * - If many pushes → use cheap push version
 * - If many pops → costly push version may be acceptable
 */
public class QueueUsingStack {

    public Stack<Integer> primary = new Stack<>();

    public Stack<Integer> secondary = new Stack<>();

    /**
     * Time complexity : O(n)
     */
    public void push(int x) {
        while (!primary.isEmpty()) {
            secondary.push(primary.pop());
        }
        primary.push(x);
        while (!secondary.isEmpty()) {
            primary.push(secondary.pop());
        }
    }

    /**
     * Time complexity : O(1)
     */
    public void push1(int x) {
        primary.push(x);
    }

    /**
     * Time complexity : O(1)
     */
    public int pop() {
        return primary.pop();
    }

    /**
     * Time complexity : O(n) -> Amortized O(1) Note: O(n) only if secondary is empty.
     */
    public int pop1() {
        if (!secondary.isEmpty()) {
            return secondary.pop();
        }
        while (!primary.isEmpty()) {
            secondary.push(primary.pop());
        }
        return secondary.pop();
    }

    /**
     * Time complexity : O(1)
     */
    public int peek() {
        return primary.peek();
    }

    /**
     * Time complexity : O(1)
     */
    public boolean empty() {
        return primary.isEmpty();
    }

    public void print() {
        System.out.println("Stack : " + primary);
    }
    public static void main(String[] args) {
        QueueUsingStack queue = new QueueUsingStack();
        queue.push(1);
        queue.print();
        queue.push(2);
        queue.print();

        queue.pop();
        queue.print();

        System.out.println("Is stack empty? " + queue.empty());
        queue.push(3);
        queue.print();
        queue.push(4);
        queue.print();

        queue.pop();
        queue.print();
        queue.pop();
        queue.print();
        queue.pop();
        queue.print();
        queue.pop();
        queue.print();
    }


}
