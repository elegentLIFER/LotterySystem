package com.sxmh.wt.lotterysystem.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.sxmh.wt.lotterysystem.IView;
import com.sxmh.wt.lotterysystem.MyApplication;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */
public abstract class BaseActivity extends AppCompatActivity implements IView {
    private AlertDialog progressDialog;
    protected Net net;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(initLayoutId());
        ButterKnife.inject(this);

        net = new Net(this);
        initData();
    }

    protected abstract int initLayoutId();

    protected abstract void initData();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }

    @Override
    public void showToast(String toast) {
        ToastUtil.newToast(this, toast);
    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new AlertDialog.Builder(this)
                    .setView(LayoutInflater.from(this).inflate(R.layout.loading, null))
                    .create();
            progressDialog.getWindow().setDimAmount(0);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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
