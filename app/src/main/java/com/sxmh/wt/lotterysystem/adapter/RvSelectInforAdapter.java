package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.response.CashInfoResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/11 0011.
 */

public class RvSelectInforAdapter extends RecyclerView.Adapter<RvSelectInforAdapter.ViewHolder> {

    private Context context;
    private List<CashInfoResponse.CashPrizeListBean> list;

    public RvSelectInforAdapter(Context context, List<CashInfoResponse.CashPrizeListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_select_info, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CashInfoResponse.CashPrizeListBean cashPrizeListBean = list.get(position);

        holder.tvSerial.setText((position+1)+".");
//        holder.tvBallType.setText(list.get(1));
        holder.tvNumber1.setText(cashPrizeListBean.getBetNum().toString());
        holder.tvNumber2.setText(cashPrizeListBean.getWinMoney()+"莫币");
//        holder.tvNumber3.setText(list.get(4));
//        holder.tvNumber4.setText(list.get(5));
//        holder.tvNumber5.setText(list.get(6));
//        holder.tvNumber6.setText(list.get(7));
//        holder.tvSelectType.setText(list.get(8));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_serial)
        TextView tvSerial;
        @InjectView(R.id.tv_ball_type)
        TextView tvBallType;
        @InjectView(R.id.tv_number_1)
        TextView tvNumber1;
        @InjectView(R.id.tv_number_2)
        TextView tvNumber2;
        @InjectView(R.id.tv_number_3)
        TextView tvNumber3;
        @InjectView(R.id.tv_number_4)
        TextView tvNumber4;
        @InjectView(R.id.tv_number_5)
        TextView tvNumber5;
        @InjectView(R.id.tv_number_6)
        TextView tvNumber6;
        @InjectView(R.id.tv_select_type)
        TextView tvSelectType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
