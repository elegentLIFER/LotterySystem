package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.CircleButton;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/11 0011.
 */
public class RvSelectNumberRoundAdapter extends RecyclerView.Adapter<RvSelectNumberRoundAdapter.ViewHolder> {
    private static final String TAG = "RvSelectNumber";
    private Context context;
    private List<CircleBtStatus> circleBtStatusList;
    private int maxCanSelect;

    public RvSelectNumberRoundAdapter(Context context, List<CircleBtStatus> circleBtStatusList, int maxCanSelect) {
        this.context = context;
        this.circleBtStatusList = circleBtStatusList;
        this.maxCanSelect = maxCanSelect;
    }

    @Override
    public RvSelectNumberRoundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_select_number_round, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CircleButton cbSelectItem = holder.cbSelectItem;
        final CircleBtStatus circleBtStatus = circleBtStatusList.get(position);
        cbSelectItem.setText(NUtil.intToLotteryPattern(circleBtStatus.getNum()));
        cbSelectItem.setCbPressed(circleBtStatus.getIsPressed());
        cbSelectItem.setBaseColor(circleBtStatus.getColor());
        cbSelectItem.setCbOnTouchEvent(() -> {
            boolean isPressed = circleBtStatus.getIsPressed();
            boolean hasNotBeyond = getPressedAmount() < maxCanSelect;
            if (hasNotBeyond) {
                circleBtStatus.setIsPressed(!isPressed);
                cbSelectItem.setCbPressed(!isPressed);
                return;
            }
            if (isPressed) {
                circleBtStatus.setIsPressed(false);
                cbSelectItem.setCbPressed(false);
            } else {
                if (getPressedAmount() == 1) {
                    resetList();
                    circleBtStatus.setIsPressed(!isPressed);
                    cbSelectItem.setCbPressed(!isPressed);
                } else {
                    ToastUtil.newToast(context, context.getString(R.string.note_not_qualify));
                }
            }
        });
    }

    private void resetList() {
        for (CircleBtStatus status :
                circleBtStatusList) {
            if (status.getIsPressed()) {
                status.setIsPressed(false);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        return circleBtStatusList.size();
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_select_item)
        CircleButton cbSelectItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public List<CircleBtStatus> getList() {
        return circleBtStatusList;
    }

    public void setMaxCanSelect(int maxCanSelect) {
        this.maxCanSelect = maxCanSelect;
    }
}
