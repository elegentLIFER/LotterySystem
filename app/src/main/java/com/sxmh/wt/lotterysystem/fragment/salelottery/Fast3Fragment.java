package com.sxmh.wt.lotterysystem.fragment.salelottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.ClearSelectedListCallback;
import com.sxmh.wt.lotterysystem.bean.NotOpenResponseCallBack;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.OnCommitSuccessListener;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.FixedTypeRequest;
import com.sxmh.wt.lotterysystem.bean.response.FixedTypeResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.BaseSelectFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.Fast3ChildBaseFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.ErBuTongFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.ErtongDoubleFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.ErtongSingleFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.HezhiFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.SanBuTongFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.SanLianAllFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.SanTongAllFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.fast3.SanTongFragment;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.view.RestTimeView;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class Fast3Fragment extends BaseSelectFragment implements AdapterView.OnItemClickListener, OnCommitSuccessListener {
    @InjectView(R.id.tv_fixed_type)
    TextView tvFixedType;
    @InjectView(R.id.rest_time_view)
    RestTimeView restTimeView;

    public static final String GAME_LIST_BEAN = "game_list_bean";
    public static final String GAME_ADD_LIST_BEAN = "game_add_list_bean";
    public static final String GAME_RULE = "game_rule";

    public static final int INDEX_HEZHI = 0;
    public static final int INDEX_ER_TONG_SINGLE = 1;
    public static final int INDEX_ER_TONG_DOUBLE = 2;
    public static final int INDEX_ER_BU_TONG = 3;
    public static final int INDEX_SAN_TONG = 4;
    public static final int INDEX_SAN_TONG_ALL = 5;
    public static final int INDEX_SAN_BU_TONG = 6;
    public static final int INDEX_SAN_LIAN_ALL = 7;

    private List<FixedTypeResponse.GameAddListBean> gameAddList;
    private ListPopupWindow modeSelectPopWin;

    private Strategy strategy;
    private FragmentManager fragmentManager;

    private List<Fast3ChildBaseFragment> childFragmentList;
    private ClearSelectedListCallback clearSelectedListCallback;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_fast_3;
    }

    @Override
    protected void initData() {
        gameAddList = new ArrayList<>();
        net.selectGameAdd(getFixedTypeRequestParams());

        initFragment();
    }

    @Override
    public void initBox() {
    }

    private FixedTypeRequest getFixedTypeRequestParams() {
        FixedTypeRequest fixedTypeRequest = new FixedTypeRequest();
        fixedTypeRequest.setAccountName(UserInfo.getInstance().getName());
        fixedTypeRequest.setInterfaceCode(InterfaceCode.SELECT_GAME_ADD);
        fixedTypeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());

        FixedTypeRequest.DataBean.GameInfoBean infoBean = new FixedTypeRequest.DataBean.GameInfoBean();
        FixedTypeRequest.DataBean dataBean = new FixedTypeRequest.DataBean();
        dataBean.setGameInfo(infoBean);
        infoBean.setGameAlias(gameListBean.getAlias());
        fixedTypeRequest.setData(dataBean);
        return fixedTypeRequest;
    }

    private void showFixedTypeDialog() {
        modeSelectPopWin = new ListPopupWindow(getContext());
        int size = gameAddList.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = gameAddList.get(i).getGamePlayName();
        }
        ArrayAdapter<String> fixedGameTypeListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strings);
        modeSelectPopWin.setAdapter(fixedGameTypeListAdapter);
        modeSelectPopWin.setAnchorView(tvFixedType);
        modeSelectPopWin.setDropDownGravity(Gravity.BOTTOM);
        modeSelectPopWin.setWidth(tvFixedType.getWidth());
        modeSelectPopWin.setHeight(ListPopupWindow.WRAP_CONTENT);
        modeSelectPopWin.setModal(true);
        modeSelectPopWin.setVerticalOffset(10);
        modeSelectPopWin.setOnItemClickListener(this);
        modeSelectPopWin.show();
    }

    private void initFragment() {
        fragmentManager = getChildFragmentManager();
        Bundle args = new Bundle();
        args.putSerializable(GAME_LIST_BEAN, gameListBean);

        childFragmentList = new ArrayList<>();
        childFragmentList.add(new HezhiFragment());
        childFragmentList.add(new ErtongSingleFragment());
        childFragmentList.add(new ErtongDoubleFragment());
        childFragmentList.add(new ErBuTongFragment());
        childFragmentList.add(new SanTongFragment());
        childFragmentList.add(new SanTongAllFragment());
        childFragmentList.add(new SanBuTongFragment());
        childFragmentList.add(new SanLianAllFragment());

        int size = childFragmentList.size();
        for (int i = 0; i < size; i++) {
            Fast3ChildBaseFragment childBaseFragment = childFragmentList.get(i);
            childBaseFragment.setOnCommitSuccessListener(this);
            childBaseFragment.setArguments(args);
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fast3ChildBaseFragment childBaseFragment :
                childFragmentList) {
            transaction.hide(childBaseFragment);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_fragments, fragment);
        }
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public void updateContent(int request, Object content) {
        super.updateContent(request, content);
        if (request == Net.REQUEST_FIXED_TYPE) {
            FixedTypeResponse response = (FixedTypeResponse) content;
            gameAddList.clear();
            gameAddList.addAll(response.getGameAddList());
            if (gameAddList.size() > 0) toSpecStatus(0);
        }
    }

    @Override
    protected void refreshRestTime(String drawNumber, String restTime) {
        restTimeView.setDraw(drawNumber);
        restTimeView.setEndTime(restTime);

        for (NotOpenResponseCallBack callBack : childFragmentList) {
            callBack.notifyResponse(notOpenQueryResponse);
        }
    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        return strategy.selectOneByMachine();
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        return strategy.insertToSelectedArea();
    }

    @Override
    public void commitLottery(LinearLayout llAllBallInfo, Bundle params) {
        strategy.commitLottery(llAllBallInfo, params);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        modeSelectPopWin.dismiss();
        toSpecStatus(position);
    }

    private void toSpecFragment(int index, Bundle bundle) {
        Fast3ChildBaseFragment fragment = childFragmentList.get(index);
        fragment.setArguments(bundle);
        showFragment(fragment);
        strategy = fragment;
    }

    private void toSpecStatus(int position) {
        FixedTypeResponse.GameAddListBean bean = gameAddList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_ADD_LIST_BEAN, bean);
        bundle.putSerializable(GAME_LIST_BEAN, gameListBean);
        bundle.putSerializable(GAME_RULE, gameRule);

        int index = 0;
        String gamePlayNum = bean.getGamePlayNum();
        if ("k3001".equals(gamePlayNum)) {
            index = INDEX_HEZHI;
        } else if ("k3002".equals(gamePlayNum)) {
            index = INDEX_SAN_TONG;
        } else if ("k3003".equals(gamePlayNum)) {
            index = INDEX_ER_TONG_SINGLE;
        } else if ("k3004".equals(gamePlayNum)) {
            index = INDEX_SAN_BU_TONG;
        } else if ("k3005".equals(gamePlayNum)) {
            index = INDEX_ER_BU_TONG;
        } else if ("k3006".equals(gamePlayNum)) {
            index = INDEX_SAN_LIAN_ALL;
        } else if ("k3007".equals(gamePlayNum)) {
            index = INDEX_SAN_TONG_ALL;
        } else if ("k3411".equals(gamePlayNum)) {
            index = INDEX_ER_TONG_DOUBLE;
        } else if ("k3001".equals(gamePlayNum)) {
            index = INDEX_HEZHI;
        }
        toSpecFragment(index, bundle);
        tvFixedType.setText(bean.getGamePlayName());
        clearSelectedListCallback.toClear();
    }

    @OnClick(R.id.tv_fixed_type)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fixed_type:
                showFixedTypeDialog();
                break;
        }
    }

    @Override
    public EscCommand print() {
        return strategy.print();
    }

    @Override
    public void OnCommitSuccess() {
        onCommitSuccessListener.OnCommitSuccess();
    }

    public void setClearSelectedListCallback(ClearSelectedListCallback clearSelectedListCallback) {
        this.clearSelectedListCallback = clearSelectedListCallback;
    }

    @Override
    public void resetWaitArea() {
        strategy.resetWaitArea();
    }
}
