package com.kodilla;

public class TestApp {
    public static void main(String[] args) {
        GameMechanics game = new GameMechanics(2,10);
        GameBoard board = game.getGameBoard();
        GameLogics logics = new GameLogics(game);

        Player player1 = new User("Patryk", 'x');
        Player player2 = new User("Andrzej", 'o');

        /*MinMax minMax = new MinMax();

        game.makeAMove(player1,1,1);
        System.out.println(game.verifyWinner(player1));

        int[] bestMove = minMax.getBestMove(board.getBoard(), player2.getSymbol(), game.getHowManyInARowToWin());
        game.makeAMove(player2,bestMove[0], bestMove[1]);
        System.out.println(game.verifyWinner(player2));

        game.makeAMove(player1,2,1);
        System.out.println(game.verifyWinner(player1));

        bestMove = minMax.getBestMove(board.getBoard(), player2.getSymbol(), game.getHowManyInARowToWin());
        game.makeAMove(player2,bestMove[0], bestMove[1]);
        System.out.println(game.verifyWinner(player2));

        game.makeAMove(player1,0,2);
        System.out.println(game.verifyWinner(player1));

        bestMove = minMax.getBestMove(board.getBoard(), player2.getSymbol(), game.getHowManyInARowToWin());
        game.makeAMove(player2,bestMove[0], bestMove[1]);
        System.out.println(game.verifyWinner(player2));

        game.makeAMove(player1,2,0);
        System.out.println(game.verifyWinner(player1));
*/
    }
}
