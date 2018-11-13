package com.sxmh.wt.lotterysystem.fragment.main;

import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.UserDataRequest;
import com.sxmh.wt.lotterysystem.bean.response.UserDataResponse;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public class CenterManageFragment extends BaseFragment {
    @InjectView(R.id.tv_device_name)
    TextView tvDeviceName;
    @InjectView(R.id.tv_device_register_time)
    TextView tvDeviceRegisterTime;
    @InjectView(R.id.tv_device_rest_money)
    TextView tvDeviceRestMoney;
    @InjectView(R.id.tv_device_owner)
    TextView tvDeviceOwner;
    @InjectView(R.id.tv_touzhi_allow)
    TextView tvTouzhiAllow;
    @InjectView(R.id.tv_touzhi_amount)
    TextView tvTouzhiAmount;
    @InjectView(R.id.tv_user_account)
    TextView tvUserAccount;
    @InjectView(R.id.tv_system_time)
    TextView tvSystemTime;
    @InjectView(R.id.tv_user_type)
    TextView tvUserType;
    @InjectView(R.id.tv_device_num)
    TextView tvDeviceNum;

    private Timer timer;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_center_manage;
    }

    @Override
    protected void initData() {
        net.userData(getUserDataRequestParams());
        showSystemTime();
    }

    private void showSystemTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String serviceTime = NUtil.getFormatDate(TimeManager.getInstance().getServiceTime());
                getActivity().runOnUiThread(() -> tvSystemTime.setText((serviceTime)));
            }
        }, 0, 1000);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) net.userData(getUserDataRequestParams());
    }

    private UserDataRequest getUserDataRequestParams() {
        UserDataRequest userDataRequest = new UserDataRequest();
        userDataRequest.setRequestTime(TimeManager.getInstance().getServiceTime());
        userDataRequest.setInterfaceCode("");
        UserDataRequest.DataBean data = new UserDataRequest.DataBean();
        UserDataRequest.DataBean.UserInfoBean userInfo = new UserDataRequest.DataBean.UserInfoBean();
        String epid = UserInfo.getInstance().getEpid();
        Integer uid = Integer.valueOf(epid);
        userInfo.setTerminalId(uid);
        data.setUserInfo(userInfo);
        userDataRequest.setData(data);
        return userDataRequest;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_USER_DATA) {
            UserDataResponse response = (UserDataResponse) content;
            UserDataResponse.UserListBean userList = response.getUserList();
            tvDeviceName.setText(userList.getTERMINANAME());
            tvDeviceOwner.setText(userList.getUsername());
            tvDeviceRegisterTime.setText(NUtil.getFormatDate(Long.valueOf(userList.getCreated() + "000")));
            tvDeviceRestMoney.setText(userList.getBALANCE());
            String isAllow = "0".equals(userList.getOVERDRAFT()) ? "不能" : "能";
            tvTouzhiAllow.setText(isAllow);
            tvUserType.setText(userList.getRoles());
            tvTouzhiAmount.setText(userList.getUSEAMOUNT());
            tvUserAccount.setText(userList.getName());
            tvDeviceNum.setText(userList.getTERMINANUM());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }
}
