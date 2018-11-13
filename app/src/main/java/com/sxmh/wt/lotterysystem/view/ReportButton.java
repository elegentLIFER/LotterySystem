package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 39867 on 2018/4/9.
 */

public class ReportButton extends AppCompatButton {
    private boolean isRbPressed;
    private Paint paint;
    private RectF rectF;

    public ReportButton(Context context) {
        super(context);
        initWork();
    }

    public ReportButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public ReportButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    /**
     * 初始化工作
     */
    private void initWork() {
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getLastWidth(widthMeasureSpec), getLastHeight(heightMeasureSpec));
    }

    /**
     * 根据widthMeasureSpec得到最终的宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int getLastWidth(int widthMeasureSpec) {
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);

        int lastW = 0;

        switch (modeW) {
            case MeasureSpec.AT_MOST:
                lastW = getMeasuredWidth();
                break;
            case MeasureSpec.EXACTLY:
                lastW = sizeW;
                break;
            case MeasureSpec.UNSPECIFIED:
                lastW = getMeasuredWidth();
                break;
            default:
                break;
        }
        return lastW;
    }

    /**
     * 根据heightMeasureSpec得到最终的宽度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int getLastHeight(int heightMeasureSpec) {
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);

        int lastH = 0;

        switch (modeH) {
            case MeasureSpec.AT_MOST:
                lastH = getMeasuredHeight();
                break;
            case MeasureSpec.EXACTLY:
                lastH = sizeH;
                break;
            case MeasureSpec.UNSPECIFIED:
                lastH = getMeasuredHeight();
                break;
            default:
                break;
        }
        return lastH;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isRbPressed) {
            setTextColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        } else {
            setTextColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
        }
        if (rectF == null) rectF = new RectF(1, 1, getWidth() - 1, getHeight() - 1);
        canvas.drawRoundRect(rectF, 5, 5, paint);
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isRbPressed = !isRbPressed;
                invalidate();
                performClick();
                break;
            default:
                break;
        }

        return true;
    }

    public boolean isRbPressed() {
        return isRbPressed;
    }

    public void setRbPressed(boolean pressed) {
        isRbPressed = pressed;
        invalidate();
    }
}
