package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.response.MessageListResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/10 0010.
 */

public class LvMessageListAdapter extends BaseAdapter {
    private static final String TAG = "LvMessageListAdapter";
    private Context context;
    private List<MessageListResponse.NewsListBean.ListBean> messageList;

    public LvMessageListAdapter(Context context, List<MessageListResponse.NewsListBean.ListBean> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_message, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        MessageListResponse.NewsListBean.ListBean bean = messageList.get(position);
        viewHolder.tvMessageContent.setText(bean.getName());

        int priority = bean.getPriority();
        int resId;
        if (priority == 1) resId = R.drawable.rank1;
        else if (priority == 2) resId = R.drawable.rank2;
        else if (priority == 3) resId = R.drawable.rank3;
        else resId = android.R.color.transparent;
        viewHolder.ivPriority.setImageResource(resId);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_message_content)
        TextView tvMessageContent;
        @InjectView(R.id.iv_priority)
        ImageView ivPriority;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}