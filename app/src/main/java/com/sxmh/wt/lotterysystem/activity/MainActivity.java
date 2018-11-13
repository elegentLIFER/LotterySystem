package com.sxmh.wt.lotterysystem.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sxmh.wt.lotterysystem.MyApplication;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseActivity;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.InstantMessage;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.ActivationRequest;
import com.sxmh.wt.lotterysystem.bean.request.LogoutRequest;
import com.sxmh.wt.lotterysystem.bean.request.TimeRequest;
import com.sxmh.wt.lotterysystem.bean.response.ActivationResponse;
import com.sxmh.wt.lotterysystem.bean.response.TimeResponse;
import com.sxmh.wt.lotterysystem.fragment.main.CashFragment;
import com.sxmh.wt.lotterysystem.fragment.main.CenterManageFragment;
import com.sxmh.wt.lotterysystem.fragment.main.MessageContentFragment;
import com.sxmh.wt.lotterysystem.fragment.main.MessageFragment;
import com.sxmh.wt.lotterysystem.fragment.main.NaviFragment;
import com.sxmh.wt.lotterysystem.fragment.main.ReportFragment;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.mqtt.MqttListener;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeManager;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.sxmh.wt.lotterysystem.mqtt.MqttListener.MQTT_CONNECT_FAILED;
import static com.sxmh.wt.lotterysystem.mqtt.MqttListener.MQTT_CONNECT_SUCESSFUL;
import static com.sxmh.wt.lotterysystem.mqtt.MqttListener.MQTT_GET_MESSAGE;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @InjectView(R.id.fl_fragments)
    FrameLayout flFragments;
    @InjectView(R.id.tv_sale_lottery)
    TextView tvSaleLottery;
    @InjectView(R.id.tv_cash)
    TextView tvCash;
    @InjectView(R.id.tv_message)
    TextView tvMessage;
    @InjectView(R.id.tv_report)
    TextView tvReport;
    @InjectView(R.id.tv_config)
    TextView tvConfig;
    @InjectView(R.id.tv_admin)
    TextView tvAdmin;
    @InjectView(R.id.iv_lock_screen)
    ImageView ivLockScreen;
    @InjectView(R.id.tv_lastest_meassge)
    TextView tvLastestMessage;

    private static final int STATUS_SALE_LOTTERY = 0;
    private static final int STATUS_CASH = 1;
    private static final int STATUS_REPORT = 2;
    private static final int STATUS_MESSAGE = 3;
    private static final int STATUS_CONFIG = 4;
    private static final int STATUS_OTHER = 5;

    private static final int CENTER_MANAGE = 0;
    private static final int LOGOUT = 1;

    public static final String INTENT_MESSAGE_CONTENT = "message";
    public static final String INTENT_MESSAGE_ID = "id";
    public static final String INTENT_MESSAGE_TITLE = "title";

    private int currentStatus = STATUS_SALE_LOTTERY;

    private FragmentManager fragmentManager;
    private long exitTime;
    private BaseFragment fragmentNow;

    public SaleLotteryFragment saleLotteryFragment;
    public CashFragment cashFragment;
    public ReportFragment reportFragment;
    public MessageFragment messageFragment;
    public NaviFragment naviFragment;
    public CenterManageFragment centerManageFragment;
    private MessageContentFragment messageContentFragment;

    private String mClientId;
    private String mMyTopic;
    private MqttListener mMqttListener;

    private String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.BLUETOOTH};
    private List<String> per;
    private static final int REQUEST_CODE = 0x004;

    @SuppressWarnings("handlerleak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MQTT_CONNECT_SUCESSFUL:
                    ToastUtil.newToast(MainActivity.this, "mqtt连接成功");
                    break;
                case MQTT_CONNECT_FAILED:
                    ToastUtil.newToast(MainActivity.this, "mqtt连接失败");
                    break;
                case MQTT_GET_MESSAGE:
                    ToastUtil.newToast(MainActivity.this, "收到新消息！");
                    String message = msg.obj.toString();
                    InstantMessage instantMessage = new Gson().fromJson(message, InstantMessage.class);
                    String content = instantMessage.getContent();
                    tvLastestMessage.setText(content);

                    messageFragment.onMqttMsgReceived();
                    break;
            }
        }
    };

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initFraments();

        mMyTopic = "DrawOpenTopic";
        mClientId = NUtil.getMqttClientId();
        mMqttListener = new MqttListener(handler, mClientId + "", mMyTopic);
        mMqttListener.init();
        mMqttListener.startReconnect();
        tvAdmin.setText(UserInfo.getInstance().getName());
        refreshSwitchBtStatus();

        net.timeCalibration(getTimeRquest());

        checkPermission();
        requestPermission();
    }

    private void checkPermission() {
        per = new ArrayList();
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission))
                per.add(permission);
        }
    }

    private void requestPermission() {
        if (per.size() == 0) return;
        String[] p = new String[per.size()];
        ActivityCompat.requestPermissions(this, per.toArray(p), REQUEST_CODE);
    }

    private TimeRequest getTimeRquest() {
        TimeRequest timeRequest = new TimeRequest();
        String uid = UserInfo.getInstance().getUid();
        timeRequest.setAccountId(Integer.valueOf(uid));
        timeRequest.setInterfaceCode(InterfaceCode.TIME_CALIBRATION);
        timeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        TimeRequest.DataBean dataBean = new TimeRequest.DataBean();
        timeRequest.setData(dataBean);
        return timeRequest;
    }

    private void initFraments() {
        fragmentManager = getSupportFragmentManager();
        saleLotteryFragment = new SaleLotteryFragment();
        cashFragment = new CashFragment();
        messageFragment = new MessageFragment();
        centerManageFragment = new CenterManageFragment();
        messageContentFragment = new MessageContentFragment();

        reportFragment = new ReportFragment();
        naviFragment = new NaviFragment();

        showDefaultFragment();
    }

    private void showDefaultFragment() {
        //开启一个事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //add：往碎片集合中添加一个碎片；
        //replace：移除之前所有的碎片，替换新的碎片（remove和add的集合体）(很少用，不推荐，因为是重新加载，所以消耗流量)
        //参数：1.公共父容器的的id  2.fragment的碎片
        fragmentTransaction.add(R.id.fl_fragments, saleLotteryFragment);
        fragmentTransaction.addToBackStack(null);

        //提交事务
        fragmentTransaction.commit();

        // 当前fragment初始化
        fragmentNow = saleLotteryFragment;
    }

    @OnClick({R.id.tv_sale_lottery, R.id.tv_cash, R.id.tv_message, R.id.tv_report, R.id.tv_config, R.id.tv_admin, R.id.iv_lock_screen, R.id.tv_lastest_meassge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sale_lottery:
                toNewFragment(saleLotteryFragment);
                currentStatus = STATUS_SALE_LOTTERY;
                refreshSwitchBtStatus();
                break;
            case R.id.tv_cash:
                toNewFragment(cashFragment);
                currentStatus = STATUS_CASH;
                refreshSwitchBtStatus();
                break;
            case R.id.tv_message:
                toNewFragment(messageFragment);
                currentStatus = STATUS_MESSAGE;
                refreshSwitchBtStatus();
                break;
            case R.id.tv_report:
                toNewFragment(reportFragment);
                currentStatus = STATUS_REPORT;
                refreshSwitchBtStatus();
                break;
            case R.id.tv_config:
                toNewFragment(naviFragment);
                currentStatus = STATUS_CONFIG;
                refreshSwitchBtStatus();
                break;
            case R.id.tv_admin:
                showListPopupWindow();
                break;
            case R.id.iv_lock_screen:
                lockScreen();
                break;
            case R.id.tv_lastest_meassge:
                String message = tvLastestMessage.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    Bundle args = new Bundle();
                    args.putString(INTENT_MESSAGE_CONTENT, message);
                    args.putString(INTENT_MESSAGE_ID, "");
//                    args.putString(MessageFragment.INTENT_MESSAGE_TITLE, listBean.getName());
                    messageContentFragment.setArguments(args);
                    toNewFragment(messageContentFragment);
                }
                break;
            default:
                break;
        }
    }

    private void lockScreen() {
        Intent intent = new Intent(MainActivity.this, LockScreenActivity.class);
        startActivity(intent);
    }

    public void toNewFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentNow);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fl_fragments, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentNow = fragment;
        fragmentTransaction.commit();
    }

    public void toMessageFragment() {
        toNewFragment(messageFragment);
    }

    public void toMessageContentFragment(String content, String id, String title) {
        Bundle args = new Bundle();
        args.putString(INTENT_MESSAGE_CONTENT, content);
        args.putString(INTENT_MESSAGE_ID, id);
        args.putString(INTENT_MESSAGE_TITLE, title);
        messageContentFragment.setArguments(args);
        toNewFragment(messageContentFragment);
    }

    private void refreshSwitchBtStatus() {

        switch (currentStatus) {
            case STATUS_SALE_LOTTERY:
                //
                tvSaleLottery.setSelected(true);
                tvCash.setSelected(false);
                tvConfig.setSelected(false);
                tvMessage.setSelected(false);
                tvReport.setSelected(false);
                break;
            case STATUS_CASH:
                tvSaleLottery.setSelected(false);
                //
                tvCash.setSelected(true);
                tvConfig.setSelected(false);
                tvMessage.setSelected(false);
                tvReport.setSelected(false);
                break;
            case STATUS_REPORT:
                tvSaleLottery.setSelected(false);
                tvCash.setSelected(false);
                tvConfig.setSelected(false);
                tvMessage.setSelected(false);
                //
                tvReport.setSelected(true);
                break;
            case STATUS_MESSAGE:
                tvSaleLottery.setSelected(false);
                tvCash.setSelected(false);
                tvConfig.setSelected(false);
                //
                tvMessage.setSelected(true);
                tvReport.setSelected(false);
                break;
            case STATUS_CONFIG:
                tvSaleLottery.setSelected(false);
                tvCash.setSelected(false);
                //
                tvConfig.setSelected(true);
                tvMessage.setSelected(false);
                tvReport.setSelected(false);
                break;
            case STATUS_OTHER:
                tvSaleLottery.setSelected(false);
                tvCash.setSelected(false);
                tvConfig.setSelected(false);
                tvMessage.setSelected(false);
                tvReport.setSelected(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.newToast(this, "再次点击退出程序");
        } else {
            MyApplication.getInstance().quitApp();
        }
        exitTime = System.currentTimeMillis();
    }

    private void showListPopupWindow() {
        final String[] list = {"中心管理", "退出登录"};
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        listPopupWindow.setAnchorView(tvAdmin);
        listPopupWindow.setModal(true);
        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setVerticalOffset(10);
        listPopupWindow.setContentWidth(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == CENTER_MANAGE) {
                toNewFragment(centerManageFragment);
            } else if (position == LOGOUT) {
                net.logout(getLogoutRequest());
            }
            listPopupWindow.dismiss();
        });
        listPopupWindow.show();
    }

    private LogoutRequest getLogoutRequest() {
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setInterfaceCode("");
        logoutRequest.setRequestTime(System.currentTimeMillis());
        return logoutRequest;
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
            manager.hideSoftInputFromWindow(tvReport.getWindowToken(), 0);
        }
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_LOGOUT) {
            finish();
        } else if (request == Net.REQUEST_SERVER_TIME) {
            TimeResponse response = (TimeResponse) content;
            long timeStamp = response.getTimeStamp();
            TimeManager.getInstance().initServerTime(timeStamp);

            Log.e(TAG, "服务器时间：" + NUtil.getFormatDate(timeStamp) + " <-----> 系统时间：" + NUtil.getFormatDate(System.currentTimeMillis()));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyDown: " + keyCode + "  " + event);
        return super.onKeyDown(keyCode, event);
    }
}


