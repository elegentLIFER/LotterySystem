package com.sxmh.wt.lotterysystem.fragment.salelottery.fast3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvSelectNumberRectAdapter;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.NotOpenResponseCallBack;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.Fast3CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Fast3NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Fast3CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.Fast3ChildBaseFragment;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.RandomUtil;
import com.sxmh.wt.lotterysystem.util.SignUtil;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.QuickSelectView;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class HezhiFragment extends Fast3ChildBaseFragment implements QuickSelectView.OnQuickItemSelectListener {
    @InjectView(R.id.rv_select_box)
    RecyclerView rvSelectBox;
    @InjectView(R.id.quick_select_view)
    QuickSelectView quickSelectView;

    private List<CircleBtStatus> statusListBox;
    private RvSelectNumberRectAdapter adapterBox;
    private int betNum;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_hezhi;
    }

    @Override
    protected void initData() {
        quickSelectView.setOnQuickItemSelectListener(this);
    }

    @Override
    public void initBox() {
        statusListBox = new ArrayList<>();
        for (int i = 3; i <= 18; i++) {
            statusListBox.add(new CircleBtStatus(Color.RED, i, false));
        }
        adapterBox = new RvSelectNumberRectAdapter(getContext(), statusListBox, 16);
        rvSelectBox.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvSelectBox.setAdapter(adapterBox);
    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();
        sbiSelectBallInfo.setChangeFormat(false);

        int[] randomIntRed = RandomUtil.getRandomInt(3, 18, 1);
        sbiSelectBallInfo.addRedBall(randomIntRed[0]);
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        betNum = 1;
        sbiSelectBallInfo.setBetNum(betNum);

        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<SelectedBallInfo> infoArrayList = new ArrayList<>();
        List<Integer> boxes = getBoxSelectNumList();
        int size = boxes.size();
        if (size == 0) return null;

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.setChangeFormat(false);
        sbiSelectBallInfo.hidePlusSymbol();

        for (int i = 0; i < size; i++) {
            sbiSelectBallInfo.addRedBall(boxes.get(i));
        }

        betNum = size;
        sbiSelectBallInfo.setBetNum(betNum);
        sbiSelectBallInfo.setPerCost(betNum * gameRule.getR007());
        sbiSelectBallInfo.setBetMode(betNum > 1 ? Constants.BET_MODE_DOUBLE : Constants.BET_MODE_SINGLE);
        resetWaitArea();
        infoArrayList.add(sbiSelectBallInfo);
        return infoArrayList;
    }

    /**
     * 得到已经选中的盒子数字
     *
     * @return
     */
    private List<Integer> getBoxSelectNumList() {
        int sizeBox = statusListBox.size();
        List<Integer> selectedBoxList = new ArrayList<>();
        for (int i = 0; i < sizeBox; i++) {
            CircleBtStatus circleBtStatus = statusListBox.get(i);
            if (circleBtStatus.getIsPressed()) {
                selectedBoxList.add(circleBtStatus.getNum());
            }
        }
        return selectedBoxList;
    }

    @Override
    public void resetWaitArea() {
        for (CircleBtStatus status : statusListBox) {
            status.setIsPressed(false);
        }
        adapterBox.notifyDataSetChanged();
        quickSelectView.resetView();
    }

    @Override
    protected List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
        int childCount = llAllBallInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
            List<String> redNumList = selectedBallInfo.getRedNumList();

            int sizeR = redNumList.size();
            for (int j = 0; j < sizeR; j++) {
                stringBuilder.append(redNumList.get(j));
                if (j != sizeR - 1) {
                    stringBuilder.append("|");
                }
            }

            Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = new Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean();
            bean.setTicket(stringBuilder.toString());
            bean.setEachBetMode(selectedBallInfo.getBetMode());
            bean.setEachTotalMoney(selectedBallInfo.getPerCost() + "");
            ticketListBean.add(bean);
        }
        return ticketListBean;
    }

    @Override
    public void notifyUI(int[] arr) {
        for (int i = 0; i < 16; i++) {
            CircleBtStatus status = statusListBox.get(i);
            status.setIsPressed(arr[i] == 1 ? true : false);
        }
        adapterBox.notifyDataSetChanged();
    }
}
