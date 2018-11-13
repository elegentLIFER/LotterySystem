package com.sxmh.wt.lotterysystem.fragment.main;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.ClearSelectedListCallback;
import com.sxmh.wt.lotterysystem.bean.GameRule;
import com.sxmh.wt.lotterysystem.bean.GameType;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.OnCommitSuccessListener;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.FindRuleRequest;
import com.sxmh.wt.lotterysystem.bean.request.GameListQueryRequest;
import com.sxmh.wt.lotterysystem.bean.response.FindRuleResponse;
import com.sxmh.wt.lotterysystem.bean.response.GameListQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.salelottery.Arrange5Fragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.DoubleBallFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.Fast3Fragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.Happy8Fragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery._36$7Fragment;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.PrinterService;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.MyRadioButton;
import com.sxmh.wt.lotterysystem.view.NumSelectViewNew;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */
public class SaleLotteryFragment extends BaseFragment implements SelectedBallInfo.OnDeleteClickedListener,
        RadioGroup.OnCheckedChangeListener, OnCommitSuccessListener, ClearSelectedListCallback, Happy8Fragment.FeiPanCallBack {
    private static final String TAG = "SaleLotteryFragment";
    @InjectView(R.id.ll_all_ball_info)
    LinearLayout llAllBallInfo;
    @InjectView(R.id.nsv_bet_double)
    NumSelectViewNew nsvBetDouble;
    @InjectView(R.id.nsv_multi)
    NumSelectViewNew nsvMulti;
    @InjectView(R.id.tv_note_amount)
    TextView tvNoteAmount;
    @InjectView(R.id.tv_bet_double)
    TextView tvBetDouble;
    @InjectView(R.id.tv_money_num)
    TextView tvMoneyNum;
    @InjectView(R.id.rg_game_type)
    RadioGroup rgGameType;
    @InjectView(R.id.scrollview_selected)
    ScrollView scrollviewSelected;
    @InjectView(R.id.tv_multiple)
    TextView tvMultiple;

    public static final String MULTI_DRAW = "multi_draw";
    public static final String BET_DOUBLE = "bet_double";
    public static final String BET_NUM = "bet_num";
    public static final String TOTAL_MONEY = "total_money";
    public static final String GAME_LIST_BEAN = "game_list_bean";
    public static final String GAME_RULE = "game_rule";

    private List<GameListQueryResponse.GameListBean> gameList;
    private Strategy strategy;
    private int totalMoney;

    private FragmentManager fragmentManager;
    private DoubleBallFragment doubleBallFragment;
    private Fast3Fragment fast3Fragment;
    private Happy8Fragment happy8Fragment;
    private Arrange5Fragment arrange5Fragment;
    private _36$7Fragment _36$7Fragment;
    private PrinterService.PrinterBinder printerBinder;

    private GameListQueryResponse.GameListBean currentGameListBean;
    private GameRule gameRule;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            printerBinder = (PrinterService.PrinterBinder) service;
            printerBinder.setConnectByBlueTooth(false);
            printerBinder.connect();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            printerBinder = null;
            Log.e(TAG, "dd: ");
        }
    };

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_sale_lottery;
    }

    @Override
    protected void initData() {
        net.getGameList(getQueryGameListRequest());
        refreshSumNum();

        fragmentManager = getChildFragmentManager();
        initFragment();
        nsvMulti.setSelectListener(() -> refreshSumNum());
        nsvBetDouble.setSelectListener(() -> refreshSumNum());

        Intent intent = new Intent(getContext(), PrinterService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        gameRule = new GameRule();
    }

    private FindRuleRequest getFindRuleRequestParams() {
        FindRuleRequest findRuleRequest = new FindRuleRequest();
        findRuleRequest.setAccountName(UserInfo.getInstance().getName());
        findRuleRequest.setInterfaceCode(InterfaceCode.FIND_RULE);
        findRuleRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        FindRuleRequest.DataBean data = new FindRuleRequest.DataBean();
        FindRuleRequest.DataBean.RuleInfoBean ruleInfo = new FindRuleRequest.DataBean.RuleInfoBean();
        ruleInfo.setGameAlias(currentGameListBean.getAlias());
        data.setRuleInfo(ruleInfo);
        findRuleRequest.setData(data);
        return findRuleRequest;
    }

    private void initFragment() {
        doubleBallFragment = new DoubleBallFragment();
        doubleBallFragment.setOnCommitSuccessListener(this);
        fast3Fragment = new Fast3Fragment();
        fast3Fragment.setClearSelectedListCallback(this);
        fast3Fragment.setOnCommitSuccessListener(this);
        happy8Fragment = new Happy8Fragment();
        happy8Fragment.setClearSelectedListCallback(this);
        happy8Fragment.setOnCommitSuccessListener(this);
        happy8Fragment.setFeiPanCallBack(this);
        arrange5Fragment = new Arrange5Fragment();
        arrange5Fragment.setOnCommitSuccessListener(this);
        _36$7Fragment = new _36$7Fragment();
        _36$7Fragment.setOnCommitSuccessListener(this);
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(doubleBallFragment);
        transaction.hide(fast3Fragment);
        transaction.hide(happy8Fragment);
        transaction.hide(arrange5Fragment);
        transaction.hide(_36$7Fragment);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_fragments, fragment);
        }
        transaction.show(fragment);
        transaction.commit();
    }

    private void refreshCurrentGame(int index) {
        currentGameListBean = gameList.get(index);
        net.findRule(getFindRuleRequestParams());

        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_LIST_BEAN, currentGameListBean);
        bundle.putSerializable(GAME_RULE, gameRule);
        switch (currentGameListBean.getId()) {
            case GameType.TYPE_ID_ARRANGE_5:
                arrange5Fragment.setArguments(bundle);
                showFragment(arrange5Fragment);
                strategy = arrange5Fragment;
                break;
            case GameType.TYPE_ID_HAPPY_8:
                happy8Fragment.setArguments(bundle);
                showFragment(happy8Fragment);
                strategy = happy8Fragment;
                break;
            case GameType.TYPE_ID_36$7:
                _36$7Fragment.setArguments(bundle);
                showFragment(_36$7Fragment);
                strategy = _36$7Fragment;
                break;
            case GameType.TYPE_ID_FAST_3:
                fast3Fragment.setArguments(bundle);
                showFragment(fast3Fragment);
                strategy = fast3Fragment;
                break;
            case GameType.TYPE_ID_DOUBLE_BALL:
                doubleBallFragment.setArguments(bundle);
                showFragment(doubleBallFragment);
                strategy = doubleBallFragment;
                break;
        }
        clearSelectedList();
    }

    private void refreshSumNum() {
        tvBetDouble.setText(nsvBetDouble.getNum() + "");
        int childCount = llAllBallInfo.getChildCount();
        tvMultiple.setText(nsvMulti.getNum() + "");

        int noteNum = 0;
        int total = 0;
        for (int i = 0; i < childCount; i++) {
            SelectedBallInfo childAt = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
            noteNum += childAt.getBetNum();
            total += childAt.getPerCost();
        }

        tvNoteAmount.setText(noteNum + "");
        totalMoney = nsvMulti.getNum() * nsvBetDouble.getNum() * total;
        tvMoneyNum.setText(totalMoney + "");
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

    private void showGameList() {
        int size = gameList.size();
        for (int i = 0; i < size; i++) {
            final MyRadioButton radioButton = new MyRadioButton(getContext());
            if (i == 0) {
                radioButton.setChecked(true);
            }
            radioButton.setId(i);
            radioButton.setText(gameList.get(i).getGameName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            radioButton.setLayoutParams(params);

            if (i == size - 1) {
                radioButton.setVisibility(View.GONE);
            }
            rgGameType.addView(radioButton);
        }
        rgGameType.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.bt_select_one_machine, R.id.bt_select_five_machine, R.id.bt_clear_list, R.id.bt_print, R.id.bt_sure_select, R.id.bt_bet_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_select_one_machine:
                selectOneByMachine();
                break;
            case R.id.bt_select_five_machine:
                selectFiveByMachine();
                refreshSumNum();
                break;
            case R.id.bt_clear_list:
                clearSelectedList();
                break;
            case R.id.bt_print:
                commit();
                break;
            case R.id.bt_sure_select:
                sureSelect();
                break;
            default:
                break;
        }
    }

    private void commit() {
        if (llAllBallInfo.getChildCount() == 0) {
            ToastUtil.newToast(getContext(), "您还没有选择号码");
            return;
        }
        if (printerBinder.isDeviceConnected()) {
            new AlertDialog.Builder(getContext())
                    .setTitle("确定此投注?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        String noteAmountString = tvNoteAmount.getText().toString();
                        Integer noteAmount = Integer.valueOf(noteAmountString);
                        int maxZhu = gameRule.getR001();
                        if (maxZhu != 0 && noteAmount > maxZhu) {
                            ToastUtil.newToast(getContext(), "一次最多投" + maxZhu + "注");
                            return;
                        }

                        int maxMoney = gameRule.getR005();
                        String moneyString = tvMoneyNum.getText().toString();
                        Integer money = Integer.valueOf(moneyString);
                        if (maxMoney != 0 && money > maxMoney) {
                            ToastUtil.newToast(getContext(), "超过了最多投注金额" + maxMoney);
                            return;
                        }

                        Bundle params = new Bundle();
                        params.putString(MULTI_DRAW, nsvMulti.getNum() + "");
                        params.putString(BET_DOUBLE, nsvBetDouble.getNum() + "");
                        params.putString(TOTAL_MONEY, moneyString);
                        params.putString(BET_NUM, noteAmountString);
                        strategy.commitLottery(llAllBallInfo, params);
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show();
            return;
        }
        new AlertDialog.Builder(getContext())
                .setTitle("连接打印机？")
                .setPositiveButton("连接", (dialog, which) -> printerBinder.connect())
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void sureSelect() {
        List<SelectedBallInfo> ballInfoList = strategy.insertToSelectedArea();
        if (ballInfoList == null) return;
        int size = ballInfoList.size();
        if (size == 0) return;
        for (int i = 0; i < size; i++) {
            SelectedBallInfo selectedBallInfo = ballInfoList.get(i);
            selectedBallInfo.setDeleteClickedListener(this);
            llAllBallInfo.addView(selectedBallInfo);
        }
        refreshSumNum();
        scrollviewSelected.fullScroll(View.FOCUS_DOWN);
    }

    private void selectOneByMachine() {
        SelectedBallInfo selectOneByMachine = strategy.selectOneByMachine();
        if (selectOneByMachine != null) {
            selectOneByMachine.setDeleteClickedListener(this);
            llAllBallInfo.addView(selectOneByMachine);
            refreshSumNum();
        }
        scrollviewSelected.post(() -> scrollviewSelected.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private void selectFiveByMachine() {
        for (int i = 0; i < 5; i++) {
            selectOneByMachine();
        }
    }

    private void clearSelectedList() {
        if (llAllBallInfo.getChildCount() != 0) {
            llAllBallInfo.removeAllViews();
        }
        refreshSumNum();
    }

    @Override
    public void OnDeleteClicked(SelectedBallInfo info) {
        if (info != null) {
            llAllBallInfo.removeView(info);
        }
        refreshSumNum();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        refreshCurrentGame(checkedId);
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GAME_LIST) {
            GameListQueryResponse response = (GameListQueryResponse) content;
            gameList = response.getGameList();
            if (gameList == null) return;
            showGameList();
            refreshCurrentGame(0);
        } else if (request == Net.REQUEST_FIND_RULE) {
            FindRuleResponse response = (FindRuleResponse) content;
            gameRule.setData(response.getRuleList());

            int maxBei = gameRule.getR002();
            if (maxBei != 0) nsvBetDouble.setMax(maxBei);
            int maxQi = gameRule.getR003();
            if (maxQi != 0) nsvMulti.setMax(maxQi);
        }
    }

    @Override
    public void toClear() {
        clearSelectedList();
    }

    @Override
    public void OnCommitSuccess() {
//        printerBinder.print(strategy.print().getCommand());
        clearSelectedList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unbindService(connection);
    }

    @Override
    public void onFeiPanSelected(boolean isChecked) {
        tvMoneyNum.setText("" + (isChecked ? totalMoney * 2 : totalMoney));
    }
}
