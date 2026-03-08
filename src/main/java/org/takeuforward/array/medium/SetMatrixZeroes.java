package org.takeuforward.array.medium;

import java.util.Arrays;
import java.util.HashSet;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 1, 1}
        };
        System.out.println("Initial matrix : ");
        printMatrix(matrix);
        System.out.println("setMatrixZeroes : ");
        printMatrix(setMatrixZeroes(matrix));

        matrix = new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 1, 1}
        };
        System.out.println("Initial matrix : ");
        printMatrix(matrix);
        System.out.println("setMatrixZeroes1 : ");
        printMatrix(setMatrixZeroes1(matrix));
    }

    /**
     * Time complexity : O(2(m * n))
     * Space complexity : O(m + n)
     */
    private static int[][] setMatrixZeroes(int[][] matrix) {
        HashSet<Integer> columns = new HashSet<>();
        HashSet<Integer> rows = new HashSet<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 0) {
                    columns.add(column);
                    rows.add(row);
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (columns.contains(column) || rows.contains(row)) {
                    matrix[row][column] = 0;
                }
            }
        }

        return matrix;
    }

    /**
     * Time complexity : O(2(m * n) + n + m)
     * Space complexity : O(1)
     */
    private static int[][] setMatrixZeroes1(int[][] matrix) {
        int col0 = 1;

        // step 1 : identify which row and column needs to be set to zero
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 0) {
                    // mark row
                    matrix[row][0] = 0;

                    // mark column
                    if (column != 0) {
                        matrix[0][column] = 0;
                    } else {
                        col0 = 0;
                    }
                }
            }
        }

        // step 2 : set zeros from (1,1) ... (m,n) referring to first column, first row, and col0
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[row].length; column++) {
                if (matrix[0][column] == 0 || matrix[row][0] == 0) {
                    matrix[row][column] = 0;
                }
            }
        }

        // step 3 : set zeroes for 1st row
        if (matrix[0][0] == 0) {
            for (int column = 0; column < matrix[0].length; column++) {
                matrix[0][column] = 0;
            }
        }

        // step 4 : set zeroes for 1st column based on col1
        if (col0 == 0) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
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
