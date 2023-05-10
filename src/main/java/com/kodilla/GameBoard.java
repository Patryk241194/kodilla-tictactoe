package com.kodilla;

public class GameBoard {

    Player player;
    NPC npc;
    private char[][] board;

    public GameBoard() {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " |");
        }
    }

    public void addPlayerMove(int i, int j) {
        board[i][j] = player.getUserSymbol();
    }

    public void addNpcMove(int i, int j) {
        board[i][j] = npc.getNpcSymbol();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
// Metodę poniżej dorzucimy do GameLogics, tu będzie tylko plansza i dodawanie argumentów.
    public boolean verifyWinner(char symbol) {
        // Verification of rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        // Verification of columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        // Verification of diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        if (board[2][0] == symbol && board[1][1] == symbol && board[0][2] == symbol) {
            return true;
        }

        return false;
    }


}
