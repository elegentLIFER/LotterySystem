package com.sxmh.wt.lotterysystem.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.LvWinListAdapter;
import com.sxmh.wt.lotterysystem.adapter.RvSelectInforAdapter;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.CashQueryRequest;
import com.sxmh.wt.lotterysystem.bean.request.GameListQueryRequest;
import com.sxmh.wt.lotterysystem.bean.response.CashInfoResponse;
import com.sxmh.wt.lotterysystem.bean.response.GameListQueryResponse;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.view.Ticket;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class CashFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @InjectView(R.id.rv_select_info)
    RecyclerView rvSelectInfo;
    @InjectView(R.id.bt_search)
    Button btSearch;
    @InjectView(R.id.bt_cash)
    Button btCash;
    @InjectView(R.id.ll_cash_chart)
    LinearLayout llCashChart;
    @InjectView(R.id.ll_cash_result)
    LinearLayout llCashResult;
    @InjectView(R.id.tv_sum_win_left)
    TextView tvSumWinLeft;
    @InjectView(R.id.tv_sum_win_right)
    TextView tvSumWinRight;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.tv_bar_code)
    TextView tvQrCode;
    @InjectView(R.id.ticket)
    Ticket ticket;
    @InjectView(R.id.tv_game_type)
    TextView tvGameType;
    @InjectView(R.id.iv_clear_input)
    ImageView ivClearInput;
    @InjectView(R.id.lv_win_list)
    ListView lvWinList;

    private RvSelectInforAdapter rvSelectInforAdapter;
    private LvWinListAdapter lvWinListAdapter;
    private List<CashInfoResponse.CashPrizeListBean> cashPrizeList;
    private List<CashInfoResponse.WinListBean> winListBeanList;
    private List<GameListQueryResponse.GameListBean> gameList;
    private ListPopupWindow modeSelectPopWin;

    private GameListQueryResponse.GameListBean currentGameType;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_cash;
    }

    @Override
    protected void initData() {
        gameList = new ArrayList<>();
        net.getGameList(getQueryGameListRequest());

        cashPrizeList = new ArrayList<>();
        rvSelectInforAdapter = new RvSelectInforAdapter(getContext(), cashPrizeList);
        rvSelectInfo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSelectInfo.setAdapter(rvSelectInforAdapter);

        winListBeanList = new ArrayList<>();
        lvWinListAdapter = new LvWinListAdapter(getContext(), winListBeanList);
        lvWinList.setAdapter(lvWinListAdapter);

        InputFilter filter = (CharSequence source, int start, int end, Spanned dest, int dstart, int dend) -> {
            if (source.equals(" ") || source.toString().contentEquals("\n")) {
                return "";
            } else {
                return null;
            }
        };
        etSearch.setFilters(new InputFilter[]{filter});
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

    @OnClick({R.id.bt_search, R.id.bt_cash, R.id.tv_game_type, R.id.iv_clear_input})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                llCashChart.setVisibility(View.GONE);
                llCashResult.setVisibility(View.GONE);
                net.cashInfoQuery(getCashQueryRequest());
                break;
            case R.id.bt_cash:
                break;
            case R.id.tv_game_type:
                showFixedTypeDialog();
                break;
            case R.id.iv_clear_input:
                etSearch.setText("");
                break;
            default:
                break;
        }
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

    private CashQueryRequest getCashQueryRequest() {
        CashQueryRequest cashRequest = new CashQueryRequest();
        cashRequest.setAccountName(UserInfo.getInstance().getName());
        cashRequest.setInterfaceCode(InterfaceCode.CASH_PRIZE_QUERY);
        cashRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        CashQueryRequest.DataBean.OrderInfoBean infoBean = new CashQueryRequest.DataBean.OrderInfoBean();
        infoBean.setOrderCode(etSearch.getText().toString());
        infoBean.setGameAlias(currentGameType.getAlias());
        CashQueryRequest.DataBean dataBean = new CashQueryRequest.DataBean();
        dataBean.setOrderInfo(infoBean);
        cashRequest.setData(dataBean);
        return cashRequest;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_CASH) {
            llCashChart.setVisibility(View.VISIBLE);
        } else if (request == Net.REQUEST_CASH_INFO_QUERY) {
            CashInfoResponse response = (CashInfoResponse) content;
            cashPrizeList.clear();
            cashPrizeList.addAll(response.getCashPrizeList());
            if (cashPrizeList != null && cashPrizeList.size() > 0) {
                llCashChart.setVisibility(View.VISIBLE);
                llCashResult.setVisibility(View.VISIBLE);

                CashInfoResponse.CashPrizeListBean cashPrizeListBean = cashPrizeList.get(0);

                ticket.setTitle(cashPrizeListBean.getGameName());
                String buyTime = NUtil.getFormatDate(cashPrizeListBean.getBuyTime());
                ticket.setBuyTime(buyTime);
                ticket.setDraw(cashPrizeListBean.getDrawNumber());
                String prizeTime = NUtil.getFormatDate(cashPrizeListBean.getPrizeTime());
                ticket.setOpenTime(prizeTime);
                String winAmount = cashPrizeListBean.getWinAmount();
                ticket.setSumWinLeft(cashPrizeListBean.getRealpayMoney() + " 莫币");
                ticket.setTerminalNum(cashPrizeListBean.getTerminalNum());
                ticket.setSafeCode(cashPrizeListBean.getSafetyCode());
                tvSumWinRight.setText(winAmount);
                rvSelectInforAdapter.notifyDataSetChanged();

                winListBeanList.clear();
                winListBeanList.addAll(response.getWinList());
                lvWinListAdapter.notifyDataSetChanged();
            }
        } else if (request == Net.REQUEST_GAME_LIST) {
            GameListQueryResponse response = (GameListQueryResponse) content;
            gameList.clear();
            gameList.addAll(response.getGameList());
            tvGameType.setText(gameList.get(0).getGameName());
            if (gameList.size() > 0) currentGameType = gameList.get(0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (modeSelectPopWin.isShowing()) modeSelectPopWin.dismiss();
        currentGameType = gameList.get(position);
        tvGameType.setText(currentGameType.getGameName());
    }
}
