package com.sxmh.wt.lotterysystem.fragment.salelottery.base;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.GameRule;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.OnCommitSuccessListener;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.FindRuleRequest;
import com.sxmh.wt.lotterysystem.bean.request.NotOpenQueryRequest;
import com.sxmh.wt.lotterysystem.bean.response.BetHistoryResponse;
import com.sxmh.wt.lotterysystem.bean.response.FindRuleResponse;
import com.sxmh.wt.lotterysystem.bean.response.GameListQueryResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeManager;
import com.sxmh.wt.lotterysystem.util.TimeUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseSelectFragment extends BaseFragment implements Strategy {
    private static final String TAG = "BaseSelectFragment";
    protected GameListQueryResponse.GameListBean gameListBean;
    protected int bet;
    protected boolean isFragmentStoped;
    protected long endTime;
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected NotOpenQueryResponse notOpenQueryResponse;
    protected OnCommitSuccessListener onCommitSuccessListener;
    private boolean isShowing;

    private Timer countDownTimer;
    protected GameRule gameRule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        gameListBean = (GameListQueryResponse.GameListBean) arguments.getSerializable(SaleLotteryFragment.GAME_LIST_BEAN);
        gameRule = (GameRule) arguments.getSerializable(SaleLotteryFragment.GAME_RULE);

        View view = super.onCreateView(inflater, container, savedInstanceState);
        initBox();
        net.getNotOpen(getNotOpenQueryRequestParams());
        return view;
    }

    private NotOpenQueryRequest getNotOpenQueryRequestParams() {
        NotOpenQueryRequest notOpenQueryRequest = new NotOpenQueryRequest();
        notOpenQueryRequest.setInterfaceCode(InterfaceCode.DRAW_NOT_OPEN_QUERY);
        notOpenQueryRequest.setAccountName(UserInfo.getInstance().getName());
        notOpenQueryRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        NotOpenQueryRequest.DataBean dataBean = new NotOpenQueryRequest.DataBean();
        NotOpenQueryRequest.DataBean.DrawInfoBean infoBean = new NotOpenQueryRequest.DataBean.DrawInfoBean();
        infoBean.setGameAlias(gameListBean.getAlias());
        dataBean.setDrawInfo(infoBean);
        notOpenQueryRequest.setData(dataBean);
        return notOpenQueryRequest;
    }

    protected void countingDown() {
        List<NotOpenQueryResponse.DrawListBean> drawList = notOpenQueryResponse.getDrawList();
        if (drawList.size() == 0) return;
        NotOpenQueryResponse.DrawListBean drawListBean = drawList.get(0);
        bet = drawListBean.getDrawId();
        refreshRestTime(drawListBean.getDrawNumber(), "");
        endTime = drawListBean.getEndTime();
        if (countDownTimer == null) {
            countDownTimer = new Timer();
            countDownTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    long l = endTime - TimeManager.getInstance().getServiceTime();
                    if (l > 0) {
                        handler.post(() -> {
                            String restTime = NUtil.formatDuring(l);
                            refreshRestTime(drawListBean.getDrawNumber(), restTime);
                        });

                        if (l < 30000 && !isShowing) {
                            handler.post(() -> {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(drawListBean.getGameName() + " 第 " + drawListBean.getDrawNumber() + " 期马上到期")
                                        .setPositiveButton("确定", null)
                                        .create().show();
                            });
                            isShowing = true;
                        }
                    } else if (l < 0 && l > -2000) {
                        handler.post(() -> {
                            net.getNotOpen(getNotOpenQueryRequestParams());
                            isShowing = false;
                        });
                    } else {
                        Log.e(TAG, "奖期时间错误");
                    }
                }
            }, 0, 1000);
        }
    }

    protected abstract void refreshRestTime(String drawNumber, String restTime);

    @Override
    public void onResume() {
        super.onResume();
        isFragmentStoped = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    public void setOnCommitSuccessListener(OnCommitSuccessListener onCommitSuccessListener) {
        this.onCommitSuccessListener = onCommitSuccessListener;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GET_NOT_OPEN) {
            notOpenQueryResponse = (NotOpenQueryResponse) content;
            countingDown();
//            List<NotOpenQueryResponse.DrawListBean> drawList = notOpenQueryResponse.getDrawList();
//            if (drawList != null && drawList.size() > 0) {
//                long endTime = drawList.get(0).getEndTime();
//                Log.e(TAG, "截止时间: " + NUtil.getFormatDate(endTime) + "\n服务器时间：" + NUtil.getFormatDate(TimeManager.getInstance().getServiceTime()) + "\n系统时间：" + NUtil.getFormatDate(System.currentTimeMillis()));
//            }
        }
    }
}