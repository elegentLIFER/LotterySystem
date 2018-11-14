package com.sxmh.wt.lotterysystem.fragment.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvBetHistoryAdapter;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.GameRule;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.BetHistoryRequest;
import com.sxmh.wt.lotterysystem.bean.request.FindRuleRequest;
import com.sxmh.wt.lotterysystem.bean.request.GameListQueryRequest;
import com.sxmh.wt.lotterysystem.bean.request.RefundTicketRequest;
import com.sxmh.wt.lotterysystem.bean.response.BetHistoryResponse;
import com.sxmh.wt.lotterysystem.bean.response.FindRuleResponse;
import com.sxmh.wt.lotterysystem.bean.response.GameListQueryResponse;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public class BuyHistoryFragment extends BaseFragment implements AdapterView.OnItemClickListener,
        RvBetHistoryAdapter.OnRefundTicketClickListener, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.rv_buy_history)
    RecyclerView rvBuyHistory;
    @InjectView(R.id.tv_game_type)
    TextView tvGameType;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<BetHistoryResponse.BettingListBean> beanList;
    private RvBetHistoryAdapter adapter;

    private ListPopupWindow modeSelectPopWin;
    private List<GameListQueryResponse.GameListBean> gameList;
    private GameListQueryResponse.GameListBean currentGame;

    private GameRule gameRule;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_buy_history;
    }

    @Override
    protected void initData() {
        gameList = new ArrayList<>();
        net.getGameList(getQueryGameListRequest());

        gameRule = new GameRule();

        beanList = new ArrayList<>();
        adapter = new RvBetHistoryAdapter(getContext(), beanList);
        adapter.setOnRefundTicketClickListener(this);
        rvBuyHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvBuyHistory.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvBuyHistory.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorMainRed));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private GameListQueryRequest getQueryGameListRequest() {
        GameListQueryRequest gameListQueryRequest = new GameListQueryRequest();
        gameListQueryRequest.setAccountName(UserInfo.getInstance().getName());
        gameListQueryRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        gameListQueryRequest.setInterfaceCode(InterfaceCode.INIT_GAME_REQ);
        GameListQueryRequest.DataBean dataBean = new GameListQueryRequest.DataBean();
        GameListQueryRequest.DataBean.InitGameReqBean initGameReqBean = new GameListQueryRequest.DataBean.InitGameReqBean();
        initGameReqBean.setGameName("");
        initGameReqBean.setFuzzySearch("");
        initGameReqBean.setLaunch("00");
        initGameReqBean.setPageNo("0");
        dataBean.setInitGameReq(initGameReqBean);
        gameListQueryRequest.setData(dataBean);
        return gameListQueryRequest;
    }

    private BetHistoryRequest getBetHistoryRequest() {
        BetHistoryRequest request = new BetHistoryRequest();
        request.setAccountName(UserInfo.getInstance().getName());
        request.setInterfaceCode(InterfaceCode.HISTORY_BETTING_QUERY);
        request.setRequestTime(TimeUtil.get10IntTimeStamp());
        BetHistoryRequest.DataBean data = new BetHistoryRequest.DataBean();
        BetHistoryRequest.DataBean.BettingInfoBean bettingInfo = new BetHistoryRequest.DataBean.BettingInfoBean();
        bettingInfo.setGameAlias(currentGame.getAlias());
        data.setBettingInfo(bettingInfo);
        request.setData(data);
        return request;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GAME_LIST) {
            GameListQueryResponse response = (GameListQueryResponse) content;
            gameList.clear();
            gameList.addAll(response.getGameList());
            currentGame = gameList.get(0);
            net.historyBettingQuery(getBetHistoryRequest());
            tvGameType.setText(gameList.get(0).getGameName());
            currentGame = gameList.get(0);
            net.findRule(getFindRuleRequest());
        } else if (request == Net.REQUEST_BET_HISTORY) {
            swipeRefreshLayout.setRefreshing(false);
            BetHistoryResponse response = (BetHistoryResponse) content;
            beanList.clear();
            beanList.addAll(response.getBettingList());
            Collections.sort(beanList, (o1, o2) -> (int) (o2.getBuyTime() - o1.getBuyTime()));
            adapter.notifyDataSetChanged();
        } else if (request == Net.REQUEST_FIND_RULE) {
            swipeRefreshLayout.setRefreshing(false);
            FindRuleResponse response = (FindRuleResponse) content;
            gameRule.setData(response.getRuleList());
            adapter.setRefundDuration(gameRule.getR008());
        } else if (request == Net.REQUEST_REFUND_TICKET) {
            new AlertDialog.Builder(getContext()).setTitle("退票成功")
                    .setPositiveButton("确定", null)
                    .create().show();
        }
    }

    @OnClick(R.id.tv_game_type)
    public void onViewClicked() {
        showFixedTypeDialog();
    }

    private void showFixedTypeDialog() {
        modeSelectPopWin = new ListPopupWindow(getContext());
        int size = gameList.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = gameList.get(i).getGameName();
        }
        ArrayAdapter<String> fixedGameTypeListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strings);
        modeSelectPopWin.setAdapter(fixedGameTypeListAdapter);
        modeSelectPopWin.setAnchorView(tvGameType);
        modeSelectPopWin.setDropDownGravity(Gravity.BOTTOM);
        modeSelectPopWin.setWidth(tvGameType.getWidth());
        modeSelectPopWin.setHeight(ListPopupWindow.WRAP_CONTENT);
        modeSelectPopWin.setModal(true);
        modeSelectPopWin.setVerticalOffset(10);
        modeSelectPopWin.setOnItemClickListener(this);
        modeSelectPopWin.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (modeSelectPopWin.isShowing()) modeSelectPopWin.dismiss();

        currentGame = gameList.get(position);
        tvGameType.setText(currentGame.getGameName());
        net.historyBettingQuery(getBetHistoryRequest());
        net.findRule(getFindRuleRequest());
    }

    private FindRuleRequest getFindRuleRequest() {
        FindRuleRequest findRuleRequest = new FindRuleRequest();
        findRuleRequest.setAccountName(UserInfo.getInstance().getName());
        findRuleRequest.setInterfaceCode(InterfaceCode.FIND_RULE);
        findRuleRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        FindRuleRequest.DataBean data = new FindRuleRequest.DataBean();
        FindRuleRequest.DataBean.RuleInfoBean ruleInfo = new FindRuleRequest.DataBean.RuleInfoBean();
        ruleInfo.setGameAlias(currentGame.getAlias());
        data.setRuleInfo(ruleInfo);
        findRuleRequest.setData(data);
        return findRuleRequest;
    }

    @Override
    public void onRefundTicketClicked(int position) {
        new AlertDialog.Builder(getContext())
                .setTitle("确定要退票吗")
                .setPositiveButton("确定", (DialogInterface dialog, int which) ->
                        net.refundTicket(getRefundTicketParams(position)))
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private RefundTicketRequest getRefundTicketParams(int position) {
        RefundTicketRequest request = new RefundTicketRequest();
        request.setInterfaceCode(InterfaceCode.REFUND_TICKET);
        request.setRequestTime(TimeUtil.get10IntTimeStamp());
        request.setAccountName(UserInfo.getInstance().getName());
        RefundTicketRequest.DataBean data = new RefundTicketRequest.DataBean();
        RefundTicketRequest.DataBean.OrderInfoBean orderInfo = new RefundTicketRequest.DataBean.OrderInfoBean();
        orderInfo.setGameAlias(currentGame.getAlias());
        BetHistoryResponse.BettingListBean bean = beanList.get(position);
        orderInfo.setOrderCode(bean.getOrderId());
        orderInfo.setDrawNumber(bean.getDrawNumber());
        data.setOrderInfo(orderInfo);
        request.setData(data);
        return request;
    }

    @Override
    public void onRefresh() {
        net.historyBettingQuery(getBetHistoryRequest());
    }
}
