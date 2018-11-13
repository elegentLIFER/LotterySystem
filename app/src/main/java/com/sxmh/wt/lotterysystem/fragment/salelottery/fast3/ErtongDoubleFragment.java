package com.sxmh.wt.lotterysystem.fragment.salelottery.fast3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class ErtongDoubleFragment extends Fast3ChildBaseFragment {
    @InjectView(R.id.rv_similar_box)
    RecyclerView rvSimilarBox;

    private List<CircleBtStatus> similarStatusList;
    private RvSelectNumberRectAdapter adapterBoxSimilar;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_ertong_double;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initBox() {
        similarStatusList = new ArrayList<>();
        for (int i = 1; i < 7; i++)
            similarStatusList.add(new CircleBtStatus(Color.RED, i * 11, false));

        adapterBoxSimilar = new RvSelectNumberRectAdapter(getContext(), similarStatusList, 6);
        adapterBoxSimilar.setAddStar(true);
        rvSimilarBox.setLayoutManager(new GridLayoutManager(getContext(), 6));
        rvSimilarBox.setAdapter(adapterBoxSimilar);
    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        int[] randomIntRed = RandomUtil.getRandomInt(0, 5, 1);
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();
        sbiSelectBallInfo.addRedBall(similarStatusList.get(randomIntRed[0]).getNum());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetNum(1);
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
        sbiSelectBallInfo.setPerCost(size * gameRule.getR007());
        sbiSelectBallInfo.setBetNum(size);
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
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
        int sizeBox = similarStatusList.size();
        List<Integer> selectedBoxList = new ArrayList<>();
        for (int i = 0; i < sizeBox; i++) {
            CircleBtStatus circleBtStatus = similarStatusList.get(i);
            if (circleBtStatus.getIsPressed()) {
                selectedBoxList.add(circleBtStatus.getNum());
            }
        }
        return selectedBoxList;
    }

    @Override
    public void resetWaitArea() {
        for (CircleBtStatus status : similarStatusList) {
            status.setIsPressed(false);
        }
        adapterBoxSimilar.notifyDataSetChanged();
    }

    @Override
    protected List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(0);
        List<String> redNumList = selectedBallInfo.getRedNumList();

        int sizeR = redNumList.size();
        for (int j = 0; j < sizeR; j++) {
            Integer integer = Integer.valueOf(redNumList.get(j));
            int num = integer / 10;
            stringBuilder.append(num + " ");
            stringBuilder.append(num + " 200");
            boolean notLast = (j != sizeR - 1);
            if (notLast) {
                stringBuilder.append("|");
            }
        }
        Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = new Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean();
        bean.setTicket(stringBuilder.toString());
        bean.setEachBetMode(selectedBallInfo.getBetMode());
        bean.setEachTotalMoney(selectedBallInfo.getPerCost() + "");
        ticketListBean.add(bean);
        return ticketListBean;
    }
}
