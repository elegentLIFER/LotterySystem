package com.sxmh.wt.lotterysystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.PriceSeletView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Wang Tao on 2018/4/11 0011.
 */
public class RvSelectNumberRectAdapter extends RecyclerView.Adapter<RvSelectNumberRectAdapter.ViewHolder> {
    private static final String TAG = "RvSelectNumberRoundAdapter";
    private Context context;
    private List<CircleBtStatus> circleBtStatusList;
    private int maxCanSelect;
    private boolean addStar;

    public RvSelectNumberRectAdapter(Context context, List<CircleBtStatus> circleBtStatusList, int maxCanSelect) {
        this.context = context;
        this.circleBtStatusList = circleBtStatusList;
        this.maxCanSelect = maxCanSelect;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item_select_number_hezhi, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PriceSeletView priceSelectView = holder.priceSelectView;
        final CircleBtStatus circleBtStatus = circleBtStatusList.get(position);

        String back = addStar ? "*" : "";
        priceSelectView.setText(circleBtStatus.getNum() + back);
        priceSelectView.setCbPressed(circleBtStatus.getIsPressed());
        priceSelectView.setBaseColor(circleBtStatus.getColor());

        priceSelectView.setCbOnTouchEvent(() -> {
            boolean isPressed = circleBtStatus.getIsPressed();
            boolean hasNotBeyond = getPressedAmount() < maxCanSelect;
            if (hasNotBeyond) {
                circleBtStatus.setIsPressed(!isPressed);
                priceSelectView.setCbPressed(!isPressed);
                return;
            }
            if (isPressed) {
                circleBtStatus.setIsPressed(false);
                priceSelectView.setCbPressed(false);
            } else {
                ToastUtil.newToast(context, context.getString(R.string.note_not_qualify));
            }
        });
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
        @InjectView(R.id.price_select_view)
        PriceSeletView priceSelectView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public List<CircleBtStatus> getList() {
        return circleBtStatusList;
    }

    public void setAddStar(boolean addStar) {
        this.addStar = addStar;
    }
}
