package com.sxmh.wt.lotterysystem.fragment.main;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.activity.MainActivity;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.MessageRequest;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class MessageContentFragment extends BaseFragment {
    private static final String TAG = "MessageFragment";
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.bt_pre_page)
    Button btPrePage;

    private String msgId;
    private String title;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_msg_content;
    }

    @Override
    protected void initData() {
        refreshPage();
        webview.setBackgroundColor(0);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) refreshPage();
    }

    private void refreshPage() {
        net.getMessage(getMessageRequestParams());
        Bundle arguments = getArguments();
        msgId = arguments.getString(MainActivity.INTENT_MESSAGE_ID);
        title = arguments.getString(MainActivity.INTENT_MESSAGE_TITLE);

        tvTitle.setText(title);
        webview.loadDataWithBaseURL(null, arguments.getString(MainActivity.INTENT_MESSAGE_CONTENT), "text/html", "UTF-8", null);
    }

    private MessageRequest getMessageRequestParams() {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setInterfaceCode("");
        messageRequest.setRequestTime(System.currentTimeMillis());
        messageRequest.setUserId(UserInfo.getInstance().getUid());
        MessageRequest.DataBean dataBean = new MessageRequest.DataBean();
        MessageRequest.DataBean.TerminalnewsInfoBean infoBean = new MessageRequest.DataBean.TerminalnewsInfoBean();
        infoBean.setId(msgId);
        dataBean.setTerminalnewsInfo(infoBean);
        messageRequest.setData(dataBean);
        return messageRequest;
    }

    @Override
    public void updateContent(int request, Object content) {
    }

    @OnClick(R.id.bt_pre_page)
    public void onViewClicked() {
        ((MainActivity) getActivity()).toMessageFragment();
    }
}
