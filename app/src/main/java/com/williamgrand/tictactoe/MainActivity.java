package com.williamgrand.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridView boardView;
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
        this.boardView = (GridView) this.findViewById(R.id.boardView);
        this.boardView.setNumColumns(GameManager.boardSize);
        this.boardView.setAdapter(boardAdapter);

        /* populate */
        boardAdapter.notifyDataSetChanged();

        /* actions */
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
                        Toast.makeText(MainActivity.this, winner == Player.O ? "O won!" : "X won!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

}
