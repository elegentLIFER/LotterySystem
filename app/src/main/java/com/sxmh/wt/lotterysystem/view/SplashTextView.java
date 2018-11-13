package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

public class SplashTextView extends AppCompatTextView {
    public SplashTextView(Context context) {
        super(context);
    }

    public SplashTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplashTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        super.onDraw(canvas);
    }
}
