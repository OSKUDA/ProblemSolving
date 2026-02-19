package org.takeuforward.stackAndQueue.medium;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * <h2>LeetCode 735: Asteroid Collision</h2>
 * <p>
 * <a href="https://leetcode.com/problems/asteroid-collision">https://leetcode.com/problems/asteroid-collision</a>
 * </p>
 *
 * <h3>Problem Summary</h3>
 * <ul>
 *   <li>Each asteroid has a <b>direction</b> and <b>size</b>:</li>
 *   <li><b>Positive</b> value → moving right</li>
 *   <li><b>Negative</b> value → moving left</li>
 *   <li>When two asteroids collide, the smaller one explodes.</li>
 *   <li>If both are equal size, both explode.</li>
 * </ul>
 *
 * <h3>Key Observation (When can a collision happen?)</h3>
 * <ul>
 *   <li>A collision only occurs when:</li>
 *   <li><b>stackTop &gt; 0</b> (moving right) and <b>current &lt; 0</b> (moving left)</li>
 *   <li>All other direction combinations never meet (they move apart or same direction).</li>
 * </ul>
 *
 * <h3>Approach</h3>
 * <ul>
 *   <li>Use a stack to represent the asteroids that have "survived so far".</li>
 *   <li>For each incoming asteroid:</li>
 *   <ul>
 *     <li>While there is a possible collision (top is right-moving, current is left-moving):</li>
 *     <ul>
 *       <li>If |current| &gt; |top| → pop top (top explodes), continue checking.</li>
 *       <li>If |current| &lt; |top| → current explodes (do not push current).</li>
 *       <li>If equal → both explode (pop top, do not push current).</li>
 *     </ul>
 *   </ul>
 *   <li>If current asteroid never explodes, push it into stack.</li>
 * </ul>
 *
 * <h3>Why stack works well</h3>
 * <ul>
 *   <li>The stack top is the most recent "active" asteroid that could collide with the incoming one.</li>
 *   <li>Collisions happen like a chain reaction → stack naturally supports this.</li>
 * </ul>
 *
 * <h3>Time Complexity</h3>
 * <ul>
 *   <li><b>O(n)</b> amortized:</li>
 *   <ul>
 *     <li>Each asteroid is pushed at most once.</li>
 *     <li>Each asteroid is popped at most once.</li>
 *     <li>Total stack operations across the whole loop ≤ 2n.</li>
 *   </ul>
 * </ul>
 *
 * <h3>Space Complexity</h3>
 * <ul>
 *   <li><b>O(n)</b> for the stack in the worst case (no collisions).</li>
 * </ul>
 *
 * <h3>Common Pitfalls</h3>
 * <ul>
 *   <li>Forgetting the collision condition: <b>(stack.peek() &gt; 0 && asteroid &lt; 0)</b>.</li>
 *   <li>Not handling equal sizes properly (both must explode).</li>
 *   <li>Not continuing collisions when current destroys stack top (chain reaction).</li>
 *   <li>Building output in correct order: stack pops in reverse → fill result from end to start.</li>
 * </ul>
 *
 * <h3>Mini Example</h3>
 * <pre>
 * Input : [3, 5, -6, 2, -1, 4]
 * Stack progress:
 * 3 → [3]
 * 5 → [3, 5]
 * -6 collides with 5 (6&gt;5) pop 5 → [3]
 * -6 collides with 3 (6&gt;3) pop 3 → []
 * push -6 → [-6]
 * 2 → [-6, 2]
 * -1 collides with 2 (2&gt;1) -1 explodes → [-6, 2]
 * 4 → [-6, 2, 4]
 * Output : [-6, 2, 4]
 * </pre>
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        // 3, 5, -6, 2, -1, 4
        int[] asteroids = {3,5,-6,2,-1,4};
        System.out.println("Input asteroids : " + Arrays.toString(asteroids));
        int[] results = collision(asteroids);
        System.out.println("After collision : " + Arrays.toString(results));

    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    public static int[] collision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return asteroids;
        if (asteroids.length == 1) return asteroids;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean selfDestroy = false;
            while (!stack.isEmpty() && (stack.peek() > 0 && asteroid < 0)) {
                if (asteroid + stack.peek() < 0) { // asteroid bigger
                    stack.pop();
                } else if (asteroid + stack.peek() > 0) { // asteroid smaller
                    selfDestroy = true;
                    break;
                } else if (asteroid + stack.peek() == 0) { // same size
                    stack.pop();
                    selfDestroy = true;
                    break;
                }
            }
            if (!selfDestroy) {
                stack.push(asteroid);
            }
        }

        // prepare result
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }


}
