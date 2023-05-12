package com.kodilla;

public class GameRunner {
    public static void main(String[] args) {
        GameMechanics game = new GameMechanics(2,'1');
        GameBoard board = game.getGameBoard();
        GameLogics logics = new GameLogics(game);

        Player player1 = new User("Patryk", 'x');
        Player player2 = new User("Andrzej", 'o');

        game.makeAMove(player1,1,1);
        game.displayBoard();
        System.out.println(logics.verifyWinner(player1.getSymbol(), game.getHowManyInARowToWin()));

        board.setFigure(player2,0,2);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player2.getSymbol(), game.getHowManyInARowToWin()));

        board.setFigure(player1,2,2);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player1.getSymbol(), game.getHowManyInARowToWin()));

        board.setFigure(player2,0,1);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player2.getSymbol(), game.getHowManyInARowToWin()));

        board.setFigure(player1,1,1);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player1.getSymbol(), game.getHowManyInARowToWin()));

        board.setFigure(player2,0,0);
        board.displayBoard();
        System.out.println(logics.verifyWinner(player2.getSymbol(), game.getHowManyInARowToWin()));

    }
}
