package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;

/**
 * Created by 39867 on 2018/4/9.
 */

public class CircleButton extends AppCompatTextView {
    private static final String TAG = "CircleButton";
    public static final int DEFAULT_PADDING = 20;

    private boolean isCbPressed = false;
    private Paint paint;
    /**
     * 基色（默认红色）
     */
    private int baseColor = Color.RED;
    private int strokeWidth = 1;

    private CbOnTouchEvent cbOnTouchEvent;

    public CircleButton(Context context) {
        super(context);
        initWork();
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        initPaint();
        setGravity(Gravity.CENTER);
        setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING);
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(baseColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int lastW = getLast(widthMeasureSpec, 60);
        int lastH = getLast(heightMeasureSpec, 60);
        setMeasuredDimension(lastW,lastH);
    }

    private int getLast(int measureSpec, int defaultValue) {
        int mode = MeasureSpec.getMode(measureSpec);
        int last = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                last = MeasureSpec.getSize(measureSpec);
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                last = defaultValue;
                break;
        }
        return last;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isCbPressed) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            setTextColor(Color.WHITE);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            setTextColor(baseColor);
        }
        int max = Math.max(getWidth(), getHeight());
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, max / 2 - strokeWidth * 2, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (cbOnTouchEvent != null) cbOnTouchEvent.onCbTouch();
                invalidate();
                return true;
        }
        return false;
    }

    public interface CbOnTouchEvent {
        void onCbTouch();
    }

    public void setCbOnTouchEvent(CbOnTouchEvent cbOnTouchEvent) {
        this.cbOnTouchEvent = cbOnTouchEvent;
    }

    public boolean isCbPressed() {
        return isCbPressed;
    }

    public void setCbPressed(boolean pressed) {
        isCbPressed = pressed;
        invalidate();
    }

    public void setBaseColor(int baseColor) {
        this.baseColor = baseColor;
        paint.setColor(baseColor);
        invalidate();
    }
}
