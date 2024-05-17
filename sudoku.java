import java.util.*;

public class sudoku {
    // int size = 9;

    public static boolean solve(int[][] board, int row, int col) {
        if(col == board.length){
            col = 0;
            row++;
        }
        if (row == board.length) {
            return true;
        }
        if(board[row][col] != 0){
            return solve(board,row,col+1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)){
                board[row][col] = num;
                if(solve(board, row, col +1)){
                    return true;
                }
                board[row][col] = 0;
            }

        }
        return false;
    }


    public static boolean isValid(int[][] board, int row, int col, int num){
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }


        }
        int firstRow = row - row % 3;
        int firstCol = col - col % 3;
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + firstRow][j + firstCol] == num) {
                    return false;
                }

            }

        }
        return true;


    }



    public static void main(String[] args) {
        int[][] board = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        solve(board,0,0);

        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }

    }

}


