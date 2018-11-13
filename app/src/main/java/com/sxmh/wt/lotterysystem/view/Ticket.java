package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Ticket extends FrameLayout {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rv_select_info)
    RecyclerView rvSelectInfo;
    @InjectView(R.id.tv_draw)
    TextView tvDraw;
    @InjectView(R.id.tv_open_time)
    TextView tvOpenTime;
    @InjectView(R.id.tv_buy_time)
    TextView tvBuyTime;
    @InjectView(R.id.tv_terminal_num)
    TextView tvTerminalNum;
    @InjectView(R.id.tv_sum_win_left)
    TextView tvSumWinLeft;
    @InjectView(R.id.tv_bar_code)
    TextView tvBarCode;
    @InjectView(R.id.ll_cash_chart)
    LinearLayout llCashChart;
    @InjectView(R.id.tv_subtitle)
    TextView tvSubtitle;
    @InjectView(R.id.tv_safe_code)
    TextView tvSafeCode;

    public Ticket(@NonNull Context context) {
        super(context);
        initWork();
    }

    public Ticket(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public Ticket(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_ticket, this, true);
        ButterKnife.inject(this, this);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setSubTitle(String subtitle) {
        tvSubtitle.setText(subtitle);
    }

    public void setOtherInfo(String otherInfo) {
        tvSafeCode.setText(otherInfo);
    }

    public void setDraw(String draw) {
        tvDraw.setText(draw);
    }

    public void setBuyTime(String buyTime) {
        tvBuyTime.setText(buyTime);
    }

    public void setOpenTime(String openTime) {
        tvOpenTime.setText(openTime);
    }

    public void setSumWinLeft(String sumWinLeft ) {
        tvSumWinLeft.setText(sumWinLeft);
    }

    public void setTerminalNum(String terminalNum) {
        tvTerminalNum.setText(terminalNum);
    }

    public void setSafeCode(String safeCode) {
        tvSafeCode.setText(safeCode);
    }
}
