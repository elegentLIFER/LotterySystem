package com.sxmh.wt.lotterysystem.fragment.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.activity.MainActivity;
import com.sxmh.wt.lotterysystem.adapter.LvMessageListAdapter;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.MessageListRequest;
import com.sxmh.wt.lotterysystem.bean.request.MessageLookRequest;
import com.sxmh.wt.lotterysystem.bean.response.MessageListResponse;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.ReadOrNotView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class MessageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        ReadOrNotView.OnReadStatusChanged, AdapterView.OnItemClickListener {
    private static final String TAG = "MessageFragment";
    @InjectView(R.id.lv_messages)
    ListView lvMessages;
    @InjectView(R.id.bt_pre_page)
    Button btPrePage;
    @InjectView(R.id.bt_next_page)
    Button btNextPage;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.read_or_not_view)
    ReadOrNotView readOrNotView;

    public static final String NOT_READ = "1";
    public static final String HAS_READ = "0";
    private int currentPage = 1;

    private boolean hasRead = true;
    private List<MessageListResponse.NewsListBean.ListBean> beanList;
    private LvMessageListAdapter adapter;

    public MessageContentFragment messageContentFragment;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorMainRed));

        messageContentFragment = new MessageContentFragment();


        readOrNotView.setOnReadStatusChanged(this);

        beanList = new ArrayList<>();
        adapter = new LvMessageListAdapter(getContext(), beanList);

        lvMessages.setOnItemClickListener(this);
        lvMessages.setAdapter(adapter);

        net.getMessageList(getMessageRequestParams(HAS_READ));
    }

    private MessageListRequest getMessageRequestParams(String readOrNot) {
        MessageListRequest request = new MessageListRequest();
        request.setInterfaceCode("");
        request.setRequestTime(System.currentTimeMillis());
        Long uid = Long.valueOf(UserInfo.getInstance().getUid());
        request.setUserId(uid);

        MessageListRequest.DataBean dataBean = new MessageListRequest.DataBean();
        MessageListRequest.DataBean.TerminalInfoBean infoBean = new MessageListRequest.DataBean.TerminalInfoBean();
        infoBean.setTerminalId(UserInfo.getInstance().getEpid());
        infoBean.setCurrent_page(currentPage + "");
        infoBean.setStatus(readOrNot);
        infoBean.setNum("10");
        dataBean.setTerminalInfo(infoBean);
        request.setData(dataBean);
        return request;
    }

    @OnClick({R.id.bt_pre_page, R.id.bt_next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_pre_page:
                if (currentPage > 1) currentPage--;
                break;
            case R.id.bt_next_page:
                currentPage++;
                break;
        }
        swipeRefreshLayout.setRefreshing(true);
        net.getMessageList(getMessageRequestParams(hasRead ? HAS_READ : NOT_READ));
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GET_MESSAGE_LIST) {
            MessageListResponse response = (MessageListResponse) content;
            List<MessageListResponse.NewsListBean.ListBean> list = response.getNewsList().getList();

            beanList.clear();
            this.beanList.addAll(list);
            adapter.notifyDataSetChanged();
            if (beanList.size() == 0)
                ToastUtil.newToast(getContext(), "已经最后一页了");

            if (!hasRead)
                readOrNotView.setHasNewMsg(beanList.size() == 0 ? false : true);
        }

        if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        net.getMessageList(getMessageRequestParams(hasRead ? HAS_READ : NOT_READ));
    }

    @Override
    public void onReadSelected() {
        currentPage = 1;
        hasRead = true;
        swipeRefreshLayout.setRefreshing(true);
        net.getMessageList(getMessageRequestParams(HAS_READ));
    }

    @Override
    public void onNotReadSelected() {
        currentPage = 1;
        hasRead = false;
        swipeRefreshLayout.setRefreshing(true);
        net.getMessageList(getMessageRequestParams(NOT_READ));
    }

    public void onMqttMsgReceived() {
        net.getMessageList(getMessageRequestParams(hasRead ? HAS_READ : NOT_READ));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MessageListResponse.NewsListBean.ListBean listBean = beanList.get(position);
        ((MainActivity) getActivity()).toMessageContentFragment(listBean.getINFORMATIONNAME(), listBean.getId(), listBean.getName());
        if (!hasRead) net.terminalnewsLook(getMessageLookRequest(beanList.get(position).getId()));
    }

    private MessageLookRequest getMessageLookRequest(String msgId) {
        MessageLookRequest messageLookRequest = new MessageLookRequest();
        messageLookRequest.setRequestTime(System.currentTimeMillis());
        messageLookRequest.setInterfaceCode("");
        messageLookRequest.setUserId(UserInfo.getInstance().getUid());
        MessageLookRequest.DataBean data = new MessageLookRequest.DataBean();
        MessageLookRequest.DataBean.TerminalnewsInfoBean terminalnewsInfo = new MessageLookRequest.DataBean.TerminalnewsInfoBean();
        terminalnewsInfo.setId(msgId);
        data.setTerminalnewsInfo(terminalnewsInfo);
        messageLookRequest.setData(data);
        return messageLookRequest;
    }

    @Override
    public void onResume() {
        super.onResume();
        net.getMessageList(getMessageRequestParams(hasRead ? HAS_READ : NOT_READ));
    }
}
