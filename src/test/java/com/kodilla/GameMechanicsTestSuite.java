package com.kodilla;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameMechanicsTestSuite {

    private GameMechanics game;

    @Test
    @DisplayName("Test to verify the wins of the 'o' symbol in the rows.")
    void testVerifyWinnerInRows_PlayerOWins_ReturnsTrue() {
        // given
        Player player = new Player("Player1", 'o');
        game = new GameMechanics(1, 3);

        // when
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 1});
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 2});

        // then
        Assertions.assertTrue(game.verifyWinner(player));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'o' symbol in the columns.")
    void testVerifyWinnerInColumns_PlayerOWins_ReturnsTrue() {
        // given
        Player player = new Player("Player1", 'o');
        game = new GameMechanics(1, 3);

        // when
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 0});

        // then
        Assertions.assertTrue(game.verifyWinner(player));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'o' symbol in the diagonals.")
    void testVerifyWinnerInDiagonals_PlayerOWins_ReturnsTrue() {
        // given
        Player player = new Player("Player1", 'o');
        game = new GameMechanics(1, 3);

        // when
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 2});

        // then
        Assertions.assertTrue(game.verifyWinner(player));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'x' symbol in the rows.")
    void testVerifyWinnerInRows_PlayerXWins_ReturnsTrue() {
        // given
        Player player = new Player("Player1", 'x');
        game = new GameMechanics(1, 3);

        // when
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 0});
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 1});
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 2});

        // then
        Assertions.assertTrue(game.verifyWinner(player));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'x' symbol in the columns.")
    void testVerifyWinnerInColumns_PlayerXWins_ReturnsTrue() {
        // given
        Player player = new Player("Player1", 'x');
        game = new GameMechanics(1, 3);

        // when
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 2});
        game.makeAMoveAndDisplayBoard(player, new int[]{1, 2});
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 2});

        // then
        Assertions.assertTrue(game.verifyWinner(player));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'x' symbol in the diagonals.")
    void testVerifyWinnerInDiagonals_PlayerXWins_ReturnsTrue() {
        // given
        Player player = new Player("Player1", 'x');
        game = new GameMechanics(1, 3);

        // when
        game.makeAMoveAndDisplayBoard(player, new int[]{0, 2});
        game.makeAMoveAndDisplayBoard(player, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player, new int[]{2, 0});

        // then
        Assertions.assertTrue(game.verifyWinner(player));
    }

    @Test
    @DisplayName("Test to verify draw situation.")
    void testVerifyDraw() {
        // given
        Player player1 = new Player("Player1", 'x');
        Player player2 = new Player("Player2", 'o');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 2});
        game.makeAMoveAndDisplayBoard(player2, new int[]{0, 2});
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 1});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player1, new int[]{2, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{2, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{2, 2});

        // then
        Assertions.assertTrue(game.verifyWinner(player1));
        Assertions.assertTrue(game.verifyWinner(player2));
    }

    @Test
    @DisplayName("Test to verify whether an appropriate exception will be thrown in the case of incorrectly performed moves.")
    void testVerifyIncorrectMove() {
        // given
        Player player1 = new Player("Player1", 'x');
        Player player2 = new Player("Player2", 'o');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 1});

        // then
        assertThrows(IllegalArgumentException.class, () -> game.makeAMoveAndDisplayBoard(player2, new int[]{1, 1}));
    }

}
