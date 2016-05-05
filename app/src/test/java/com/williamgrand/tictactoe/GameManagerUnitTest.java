package com.williamgrand.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GameManagerUnitTest {

    // region win checks

    @Test
    public void checkVerticalWin_isWinningBoard() throws Exception {

        Player[][] winningBoard = new Player[][]{
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null}
        };

        GameManager.createGame(winningBoard);

        // O
        assertNotNull(GameManager.checkVerticalWin(Player.O));

        // X
        assertNotNull(GameManager.checkVerticalWin(Player.X));

    }
    @Test
    public void checkVerticalWin_isLosingBoard() throws Exception {

        Player[][] losingBoard = new Player[][] {
                {null, null, null, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null}
        };

        GameManager.createGame(losingBoard);

        // O
        assertNull(GameManager.checkVerticalWin(Player.O));

        // X
        assertNull(GameManager.checkVerticalWin(Player.X));

    }

    @Test
    public void checkHorizontalWin_isWinningBoard() throws Exception {

        Player[][] winningBoard = new Player[][]{
                {null, null, null, null},
                {Player.O, Player.O, Player.O, Player.O},
                {Player.X, Player.X, Player.X, Player.X},
                {null, null, null, null}
        };

        GameManager.createGame(winningBoard);

        // O
        assertNotNull(GameManager.checkHorizontalWin(Player.O));

        // X
        assertNotNull(GameManager.checkHorizontalWin(Player.X));

    }

    @Test
    public void checkHorizontalWin_isLosingBoard() throws Exception {

        Player[][] losingBoard = new Player[][]{
                {null, null, null, null},
                {null, Player.O, Player.O, Player.O},
                {null, Player.X, Player.X, Player.X},
                {null, null, null, null}
        };

        GameManager.createGame(losingBoard);

        // O
        assertNull(GameManager.checkHorizontalWin(Player.O));

        // X
        assertNull(GameManager.checkHorizontalWin(Player.X));

    }

    @Test
    public void checkDiagonalWin_isWinningBoard() throws Exception {

        /** Check wins for Player O **/
        Player[][] winningBoard_O_1 = new Player[][]{
                {Player.O, null, null, null},
                {null, Player.O, null, null},
                {null, null, Player.O, null},
                {null, null, null, Player.O}
        };

        GameManager.createGame(winningBoard_O_1);

        // O
        assertNotNull(GameManager.checkDiagonalWin(Player.O));

        // X
        assertNull(GameManager.checkDiagonalWin(Player.X));


        Player[][] winningBoard_O_2 = new Player[][]{
                {null, null, null, Player.O},
                {null, null, Player.O, null},
                {null, Player.O, null, null},
                {Player.O, null, null, null}
        };

        GameManager.createGame(winningBoard_O_2);

        // O
        assertNotNull(GameManager.checkDiagonalWin(Player.O));

        // X
        assertNull(GameManager.checkDiagonalWin(Player.X));



        /** Check wins for Player X **/
        Player[][] winningBoard_X_1 = new Player[][]{
                {Player.X, null, null, null},
                {null, Player.X, null, null},
                {null, null, Player.X, null},
                {null, null, null, Player.X}
        };

        GameManager.createGame(winningBoard_X_1);

        // X
        assertNotNull(GameManager.checkDiagonalWin(Player.X));

        // O
        assertNull(GameManager.checkDiagonalWin(Player.O));


        Player[][] winningBoard_X_2 = new Player[][]{
                {null, null, null, Player.X},
                {null, null, Player.X, null},
                {null, Player.X, null, null},
                {Player.X, null, null, null}
        };

        GameManager.createGame(winningBoard_X_2);

        // X
        assertNotNull(GameManager.checkDiagonalWin(Player.X));

        // O
        assertNull(GameManager.checkDiagonalWin(Player.O));

    }

    @Test
    public void checkFourCorners_isWinningBoard() throws Exception {

        Player[][] winningBoard_X = new Player[][]{
                {Player.X, null, null, Player.X},
                {null, null, null, null},
                {null, null, null, null},
                {Player.X, null, null, Player.X}
        };

        GameManager.createGame(winningBoard_X);

        // X
        assertNotNull(GameManager.checkFourCornersWin(Player.X));

        // O
        assertNull(GameManager.checkFourCornersWin(Player.O));

        Player[][] winningBoard_O = new Player[][]{
                {Player.O, null, null, Player.O},
                {null, null, null, null},
                {null, null, null, null},
                {Player.O, null, null, Player.O}
        };

        GameManager.createGame(winningBoard_O);

        // X
        assertNull(GameManager.checkFourCornersWin(Player.X));

        // O
        assertNotNull(GameManager.checkFourCornersWin(Player.O));

    }


    @Test
    public void checkFourCorners_isLosingBoard() throws Exception {

        Player[][] losingBoard = new Player[][]{
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
        };

        GameManager.createGame(losingBoard);

        // X
        assertNull(GameManager.checkFourCornersWin(Player.X));

        // O
        assertNull(GameManager.checkFourCornersWin(Player.O));

    }


    @Test
    public void checkBlobWin_isWinningBoard() throws Exception {

        Player[][] winningBoard = new Player[][]{
                {null, null, null, null},
                {null, Player.O, Player.O, null},
                {null, Player.O, Player.O, null},
                {null, null, null, null}
        };

        GameManager.createGame(winningBoard);

        // O
        assertNotNull(GameManager.checkBlobWin(Player.O));

        // X
        assertNull(GameManager.checkBlobWin(Player.X));

    }


    // endregion
}