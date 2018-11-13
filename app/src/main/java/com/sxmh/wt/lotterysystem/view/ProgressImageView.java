package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ProgressImageView extends android.support.v7.widget.AppCompatImageView {

    public ProgressImageView(Context context) {
        super(context);
        initWork();
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    /**
     * 初始化工作
     */
    private void initWork() {
    }

    /**
     * 开始旋转
     */
    public void startRotate(){

    }

}
