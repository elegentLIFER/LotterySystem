package com.sxmh.wt.lotterysystem.fragment.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.request.ChangePswRequest;
import com.sxmh.wt.lotterysystem.bean.response.ChangePswResponse;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public class ChangePswFragment extends BaseFragment {
    @InjectView(R.id.et_pre_psw)
    EditText etPrePsw;
    @InjectView(R.id.et_new_psw)
    EditText etNewPsw;
    @InjectView(R.id.et_confirm_psw)
    EditText etConfirmPsw;
    @InjectView(R.id.bt_change_psw)
    Button btChangePsw;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_change_psw;
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.bt_change_psw)
    public void onViewClicked() {
        showSureChangePswDialog();
    }

    public void showSureChangePswDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("确定修改密码?")
                .setPositiveButton("确定", (dialog, which) -> net.changePsw(getChangePswRequestParams()))
                .setNegativeButton("取消", null).create();
        alertDialog.show();
    }

    private ChangePswRequest getChangePswRequestParams() {
        ChangePswRequest changePswRequest = new ChangePswRequest();
        changePswRequest.setInterfaceCode("");
        changePswRequest.setRequestTime(System.currentTimeMillis());
        ChangePswRequest.DataBean dataBean = new ChangePswRequest.DataBean();
        ChangePswRequest.DataBean.UserInfoBean infoBean = new ChangePswRequest.DataBean.UserInfoBean();
        infoBean.setOldPass(etPrePsw.getText().toString());
        infoBean.setNewPass(etNewPsw.getText().toString());
        dataBean.setUserInfo(infoBean);
        changePswRequest.setData(dataBean);
        return changePswRequest;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_CHANGE_PSW) {
            getActivity().finish();
        }
    }
}
