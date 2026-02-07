package org.takeuforward.stackAndQueue.learning;

import java.util.Stack;

/**
 * LeetCode 155: Min Stack
 * <a href="https://leetcode.com/problems/min-stack/">https://leetcode.com/problems/min-stack/</a>
 *
 * Problem:
 * - Design a stack that supports push, pop, top, and retrieving the minimum element
 *   in constant time.
 *
 * Core Idea:
 * - Use TWO stacks:
 *   1) Primary stack -> stores actual values
 *   2) Secondary stack -> stores the minimum value at each level
 *
 * Key Insight:
 * - For every element pushed into the primary stack, we also push the
 *   current minimum into the secondary stack.
 * - This guarantees that the top of the secondary stack always holds
 *   the minimum value of the stack so far.
 *
 * Push Logic:
 * - Compare incoming value with current minimum
 * - Push value to primary stack
 * - Push updated minimum to secondary stack
 *
 * Pop Logic:
 * - Pop from both stacks simultaneously
 * - Restore minimum using secondary stack's top
 *
 * GetMin Logic:
 * - Simply return secondary.peek()
 *
 * Why this works:
 * - The secondary stack mirrors the primary stack size
 * - Each index stores the minimum up to that point
 * - No scanning or recomputation needed
 *
 * Time Complexity:
 * - push()   -> O(1)
 * - pop()    -> O(1)
 * - top()    -> O(1)
 * - getMin() -> O(1)
 *
 * Space Complexity:
 * - O(n)
 *   (Auxiliary stack stores one element per primary stack element)
 *
 * Interview Notes:
 * - This is the STANDARD optimal solution expected in interviews.
 * - Avoid scanning the stack to find minimum (O(n)) during getMin().
 * - Space O(2n) simplifies to O(n) in Big-O notation.
 *
 * Common Pitfalls:
 * - Forgetting to sync pops between primary and secondary stacks
 * - Trying to compute min on-demand instead of tracking it incrementally
 *
 * Variations:
 * - Single-stack solution using encoded values (advanced)
 * - LinkedList-based implementation (same asymptotic complexity)
 */
public class MinStack {

    public Stack<Integer> primary = new Stack<>();
    public Stack<Integer> secondary = new Stack<>();

    public int min = Integer.MAX_VALUE;

    /**
     * Time complexity : O(1)
     */
    public void push(int x) {
        primary.push(x);
    }

    /**
     * Time complexity : O(1)
     */
    public void push1(int x) {
        if (x < min) min = x;
        primary.push(x);
        secondary.push(min);
    }

    /**
     * Time complexity : O(1)
     */
    public int pop() {
        return primary.pop();
    }

    /**
     * Time complexity : O(1)
     */
    public int pop1() {
        secondary.pop();
        min = secondary.isEmpty() ? Integer.MAX_VALUE : secondary.peek();
        return primary.pop();
    }

    /**
     * Time complexity : O(1)
     */
    public int peek() {
        return primary.peek();
    }

    /**
     * Time complexity : O(n)
     */
    public int min() {
        int min = Integer.MAX_VALUE;
        while (!primary.isEmpty()) {
            int value = primary.peek();
            if (value < min) {
                min = value;
            }
            secondary.push(primary.pop());
        }
        while (!secondary.isEmpty()) {
            primary.push(secondary.pop());
        }
        return min;
    }

    /**
     * Time complexity : O(1)
     */
    public int min1() {
        return min;
    }

    public static void main(String[] args) {

    }
}
