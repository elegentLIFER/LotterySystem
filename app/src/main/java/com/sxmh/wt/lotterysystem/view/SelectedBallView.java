package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.sxmh.wt.lotterysystem.R;

public class SelectedBallView extends AppCompatTextView {
    public SelectedBallView(Context context) {
        super(context);
        initWork();
    }

    public SelectedBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public SelectedBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        setGravity(Gravity.CENTER);
        setTextColor(Color.WHITE);
        setPadding(10,0,10,0);
        setBackgroundResource(R.drawable.shape_select_view_red);
    }

    public void setBlue() {
        setBackgroundResource(R.drawable.shape_select_view_blue);
    }
}
