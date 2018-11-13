package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sxmh.wt.lotterysystem.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class QuickSelectView extends FrameLayout {
    @InjectView(R.id.rb_big)
    ReportButton rbBig;
    @InjectView(R.id.rb_small)
    ReportButton rbSmall;
    @InjectView(R.id.rb_single)
    ReportButton rbSingle;
    @InjectView(R.id.rb_double)
    ReportButton rbDouble;

    private OnQuickItemSelectListener listener;

    public QuickSelectView(@NonNull Context context) {
        super(context);
        initWork();
    }

    public QuickSelectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public QuickSelectView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_quick_select, this);
        ButterKnife.inject(this, this);
    }

    @OnClick({R.id.rb_big, R.id.rb_small, R.id.rb_single, R.id.rb_double})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_big:
                rbSmall.setRbPressed(false);
                if (rbBig.isRbPressed()) {
                    if (rbSingle.isRbPressed()) {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0};
                        listener.notifyUI(arr);
                    } else if (rbDouble.isRbPressed()) {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
                        listener.notifyUI(arr);
                    }
                } else {
                    if (rbSingle.isRbPressed()) {
                        int[] arr = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
                        listener.notifyUI(arr);
                    } else if (rbDouble.isRbPressed()) {
                        int[] arr = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    }
                }
                break;
            case R.id.rb_small:
                rbBig.setRbPressed(false);
                if (rbSmall.isRbPressed()) {
                    if (rbSingle.isRbPressed()) {
                        int[] arr = {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    } else if (rbDouble.isRbPressed()) {
                        int[] arr = {0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    }
                } else {
                    if (rbSingle.isRbPressed()) {
                        int[] arr = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
                        listener.notifyUI(arr);
                    } else if (rbDouble.isRbPressed()) {
                        int[] arr = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    }
                }
                break;
            case R.id.rb_single:
                rbDouble.setRbPressed(false);
                if (rbSingle.isRbPressed()) {
                    if (rbBig.isRbPressed()) {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0};
                        listener.notifyUI(arr);
                    } else if (rbSmall.isRbPressed()) {
                        int[] arr = {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
                        listener.notifyUI(arr);
                    }
                } else {
                    if (rbBig.isRbPressed()) {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
                        listener.notifyUI(arr);
                    } else if (rbSmall.isRbPressed()) {
                        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    }
                }

                break;
            case R.id.rb_double:
                rbSingle.setRbPressed(false);
                if (rbDouble.isRbPressed()) {
                    if (rbBig.isRbPressed()) {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1};
                        listener.notifyUI(arr);
                    } else if (rbSmall.isRbPressed()) {
                        int[] arr = {0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
                        listener.notifyUI(arr);
                    }
                } else {
                    if (rbBig.isRbPressed()) {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
                        listener.notifyUI(arr);
                    } else if (rbSmall.isRbPressed()) {
                        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    } else {
                        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        listener.notifyUI(arr);
                    }
                }
                break;
        }
    }

    public interface OnQuickItemSelectListener {
        void notifyUI(int[] arr);
    }

    public void setOnQuickItemSelectListener(OnQuickItemSelectListener listener) {
        this.listener = listener;
    }

    public void resetView() {
        rbBig.setRbPressed(false);
        rbSmall.setRbPressed(false);
        rbDouble.setRbPressed(false);
        rbSingle.setRbPressed(false);
    }
}
