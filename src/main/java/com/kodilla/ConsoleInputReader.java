package com.kodilla;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputReader {
    private final Scanner scanner;

    public ConsoleInputReader() {
        scanner = new Scanner(System.in);
    }

    public String Name() {
        String name;
        do {
            System.out.print("\nPlease enter your name: ");
            name = scanner.nextLine()/*.trim()*/; // remove whitespace from start and end

//          if-condition (name.isEmpty() || name.matches("\\s+") checks if the string is empty or consists only of whitespace.

            if (name.isEmpty() || name.matches("\\s+")) {
                System.out.println("Invalid input. Please enter a name with at least one digit.");
            }
        } while (name.isEmpty() || name.matches("\\s+"));

        return name;
    }

    public void nextLine() {
        scanner.nextLine();
    }

    public char Symbol() {
        char symbol;
        do {
            System.out.print("Select your symbol: \"x\" or \"o\": ");
            symbol = Character.toLowerCase(scanner.next().charAt(0));
            if (symbol != Symbol.X && symbol != Symbol.O) {
                System.out.println("Invalid input. Please try again!");
            }
        } while (symbol != Symbol.X && symbol != Symbol.O);

        return symbol;
    }

    public int numberOfPlayers() {
        boolean isCorrectType;
        int numberOfPlayers = 0;
        do {
            System.out.print("Single player: press 1\n" +
                    "Multi player: press 2 \n" +
                    "Choose: ");

            try {
                numberOfPlayers = scanner.nextInt();
                isCorrectType = true;

                if (numberOfPlayers != 1 && numberOfPlayers != 2) {
                    System.out.println("Invalid input. You can only choose '1' or '2'.");
                    isCorrectType = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical integer value. You can only choose '1' or '2'.");
                isCorrectType = false;
                scanner.next();
            }
        } while (!isCorrectType);

        return numberOfPlayers;
    }

    public int boardSize() {
        boolean isCorrectType;
        int boardSize = 0;
        do {
            System.out.print("Please choose the size of the board, you can choose between 3x3 - 10x10:\n" +
                    "Press '3' for 3x3 and so on.: ");

            try {
                boardSize = scanner.nextInt();
                isCorrectType = true;

                if (boardSize <= 0) {
                    System.out.println("Invalid input. Please choose a value between 3 and 10.");
                    isCorrectType = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical integer value between 3 and 10.");
                isCorrectType = false;
                scanner.next();
            }
        } while (!isCorrectType);

        return boardSize;
    }

    public String difficulty() {
        String difficulty;
        do {
            System.out.print("\nSelect the difficulty level: \"easy\" / \"medium\" / \"hard\" : ");
            difficulty = scanner.next().toLowerCase();
            if (!difficulty.equals("easy") && !difficulty.equals("medium") && !difficulty.equals("hard")) {
                System.out.println("Invalid input. Please try again!");
            }
        } while (!difficulty.equals("easy") && !difficulty.equals("medium") && !difficulty.equals("hard"));

        return difficulty;
    }

    public int row(int boardSize) {
        boolean isCorrectType;
        int playerMove = 0;
        do {
            System.out.print("Choose a row value between 0 and " + (boardSize - 1) + ": ");

            try {
                playerMove = scanner.nextInt();
                isCorrectType = true;

                if (playerMove < 0 || playerMove > boardSize) {
                    System.out.println("Invalid input. Please choose a value between 0 and" + (boardSize - 1) + ".");
                    isCorrectType = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical integer value between 0 and" + (boardSize - 1) + ".");
                isCorrectType = false;
                scanner.next();
            }
        } while (!isCorrectType);

        return playerMove;
    }

    public int col(int boardSize) {
        boolean isCorrectType;
        int playerMove = 0;
        do {
            System.out.print("Choose a column value between 0 and " + (boardSize - 1) + ": ");

            try {
                playerMove = scanner.nextInt();
                isCorrectType = true;

                if (playerMove < 0 || playerMove > boardSize) {
                    System.out.println("Invalid input. Please choose a value between 0 and" + (boardSize - 1) + ".");
                    isCorrectType = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical integer value between 0 and " + (boardSize - 1) + ".");
                isCorrectType = false;
                scanner.next();
            }
        } while (!isCorrectType);

        return playerMove;
    }

    public int[] move(GameBoard gameBoard, int boardSize) {
        int[] move = new int[2];
        boolean isPossible = false;

        do {
            int row = row(boardSize);
            int col = col(boardSize);

            try {
                if (gameBoard.getFigure(row, col) == ' ') {
                    isPossible = true;
                    move[0] = row;
                    move[1] = col;
                } else {
                    System.out.println("Invalid movement. The selected field is not empty.");
                    isPossible = false;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid movement. The selected field is not empty.");
                isPossible = false;
            }
        } while (!isPossible);

        return move;
    }

    public boolean requestRematch() {
        System.out.println("\nChoose whether you want to continue playing or quit the game:");
        System.out.println("Type 'yes' for rematch,");
        System.out.println("Type 'no' for end of game.");

        String offerRematch;
        boolean validInput = false;
        do {
            offerRematch = scanner.next().toLowerCase();
            if (offerRematch.equals("yes")) {
                validInput = false;
                return false;
            } else if (offerRematch.equals("no")) {
                validInput = true;
                return true;
            } else {
                System.out.println("Invalid input, please type 'yes' or 'no':");
            }
        } while (!validInput);
        return true;
    }
}
