package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NumSelectViewNew extends LinearLayout {
    @InjectView(R.id.tv_sub)
    TextView tvSub;
    @InjectView(R.id.et_num)
    EditText etNum;
    @InjectView(R.id.tv_add)
    TextView tvAdd;

    private int max;
    private OnNumSelectListener selectListener;

    public NumSelectViewNew(Context context) {
        super(context);
        initWork();
    }

    public NumSelectViewNew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public NumSelectViewNew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_num_select, this);
        ButterKnife.inject(this, this);
        setNum(1);
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getNum() < 1) setNum(1);
                if (getNum() > max) setNum(max);

                selectListener.OnNumSelect();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_sub, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sub:
                if (getNum() > 1) setNum(getNum() - 1);
                break;
            case R.id.tv_add:
                if (getNum() < max) setNum(getNum() + 1);
                break;
        }
    }

    public int getNum() {
        String numString = etNum.getText().toString();
        return TextUtils.isEmpty(numString) ? 1 : Integer.valueOf(numString);
    }

    public void setNum(int num) {
        etNum.setText(num + "");
        if (selectListener!=null) selectListener.OnNumSelect();
    }

    public interface OnNumSelectListener {
        void OnNumSelect();
    }

    public void setSelectListener(OnNumSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
