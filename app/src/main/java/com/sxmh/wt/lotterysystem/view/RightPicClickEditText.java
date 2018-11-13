package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.sxmh.wt.lotterysystem.util.ToastUtil;

/**
 * Created by Wang Tao on 2018/4/13 0013.
 */

public class RightPicClickEditText extends AppCompatEditText {

    private static final String TAG = "RightPicClickEditText";

    private ETRightDrawableOnClickListener listener;

    public RightPicClickEditText(Context context) {
        super(context);
    }

    public RightPicClickEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightPicClickEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN && listener != null) {

            float x = event.getX();
            float y = event.getY();

            Drawable[] compoundDrawables = getCompoundDrawables();
            Drawable rDrawable = compoundDrawables[2];
            if (rDrawable != null) {
                Rect bounds = rDrawable.getBounds();

                if (x > getRight() - bounds.width()) {
                    listener.onClick();
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 点击右侧图片后处理监听器
     */
    public interface ETRightDrawableOnClickListener {

        void onClick();
    }

    public void setETRightDrawableOnClickListener(ETRightDrawableOnClickListener listener) {
        this.listener = listener;
    }
}
