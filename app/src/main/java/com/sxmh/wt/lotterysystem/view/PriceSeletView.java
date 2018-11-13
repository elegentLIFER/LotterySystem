package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

/**
 * Created by 39867 on 2018/4/9.
 */

public class PriceSeletView extends AppCompatTextView {
    private static final String TAG = "CircleButton";

    private boolean isRbPressed;
    private Paint paint;
    private RectF rectF;
    /**
     * 基色（默认红色）
     */
    private int baseColor = Color.RED;
    private int strokeWidth = 1;
    private CbOnTouchEvent cbOnTouchEvent;

    public PriceSeletView(Context context) {
        super(context);
        initWork();
    }

    public PriceSeletView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public PriceSeletView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        initPaint();
        setPadding(20, 10, 20, 10);
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(baseColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getLast(widthMeasureSpec, 130), getLast(heightMeasureSpec, 55));
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
        if (rectF == null) rectF = new RectF(strokeWidth, strokeWidth, getWidth() - strokeWidth, getHeight() - strokeWidth);
        if (isRbPressed) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            setTextColor(Color.WHITE);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            setTextColor(baseColor);
        }

        setGravity(Gravity.CENTER);
        canvas.drawRoundRect(rectF, 2, 2, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (cbOnTouchEvent != null) cbOnTouchEvent.onCbTouch();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public interface CbOnTouchEvent {
        void onCbTouch();
    }

    public void setCbOnTouchEvent(CbOnTouchEvent cbOnTouchEvent) {
        this.cbOnTouchEvent = cbOnTouchEvent;
    }

    @Override
    public boolean isPressed() {
        return isRbPressed;
    }

    public void setCbPressed(boolean pressed) {
        isRbPressed = pressed;
        invalidate();
    }

    public void setBaseColor(int baseColor) {
        this.baseColor = baseColor;
        paint.setColor(baseColor);
        invalidate();
    }

    public int getBaseColor() {
        return baseColor;
    }

    /**
     * 设置线宽
     *
     * @param strokeWidth
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        invalidate();
    }
}
