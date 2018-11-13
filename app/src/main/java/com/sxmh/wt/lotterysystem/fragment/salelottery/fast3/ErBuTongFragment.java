package com.sxmh.wt.lotterysystem.fragment.salelottery.fast3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvSelectNumberRectAdapter;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.bean.NotOpenResponseCallBack;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.Fast3CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Fast3NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Fast3CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.FixedTypeResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.BaseSelectFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.Fast3Fragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.Fast3ChildBaseFragment;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.LotteryUtil;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.RandomUtil;
import com.sxmh.wt.lotterysystem.util.SignUtil;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class ErBuTongFragment extends Fast3ChildBaseFragment  {
    @InjectView(R.id.rv_select_box)
    RecyclerView rvSelectBox;

    private List<CircleBtStatus> statusListBox;
    private RvSelectNumberRectAdapter adapterBox;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_erbutong;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initBox() {
        statusListBox = new ArrayList<>();
        statusListBox.add(new CircleBtStatus(Color.RED, 1, false));
        statusListBox.add(new CircleBtStatus(Color.RED, 2, false));
        statusListBox.add(new CircleBtStatus(Color.RED, 3, false));
        statusListBox.add(new CircleBtStatus(Color.RED, 4, false));
        statusListBox.add(new CircleBtStatus(Color.RED, 5, false));
        statusListBox.add(new CircleBtStatus(Color.RED, 6, false));
        adapterBox = new RvSelectNumberRectAdapter(getContext(), statusListBox, 6);
        rvSelectBox.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvSelectBox.setAdapter(adapterBox);
    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        int[] randomIntRed = RandomUtil.getRandomInt(1, 6, 2);
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();
        sbiSelectBallInfo.setChangeFormat(false);
        sbiSelectBallInfo.addRedBall(randomIntRed[0]);
        sbiSelectBallInfo.addRedBall(randomIntRed[1]);

        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setBetNum(1);
        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<SelectedBallInfo> infoArrayList = new ArrayList<>();

        List<Integer> boxes = getBoxSelectNumList();
        int size = boxes.size();
        if (size < 2) {
            ToastUtil.newToast(getContext(), "二不同号请至少选择2个号码");
            return null;
        }

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.setChangeFormat(false);
        sbiSelectBallInfo.hidePlusSymbol();

        List<List<Integer>> twoZuhe = NUtil.getTwoZuhe(boxes);
        int size1 = twoZuhe.size();
        for (int i = 0; i < size; i++) {
//            List<Integer> integers = twoZuhe.get(i);
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < integers.size(); j++) {
//                sb.append(integers.get(j));
//            }
            sbiSelectBallInfo.addRedBall(boxes.get(i));
        }

        sbiSelectBallInfo.setBetNum(size1);
        sbiSelectBallInfo.setBetMode(size1 > 1 ? Constants.BET_MODE_DOUBLE : Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setPerCost(gameRule.getR007() * size1);
        infoArrayList.add(sbiSelectBallInfo);
        resetWaitArea();
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
                String s = redNumList.get(j);
                stringBuilder.append(s);
                if (j != sizeR - 1) {
                    stringBuilder.append(" ");
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
}
