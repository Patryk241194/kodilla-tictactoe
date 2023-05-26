package com.kodilla.backend;

import java.util.Random;

public class GameMechanics {


    private final GameBoard gameBoard;
    private final GameLogics gameLogics;
    private final GameValidator validate;
    private final ConsoleInputReader scan;
    private final GameRanking ranking;
    private final MinMax analyze;
    private Player startingPlayer;
    private Player secondPlayer;
    private Player player1;
    private Player player2;
    private final int numberOfPlayers;
    private final int howManyInARowToWin;
    private String difficulty;
    private final int boardSize;

    public GameMechanics(int numberOfPlayers, int boardSize) {
        validate = new GameValidator();
        scan = new ConsoleInputReader();
        analyze = new MinMax();
        ranking = new GameRanking();
        this.numberOfPlayers = validate.numberOfPlayers(numberOfPlayers);
        this.boardSize = validate.boardSize(boardSize);
        this.howManyInARowToWin = getHowManyInARowToWin();
        this.gameBoard = new GameBoard(boardSize, howManyInARowToWin);
        this.gameLogics = new GameLogics(this);
        this.gameLogics.getRules();
    }

    public void play() {
        boolean endOfProgram = false;
        while (!endOfProgram) {
            System.out.println(gameLogics.MENU_MESSAGE);
            int menuChoice = validate.menuChoice(scan.choice());
//            scan.nextLine();

            switch (menuChoice) {
                case 1:
                    if (numberOfPlayers == 1) {
                        this.difficulty = validate.difficulty(scan.difficulty());
//                        scan.nextLine();
                    }
                    createPlayer(numberOfPlayers);
                    boolean endOfGame = false;
                    while (!endOfGame) {
                        drawWhoStarts();
                        boolean isDuelOver = false;
                        while (!isDuelOver) {
                            // The variant where the player starts
                            if (startingPlayer == player1) {

                                System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + gameLogics.NEW_LINE, startingPlayer.toString());
                                makeAMoveAndDisplayBoard(startingPlayer, scan.move(gameBoard, boardSize));
                                isDuelOver = verifyResultOfTheDuel(startingPlayer, secondPlayer);
                                if (isDuelOver) {
                                    break;
                                }

                                switch (numberOfPlayers) {
                                    case 2 -> {
                                        System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + gameLogics.NEW_LINE, secondPlayer.toString());
                                        makeAMoveAndDisplayBoard(secondPlayer, scan.move(gameBoard, boardSize));
                                    }
                                    case 1 -> {
                                        int[] bestMove = analyze.getBestMove(gameBoard.getBoard(), howManyInARowToWin, getDifficulty(boardSize, difficulty));
                                        makeAMoveAndDisplayBoard(secondPlayer, bestMove);
                                    }
                                }
                                isDuelOver = verifyResultOfTheDuel(startingPlayer, secondPlayer);

                            } else if (startingPlayer == player2) {
                                // Variant in which the player or the npc starts, depending on whether we play vs computer or vs player
                                switch (numberOfPlayers) {
                                    case 2 -> {
                                        System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + gameLogics.NEW_LINE, startingPlayer.toString());
                                        makeAMoveAndDisplayBoard(startingPlayer, scan.move(gameBoard, boardSize));
                                    }
                                    case 1 -> {
                                        int[] bestMove = analyze.getBestMove(gameBoard.getBoard(), howManyInARowToWin, getDifficulty(boardSize, difficulty));
                                        makeAMoveAndDisplayBoard(startingPlayer, bestMove);
                                    }
                                }
                                isDuelOver = verifyResultOfTheDuel(startingPlayer, secondPlayer);
                                if (isDuelOver) {
                                    break;
                                }

                                System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + gameLogics.NEW_LINE, secondPlayer.toString());
                                makeAMoveAndDisplayBoard(secondPlayer, scan.move(gameBoard, boardSize));
                                isDuelOver = verifyResultOfTheDuel(startingPlayer, secondPlayer);
                            }
                        }
                        if (isDuelOver) {
                            updatePlayersScore(player1, player2);
                        }
                        endOfGame = scan.requestRematch();
//                        scan.nextLine();
                        gameBoard.resetBoard();
                    }
                case 2:
                    ranking.displayFullRanking();
                    break;
                case 3:
                    endOfProgram = true;
                    break;
                default:
                    System.out.println("Invalid menu selection. Try again.");
                    break;
            }
        }
    }

    public void createPlayer(int numberOfPlayers) {
        int players = validate.numberOfPlayers(numberOfPlayers);

        if (players == 1) {
            player1 = new Player(validate.username(scan.name()), validate.symbol(Symbol.O));
            player2 = new Player(gameLogics.NPC_NAME, validate.symbol(Symbol.X));
        } else if (players == 2) {
            System.out.printf(gameLogics.PLAYER1);
            player1 = new Player(validate.username(scan.name()), validate.symbol(scan.symbol()));
//            scan.nextLine();
            System.out.printf(gameLogics.PLAYER2);
            player2 = new Player(validate.username(scan.name()), validate.symbol((player1.getSymbol() == Symbol.X) ? Symbol.O : Symbol.X));
        }
    }

    private void drawWhoStarts() {
        startingPlayer = (new Random().nextInt(2) == 0) ? player1 : player2;
        secondPlayer = (startingPlayer == player1) ? player2 : player1;
        System.out.printf((gameLogics.STARTING_PLAYER_MESSAGE) + gameLogics.NEW_LINE, startingPlayer.getName());
    }

    public void makeAMoveAndDisplayBoard(Player player, int[] move) {
        System.out.printf(gameLogics.MOVE_MESSAGE, player.toString());
        int row = validate.movementRange(move[0], boardSize);
        int column = validate.movementRange(move[1], boardSize);
        boolean isMovementPossible = validate.movementPossibility(gameBoard, row, column);

        if (isMovementPossible) {
            gameBoard.setFigure(player, row, column);
        }
        gameBoard.displayBoard();
    }

    public boolean verifyResultOfTheDuel(Player player1, Player player2) {
        boolean isGameOver = false;

        if (GameLogics.verifyWinner(gameBoard.getBoard(), player1.getSymbol(), player2.getSymbol(), howManyInARowToWin) == -1) {
            System.out.printf((gameLogics.WIN_MESSAGE) + gameLogics.NEW_LINE, player1.getName());
            isGameOver = true;
        } else if (GameLogics.verifyWinner(gameBoard.getBoard(), player1.getSymbol(), player2.getSymbol(), howManyInARowToWin) == 1) {
            System.out.printf((gameLogics.WIN_MESSAGE) + gameLogics.NEW_LINE, player2.getName());
            isGameOver = true;
        } else if (GameLogics.isBoardCompleted(gameBoard.getBoard())) {
            System.out.println(gameLogics.DRAW_MESSAGE);
            isGameOver = true;
        } else if (!GameLogics.hasChanceToWin(gameBoard.getBoard(), player1.getSymbol(), howManyInARowToWin) &&
                !GameLogics.hasChanceToWin(gameBoard.getBoard(), player2.getSymbol(), howManyInARowToWin)) {
            System.out.println(gameLogics.DRAW_MESSAGE);
            isGameOver = true;
        }
        return isGameOver;
    }

    private Player getWinnerPlayer(Player player1, Player player2) {
        if (GameLogics.verifyWinner(gameBoard.getBoard(), player1.getSymbol(), player2.getSymbol(), howManyInARowToWin) == -1) {
            return player1;
        } else if (GameLogics.verifyWinner(gameBoard.getBoard(), player1.getSymbol(), player2.getSymbol(), howManyInARowToWin) == 1) {
            return player2;
        } else {
            return null; // No winner
        }
    }

    private void updatePlayersScore(Player player1, Player player2) {
        Player winnerPlayer = getWinnerPlayer(player1, player2);

        if (winnerPlayer != null) {
            switch (numberOfPlayers) {
                case 2 -> {
                    if (startingPlayer == player1) {
                        if (winnerPlayer == startingPlayer) {
                            ranking.addWin(player1);
                            ranking.addLoss(player2);
                        } else {
                            ranking.addLoss(player1);
                            ranking.addWin(player2);
                        }
                    } else if (startingPlayer == player2) {
                        if (winnerPlayer == startingPlayer) {
                            ranking.addWin(player2);
                            ranking.addLoss(player1);
                        } else {
                            ranking.addLoss(player2);
                            ranking.addWin(player1);
                        }
                    }
                    ranking.displayPlayerRanking(player1);
                    ranking.displayPlayerRanking(player2);
                }
                case 1 -> {
                    if (winnerPlayer == player1) {
                        ranking.addWin(player1);
                    } else {
                        ranking.addLoss(player1);
                    }
                    ranking.displayPlayerRanking(player1);
                }
            }
        }
    }

    public int getHowManyInARowToWin() {
        if (this.getBoardSize() == 3) {
            return 3;
        } else if (this.getBoardSize() > 3 && this.getBoardSize() <= 6) {
            return 4;
        } else if (this.getBoardSize() > 6) {
            return 5;
        }
        return 0;
    }

    public int getDifficulty(int boardSize, String difficulty) {
        if (difficulty.equalsIgnoreCase("easy")) {
            return 1;
        } else if (difficulty.equalsIgnoreCase("medium")) {
            if (boardSize == 3) {
                return 5;
            } else if (boardSize >= 4 && boardSize <= 10) {
                return 2;
            }
        } else if (difficulty.equalsIgnoreCase("hard")) {
            if (boardSize == 3) {
                return 8;
            } else if (boardSize >= 4 && boardSize <= 6) {
                return 4;
            } else if (boardSize >= 7 && boardSize <= 10) {
                return 3;
            }
        }
        return 1;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

}