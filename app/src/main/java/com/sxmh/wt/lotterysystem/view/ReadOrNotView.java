package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ReadOrNotView extends FrameLayout {
    @InjectView(R.id.tv_has_read)
    TextView tvHasRead;
    @InjectView(R.id.fl_not_read)
    FrameLayout flNotRead;
    @InjectView(R.id.iv_red_dot)
    ImageView ivRedDot;

    private OnReadStatusChanged onReadStatusChanged;

    public ReadOrNotView(@NonNull Context context) {
        super(context);
        initWork();
    }

    public ReadOrNotView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public ReadOrNotView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_read_or_not, this);
        ButterKnife.inject(this, this);

        tvHasRead.setSelected(true);
        flNotRead.setSelected(false);
    }

    @OnClick({R.id.tv_has_read, R.id.fl_not_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_not_read:
                tvHasRead.setSelected(false);
                flNotRead.setSelected(true);
                onReadStatusChanged.onNotReadSelected();
                break;
            case R.id.tv_has_read:
                tvHasRead.setSelected(true);
                flNotRead.setSelected(false);
                onReadStatusChanged.onReadSelected();
                break;
        }
    }

    public interface OnReadStatusChanged {
        void onReadSelected();

        void onNotReadSelected();
    }

    public void setOnReadStatusChanged(OnReadStatusChanged onReadStatusChanged) {
        this.onReadStatusChanged = onReadStatusChanged;
    }

    public void setHasNewMsg(boolean hasNewMsg) {
        ivRedDot.setImageResource(hasNewMsg ? R.drawable.message_point_red : android.R.color.transparent);
    }
}
