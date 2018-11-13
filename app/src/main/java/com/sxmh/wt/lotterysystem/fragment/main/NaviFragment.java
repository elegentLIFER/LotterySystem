package com.sxmh.wt.lotterysystem.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.activity.MainActivity;
import com.sxmh.wt.lotterysystem.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public class NaviFragment extends BaseFragment {
    @InjectView(R.id.tv_system_diagnose)
    TextView tvSystemDiagnose;
    @InjectView(R.id.tv_psw_change)
    TextView tvPswChange;
    @InjectView(R.id.tv_account_add)
    TextView tvAccountAdd;
    @InjectView(R.id.tv_system_version)
    TextView tvSystemVersion;
    @InjectView(R.id.tv_error_fix)
    TextView tvErrorFix;
    @InjectView(R.id.tv_buy_history)
    TextView tvBuyHistory;

    private SystemDiagnoseFragment systemDiagnoseFragment;
    private ChangePswFragment changePswFragment;
    private AccountRechargeFragment accountAddFragment;
    private SystemVersionFragment systemVersionFragment;
    private TroubleUploadFragment errorFixFragment;
    private BuyHistoryFragment buyHistoryFragment;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_navi;
    }

    @Override
    protected void initData() {
        systemDiagnoseFragment = new SystemDiagnoseFragment();
        accountAddFragment = new AccountRechargeFragment();
        changePswFragment = new ChangePswFragment();
        systemVersionFragment = new SystemVersionFragment();
        errorFixFragment = new TroubleUploadFragment();
        buyHistoryFragment = new BuyHistoryFragment();
    }

    @OnClick({R.id.tv_system_diagnose, R.id.tv_psw_change, R.id.tv_account_add, R.id.tv_system_version, R.id.tv_error_fix, R.id.tv_buy_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_system_diagnose:
                ((MainActivity) getActivity()).toNewFragment(systemDiagnoseFragment);
                break;
            case R.id.tv_psw_change:
                ((MainActivity) getActivity()).toNewFragment(changePswFragment);
                break;
            case R.id.tv_account_add:
                ((MainActivity) getActivity()).toNewFragment(accountAddFragment);
                break;
            case R.id.tv_system_version:
                ((MainActivity) getActivity()).toNewFragment(systemVersionFragment);
                break;
            case R.id.tv_error_fix:
                ((MainActivity) getActivity()).toNewFragment(errorFixFragment);
                break;
            case R.id.tv_buy_history:
                ((MainActivity) getActivity()).toNewFragment(buyHistoryFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateContent(int request, Object content) {

    }
}
