package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.printer.Constant;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.NUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/17 0017.
 */
public class SelectedBallInfo extends LinearLayout {
    private static final String TAG = "SelectedBallInfo";
    @InjectView(R.id.ll_red)
    AutoChangeLineLinearLayout llRed;
    @InjectView(R.id.tv_plus)
    TextView tvPlus;
    @InjectView(R.id.ll_blue)
    LinearLayout llBlue;
    @InjectView(R.id.iv_delete)
    ImageView ivDelete;
    @InjectView(R.id.tv_single_double)
    TextView tvSingleDouble;

    private String betMode;
    private int perCost;
    private int betNum;
    private List<String> redNumList;
    private List<String> blueNumList;
    private boolean changeFormat = true;

    private OnDeleteClickedListener deleteClickedListener;

    public SelectedBallInfo(Context context) {
        super(context);
        initWork();
    }

    public void setChangeFormat(boolean changeFormat) {
        this.changeFormat = changeFormat;
    }

    public SelectedBallInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public SelectedBallInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_selected_ball_info, this, true);
        ButterKnife.inject(this, this);
        redNumList = new ArrayList<>();
        blueNumList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int modeW = MeasureSpec.getMode(widthMeasureSpec);
//        int modeH = MeasureSpec.getMode(heightMeasureSpec);
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//
//        int childCount = getChildCount();
//
//        int w = 0;
//        if (modeW == MeasureSpec.AT_MOST && childCount > 0) {
//            for (int i = 0; i < childCount; i++) {
//                w = w + getChildAt(childCount).getWidth();
//                getChildAt(i).measure(widthMeasureSpec,heightMeasureSpec);//
//            }
//        }else {
//            w = MeasureSpec.getSize(widthMeasureSpec);
//        }
//
//        int h = 0;
//        if (modeH == MeasureSpec.AT_MOST && childCount > 0) {
//            for (int i = 0; i < childCount; i++) {
//                h = h + getChildAt(childCount).getWidth();
//            }
//        }else {
//            h = MeasureSpec.getSize(heightMeasureSpec);
//        }
//
//        setMeasuredDimension(w, h);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//
//        int childCount = getChildCount();
//        int x = 0;
//        int y = 0;
//        for (int i = 0; i < childCount; i++) {
//            View childAt = getChildAt(i);
//            childAt.layout(x, y, x + childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
//            x = x + childAt.getMeasuredWidth();
//            Log.e(TAG, "onLayout: "+childCount);
//        }

//        View childAt = getChildAt(0);
//        int width = childAt.getMeasuredWidth();
//        int height = childAt.getMeasuredHeight();
//        childAt.layout(0, 0, width, height);
    }

    public void addRedBall(int num) {
        String numString = changeFormat ? NUtil.intToLotteryPattern(num) : num + "";
        addRedBallByString(numString);
    }

    public void addRedBallByString(String numString) {
        SelectedBallView ballView = new SelectedBallView(getContext());
        ballView.setText(numString);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 0);
        ballView.setLayoutParams(params);
        redNumList.add(numString);
        llRed.addView(ballView);
    }

    public void addBlueBall(int num) {
        SelectedBallView ballView = new SelectedBallView(getContext());
        String numString = changeFormat ? NUtil.intToLotteryPattern(num) : num + "";
        ballView.setText(numString);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 0);
        ballView.setLayoutParams(params);
        ballView.setBlue();
        blueNumList.add(numString);
        llBlue.addView(ballView);
    }

    @OnClick(R.id.iv_delete)
    public void onViewClicked() {
        if (deleteClickedListener != null) {
            deleteClickedListener.OnDeleteClicked(SelectedBallInfo.this);
        }
    }

    public interface OnDeleteClickedListener {
        void OnDeleteClicked(SelectedBallInfo info);
    }

    public void setDeleteClickedListener(OnDeleteClickedListener deleteClickedListener) {
        this.deleteClickedListener = deleteClickedListener;
    }

    public List<String> getRedNumList() {
        return redNumList;
    }

    public List<String> getBlueNumList() {
        return blueNumList;
    }

    public void hidePlusSymbol() {
        tvPlus.setVisibility(INVISIBLE);
    }

    public void setBetMode(String betMode) {
        this.betMode = betMode;
        if (Constants.BET_MODE_SINGLE.equals(betMode)) {
            tvSingleDouble.setText("单式");
        } else if (Constants.BET_MODE_DOUBLE.equals(betMode)) {
            tvSingleDouble.setText("复式");
        } else if (Constants.BET_MODE_DRAG_DOUBLE.equals(betMode)) {
            tvSingleDouble.setText("胆拖复式");
        } else if (Constants.BET_MODE_DOUBLE.equals(betMode)) {
            tvSingleDouble.setText("混合模式");
        }
    }

    public String getBetMode() {
        return betMode;
    }

    public void setPerCost(int perCost) {
        this.perCost = perCost;
    }

    public int getPerCost() {
        return perCost;
    }

    public int getBetNum() {
        return betNum;
    }

    public void setBetNum(int betNum) {
        this.betNum = betNum;
    }
}