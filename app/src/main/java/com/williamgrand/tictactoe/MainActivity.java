package com.williamgrand.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
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
        this.boardView.setNumColumns(GameManager.dimen);
        this.boardView.setAdapter(boardAdapter);

        /* populate */
        drawBoard();

        /* actions */

        // board click
        boardView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int col = position % GameManager.dimen;
                int row = position / GameManager.dimen;

                boolean moveMade = GameManager.makeMove(row, col);

                if (moveMade) {

                    // update board
                    drawBoard();

                    Player[][] w = GameManager.getWinningMatrix();

                    if (w != null) {
                        flashWin(w);
                    }

                    // TODO handle tie

                }

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

    protected void drawBoard()
    {
        // render board
        boardAdapter.notifyDataSetChanged();

        // show player turn
        turnView.setBackgroundResource(GameManager.getTurnColorResourceId());

    }

    protected void flashWin(Player[][] w)
    {

//        Toast.makeText(MainActivity.this, winner == Player.O ? "O won!" : "X won!", Toast.LENGTH_SHORT).show(); // TODO prompt user

        Toast.makeText(MainActivity.this, "Win!", Toast.LENGTH_SHORT).show(); // TODO prompt user

        int position = 0;
        for (int row = 0; row < GameManager.dimen; row++) {
            for (int col = 0; col < GameManager.dimen; col++) {

                // get position
                position = row*GameManager.dimen+col;

                // apply animation to winner's tiles
                if (w[row][col] != null) {

                    // get the white overlay on the tile
                    View tileView = this.boardView.getChildAt(position);
                    final View flashWinnerOverlayView = tileView.findViewById(R.id.flashWinnerOverlayView);

                    // set the overlay to visible
                    flashWinnerOverlayView.setVisibility(View.VISIBLE);

                    AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
                    animation.setDuration(500);
//                animation.setStartOffset(100*position);
//                animation.setRepeatMode(Animation.REVERSE);
                    animation.setRepeatCount(3);

                    animation.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                            flashWinnerOverlayView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationStart(Animation arg0) {
                            flashWinnerOverlayView.setVisibility(View.VISIBLE);

                        }

                    });

                    flashWinnerOverlayView.setAnimation(animation);
                }

            }
            System.out.println();
        }

    }
}
