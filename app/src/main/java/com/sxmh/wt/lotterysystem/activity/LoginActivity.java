package com.sxmh.wt.lotterysystem.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseActivity;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.ActivationRequest;
import com.sxmh.wt.lotterysystem.bean.request.LoginRequest;
import com.sxmh.wt.lotterysystem.bean.response.ActivationResponse;
import com.sxmh.wt.lotterysystem.bean.response.LoginResponse;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import java.io.DataOutputStream;
import java.io.IOException;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/17 0017.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.iv_list_users)
    ImageView ivListUsers;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.bt_login)
    Button btLogin;

    private ListPopupWindow listPopupWindow;
    public static final String TERMINAL_NUMBER = "terminal_num";
    private AlertDialog alertDialog;
    private String inputString;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        // TODO: 2018/9/13 0013 调试直接登录
//        net.login(getLoginRequestParams());
    }

    /**
     * 更新系统时间,需要root
     * @param time  时间格式为yyyyMMdd.HHmmss
     */
    public void updateSysTime(String time){
        try {
            Process process = Runtime.getRuntime().exec("su");
            //String datetime="20131023.112800"; //测试的设置的时间【时间格式 yyyyMMdd.HHmmss】  可能存在时区问题
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT\n");
            os.writeBytes("/system/bin/date -s "+time+"\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_list_users, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_list_users:
                showUserTypesPop();
                break;
            case R.id.bt_login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        net.login(getLoginRequestParams());
    }

    private void saveUserInfo(LoginResponse.UserInfoBeanX userInfo) {
        UserInfo instance = UserInfo.getInstance();
        instance.setSessionName(userInfo.getSession_name());
        instance.setSessionId(userInfo.getSessid());
        instance.setToken(userInfo.getToken());
        instance.setRole(userInfo.getRole());
        LoginResponse.UserInfoBeanX.UserBean user = userInfo.getUser();
        instance.setUid(user.getUid());
        instance.setMail(user.getMail());
        instance.setName(user.getName());
        instance.setEpid(user.getEpid());
        instance.setEpnumbei(user.getEpnumbei());
    }

    private LoginRequest getLoginRequestParams() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setInterfaceCode("");
        loginRequest.setRequestTime(System.currentTimeMillis());
        LoginRequest.DataBean.UserInfoBean userInfo = new LoginRequest.DataBean.UserInfoBean();
        userInfo.setUserName(etUsername.getText().toString());
        userInfo.setPassWord(etPassword.getText().toString());
        userInfo.setIsTerminal("1");

        userInfo.setNumBercard(getTerminalNumber());
        LoginRequest.DataBean dataBean = new LoginRequest.DataBean();
        dataBean.setUserInfo(userInfo);
        loginRequest.setData(dataBean);
        return loginRequest;
    }

    private void showUserTypesPop() {
        final String[] list = {"item1", "item2", "item3", "item4"};
        if (listPopupWindow == null) {
            listPopupWindow = new ListPopupWindow(LoginActivity.this);
            listPopupWindow.setAdapter(new ArrayAdapter<>(LoginActivity.this,
                    android.R.layout.simple_list_item_1, list));
            listPopupWindow.setAnchorView(etUsername);
            listPopupWindow.setModal(true);

            listPopupWindow.setOnItemClickListener((adapterView, view, i, l) -> {
                etUsername.setText(list[i]);
                listPopupWindow.dismiss();
            });

            listPopupWindow.setOnDismissListener(() -> {
//                    etUsername.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.pull_down1), null);
            });
        }

        listPopupWindow.show();
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
            manager.hideSoftInputFromWindow(btLogin.getWindowToken(), 0);
        }
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_LOGIN) {
            LoginResponse response = (LoginResponse) content;
            String code = response.getCode();
            LoginResponse.UserInfoBeanX userInfo = response.getUserInfo();

            if (Constants.CODE_SUCCESS.equals(code)) {
                loginSuccess();
                saveUserInfo(userInfo);
            } else {
                if (userInfo == null) return;
                String role = userInfo.getRole();
                if ("clerk".equals(role)) {
                } else if ("centralistrator".equals(role)) {
                    String terminalNumber = getTerminalNumber();
                    boolean empty = TextUtils.isEmpty(terminalNumber);

                    if (alertDialog == null) {
                        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_activite, null);
                        alertDialog = new AlertDialog.Builder(this)
                                .setView(inflate)
                                .create();
                        TextView tvTitle = inflate.findViewById(R.id.tv_title);
                        tvTitle.setText(empty ? "请输入激活码" : "请重新输入激活码");
                        Button activate = inflate.findViewById(R.id.bt_activate);
                        Button cancel = inflate.findViewById(R.id.bt_cancel);
                        EditText input = inflate.findViewById(R.id.et_input);
                        input.setText(empty ? "" : terminalNumber);
                        activate.setOnClickListener((v) -> {
                            inputString = input.getText().toString();
                            net.activationTerminal(getActivationRequest());
                        });
                        cancel.setOnClickListener((v -> alertDialog.dismiss()));
                    }
                    alertDialog.show();
                }
            }
        } else if (request == Net.REQUEST_ACTIVATION) {
            saveTerminalNumber(inputString);
            if (alertDialog.isShowing()) alertDialog.dismiss();
        }
    }

    private ActivationRequest getActivationRequest() {
        ActivationRequest activationRequest = new ActivationRequest();
        activationRequest.setInterfaceCode("");
        activationRequest.setRequestTime(System.currentTimeMillis());
        activationRequest.setUserId(UserInfo.getInstance().getUid());
        ActivationRequest.DataBean dataBean = new ActivationRequest.DataBean();
        ActivationRequest.DataBean.TerminalInfoBean terminalInfo = new ActivationRequest.DataBean.TerminalInfoBean();
        terminalInfo.setNumbercard(inputString);
        terminalInfo.setUserName(etUsername.getText().toString());
        terminalInfo.setPassWord(etPassword.getText().toString());

        dataBean.setTerminalInfo(terminalInfo);
        activationRequest.setData(dataBean);
        return activationRequest;
    }

    private void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        ToastUtil.newToast(getApplicationContext(), "登录成功");
    }

    private void saveTerminalNumber(String number) {
        SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(TERMINAL_NUMBER, number);
        edit.commit();
    }

    private String getTerminalNumber() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(LoginActivity.TERMINAL_NUMBER, "");
    }
}
