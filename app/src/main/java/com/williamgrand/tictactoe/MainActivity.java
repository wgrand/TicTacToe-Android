package com.williamgrand.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View turnView;
    private GridView boardView;
    private View winBoardView;
    private GridViewAdapter boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initialize */

        // create a game
        GameManager.createGame();

        boardAdapter = new GridViewAdapter(this);

        /* layout */
        this.turnView = this.findViewById(R.id.turnView);
        this.winBoardView = this.findViewById(R.id.winBoardView);
        this.boardView = (GridView) this.findViewById(R.id.boardView);
        this.boardView.setNumColumns(GameManager.boardSize);
        this.boardView.setAdapter(boardAdapter);

        /* populate */

        // render board
        boardAdapter.notifyDataSetChanged();

        // show player turn
        turnView.setBackgroundResource(GameManager.getTurnColorResourceId());

        /* actions */

        // board click
        boardView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int col = position % GameManager.boardSize;
                int row = position / GameManager.boardSize;

                boolean success = GameManager.makeMove(row, col);

                if (success) {

                    // update board
                    boardAdapter.notifyDataSetChanged();

                    Player winner = GameManager.checkWinner();

                    if (winner != null)
                        Toast.makeText(MainActivity.this, winner == Player.O ? "O won!" : "X won!", Toast.LENGTH_SHORT).show(); // TODO prompt user

                    // TODO handle tie

                }

                // show player turn
                turnView.setBackgroundResource(GameManager.getTurnColorResourceId());

            }
        });

        // new game
        this.findViewById(R.id.newGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create a new game
                GameManager.createGame();

                // redraw the board
                boardAdapter.notifyDataSetChanged();

                // show player turn
                turnView.setBackgroundResource(GameManager.getTurnColorResourceId());

            }
        });


    }

}
