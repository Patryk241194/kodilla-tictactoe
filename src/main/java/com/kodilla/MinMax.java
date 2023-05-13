package com.kodilla;

public class MinMax {

    public int[] getBestMove(char[][] board, char computerSymbol, int howManyInARowToWin) {
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = computerSymbol;
                    int score = minimax(board, 0, computerSymbol, howManyInARowToWin, true);
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

    public int minimax(char[][] board, int depth, char computerSymbol, int howManyInARowToWin, boolean isMaximizing) {
        int result = analyze(board, computerSymbol, howManyInARowToWin);
        char playerSymbol = (computerSymbol == 'x') ? 'o' : 'x';

        if (result != 0) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = computerSymbol;
                        int score = minimax(board, depth + 1, computerSymbol, howManyInARowToWin, false);
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
                        board[i][j] = playerSymbol;
                        int score = minimax(board, depth + 1, computerSymbol, howManyInARowToWin, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    // Method analyzes board to determine if the game is over and who has won, or if it is still ongoing.
    private int analyze(char[][] board, char playerSymbol, int howManyInARowToWin) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            int count = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == playerSymbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == howManyInARowToWin) {
                    return 1;
                }
            }
        }

        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == playerSymbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == howManyInARowToWin) {
                    return 1;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = 0; j <= board[i].length - howManyInARowToWin; j++) {
                // top-left to bottom-right
                boolean diagonal1 = true;
                // top-right to bottom-left
                boolean diagonal2 = true;
                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j + k] != playerSymbol) {
                        diagonal1 = false;
                    }
                    if (board[i + k][j + howManyInARowToWin - k - 1] != playerSymbol) {
                        diagonal2 = false;
                    }
                }
                if (diagonal1 || diagonal2) {
                    return 1;
                }
            }
        }
        // Game is not over.
        return 0;
    }
}
