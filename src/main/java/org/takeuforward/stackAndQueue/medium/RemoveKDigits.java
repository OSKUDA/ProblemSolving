package org.takeuforward.stackAndQueue.medium;

import java.util.ArrayDeque;

/**
 * <h2>LeetCode 402: Remove K Digits</h2>
 * <p>
 * <a href="https://leetcode.com/problems/remove-k-digits/">LeetCode Link</a>
 * </p>
 *
 * <h3>Problem Statement</h3>
 * <p>
 * Given a non-negative integer <code>num</code> represented as a string,
 * remove <code>k</code> digits from the number so that the new number is the smallest possible.
 * </p>
 *
 * <h3>Core Idea (Greedy + Monotonic Stack)</h3>
 * <p>
 * To make the number smallest:
 * </p>
 * <ul>
 *   <li>If a larger digit appears before a smaller digit, remove the larger digit.</li>
 *   <li>This ensures lexicographically smaller number.</li>
 * </ul>
 *
 * <p>
 * We maintain a <b>monotonically increasing stack</b>.
 * </p>
 *
 * <h3>Algorithm Steps</h3>
 * <ol>
 *   <li>Traverse each digit in the string.</li>
 *   <li>
 *     While:
 *     <ul>
 *       <li>Stack is not empty</li>
 *       <li>Top of stack &gt; current digit</li>
 *       <li>We still have removals left (removed &lt; k)</li>
 *     </ul>
 *     Pop from stack.
 *   </li>
 *   <li>Push current digit into stack.</li>
 *   <li>
 *     If removals still remain after traversal,
 *     remove from top (handles already increasing sequences like "12345").
 *   </li>
 *   <li>Build result from stack.</li>
 *   <li>Reverse it (because stack reverses order).</li>
 *   <li>Remove leading zeros.</li>
 *   <li>If empty, return "0".</li>
 * </ol>
 *
 * <h3>Why This Greedy Strategy Works</h3>
 * <p>
 * Removing a larger digit before a smaller one reduces the number's magnitude
 * at the highest possible place value. This guarantees minimal final result.
 * </p>
 *
 * <h3>Time Complexity</h3>
 * <ul>
 *   <li>Each digit is pushed once and popped at most once.</li>
 *   <li>All stack operations are amortized O(1).</li>
 *   <li>Building and reversing result: O(n).</li>
 * </ul>
 *
 * <p><b>Overall Time Complexity:</b> O(n)</p>
 *
 * <h3>Space Complexity</h3>
 * <ul>
 *   <li>Stack stores up to n digits → O(n)</li>
 *   <li>StringBuilder stores up to n digits → O(n)</li>
 * </ul>
 *
 * <p><b>Overall Space Complexity:</b> O(n)</p>
 *
 * <h3>Edge Cases Handled</h3>
 * <ul>
 *   <li>k equals length of number → return "0"</li>
 *   <li>Leading zeros after removals → stripped</li>
 *   <li>Already increasing sequence → remove from end</li>
 *   <li>All digits removed → return "0"</li>
 * </ul>
 *
 * <h3>Example</h3>
 * <pre>
 * Input:  num = "1432219", k = 3
 * Output: "1219"
 *
 * Input:  num = "10200", k = 1
 * Output: "200"
 *
 * Input:  num = "10", k = 2
 * Output: "0"
 * </pre>
 *
 * <h3>Interview Keywords</h3>
 * <ul>
 *   <li>Monotonic Stack</li>
 *   <li>Greedy Strategy</li>
 *   <li>Amortized Analysis</li>
 *   <li>Lexicographical Minimization</li>
 * </ul>
 */
public class RemoveKDigits {
    public static void main(String[] args) {
        String num = "33526221184202197273";
        int k = 19;
        System.out.println("Input : " + num + ", k = " + k);
        String result = remove(num, k);
        System.out.println("Output : " + result);

    }

    /**
     * Time complexity : O(n + k + n + n + n) => O(n + k)
     * Space complexity : O(n + n) => O(n)
     */
    public static String remove(String num, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int removed = 0;
        for (char c : num.toCharArray()) {      // O(n)
            int val = c - '0';
            while (!stack.isEmpty() && stack.peek() > val && removed < k) {
                stack.pop();
                removed++;
            }
            stack.push(val);
        }
        // remove from tos
        while (removed < k && !stack.isEmpty()) { // O(k)
            stack.pop();
            removed++;
        }

        // build result
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) { // O(n)
            sb.append(stack.pop());
        }
        sb.reverse(); // O(n)
        // remove leading zeros
        String result = sb.toString();
        int i = 0;
        while (i < result.length() && result.charAt(i) == '0') { // O(n)
            i++;
        }
        result = result.substring(i);
        return result.isEmpty() ? "0" : result;
    }
}
