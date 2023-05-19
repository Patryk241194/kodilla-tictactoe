package com.kodilla;

public class GameRunner {
    public static void main(String[] args) {
/*        ConsoleInputReader scan = new ConsoleInputReader();
        GameMechanics game = new GameMechanics(scan.numberOfPlayers(),scan.boardSize());
        game.play();*/

        char board[][] = {{'o', 'x', ' '},
                {' ', 'x', ' '},
                {' ', ' ', ' '}};

        MinMax3x3 bestMove = new MinMax3x3();
        int[] lol = bestMove.getBestMove(board);
        System.out.printf("The Optimal Move is :\n");
        System.out.printf("ROW: %d COL: %d\n\n",
                lol[0], lol[1] );
    }
}

