import java.util.Arrays;

public class Queens {


    public static boolean solve(int[][] board, int col) {
        if (col == board.length) {
            return true;
        }

        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (solve(board, col + 1)) {
                    return true;

                }
                board[row][col] = 0;
            }


        }
        return false;
    }


    private static boolean isSafe(int[][] board, int row, int col) {
        return isRowSafe(board, row, col) && isUpperSafe(board, row, col) && isLowerSafe(board, row, col);
    }

    private static boolean isRowSafe(int[][] board, int row, int col) {
        for (int i = 0; col - i >= 0; i++) {
            if (board[row][col - i] == 1) {
                return false;
            }

        }
        return true;
    }

    private static boolean isUpperSafe(int[][] board, int row, int col) {
        for (int i = 0; row - i >= 0 && col - i >= 0; i++) {
            if (board[row - i][col - i] == 1) {
                return false;
            }

        }
        return true;
    }

    private static boolean isLowerSafe(int[][] board, int row, int col) {
        for (int i = 0; row + i < board.length && col - i >= 0; i++) {
            if (board[row + i][col - i] == 1) {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = new int[8][8];

        solve(board, 0);
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}

