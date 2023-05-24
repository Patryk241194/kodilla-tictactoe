package com.kodilla.backend;

import java.util.Arrays;

public class GameBoard {
    private final int howManyInARowToWin;
    private final char[][] board;

    public GameBoard(int size, int howManyInARowToWin) {
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Symbol.EMPTY_FIELD;
            }
        }
        this.howManyInARowToWin = howManyInARowToWin;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getFigure(int row, int col) {
        return board[row][col];
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public void displayPossibleMoves() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + String.format("%02d", i * 10 + j) + " ");
            }
            System.out.println("|");
        }
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Symbol.EMPTY_FIELD;
            }
        }
    }

    public void setFigure(Player player, int row, int col) {
        board[row][col] = player.getSymbol();
    }

    @Override
    public String toString() {
        return "GameBoard{" +
                "board=" + Arrays.toString(board) +
                '}';
    }
}
