package com.williamgrand.tictactoe;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

/**
 * Created by wgrand on 5/4/16.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    public GridViewAdapter (Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return GameManager.boardSize*GameManager.boardSize;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RelativeLayout view;

        if (convertView == null) {

            view = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.board_tile_view, parent, false);

            // hardcode width and height
            view.setLayoutParams(new ViewGroup.LayoutParams(convertDpToPixel(300f/GameManager.boardSize), convertDpToPixel(300f/GameManager.boardSize)));

        } else {

            view = (RelativeLayout) convertView;

        }

        int col = position % GameManager.boardSize;
        int row = position / GameManager.boardSize;

        if (GameManager.board[row][col] == null) {
            view.findViewById(R.id.playerOImageView).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.playerXImageView).setVisibility(View.INVISIBLE);
        } else if (GameManager.board[row][col] == Player.O) {
            view.findViewById(R.id.playerOImageView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.playerXImageView).setVisibility(View.INVISIBLE);
        } else if (GameManager.board[row][col] == Player.X) {
            view.findViewById(R.id.playerOImageView).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.playerXImageView).setVisibility(View.VISIBLE);
        }

        return view;
    }

    private int convertDpToPixel(float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
