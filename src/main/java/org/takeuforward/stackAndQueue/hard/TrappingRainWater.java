package org.takeuforward.stackAndQueue.hard;

import java.util.Arrays;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Input arr : " + Arrays.toString(height));
        int result = trap(height);
        System.out.println("Output : " + result);

        height = new int[]{4,2,0,3,2,5};
        System.out.println("Input arr : " + Arrays.toString(height));
        result = trap(height);
        System.out.println("Output : " + result);
    }

    /**
     * Time complexity : O(n) - 3 pass
     * Space complexity : O(n)
     * Trick : Math.min(MaxLeft[i],MaxRight[i]) - height[i]
     */
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            maxLeft[i] = max;
            if (max < height[i]) {
                max = height[i];
            }
        }

        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            maxRight[i] = max;
            if (max < height[i]) {
                max = height[i];
            }
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            height[i] = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (height[i] < 0) height[i] = 0;
            sum += height[i];
        }
        return sum;
    }
}
