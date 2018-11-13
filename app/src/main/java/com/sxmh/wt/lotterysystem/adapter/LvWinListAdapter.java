package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.response.CashInfoResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public class LvWinListAdapter extends BaseAdapter {
    private static final String TAG = "LvMessageListAdapter";
    private Context context;
    private List<CashInfoResponse.WinListBean> winListBeanList;

    public LvWinListAdapter(Context context, List<CashInfoResponse.WinListBean> winListBeanList) {
        this.context = context;
        this.winListBeanList = winListBeanList;
    }

    @Override
    public int getCount() {
        return winListBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return winListBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_item_win_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        CashInfoResponse.WinListBean bean = winListBeanList.get(position);

        viewHolder.tvWinLevel.setText(bean.getWinLevel() + " 等奖");
        viewHolder.tvWinNum.setText(bean.getWinNum() + "");
        viewHolder.tvWinMoney.setText(bean.getWinMoney() + "");
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.tv_win_level)
        TextView tvWinLevel;
        @InjectView(R.id.tv_win_num)
        TextView tvWinNum;
        @InjectView(R.id.tv_win_money)
        TextView tvWinMoney;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
