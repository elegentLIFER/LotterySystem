package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class AutoChangeLineLinearLayout extends LinearLayout {
    private static final String TAG = "AutoChangeLineLinearLay";

    public AutoChangeLineLinearLayout(Context context) {
        super(context);
    }

    public AutoChangeLineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoChangeLineLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setBackgroundColor(Color.GREEN);
//        setMeasuredDimension(40 * 10, getChildCount() / 5 * 40);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        int childCount = getChildCount();
//        int x = 0;
//        int y = 0;
//        int unit = 10;
//        for (int i = 0; i < childCount; i++) {
//            View childAt = getChildAt(i);
//            childAt.layout(x, y, x + childAt.getMeasuredWidth(), y + childAt.getMeasuredHeight());
//            boolean isLast = (i + 1) % unit == 0;
//            if (isLast) {
//                x = 0;
//                y += childAt.getHeight();
//                continue;
//            }
//            x = x + 40;
//        }
    }
}
