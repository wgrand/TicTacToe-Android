package com.williamgrand.tictactoe;

/**
 * Created by wgrand on 4/29/16.
 */
public class GameManager {

    public static Player[][] board;
    public static int dimen = 4;

    private static Player turn = Player.O;
    private static int moveCount = 0;
    private static Player[][] lastWin;

    public static void createGame()
    {
        // create a game of size 4x4
        createGame(4);
    }

    public static void createGame(Player[][] board)
    {
        GameManager.board = board;
        dimen = board[0].length;
        moveCount = 0;
        lastWin = null;
    }

    public static void createGame(int boardSize)
    {
        board = new Player[boardSize][boardSize];
        GameManager.dimen = boardSize;
        moveCount = 0;
        lastWin = null;
    }

    public static void printBoard()
    {
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++)
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

        // a move has already been made at that coordinate, return unsuccessful
        if (board[row][col] != null)
            return false;

        // check for full board
        if (moveCount >= dimen * dimen)
            return false;

        // check if game already won
        if (lastWin != null)
            return false;

        // set that coordinate to the new player
        board[row][col] = turn;

        // move was successful
        moveCount++;


            // TODO
            System.out.println("Game over");

        turn = (turn == Player.O ? Player.X : Player.O);

        return true;

    }

    public static boolean maxMovesReached()
    {
        return moveCount >= dimen * dimen;
    }


    // endregion


    // region Check Win

    public static Player[][] getWinningMatrix()
    {
        lastWin = null;
        if ((lastWin = checkWin(Player.O)) != null)
            return lastWin;
        if ((lastWin = checkWin(Player.X)) != null)
            return lastWin;
        return lastWin;
    }

    private static Player[][] checkWin(Player p)
    {
        lastWin = null;

        if ((lastWin = checkVerticalWin(p)) != null)
            return lastWin;
        else if ((lastWin = checkHorizontalWin(p)) != null)
            return lastWin;
        else if ((lastWin = checkDiagonalWin(p)) != null)
            return lastWin;
        else if ((lastWin = checkFourCornersWin(p)) != null)
            return lastWin;
        else if ((lastWin = checkBlobWin(p)) != null)
            return lastWin;
        else
            return lastWin;
    }

    public static Player[][] checkVerticalWin(Player p)
    {
        Player[][] w = new Player[dimen][dimen];

        boolean foundWinInColumn = false;

        // traverse horizontally until we find a player
        for (int col = 0; col < dimen; col++) {
            if (board[0][col] == p) // found possible win, traverse downward to verify
            {
                foundWinInColumn = true;
                for (int row = 0; row < dimen; row++)

                    if (board[row][col] == p) {
                        // populate the winning matrix with the player's marker
                        w[row][col] = p;
                    } else {
                        // found opposing player, continue to loop through the columns
                        foundWinInColumn = false;
                        // clear matrix
                        w = new Player[dimen][dimen];
                        break;
                    }
                // entire column belonged to player, return true
                if (foundWinInColumn)
                    return w;
            }
        }

        return null;

    }

    public static Player[][] checkHorizontalWin(Player p)
    {
        Player[][] w = new Player[dimen][dimen];

        // traverse vertically until we find a player
        boolean foundWinInRow = false;
        for (int row = 0; row < dimen; row++)
            if (board[row][0] == p) // found possible win, traverse downward to verify
            {
                foundWinInRow = true;
                for (int col = 0; col < dimen; col++)
                    if (board[row][col] == p) {
                        // populate the winning matrix with the player's marker
                        w[row][col] = p;
                    } else {
                        // found opposing player, continue to loop through the rows
                        foundWinInRow = false;
                        // clear matrix
                        w = new Player[dimen][dimen];
                        break;
                    }

                // entire row belonged to player, return true
                if (foundWinInRow)
                    return w;
            }

        return null;
    }

    public static Player[][] checkDiagonalWin(Player p)
    {

        Player[][] w = new Player[dimen][dimen];

        boolean foundWin = true;

        // check top-left to bottom-right
        for (int i = 0; i < dimen; i++)
            if (board[i][i] == p)
                w[i][i] = p;
            else // no win found, move on to check other diagonal
            {
                foundWin = false;
                // clear matrix
                w = new Player[dimen][dimen];
                break;
            }

        if (foundWin)
            return w;

        // check bottom-left to top-right
        for (int i = 0; i < dimen; i++) {
            if (board[i][dimen - 1 - i] == p)
                w[i][dimen - 1 - i] = p;
            else
                return null; // no win in this diagonal either, return false
        }

        return w; // found win in bottom-left to top-right diagonal

    }

    public static Player[][] checkFourCornersWin(Player p)
    {

        if (board[0][0] == p
                && board[dimen-1][0] == p
                && board[0][dimen-1] == p
                && board[dimen-1][dimen-1] == p) {
            Player[][] w = new Player[dimen][dimen];
            w[0][0] = w[dimen - 1][0] = w[0][dimen - 1] = w[dimen - 1][dimen - 1] = p;
            return w;
        }
        return null;
    }

    // Connected-component labeling algorithm could handle larger areas and various patterns: https://en.wikipedia.org/wiki/Connected-component_labeling
    public static Player[][] checkBlobWin(Player p)
    {

        Player[][] w = new Player[dimen][dimen];

        // traverse by row
        for (int v = 1; v < dimen; v++) {

            // traverse by column
            for (int u = 1; u < dimen; u++) {

                // found match, so check left, above, and left-above
                if (board[v][u] == p) {

                    Player leftP = u - 1 < 0 ? null : board[v][u - 1];
                    Player aboveP = v - 1 < 0 ? null : board[v-1][u];
                    Player leftAboveP = (u-1 < 0 || v-1 < 0) ? null : board[v-1][u-1];

                    if (leftP == p && aboveP == p && leftAboveP == p) {
                        w[v][u] = w[v][u - 1] = w[v - 1][u] = w[v - 1][u - 1] = p;
                        return w;
                    }

                }

            }
        }

        return null;

    }

    //endregion
}
