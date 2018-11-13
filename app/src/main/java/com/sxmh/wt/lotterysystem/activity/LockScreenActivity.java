package com.sxmh.wt.lotterysystem.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseActivity;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/16 0016.
 */

public class LockScreenActivity extends BaseActivity {
    @InjectView(R.id.et_lock_username)
    EditText etLockUsername;
    @InjectView(R.id.et_lock_psw)
    EditText etLockPsw;
    @InjectView(R.id.bt_unlock)
    Button btUnlock;
    @InjectView(R.id.bt_lock)
    Button btLock;

    private boolean hasLocked;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_lock_screen;
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (hasLocked) {
            ToastUtil.newToast(LockScreenActivity.this, "请输入用户名密码进入");
            return false;
        }
        super.onKeyDown(keyCode, event);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View currentFocus = getCurrentFocus();

            if (shouldHideInput(currentFocus, ev)) {
                hideInput();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean shouldHideInput(View currentFocus, MotionEvent ev) {
        if (currentFocus != null && currentFocus instanceof EditText) {

            float x = ev.getX();
            float y = ev.getY();

            int[] outLocation = new int[2];
            currentFocus.getLocationInWindow(outLocation);
            int lx = outLocation[0];
            int ly = outLocation[1];

            // 点击的位置在EditText范围内与否
            boolean b = x > lx && x < lx + currentFocus.getWidth() && y < ly + currentFocus.getHeight() && y > ly;
            if (b) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void hideInput() {
        InputMethodManager manager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(etLockPsw.getWindowToken(), 0);
        }
    }

    @OnClick({R.id.bt_unlock, R.id.bt_lock})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_unlock:
                String psw = getSavedLockPsw();
                // 密码正确与否
                if (psw.equals(etLockPsw.getText().toString())) {
                    LockScreenActivity.this.finish();
                } else {
                    ToastUtil.newToast(LockScreenActivity.this, "密码错误");
                }
                break;
            case R.id.bt_lock:
                String s = etLockPsw.getText().toString();
                // 如果输入不为空
                if (s!=null&&!"".equals(s)){
                    // 保存输入的密码
                    saveLockPsw(s);
                    ToastUtil.newToast(LockScreenActivity.this, "锁定成功！");
                    btLock.setVisibility(View.GONE);
                    btUnlock.setVisibility(View.VISIBLE);
                    // 密码框置空
                    etLockPsw.setText("");
                    // 已锁定
                    hasLocked = true;
                }else {
                    ToastUtil.newToast(LockScreenActivity.this, "密码不能为空！");
                }
                break;
        }
    }

    /**
     * 获得已经保存的锁屏密码
     */
    private String getSavedLockPsw() {
        SharedPreferences sp = getSharedPreferences("LockPsw", MODE_PRIVATE);
        String psw = sp.getString("lockPsw", "Null");
        return psw;
    }

    /**
     * 保存锁屏密码到文件
     *
     * @param psw
     */
    private void saveLockPsw(String psw) {
        SharedPreferences sp = getSharedPreferences("LockPsw", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("lockPsw", psw);
        edit.commit();
    }

    @Override
    public void updateContent(int request, Object content) {

    }
}
