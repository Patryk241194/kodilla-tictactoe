/*
package com.kodilla.backend;

public class MinMax {

    public int[] getBestMove(char[][] board, int howManyInARowToWin, int maxDepth) {
        char computerSymbol = Symbol.X;
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Symbol.EMPTY_FIELD) {
                    board[i][j] = computerSymbol;
                    int score = alphaBeta(board, 0, howManyInARowToWin, Integer.MIN_VALUE, Integer.MAX_VALUE, false, maxDepth);
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

    public int alphaBeta(char[][] board, int depth, int howManyInARowToWin, int alpha, int beta, boolean isMaximizing, int maxDepth) {
        char playerSymbol = Symbol.O;
        char computerSymbol = Symbol.X;
        int result = GameLogics.verifyWinner(board, playerSymbol, computerSymbol, howManyInARowToWin);

        if (result != 0 || depth == maxDepth) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == Symbol.EMPTY_FIELD) {
                        board[i][j] = computerSymbol;
                        int score = alphaBeta(board, depth + 1, howManyInARowToWin, alpha, beta, false, maxDepth);
                        board[i][j] = Symbol.EMPTY_FIELD;
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, score);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestScore == Integer.MIN_VALUE ? 0 : bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == Symbol.EMPTY_FIELD) {
                        board[i][j] = playerSymbol;
                        int score = alphaBeta(board, depth + 1, howManyInARowToWin, alpha, beta, true, maxDepth);
                        board[i][j] = Symbol.EMPTY_FIELD;
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, score);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestScore == Integer.MAX_VALUE ? 0 : bestScore;
        }
    }
}
*/
