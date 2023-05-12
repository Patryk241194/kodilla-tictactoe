package com.kodilla;

public class GameBoard {
    private char[][] board;

    public GameBoard(char gameVariant) {
        if (gameVariant == '1') {
            this.board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }
        } else if (gameVariant == '2') {
            this.board = new char[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public char getFigure(int col, int row) {
        return board[col][row];
    }

    public void displayBoard() {
        System.out.println();
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
                board[i][j] = ' ';
            }
        }
    }

    public boolean isBoardCompleted() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void setFigure(Player player, int col, int row) {
        board[col][row] = player.getSymbol();
    }

}
