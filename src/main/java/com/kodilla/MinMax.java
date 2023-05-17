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
                    int score = minimax(board, 0, computerSymbol, howManyInARowToWin, false);
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
        char playerSymbol = (computerSymbol == 'x') ? 'o' : 'x';
        int result = analyze(board, playerSymbol, computerSymbol, howManyInARowToWin);


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
                        /*bestScore = Math.max(score, bestScore);*/
                        if (score > bestScore) {
                            bestScore = score;
                        }
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
                        /*bestScore = Math.min(score, bestScore);*/
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    private int analyze(char[][] board, char playerSymbol, char computerSymbol, int howManyInARowToWin) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            int countPlayer = 0;
            int countComputer = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == playerSymbol) {
                    countPlayer++;
                    countComputer = 0;
                } else if (board[i][j] == computerSymbol) {
                    countComputer++;
                    countPlayer = 0;
                } else {
                    countPlayer = 0;
                    countComputer = 0;
                }
                if (countPlayer == howManyInARowToWin) {
                    return 1;
                } else if (countComputer == howManyInARowToWin) {
                    return -1;
                }
            }
        }

        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            int countPlayer = 0;
            int countComputer = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == playerSymbol) {
                    countPlayer++;
                    countComputer = 0;
                } else if (board[i][j] == computerSymbol) {
                    countComputer++;
                    countPlayer = 0;
                } else {
                    countPlayer = 0;
                    countComputer = 0;
                }
                if (countPlayer == howManyInARowToWin) {
                    return 1;
                } else if (countComputer == howManyInARowToWin) {
                    return -1;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = 0; j <= board[i].length - howManyInARowToWin; j++) {
                // top-left to bottom-right
                boolean diagonal1Player = true;
                boolean diagonal1Computer = true;
                // top-right to bottom-left
                boolean diagonal2Player = true;
                boolean diagonal2Computer = true;
                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j + k] != playerSymbol) {
                        diagonal1Player = false;
                    }
                    if (board[i + k][j + k] != computerSymbol) {
                        diagonal1Computer = false;
                    }
                    if (board[i + k][j + howManyInARowToWin - 1 - k] != playerSymbol) {
                        diagonal2Player = false;
                    }
                    if (board[i + k][j + howManyInARowToWin - 1 - k] != computerSymbol) {
                        diagonal2Computer = false;
                    }
                }
                if (diagonal1Player || diagonal2Player) {
                    return 1;
                } else if (diagonal1Computer || diagonal2Computer) {
                    return -1;
                }
            }
        }
        // Game is not over.
        return 0;
    }
}
