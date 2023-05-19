package com.kodilla;

public class MinMax3x3 {

    public int[] getBestMove(char[][] board) {
        char computerSymbol = Symbol.X;
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Symbol.EMPTY_FIELD) {
                    board[i][j] = computerSymbol;
                    int score = minimax(board, 0, false);
                    board[i][j] = Symbol.EMPTY_FIELD;
                    if (score > bestScore) {
                        bestScore = score;
                        row = i;
                        col = j;
                    }
                }
            }
        }
        int[] bestMove = {row, col};
        return bestMove;
    }

    public int minimax(char[][] board, int depth, boolean isMaximizing) {
        char playerSymbol = Symbol.O;
        char computerSymbol = Symbol.X;
        int result = analyze3x3(board, playerSymbol, computerSymbol);

        if (result != 0) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == Symbol.EMPTY_FIELD) {
                        board[i][j] = computerSymbol;
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = Symbol.EMPTY_FIELD;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == Symbol.EMPTY_FIELD) {
                        board[i][j] = playerSymbol;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = Symbol.EMPTY_FIELD;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private int analyze3x3(char[][] board, char playerSymbol, char computerSymbol) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == playerSymbol && board[row][1] == playerSymbol && board[row][2] == playerSymbol) {
                return 1; // Player wins
            } else if (board[row][0] == computerSymbol && board[row][1] == computerSymbol && board[row][2] == computerSymbol) {
                return -1; // Computer wins
            }
        }

        // Check columns
        for (int column = 0; column < 3; column++) {
            if (board[0][column] == playerSymbol && board[1][column] == playerSymbol && board[2][column] == playerSymbol) {
                return 1; // Player wins
            } else if (board[0][column] == computerSymbol && board[1][column] == computerSymbol && board[2][column] == computerSymbol) {
                return -1; // Computer wins
            }
        }

        // Check diagonals
        if ((board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol) ||
                (board[0][2] == playerSymbol && board[1][1] == playerSymbol && board[2][0] == playerSymbol)) {
            return 1; // Player wins
        } else if ((board[0][0] == computerSymbol && board[1][1] == computerSymbol && board[2][2] == computerSymbol) ||
                (board[0][2] == computerSymbol && board[1][1] == computerSymbol && board[2][0] == computerSymbol)) {
            return -1; // Computer wins
        }

        // It's a draw
        return 0;
    }

}
