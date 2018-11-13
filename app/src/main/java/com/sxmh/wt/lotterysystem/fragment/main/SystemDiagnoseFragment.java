package com.sxmh.wt.lotterysystem.fragment.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.activity.LoginActivity;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.RestMoneyRequest;
import com.sxmh.wt.lotterysystem.bean.request.TroubleUploadRequest;
import com.sxmh.wt.lotterysystem.bean.response.RestMoneyResponse;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.util.VersionUtil;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class SystemDiagnoseFragment extends BaseFragment {
    private static final String TAG = "SystemDiagnoseFragment";
    @InjectView(R.id.bt_stop_diagnose)
    Button btStopDiagnose;
    @InjectView(R.id.bt_send_log)
    Button btSendLog;
    @InjectView(R.id.bt_start_diagnose)
    Button btStartDiagnose;
    @InjectView(R.id.tv_check_print)
    TextView tvCheckPrint;
    @InjectView(R.id.tv_check_network)
    TextView tvCheckNetwork;
    @InjectView(R.id.tv_check_version)
    TextView tvCheckVersion;
    @InjectView(R.id.tv_check_rest_money)
    TextView tvCheckRestMoney;
    @InjectView(R.id.iv_checking_print)
    ImageView ivCheckingPrint;
    @InjectView(R.id.ll_check_print)
    LinearLayout llCheckPrint;
    @InjectView(R.id.iv_checking_net)
    ImageView ivCheckingNet;
    @InjectView(R.id.ll_check_net)
    LinearLayout llCheckNet;
    @InjectView(R.id.iv_checking_version)
    ImageView ivCheckingVersion;
    @InjectView(R.id.ll_check_version)
    LinearLayout llCheckVersion;
    @InjectView(R.id.iv_checking_rest)
    ImageView ivCheckingRest;
    @InjectView(R.id.ll_check_rest_money)
    LinearLayout llCheckRestMoney;
    @InjectView(R.id.tv_log)
    TextView tvLog;

    private StringBuilder errorLogBuilder;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_system_diagnose;
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.bt_stop_diagnose, R.id.bt_send_log, R.id.bt_start_diagnose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_stop_diagnose:
                break;
            case R.id.bt_send_log:
                net.uploadTrouble(getTroubleUploadParams());
                break;
            case R.id.bt_start_diagnose:
                errorLogBuilder = new StringBuilder();
                systemDiagnose();
                break;
            default:
                break;
        }
    }

    private void systemDiagnose() {
        diagnoseReset();
        checkPrinter();
        checkNetwork();
        checkVersion();
        checkRestMoney();
    }

    private void checkVersion() {
        llCheckVersion.setVisibility(View.VISIBLE);
        String versionName = VersionUtil.getVersionName();
        if (versionName != null && !versionName.equals("")) {
            tvCheckVersion.setText(versionName);
            ivCheckingVersion.setImageResource(R.drawable.tongguo);
        } else {
            tvCheckVersion.setText("版本信息获取失败");
            errorLogBuilder.append("版本信息获取失败\n");
            ivCheckingVersion.setImageResource(R.drawable.tishi);
        }
    }

    private void checkNetwork() {
        llCheckNet.setVisibility(View.VISIBLE);
        boolean networkConnected = isNetworkConnected(getActivity());
        if (networkConnected) {
            tvCheckNetwork.setText(getString(R.string.network_connect_correct));
            ivCheckingNet.setImageResource(R.drawable.tongguo);
        } else {
            tvCheckNetwork.setText("网络连接异常");
            errorLogBuilder.append("版本信息获取失败\n");
            ivCheckingNet.setImageResource(R.drawable.tishi);
        }
    }

    private void checkPrinter() {
        llCheckPrint.setVisibility(View.VISIBLE);
        boolean printerConnected = true;
        if (printerConnected) {
            tvCheckPrint.setText(getString(R.string.printer_connect_correct));
            ivCheckingPrint.setImageResource(R.drawable.tongguo);
        } else {
            tvCheckPrint.setText("打印机连接异常");
            errorLogBuilder.append("版本信息获取失败\n");
            ivCheckingPrint.setImageResource(R.drawable.tishi);
        }
    }

    private void diagnoseReset() {
        llCheckRestMoney.setVisibility(View.INVISIBLE);
        llCheckVersion.setVisibility(View.INVISIBLE);
        llCheckNet.setVisibility(View.INVISIBLE);
        llCheckPrint.setVisibility(View.INVISIBLE);
        tvCheckRestMoney.setText("");
        tvCheckVersion.setText("");
        tvCheckPrint.setText(getString(R.string.check_printer_connect));
        tvCheckNetwork.setText(getString(R.string.check_net_connect));
        ivCheckingRest.setImageResource(R.drawable.zzjc);
        ivCheckingVersion.setImageResource(R.drawable.zzjc);
        ivCheckingNet.setImageResource(R.drawable.zzjc);
        ivCheckingPrint.setImageResource(R.drawable.zzjc);
    }

    private void checkRestMoney() {
        llCheckRestMoney.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(10);
        // 加载动画
        ivCheckingRest.startAnimation(animation);

        net.getRestMoney(getRestMoneyRequestParams());
    }

    private RestMoneyRequest getRestMoneyRequestParams() {
        RestMoneyRequest restMoneyRequest = new RestMoneyRequest();
        restMoneyRequest.setInterfaceCode("");
        restMoneyRequest.setRequestTime(System.currentTimeMillis());
        Long uid = Long.valueOf(UserInfo.getInstance().getUid());
        restMoneyRequest.setUserId(uid);

        RestMoneyRequest.DataBean dataBean = new RestMoneyRequest.DataBean();
        RestMoneyRequest.DataBean.TerminalInfoBean infoBean = new RestMoneyRequest.DataBean.TerminalInfoBean();
        infoBean.setTerminalId(UserInfo.getInstance().getEpid());
        dataBean.setTerminalInfo(infoBean);
        restMoneyRequest.setData(dataBean);
        return restMoneyRequest;
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GET_REST_MONEY) {
            RestMoneyResponse response = (RestMoneyResponse) content;
            tvCheckRestMoney.setText(response.getMessage());
            ivCheckingRest.clearAnimation();
            ivCheckingRest.setImageResource(R.drawable.tongguo);

            errorLogBuilder.append(response.getMessage() + "\n");
            tvLog.setText(errorLogBuilder.toString());
        }
    }

    private TroubleUploadRequest getTroubleUploadParams() {
        TroubleUploadRequest request = new TroubleUploadRequest();
        request.setInterfaceCode("");
        request.setRequestTime(System.currentTimeMillis());
        request.setUserId(UserInfo.getInstance().getUid());
        TroubleUploadRequest.DataBean dataBean = new TroubleUploadRequest.DataBean();
        TroubleUploadRequest.DataBean.customerserviceListBean listBean = new TroubleUploadRequest.DataBean.customerserviceListBean();
        listBean.setType("1");
        listBean.setContent(tvLog.getText().toString());
        listBean.setTitle(tvLog.getText().toString());
        listBean.setFaultType("1");
        listBean.setTerminaNum(getTerminalNumber());

        listBean.setReservationMaintenance("");
        dataBean.setCustomerserviceList(listBean);
        request.setData(dataBean);
        return request;
    }

    private String getTerminalNumber() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(LoginActivity.TERMINAL_NUMBER, "");
    }
}
