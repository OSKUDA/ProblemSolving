package org.takeuforward.binarysearch.onedimensionalarray;

import java.util.Arrays;

/**
 * <h2>Find Peak Element</h2>
 *
 * <p>
 * A <b>peak element</b> is an element that is strictly greater than its adjacent elements.
 * For boundary elements:
 * </p>
 * <ul>
 *   <li>First element is a peak if it is greater than the second.</li>
 *   <li>Last element is a peak if it is greater than the second last.</li>
 * </ul>
 *
 * <p>
 * LeetCode reference:
 * <a href="https://leetcode.com/problems/find-peak-element/" target="_blank">
 * https://leetcode.com/problems/find-peak-element/
 * </a>
 * </p>
 *
 * <hr>
 *
 * <h3>Example</h3>
 *
 * <pre>
 * Input  : [1, 2, 3, 1]
 * Output : 2
 *
 * Explanation:
 * nums[2] = 3 is greater than nums[1] = 2 and nums[3] = 1
 * </pre>
 *
 * <pre>
 * Input  : [1, 2, 1, 3, 5, 6, 4]
 * Output : 1 or 5
 *
 * Explanation:
 * Both indices 1 and 5 are valid peak positions.
 * </pre>
 *
 * <hr>
 *
 * <h3>Approach 1: Linear Scan</h3>
 *
 * <p>
 * Check:
 * </p>
 * <ul>
 *   <li>first element</li>
 *   <li>last element</li>
 *   <li>every middle element</li>
 * </ul>
 *
 * <p>
 * A middle element <code>arr[i]</code> is a peak if:
 * </p>
 *
 * <pre>
 * arr[i - 1] &lt; arr[i] &amp;&amp; arr[i] &gt; arr[i + 1]
 * </pre>
 *
 * <b>Time Complexity</b>
 * <ul>
 *   <li>O(n)</li>
 * </ul>
 *
 * <b>Space Complexity</b>
 * <ul>
 *   <li>O(1)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Approach 2: Binary Search (Optimal)</h3>
 *
 * <p>
 * This problem can be optimized using binary search because of an important observation:
 * </p>
 *
 * <ul>
 *   <li>If we are on an <b>ascending slope</b>, then a peak must exist on the <b>right side</b>.</li>
 *   <li>If we are on a <b>descending slope</b>, then a peak must exist on the <b>left side</b> (or current side).</li>
 * </ul>
 *
 * <p>
 * So, instead of checking all elements, we can discard half of the search space each time.
 * </p>
 *
 * <hr>
 *
 * <h3>Binary Search Logic</h3>
 *
 * <p>
 * For a middle element <code>mid</code>:
 * </p>
 *
 * <ul>
 *   <li>If <code>arr[mid - 1] &lt; arr[mid] &amp;&amp; arr[mid] &gt; arr[mid + 1]</code>,
 *       then <code>mid</code> itself is the peak.</li>
 *   <li>If <code>arr[mid - 1] &lt; arr[mid]</code>,
 *       then we are in an increasing region → move right.</li>
 *   <li>Otherwise, we are in a decreasing region → move left.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Intuition (Hill Driving Analogy)</h3>
 *
 * <p>
 * Imagine driving through a hilly region:
 * </p>
 *
 * <ul>
 *   <li>If the road is going upward, a peak must lie ahead.</li>
 *   <li>If the road is going downward, the peak lies behind you or at your current area.</li>
 * </ul>
 *
 * <p>
 * This is why binary search works: the local slope tells us which half can be eliminated.
 * </p>
 *
 * <hr>
 *
 * <h3>Edge Cases</h3>
 *
 * <ul>
 *   <li>Single element array → that element is a peak.</li>
 *   <li>First element can be peak.</li>
 *   <li>Last element can be peak.</li>
 *   <li>There may be multiple peaks; returning any one valid peak is acceptable.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Time Complexity</h3>
 *
 * <ul>
 *   <li>Linear scan: O(n)</li>
 *   <li>Binary search: O(log n)</li>
 * </ul>
 *
 * <h3>Space Complexity</h3>
 *
 * <ul>
 *   <li>O(1)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Interview Notes</h3>
 *
 * <ul>
 *   <li>This is a classic example of applying binary search on an <b>answer property</b>, not on a sorted array.</li>
 *   <li>The array is not fully sorted, but local slope information is enough to eliminate half the search space.</li>
 *   <li>Always handle boundary elements separately to avoid out-of-bounds checks inside binary search.</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>Summary</h3>
 *
 * <ul>
 *   <li>Linear scan is straightforward and easy to derive.</li>
 *   <li>Binary search is optimal and relies on the increasing/decreasing slope observation.</li>
 *   <li>Peak existence is guaranteed under the problem definition.</li>
 * </ul>
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 5, 1};
        System.out.println("Initial array : " + Arrays.toString(arr));
        System.out.println("Peak element index: " + peakElement(arr));
        System.out.println("Peak element index: " + peakElement1(arr));

        arr = new int[]{1, 2, 3, 1};
        System.out.println("Initial array : " + Arrays.toString(arr));
        System.out.println("Peak element index: " + peakElement(arr));
        System.out.println("Peak element index: " + peakElement1(arr));
    }

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    private static int peakElement(int[] arr) {
        if (arr.length == 1) return 0;
        // check if first element is peak
        if (arr[0] > arr[1]) return 0;
        // check if last element is peak
        if (arr[arr.length - 2] < arr[arr.length - 1]) return arr.length - 1;

        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Time complexity : O(log(n))
     * Space complexity : O(1)
     * Trick: Imagine that I am driving a car in a hilly region. I know that my starting point and ending point have the same altitude, i.e., 0 meters.
     * Now, at any given time, if I am climbing uphill, I can say that the peak will come in the future. If we’re going up, we will definitely reach a peak and then descend back down to 0 meters.
     * If I am currently descending, then either I have already conquered the peak, or I can expect a peak to come later.
     * Using the information about whether I am currently ascending or descending, we can eliminate the left or right half when searching for the peak.
     **/
    private static int peakElement1(int[] arr) {
        if (arr.length == 1) return 0;
        // check if first element is peak
        if (arr[0] > arr[1]) return 0;
        // check if last element is peak
        if (arr[arr.length - 2] < arr[arr.length - 1]) return arr.length - 1;

        int low = 1;
        int high = arr.length - 2;
        while (low <= high) {
            int mid = low + ((high - low) / 2);

            // check if mid is peak
            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) return mid;

            // check if we're in left half
            if (arr[mid - 1] < arr[mid]) {
                // move to right half
                low = mid + 1;
            } else {
                // we're in right half
                high = mid - 1;
            }
        }
        return -1;
    }
}
