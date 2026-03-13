package org.takeuforward.heap.medium;

import java.util.*;

/**
 * <h2>Task Scheduler</h2>
 *
 * <p>
 * LeetCode Problem:
 * <a href="https://leetcode.com/problems/task-scheduler/">
 * https://leetcode.com/problems/task-scheduler/
 * </a>
 * </p>
 *
 * <h3>Problem Statement</h3>
 *
 * <p>
 * You are given a list of CPU tasks represented by characters and a cooling
 * interval <b>n</b>.
 * </p>
 *
 * <p>
 * The same task must have at least <b>n</b> intervals between two executions.
 * </p>
 *
 * <p>
 * Each interval can either execute a task or stay <b>idle</b>.
 * </p>
 *
 * <p>
 * Return the <b>minimum number of CPU intervals</b> required to finish all tasks.
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * tasks = [A,A,A,B,B,B]
 * n = 2
 *
 * Valid schedule:
 *
 * A → B → idle → A → B → idle → A → B
 *
 * Total intervals = 8
 * </pre>
 *
 * <hr>
 *
 * <h3>Key Observation</h3>
 *
 * <p>
 * The task with the <b>highest frequency</b> determines the minimum schedule
 * length because it creates the largest cooling constraints.
 * </p>
 *
 * <p>
 * Two main approaches exist:
 * </p>
 *
 * <ul>
 *     <li>Max Heap + Cooldown Queue (simulation)</li>
 *     <li>Greedy mathematical formula</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 1 — Max Heap + Cooldown Queue</h3>
 *
 * <p>
 * Simulate CPU execution using:
 * </p>
 *
 * <ul>
 *     <li><b>Max Heap</b> → always schedule the task with highest remaining frequency.</li>
 *     <li><b>Cooldown Queue</b> → store tasks that cannot be executed yet.</li>
 * </ul>
 *
 * <h4>Data Structures</h4>
 *
 * <pre>
 * HashMap          → count task frequencies
 * PriorityQueue    → max heap based on frequency
 * Queue<int[]>     → tasks in cooldown
 * </pre>
 *
 * <h4>Algorithm</h4>
 *
 * <ol>
 *     <li>Count frequency of each task.</li>
 *     <li>Insert frequencies into a max heap.</li>
 *     <li>Maintain a cooldown queue storing:
 *         <pre>[remainingFrequency, nextAvailableTime]</pre>
 *     </li>
 *     <li>For each CPU tick:
 *         <ul>
 *             <li>Execute the most frequent task from heap.</li>
 *             <li>Decrease its frequency.</li>
 *             <li>If still remaining → move it to cooldown queue.</li>
 *         </ul>
 *     </li>
 *     <li>When cooldown expires → move task back to heap.</li>
 * </ol>
 *
 * <h4>Complexity</h4>
 *
 * <pre>
 * Time Complexity  : O(n log k)
 * Space Complexity : O(k)
 *
 * n = total number of tasks
 * k = number of unique tasks
 * </pre>
 *
 * <hr>
 *
 * <h3>Approach 2 — Greedy Formula (Optimal)</h3>
 *
 * <p>
 * Instead of simulating scheduling, we can derive the schedule length
 * using greedy reasoning.
 * </p>
 *
 * <h4>Step 1 — Identify Most Frequent Task</h4>
 *
 * <pre>
 * maxFreq = maximum frequency among tasks
 * </pre>
 *
 * <h4>Step 2 — Count Tasks with Same Maximum Frequency</h4>
 *
 * <pre>
 * countMax = number of tasks having frequency = maxFreq
 * </pre>
 *
 * <h4>Step 3 — Build Scheduling Blocks</h4>
 *
 * <pre>
 * (maxFreq - 1) blocks
 * each block size = (n + 1)
 * </pre>
 *
 * Example:
 *
 * <pre>
 * A _ _ A _ _ A
 * </pre>
 *
 * <h4>Formula</h4>
 *
 * <pre>
 * scheduleLength =
 * (maxFreq - 1) * (n + 1) + countMax
 * </pre>
 *
 * <p>
 * However, if there are enough tasks to fill idle slots,
 * the schedule cannot be shorter than the number of tasks.
 * </p>
 *
 * <pre>
 * result = max(tasks.length, scheduleLength)
 * </pre>
 *
 * <h4>Example</h4>
 *
 * <pre>
 * tasks = [A,A,A,B,B,B]
 * n = 2
 *
 * maxFreq = 3
 * countMax = 2
 *
 * (3 - 1) * (2 + 1) + 2
 * = 2 * 3 + 2
 * = 8
 * </pre>
 *
 * <h4>Complexity</h4>
 *
 * <pre>
 * Time Complexity  : O(n log n)
 * Space Complexity : O(1)
 * </pre>
 *
 * (Sorting only 26 characters in practice → effectively constant)
 *
 * <hr>
 *
 * <h3>Why Greedy Works</h3>
 *
 * <p>
 * The most frequent task determines the structure of the schedule.
 * Once those tasks are placed with cooldown gaps,
 * remaining tasks simply fill the empty slots.
 * </p>
 *
 * <hr>
 *
 * <h3>Summary</h3>
 *
 * <table border="1">
 * <tr>
 *     <th>Approach</th>
 *     <th>Idea</th>
 *     <th>Time Complexity</th>
 *     <th>Space</th>
 * </tr>
 * <tr>
 *     <td>Max Heap</td>
 *     <td>Simulate CPU scheduling</td>
 *     <td>O(n log k)</td>
 *     <td>O(k)</td>
 * </tr>
 * <tr>
 *     <td>Greedy Formula</td>
 *     <td>Compute idle slots mathematically</td>
 *     <td>O(n log n)</td>
 *     <td>O(1)</td>
 * </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>Interview Takeaways</h3>
 *
 * <ul>
 *     <li>Most frequent task dictates schedule constraints.</li>
 *     <li>Heap solution demonstrates scheduling simulation.</li>
 *     <li>Greedy formula is the optimal analytical solution.</li>
 *     <li>Recognize this problem as a <b>frequency-based greedy scheduling problem</b>.</li>
 * </ul>
 *
 */
public class TaskScheduler {
    public static void main(String[] args) {
        char[] arr = new char[]{'A','A','A','B','B','B'};
        int n = 2;
        System.out.println("Input array : " + Arrays.toString(arr));
        int i = leastInterval(arr, n);
        System.out.println("Output : " + i);

        arr = new char[]{'B','C','D','A','A','A','A','G'};
        n = 1;
        System.out.println("Input array : " + Arrays.toString(arr));
        i = leastInterval(arr, n);
        System.out.println("Output : " + i);
    }

    /**
     * Time complexity : O(n + k log(k) + n log(k)) -> O(n log(k)) here, n is number of elements in tasks, k is unique elements
     * Space complexity : O(k + k + k) -> O(k)
     */
    private static int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : tasks) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            priorityQueue.offer(entry.getValue());
        }

        Queue<int[]> cooldownQueue = new LinkedList<>();

        int tick = 0;
        while (!priorityQueue.isEmpty() || !cooldownQueue.isEmpty()) {
            tick++;
            if (!priorityQueue.isEmpty()) {
                int value = priorityQueue.poll();
                value--;
                if (value > 0) {
                    cooldownQueue.add(new int[]{value, tick + n});
                }
            }
            // check cool down queue
            if (!cooldownQueue.isEmpty()) {
                if (cooldownQueue.peek()[1] == tick) {
                    priorityQueue.offer(cooldownQueue.poll()[0]);
                }
            }
        }

        return tick;
    }

    /**
     * Time complexity : O(n + n log(n)) -> O(n log(n))
     * Space complexity : O(1)
     * Note : Uses greedy approach.
     */
    public int leastInterval1(char[] tasks, int n) {

        int[] freq = new int[26];
        for(char t : tasks)
            freq[t - 'A']++;

        Arrays.sort(freq);

        int maxFreq = freq[25];
        int countMax = 1;

        for(int i = 24; i >= 0; i--){
            if(freq[i] == maxFreq)
                countMax++;
            else
                break;
        }

        int result = (maxFreq - 1) * (n + 1) + countMax;

        return Math.max(tasks.length, result);
    }
}
