package com.williamgrand.tictactoe;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;

/**
 * Created by wgrand on 4/29/16.
 */
public class GameManager {

    public static Player[][] board;
    public static int boardSize = 4;

    private static Player turn = Player.O;
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

    public static int getTurnColorResourceId() {
        if (turn == Player.O)
            return R.color.playerOColor;
        if (turn == Player.X)
            return R.color.playerXColor;
        return 0; // TODO this isn't a good idea to do
    }

    // region Game Play
    public static boolean makeMove (int row, int col)
    {

        if (board[row][col] != null)
            // a move has already been made at that coordinate, return unsuccessful
            return false;

        // set that coordinate to the new player
        board[row][col] = turn;

        // move was successful
        // TODO check for a win
        moveCount++;

        if (moveCount == boardSize*boardSize)
            // TODO
            System.out.println("Game over");

        turn = (turn == Player.O ? Player.X : Player.O);

        return true;

    }


    // endregion


    // region Check Win

    public static Player checkWinner()
    {
        boolean didWin = checkWin(Player.O);
        if (didWin)
            return Player.O;
        didWin = checkWin(Player.X);
        if (didWin)
            return Player.X;
        return null;
    }

    public static boolean checkWin(Player p)
    {
        if (checkVerticalWin(p))
            return true;
        else if (checkHorizontalWin(p))
            return true;
        else if (checkDiagonalWin(p))
            return true;
        else if (checkFourCornersWin(p))
            return true;
        else if (checkBlobWin(p))
            return true;
        else
            return false;
    }

    public static boolean checkVerticalWin(Player p)
    {
        boolean foundWinInColumn = false;

        // traverse horizontally until we find a player
        for (int col = 0; col < boardSize; col++) {
            if (board[0][col] == p) // found possible win, traverse downward to verify
            {
                foundWinInColumn = true;
                for (int row = 0; row < boardSize; row++)
                    if (board[row][col] != p) {
                        // found opposing player, continue to loop through the columns
                        foundWinInColumn = false;
                        break;
                    }
                // entire column belonged to player, return true
                if (foundWinInColumn)
                    return true;
            }
        }

        return false;

    }

    public static boolean checkHorizontalWin(Player p)
    {
        // traverse vertically until we find a player
        boolean foundWinInRow = false;
        for (int row = 0; row < boardSize; row++)
            if (board[row][0] == p) // found possible win, traverse downward to verify
            {
                foundWinInRow = true;
                for (int col = 0; col < boardSize; col++)
                    if (board[row][col] != p) {
                        // found opposing player, continue to loop through the rows
                        foundWinInRow = false;
                        break;
                    }

                // entire row belonged to player, return true
                if (foundWinInRow)
                    return true;
            }

        return false;
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

    // Connected-component labeling algorithm could handle larger areas and various patterns: https://en.wikipedia.org/wiki/Connected-component_labeling
    public static boolean checkBlobWin(Player p)
    {

        // traverse by row
        for (int v = 1; v < boardSize; v++) {

            // traverse by column
            for (int u = 1; u < boardSize; u++) {

                // found match, so check left, above, and left-above
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
