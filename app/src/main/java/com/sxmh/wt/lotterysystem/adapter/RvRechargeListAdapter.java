package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.response.AccountRechargeInfoResponse;
import com.sxmh.wt.lotterysystem.util.NUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/11 0011.
 */

public class RvRechargeListAdapter extends RecyclerView.Adapter<RvRechargeListAdapter.ViewHolder> {

    private Context context;
    private List<AccountRechargeInfoResponse.PaymentListBean> list;

    public RvRechargeListAdapter(Context context, List<AccountRechargeInfoResponse.PaymentListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_recharge, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AccountRechargeInfoResponse.PaymentListBean paymentListBean = list.get(position);
        holder.tvSerialNumber.setText(paymentListBean.getPaymentId());
//        holder.tvOprateType.setText(paymentListBean.getDepositType());
        holder.tvSumMoney.setText(String.valueOf(paymentListBean.getDepositMoney()));

        // 资金渠道
        String type = String.valueOf(paymentListBean.getDepositType());
        if ("00".equals(type)) {
            holder.tvMoneyChannel.setText("支付宝");
        } else if ("01".equals(type)) {
            holder.tvMoneyChannel.setText("微信");
        }

        // 充值时间
        long createTime = paymentListBean.getCreateTime();
        holder.tvTime.setText(NUtil.getFormatDate(createTime));

        // 审核状态
        String status = String.valueOf(paymentListBean.getPaymentState());
        if ("00".equals(status)) {
            holder.tvStatus.setText("未审核");
        } else {
            holder.tvStatus.setText("已审核");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_serial_number)
        TextView tvSerialNumber;
        @InjectView(R.id.tv_oprate_type)
        TextView tvOprateType;
        @InjectView(R.id.tv_sum_money)
        TextView tvSumMoney;
        @InjectView(R.id.tv_money_channel)
        TextView tvMoneyChannel;
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
