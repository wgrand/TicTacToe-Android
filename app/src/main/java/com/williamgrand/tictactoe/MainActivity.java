package com.williamgrand.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView boardView;
    private GridViewAdapter boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initialize */

        // create a game
        GameManager.createGame(new Player[][] {
                {Player.O, null, null, Player.X},
                {null, Player.O, null, null},
                {null, null, Player.O, null},
                {null, null, null, Player.O}
        });

        boardAdapter = new GridViewAdapter(this);

        /* layout */
        this.boardView = (GridView) this.findViewById(R.id.boardView);
        this.boardView.setNumColumns(GameManager.boardSize);
        this.boardView.setAdapter(boardAdapter);

        /* populate */

        /* actions */
        boardView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int col = position % GameManager.boardSize;
                int row = position / GameManager.boardSize;
                boolean success = GameManager.makeMove(row, col);
                if (success)
                    boardAdapter.notifyDataSetChanged();
            }
        });


    }

}
