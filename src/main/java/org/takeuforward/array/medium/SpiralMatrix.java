package org.takeuforward.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        System.out.println("Initial matrix : ");
        printMatrix(matrix);
        System.out.println("Spiral matrix : " + spiralMatrix(matrix));

        matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("Initial matrix : ");
        printMatrix(matrix);
        System.out.println("Spiral matrix : " + spiralMatrix(matrix));

    }

    /**
     * Time complexity : O(m * n)
     * Space complexity : O(1)
     */
    private static List<Integer> spiralMatrix(int[][] matrix) {
        int up = 0, left = 0, right = matrix[0].length - 1, down = matrix.length - 1;

        List<Integer> result = new ArrayList<>();

        while (left <= right && up <= down) {

            // move right
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            up++;

            // move down
            for (int i = up; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // move left
            if (up <= down) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[down][i]);
                }
                down--;
            }

            // move up
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
