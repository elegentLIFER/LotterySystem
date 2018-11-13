package com.sxmh.wt.lotterysystem.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvArrage5ItemAdapter;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Arrange5BallSelectView extends FrameLayout {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rv_content)
    RecyclerView rvContent;

    private List<CircleBtStatus> circleBtStatusList;
    private RvArrage5ItemAdapter myAdapter;

    public Arrange5BallSelectView(@NonNull Context context) {
        super(context);
        initWork();
    }

    public Arrange5BallSelectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    public Arrange5BallSelectView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    private void initWork() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_arrange5_ball_select, this, true);
        ButterKnife.inject(this, this);

        circleBtStatusList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CircleBtStatus circleBtStatus = new CircleBtStatus();
            circleBtStatus.setColor(Color.RED);
            circleBtStatus.setNum(i);
            circleBtStatus.setIsPressed(false);
            circleBtStatusList.add(circleBtStatus);
        }
        myAdapter = new RvArrage5ItemAdapter(getContext(),circleBtStatusList);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvContent.setAdapter(myAdapter);
    }

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    public List<Integer> getSelectedNumList() {
//        int size = circleBtStatusList.size();
//        int[] last = new int[size];
//        int m = 0;
//        for (int i = 0; i < size; i++) {
//            if (circleBtStatusList.get(i).getIsPressed()) {
//                last[m] = circleBtStatusList.get(i).getNum();
//                m++;
//            }
//        }
//        int[] last2 = new int[m];
//        System.arraycopy(last, 0, last2, 0, m);
        ArrayList<Integer> list = new ArrayList<>();
        int size = circleBtStatusList.size();
        for (int i = 0; i < size; i++) {
            CircleBtStatus circleBtStatus = circleBtStatusList.get(i);
            if (circleBtStatus.getIsPressed()) {
                list.add(circleBtStatus.getNum());
            }
        }
        return list;
    }

    public void reset() {
        int size = circleBtStatusList.size();
        for (int i = 0; i < size; i++) {
            circleBtStatusList.get(i).setIsPressed(false);
        }
        myAdapter.notifyDataSetChanged();
    }
}
