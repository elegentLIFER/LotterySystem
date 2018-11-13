package com.sxmh.wt.lotterysystem.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.sxmh.wt.lotterysystem.IView;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public abstract class BaseFragment extends Fragment implements IView{
    private AlertDialog progressDialog ;
    protected Net net;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(initLayoutId(), container, false);
        ButterKnife.inject(this, inflate);

        net = new Net(this);
        initData();
        return inflate;
    }

    protected abstract int initLayoutId();

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showToast(String toast) {
        ToastUtil.newToast(getContext(), toast);
    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new AlertDialog.Builder(getContext())
                    .setView(LayoutInflater.from(getContext()).inflate(R.layout.loading, null))
                    .create();
            Window window = progressDialog.getWindow();
            window.setDimAmount(0);
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void cancelLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
