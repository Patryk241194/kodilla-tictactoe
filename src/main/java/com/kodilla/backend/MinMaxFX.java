package com.kodilla.backend;

import com.kodilla.frontend.TileBoard;

public class MinMaxFX {

    private Player player1;
    private Player player2;

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int[] getBestMove(TileBoard.Tile[][] tiles, int howManyInARowToWin, int maxDepth, Player player1, Player player2) {
        setPlayer1(player1);
        setPlayer2(player2);
        String computerSymbol = player2.getSymbol();
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getValue().isEmpty()) {
                    tiles[i][j].setValue(computerSymbol);
                    int score = alphaBeta(tiles, 0, howManyInARowToWin, Integer.MIN_VALUE, Integer.MAX_VALUE, false, maxDepth);
                    tiles[i][j].setValue(Symbol.EMPTY_FIELD);
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

    public int alphaBeta(TileBoard.Tile[][] tiles, int depth, int howManyInARowToWin, int alpha, int beta, boolean isMaximizing, int maxDepth) {
        String playerSymbol = player1.getSymbol();
        String computerSymbol = player2.getSymbol();
        int result = GameLogicsFX.verifyWinner(tiles, playerSymbol, computerSymbol, howManyInARowToWin);

        if (result != 0 || depth == maxDepth) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (tiles[i][j].getValue().isEmpty()) {
                        tiles[i][j].setValue(computerSymbol);
                        int score = alphaBeta(tiles, depth + 1, howManyInARowToWin, alpha, beta, false, maxDepth);
                        tiles[i][j].setValue(Symbol.EMPTY_FIELD);
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
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (tiles[i][j].getValue().isEmpty()) {
                        tiles[i][j].setValue(playerSymbol);
                        int score = alphaBeta(tiles, depth + 1, howManyInARowToWin, alpha, beta, true, maxDepth);
                        tiles[i][j].setValue(Symbol.EMPTY_FIELD);
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
