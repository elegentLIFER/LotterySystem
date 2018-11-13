package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.CircleButton;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RvArrage5ItemAdapter extends RecyclerView.Adapter<RvArrage5ItemAdapter.RvThisViewHolder> {
    private List<CircleBtStatus> circleBtStatusList;
    private Context context;

    public RvArrage5ItemAdapter(Context context, List<CircleBtStatus> circleBtStatusList) {
        this.context = context;
        this.circleBtStatusList = circleBtStatusList;
    }

    @Override
    public RvThisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_ball, parent, false);
        RvThisViewHolder viewHolder = new RvThisViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RvThisViewHolder holder, int position) {
        CircleButton cbItem = holder.cbItem;
        CircleBtStatus circleBtStatus = circleBtStatusList.get(position);
        cbItem.setText(circleBtStatus.getNum()+"");
        cbItem.setCbPressed(circleBtStatus.getIsPressed());
        cbItem.setBaseColor(circleBtStatus.getColor());

        cbItem.setCbOnTouchEvent(() -> {
            boolean isPressed = circleBtStatus.getIsPressed();
            boolean hasNotBeyond = getPressedAmount() < 10;
            if (hasNotBeyond) {
                circleBtStatus.setIsPressed(!isPressed);
                cbItem.setCbPressed(!isPressed);
                return;
            }
            if (isPressed) {
                circleBtStatus.setIsPressed(false);
                cbItem.setCbPressed(false);
            } else {
                ToastUtil.newToast(context, context.getString(R.string.note_not_qualify));
            }
        });
    }

    public int getPressedAmount() {
        int size = circleBtStatusList.size();
        int pressedCount = 0;
        for (int i = 0; i < size; i++) {
            if (circleBtStatusList.get(i).getIsPressed()) {
                pressedCount++;
            }
        }
        return pressedCount;
    }

    @Override
    public int getItemCount() {
        return circleBtStatusList.size();
    }

    class RvThisViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_item)
        CircleButton cbItem;

        public RvThisViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
