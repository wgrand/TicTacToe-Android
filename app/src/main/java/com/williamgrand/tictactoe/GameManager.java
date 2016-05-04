package com.williamgrand.tictactoe;

import java.util.ArrayList;

/**
 * Created by wgrand on 4/29/16.
 */
public class GameManager {

    public static Player[][] board;
    private static int boardSize;
    private static int moveCount = 0;

    public static void createGame()
    {
        // create a game of size 4x4
        createGame(4);
    }

    public static void createGame(Player[][] board)
    {
        GameManager.board = board;
        boardSize = board[0].length;
        moveCount = 0;
    }

    public static void createGame(int boardSize)
    {
        board = new Player[boardSize][boardSize];
        GameManager.boardSize = boardSize;
        moveCount = 0;
    }

    public static void printBoard()
    {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++)
                System.out.print(board[row][col] + ",");
            System.out.println();
        }
    }

    // region Game Play
    public static boolean makeMove (Player p, int row, int col)
    {

        if (board[row][col] != null)
            // a move has already been made at that coordinate, return unsuccessful
            return false;

        // set that coordinate to the new player
        board[row][col] = p;

        // move was successful
        // TODO check for a win
        moveCount++;

        if (moveCount == boardSize*boardSize)
            // TODO
            System.out.println("Game over");

        return true;

    }


    // endregion


    // region Check Win

    public static boolean checkWin(Player p)
    {
        if (checkVerticalWin(p))
            return true;
        else if (checkHorizontalWin(p))
            return true;
        else if (checkDiagonalWin(p))
            return true;
        else if(checkFourCornersWin(p))
            return true;
        else if(checkBlobWin(p))
            return true;
        return false;
    }

    public static boolean checkVerticalWin(Player p)
    {
        // traverse horizontally until we find a player
        boolean foundWin = false;
        for (int col = 0; col < boardSize; col++) {
            if (board[0][col] == p) // found possible win, traverse downward to verify
            {
                for (int row = 0; row < boardSize; row++)
                    if (board[row][col] != p)
                        break; // found another player, continue to loop through the columns
                foundWin = true;
            }
        }

        return foundWin;

    }

    public static boolean checkHorizontalWin(Player p)
    {
        // traverse vertically until we find a player
        boolean foundWin = false;
        for (int row = 0; row < boardSize; row++)
            if (board[row][0] == p) // found possible win, traverse downward to verify
            {
                for (int col = 0; col < boardSize; col++)
                    if (board[row][col] != p)
                        break; // found another player, continue to loop through the rows
                foundWin = true;
            }

        return foundWin;
    }

    public static boolean checkDiagonalWin(Player p)
    {
        boolean foundWin = true;

        // check top-left to bottom-right
        for (int i = 0; i < boardSize; i++)
            if (board[i][i] != p)  // no win found, move on to check other diagonal
            {
                foundWin = false;
                break;
            }


        if (foundWin)
            return true;
        else // check bottom-left to top-right
            for (int i = 0; i < boardSize; i++) {
                if (board[i][boardSize - 1 - i] != p)
                    return false; // no win in this diagonal either, return false
            }

        return true; // found win in bottom-left to top-right diagonal

    }

    public static boolean checkFourCornersWin(Player p)
    {
        return board[0][0] == p
                && board[boardSize-1][0] == p
                && board[0][boardSize-1] == p
                && board[boardSize-1][boardSize-1] == p;
    }

    // might need Connected-component labeling algorithm: https://en.wikipedia.org/wiki/Connected-component_labeling
    // do union thing; if you end up with a 2x2 array, then you have a match
    public static boolean checkBlobWin(Player p)
    {

        // traverse by row
        for (int v = 1; v < boardSize; v++) {

            // traverse by column
            for (int u = 1; u < boardSize; u++) {

                System.out.println(v + "," + u);

                // found match, so check left and above
                if (board[v][u] == p) {

                    Player leftP = u - 1 < 0 ? null : board[v][u - 1];
                    Player aboveP = v - 1 < 0 ? null : board[v-1][u];
                    Player leftAboveP = (u-1 < 0 || v-1 < 0) ? null : board[v-1][u-1];

                    if (leftP == p && aboveP == p && leftAboveP == p)
                        return true;

                }

            }
        }



        return false;

    }

    //endregion
}
