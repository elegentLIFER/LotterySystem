package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.response.BetHistoryResponse;
import com.sxmh.wt.lotterysystem.util.LotteryUtil;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.TimeManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RvBetHistoryAdapter extends RecyclerView.Adapter<RvBetHistoryAdapter.RvThisViewHolder> {
    private Context context;
    private List<BetHistoryResponse.BettingListBean> beanList;
    private OnRefundTicketClickListener onRefundTicketClickListener;
    private int refundDuration;

    public RvBetHistoryAdapter(Context context, List<BetHistoryResponse.BettingListBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public RvThisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_goucai_history, parent, false);
        return new RvThisViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RvThisViewHolder holder, int position) {
        BetHistoryResponse.BettingListBean bean = beanList.get(position);
        holder.tvBetDouble.setText(bean.getBetDouble() + "");
        String betMode = bean.getBetMode();
        holder.tvBetMode.setText(LotteryUtil.getBetModeByNum(betMode));
        holder.tvBetNum.setText(bean.getBetNum());
        long buyTime = bean.getBuyTime();
        holder.tvBuyTime.setText(NUtil.getFormatDate(buyTime));
        holder.tvDraw.setText(bean.getDrawNumber());
        holder.tvGameName.setText(bean.getGameName());
        holder.tvNoteNumber.setText(bean.getNoteNumber() + "");
        holder.tvOrderId.setText(bean.getOrderId());
        holder.tvMultiDraw.setText(bean.getMultiDraw());
        holder.tvOrderMoney.setText(bean.getOrderMoney() + "");

        long serviceTime = TimeManager.getInstance().getServiceTime();
        boolean canRefund = serviceTime - buyTime < (refundDuration * 60 * 1000);
        holder.tvRefundTicket.setVisibility(canRefund ? View.VISIBLE : View.INVISIBLE);
        holder.tvRefundTicket.setOnClickListener((view) -> onRefundTicketClickListener.onRefundTicketClicked(position));
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    protected class RvThisViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_order_id)
        TextView tvOrderId;
        @InjectView(R.id.tv_draw)
        TextView tvDraw;
        @InjectView(R.id.tv_game_name)
        TextView tvGameName;
        @InjectView(R.id.tv_buy_time)
        TextView tvBuyTime;
        @InjectView(R.id.tv_bet_num)
        TextView tvBetNum;
        @InjectView(R.id.tv_order_money)
        TextView tvOrderMoney;
        @InjectView(R.id.tv_bet_mode)
        TextView tvBetMode;
        @InjectView(R.id.tv_bet_double)
        TextView tvBetDouble;
        @InjectView(R.id.tv_multi_draw)
        TextView tvMultiDraw;
        @InjectView(R.id.tv_note_number)
        TextView tvNoteNumber;
        @InjectView(R.id.tv_refund_ticket)
        TextView tvRefundTicket;

        public RvThisViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }

    public interface OnRefundTicketClickListener {
        void onRefundTicketClicked(int position);
    }

    public void setOnRefundTicketClickListener(OnRefundTicketClickListener onRefundTicketClickListener) {
        this.onRefundTicketClickListener = onRefundTicketClickListener;
    }

    public int getRefundDuration() {
        return refundDuration;
    }

    public void setRefundDuration(int refundDuration) {
        this.refundDuration = refundDuration;
    }
}
