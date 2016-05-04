package com.williamgrand.tictactoe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
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

        View view;

        if (convertView == null) {

            view = new View(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(convertDpToPixel(75f), convertDpToPixel(75f)));

        } else {

            view = convertView;

        }

        int col = position % GameManager.boardSize;
        int row = position / GameManager.boardSize;

        if (GameManager.board[row][col] == null)
            view.setBackgroundColor(Color.WHITE);
        else if (GameManager.board[row][col] == Player.O)
            view.setBackgroundColor(Color.BLUE);
        else if (GameManager.board[row][col] == Player.X)
            view.setBackgroundColor(Color.RED);

        return view;
    }

    private int convertDpToPixel(float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}
