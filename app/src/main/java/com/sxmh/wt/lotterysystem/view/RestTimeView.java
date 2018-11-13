package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RestTimeView extends FrameLayout {
    @InjectView(R.id.tv_draw)
    TextView tvDraw;
    @InjectView(R.id.tv_end_time)
    TextView tvEndTime;

    public RestTimeView(@NonNull Context context) {
        super(context);
        initWork();
    }

    public RestTimeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public RestTimeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_rest_time, this);
        ButterKnife.inject(this, this);
    }

    public void setDraw(String draw) {
        tvDraw.setText(draw);
    }

    public void setEndTime(String endTime) {
        tvEndTime.setText(endTime);
    }
}
