package com.williamgrand.tictactoe;

/**
 * Created by wgrand on 4/29/16.
 */
public class GameManager {

    public static Player[][] board;
    private static int boardSize;

    public static void createGame()
    {
        // create a game of size 4x4
        createGame(4);
    }

    public static void createGame(Player[][] board)
    {
        GameManager.board = board;
        boardSize = board[0].length;
    }

    public static void createGame(int boardSize)
    {
        board = new Player[boardSize][boardSize];
        GameManager.boardSize = boardSize;
    }

    public static void printBoard()
    {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++)
                System.out.print(board[row][col] + ",");
            System.out.println();
        }
    }

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

    public static boolean checkSquareWin(Player p)
    {
        int l = boardSize/2;
        boolean foundWin = false;

//        // spiral to the center
//        for (int col = 0; col < boardSize; col++)
//            for (int)

        // there aren't too many possibilities for a square win
        // first, we start by checking each corner


        // top-left
        if (board[0][0] == p)
            if (checkWinInNeighbors(p, 0, 0, l))
                return true; // found a win in top-left corner


        // bottom-left
        if (board[0][boardSize-1] == p)
            if (checkWinInNeighbors(p, 0, boardSize-1, l))
                return true; // found a win in top-right corner

        // bottom-right
        if (board[boardSize-1][boardSize-1] == p)
            if (checkWinInNeighbors(p, boardSize-1, boardSize-1, l))
                return true; // found a win in bottom-right corner

        // top-right
        if (board[boardSize-1][0] == p)
            if (checkWinInNeighbors(p, boardSize-1, 0, l))
                return true;

        // check middle

        return false;
    }

    private static boolean checkWinInNeighbors(Player p, int col, int row, int num)
    {
        return false; // TODO
    }

    //endregion
}
