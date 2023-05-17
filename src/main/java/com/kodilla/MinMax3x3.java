package com.kodilla;

public class MinMax3x3 {

    public int[] getBestMove(char[][] board, char computerSymbol) {
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = computerSymbol;
                    int score = minimax(board, 0, computerSymbol, false);
                    board[i][j] = ' ';
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

    public int minimax(char[][] board, int depth, char playerSymbol, boolean isMaximizing) {
        char opponentSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        int result = analyze3x3(board, playerSymbol, opponentSymbol);

        if (result != 0) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = playerSymbol;
                        int score = minimax(board, depth + 1, playerSymbol, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = opponentSymbol;
                        int score = minimax(board, depth + 1, playerSymbol, true);
                        board[i][j] = ' ';
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
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == playerSymbol && board[1][col] == playerSymbol && board[2][col] == playerSymbol) {
                return 1; // Player wins
            } else if (board[0][col] == computerSymbol && board[1][col] == computerSymbol && board[2][col] == computerSymbol) {
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
