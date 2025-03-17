public class Main {
    public static void main(String[] args) {
        int[][] matrix = { // matrix to be transposed
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        transposeMatrix(matrix); // calling the transpose matrix method
    }

    private static void transposeMatrix(int[][] matrix) { // transpose matrix method

        int[][] result = new int[matrix[0].length][matrix.length]; // initializing the result value

        for (int i = 0; i < matrix.length; ++i) { // loop for transposing the matrix
            for (int j = 0; j < matrix[i].length; ++j) {
                result[j][i] = matrix[i][j];
            }
        }

        for (int[] ints : result) { // loop for displaying the matrix
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}