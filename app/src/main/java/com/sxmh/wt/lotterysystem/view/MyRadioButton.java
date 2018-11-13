package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MyRadioButton extends AppCompatRadioButton {
    private Paint paint;
    private RectF rectF;

    public MyRadioButton(Context context) {
        super(context);
        initWork();
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(getTextSize());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int lastW = getLast(widthMeasureSpec, 100);
        int lastH = getLast(heightMeasureSpec, 50);
        setMeasuredDimension(lastW, lastH);
    }

    /**
     * 获取最终计算出的宽/高
     *
     * @param measureSpec
     * @param defaultValue
     * @return
     */
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
        if (rectF == null) rectF = new RectF(3, 1, getWidth() - 3, getHeight() - 1);
        int width = getWidth();
        int height = getHeight();

        String text = getText().toString();
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textW = paint.measureText(text);

        paint.setColor(Color.RED);
        if (isChecked()) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRoundRect(rectF, 10, 10, paint);
            paint.setColor(Color.WHITE);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(rectF, 10, 10, paint);
        }

        canvas.drawText(text,
                (width - textW) / 2,
                height / 2 - fm.descent + (fm.descent - fm.ascent) / 2,
                paint);

    }
}
