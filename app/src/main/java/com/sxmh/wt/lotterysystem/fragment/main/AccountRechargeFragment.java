package com.sxmh.wt.lotterysystem.fragment.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvRechargeListAdapter;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.AccountRechargeInfoRequest;
import com.sxmh.wt.lotterysystem.bean.response.AccountRechargeInfoResponse;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */
public class AccountRechargeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "AccountRechargeFragment";
    @InjectView(R.id.rv_account_add_list)
    RecyclerView rvAccountAddList;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int currentPage;
    private RvRechargeListAdapter addListAdapter;
    private List<AccountRechargeInfoResponse.PaymentListBean> paymentList;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_account_add;
    }

    @Override
    protected void initData() {
        paymentList = new ArrayList<>();
        addListAdapter = new RvRechargeListAdapter(getContext(), paymentList);
        rvAccountAddList.setAdapter(addListAdapter);
        rvAccountAddList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvAccountAddList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvAccountAddList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != RecyclerView.SCROLL_STATE_IDLE) return;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                if (lastPosition == layoutManager.getItemCount() - 1) {
                    // 滑动到底了
                    currentPage++;
                    net.getChargeInfo(getRechargeInfoRequest());
                }
            }
        });

        net.getChargeInfo(getRechargeInfoRequest());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private AccountRechargeInfoRequest getRechargeInfoRequest() {
        AccountRechargeInfoRequest request = new AccountRechargeInfoRequest();
        request.setRequestTime(TimeUtil.get10IntTimeStamp());
        request.setAccountId(UserInfo.getInstance().getUid());
        request.setInterfaceCode(InterfaceCode.QUERY_LIST_RECHARGE);
        AccountRechargeInfoRequest.DataBean dataBean = new AccountRechargeInfoRequest.DataBean();
        AccountRechargeInfoRequest.DataBean.ListRechargeBean listRechargeBean = new AccountRechargeInfoRequest.DataBean.ListRechargeBean();
        listRechargeBean.setDepositNum("");
        listRechargeBean.setDepositType("");
        listRechargeBean.setPageNo(currentPage + "");
        listRechargeBean.setPaymentId("");
        listRechargeBean.setPhone("");
        listRechargeBean.setUserId(UserInfo.getInstance().getUid());
        dataBean.setListRecharge(listRechargeBean);
        request.setData(dataBean);
        return request;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GET_CHARGE_INFO) {
            AccountRechargeInfoResponse response = (AccountRechargeInfoResponse) content;

            paymentList.clear();
            paymentList.addAll(response.getPaymentList());
            addListAdapter.notifyDataSetChanged();
            if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        currentPage = 0;
        net.getChargeInfo(getRechargeInfoRequest());
    }
}
