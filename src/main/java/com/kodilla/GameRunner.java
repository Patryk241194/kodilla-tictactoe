package com.kodilla;

public class GameRunner {
    public static void main(String[] args) {
        GameMechanics game = new GameMechanics(2,'1');
        GameBoard board = new GameBoard(game.getGameVariant());
        GameLogics logics = new GameLogics(game,board);

        Player player1 = new User("Patryk", 'x');
        Player player2 = new User("Andrzej", 'o');

        game.makeAMove(player1,1,1);
        System.out.println(logics.verifyWinner(player1.getSymbol(), game.getHowManyInARowToWin()));

        board.addMoveToTheBoard(player2,0,2);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player2.getSymbol(), game.getHowManyInARowToWin()));

        board.addMoveToTheBoard(player1,2,2);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player1.getSymbol(), game.getHowManyInARowToWin()));

        board.addMoveToTheBoard(player2,0,1);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player2.getSymbol(), game.getHowManyInARowToWin()));

        board.addMoveToTheBoard(player1,1,1);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player1.getSymbol(), game.getHowManyInARowToWin()));

        board.addMoveToTheBoard(player2,0,0);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player2.getSymbol(), game.getHowManyInARowToWin()));

    }
}
