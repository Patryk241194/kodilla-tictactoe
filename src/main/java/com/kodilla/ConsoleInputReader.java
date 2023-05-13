package com.kodilla;

import java.util.Scanner;

public class ConsoleInputReader {
    private Scanner scanner;

    public ConsoleInputReader() {
        scanner = new Scanner(System.in);
    }

    public String Name() {
        String name;
        do {
            System.out.print("Please enter your name: ");
            name = scanner.nextLine().trim(); // remove whitespace from start and end

//          if-condition (name.isEmpty() || name.matches("\\s+") checks if the string is empty or consists only of whitespace.

            if (name.isEmpty() || name.matches("\\s+")) {
                System.out.println("Invalid input. Please enter a name with at least one digit.");
            }
        } while (name.isEmpty() || name.matches("\\s+"));

        return name;
    }

    public char Symbol() {
        char symbol;
        do {
            System.out.print("Select your symbol: \"x\" or \"o\".");
            symbol = scanner.next().charAt(0);
            if (symbol != 'x' && symbol != 'o') {
                System.out.println("Invalid input. Please try again!");
            }
        } while (symbol != 'x' && symbol != 'o');

        return symbol;
    }

}
