package com.williamgrand.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    // region win checks

//    @Test
//    public void check_allWins() throws Exception {
//
//
//
//
//        if (checkVerticalWin(p))
//            return true;
//        else if (checkHorizontalWin(p))
//            return true;
//        else if (checkDiagonalWin(p))
//            return true;
//        else if(checkFourCornersWin(p))
//            return true;
//        return false;
//    }

    @Test
    public void checkVerticalWin(Player p) throws Exception {

        Player[][] winningBoard = new Player[][] {
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null}
        };

        GameManager.board = winningBoard;

        // O
        if(BuildConfig.DEBUG && !GameManager.checkVerticalWin(Player.O)) throw new AssertionError();

        // X
        if(BuildConfig.DEBUG && !GameManager.checkVerticalWin(Player.X)) throw new AssertionError();

        Player[][] losingBoard = new Player[][] {
                {null, null, null, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null},
                {null, Player.O, Player.X, null}
        };

        GameManager.board = losingBoard;

        // O
        if (BuildConfig.DEBUG && !GameManager.checkVerticalWin(Player.O)) throw new AssertionError();

        // X
        if (BuildConfig.DEBUG && !GameManager.checkVerticalWin(Player.X)) throw new AssertionError();


    }

//    @Test
//    public static boolean checkHorizontalWin(Player p)
//    {
//    }
//
//    @Test
//    public static boolean checkDiagonalWin(Player p)
//    {
//
//    }
//
//    @Test
//    public static boolean checkFourCornersWin(Player p)
//    {
//    }
//
//    @Test
//    public static boolean checkSquareWin(Player p) {
//
//    }


    // endregion
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}