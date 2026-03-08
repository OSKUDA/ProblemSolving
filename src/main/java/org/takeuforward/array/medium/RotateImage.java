package org.takeuforward.array.medium;

import java.util.Arrays;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("Initial image : ");
        printMatrix(matrix);
        System.out.println("Rotate image : ");
        printMatrix(rotateImage(matrix));

        matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("Initial image : ");
        printMatrix(matrix);
        System.out.println("Rotate image");
        printMatrix(rotateImage1(matrix));
    }

    /**
     * Time complexity : O(m * n)
     * Space complexity : O(m * n)
     */
    private static int[][] rotateImage(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int row = 0, columnR = n - 1; row < n && columnR >= 0; row++, columnR--) {
            for (int column = 0, rowR = 0; column < n && rowR < n; column++, rowR++) {
                result[rowR][columnR] = matrix[row][column];
            }
        }

        return result;
    }

    /**
     * Time complexity : O(m * n)
     * Space complexity : O(1)
     */
    private static int[][] rotateImage1(int[][] matrix) {

        // transpose the matrix
        for (int row = 0; row < matrix.length; row++) {
            for (int column = row; column < matrix[row].length; column++) {
                int temp = matrix[row][column];
                matrix[row][column] = matrix[column][row];
                matrix[column][row] = temp;
            }
        }

        // reverse each row
        for (int row = 0; row < matrix.length; row++) {
            int start = 0;
            int end = matrix[row].length - 1;
            while (start <= end) {
                int temp = matrix[row][start];
                matrix[row][start] = matrix[row][end];
                matrix[row][end] = temp;
                start++;
                end--;
            }
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
