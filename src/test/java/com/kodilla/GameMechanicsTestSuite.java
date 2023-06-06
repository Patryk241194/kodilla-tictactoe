/*
package com.kodilla;

import com.kodilla.backend.GameLogics;
import com.kodilla.backend.GameMechanics;
import com.kodilla.backend.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameMechanicsTestSuite {

    private GameMechanics game;

    @Test
    @DisplayName("Test to verify the wins of the 'o' symbol in the rows.")
    void testVerifyWinnerInRows_PlayerOWins_ReturnsTrue() {
        // given
        Player player1 = new Player("Player1", 'o');
        Player player2 = new Player("Player2", 'x');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 1});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 2});

        // then
        int expectedWinner = -1;
        int actualWinner = GameLogicsFX.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'o' symbol in the columns.")
    void testVerifyWinnerInColumns_PlayerOWins_ReturnsTrue() {
        // given
        Player player1 = new Player("Player1", 'o');
        Player player2 = new Player("Player2", 'x');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{0, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{2, 0});

        // then
        int expectedWinner = -1;
        int actualWinner = GameLogics.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'o' symbol in the diagonals.")
    void testVerifyWinnerInDiagonals_PlayerOWins_ReturnsTrue() {
        // given
        Player player1 = new Player("Player1", 'o');
        Player player2 = new Player("Player2", 'x');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{0, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 2});
        game.makeAMoveAndDisplayBoard(player1, new int[]{2, 2});

        // then
        int expectedWinner = -1;
        int actualWinner = GameLogics.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'x' symbol in the rows.")
    void testVerifyWinnerInRows_PlayerXWins_ReturnsTrue() {
        // given
        Player player1 = new Player("Player1", 'o');
        Player player2 = new Player("Player2", 'x');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 1});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{2, 2});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 2});

        // then
        int expectedWinner = 1;
        int actualWinner = GameLogics.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'x' symbol in the columns.")
    void testVerifyWinnerInColumns_PlayerXWins_ReturnsTrue() {
        // given
        Player player1 = new Player("Player1", 'o');
        Player player2 = new Player("Player2", 'x');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{0, 2});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 2});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player2, new int[]{2, 2});

        // then
        int expectedWinner = 1;
        int actualWinner = GameLogics.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
    }

    @Test
    @DisplayName("Test to verify the wins of the 'x' symbol in the diagonals.")
    void testVerifyWinnerInDiagonals_PlayerXWins_ReturnsTrue() {
        // given
        Player player1 = new Player("Player1", 'o');
        Player player2 = new Player("Player2", 'x');
        game = new GameMechanics(2, 3);

        // when
        game.makeAMoveAndDisplayBoard(player1, new int[]{0, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{0, 2});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 0});
        game.makeAMoveAndDisplayBoard(player2, new int[]{1, 1});
        game.makeAMoveAndDisplayBoard(player1, new int[]{1, 2});
        game.makeAMoveAndDisplayBoard(player2, new int[]{2, 0});

        // then
        int expectedWinner = 1;
        int actualWinner = GameLogics.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
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
        int expectedWinner = 0;
        int actualWinner = GameLogics.verifyWinner(game.getGameBoard().getBoard(), player1.getSymbol(), player2.getSymbol(), game.getHowManyInARowToWin());

        assertEquals(expectedWinner, actualWinner);
        assertTrue(game.verifyResultOfTheDuel(player1, player2));
        assertFalse(GameLogics.hasChanceToWin(game.getGameBoard().getBoard(), player1.getSymbol(), game.getHowManyInARowToWin()));
        assertFalse(GameLogics.hasChanceToWin(game.getGameBoard().getBoard(), player2.getSymbol(), game.getHowManyInARowToWin()));
        assertTrue(GameLogics.isBoardCompleted(game.getGameBoard().getBoard()));
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
*/
