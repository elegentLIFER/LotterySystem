package com.sxmh.wt.lotterysystem.fragment.main;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.activity.LoginActivity;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.TroubleUploadRequest;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class TroubleUploadFragment extends BaseFragment implements CalendarView.OnDateChangeListener {
    private static final String TAG = "TroubleUploadFragment";
    @InjectView(R.id.bt_upload)
    Button btUpload;
    @InjectView(R.id.tv_trouble_type)
    TextView tvTroubleType;
    @InjectView(R.id.et_trouble_content)
    EditText etTroubleContent;
    @InjectView(R.id.et_start_time)
    EditText etStartTime;
    @InjectView(R.id.et_end_time)
    EditText etEndTime;
    @InjectView(R.id.et_trouble_title)
    EditText etTroubleTitle;
    @InjectView(R.id.tv_quick_question)
    TextView tvQuickQuestion;

    private String[] faultTypes = {"类型一", "类型二", "类型三"};
    private String[] quickQuestions = {"常见问题一", "常见问题二", "常见问题三"};
    private ListPopupWindow typeSelectPopupWindow;
    private ListPopupWindow quickQuestionPopupWindow;
    private AlertDialog timeChooseDialog;
    private boolean isEndTime;


    @Override
    protected int initLayoutId() {
        return R.layout.fragment_trouble_upload;
    }

    @Override
    protected void initData() {
        tvTroubleType.setText(faultTypes[0]);
        etStartTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                float x = event.getX();
                Drawable drawable = etStartTime.getCompoundDrawables()[2];
                Rect bounds = drawable.getBounds();
                if (x > etStartTime.getWidth() - bounds.width() * 3) {
                    showTimeSelectDialog();
                    isEndTime = false;
                }
                return true;
            }
            return false;
        });

        etEndTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                float x = event.getX();
                Drawable drawable = etEndTime.getCompoundDrawables()[2];
                Rect bounds = drawable.getBounds();
                if (x > etEndTime.getWidth() - bounds.width() * 3) {
                    showTimeSelectDialog();
                    isEndTime = true;
                }
                return true;
            }
            return false;
        });
    }

    private void showTimeSelectDialog() {
        if (timeChooseDialog == null) {
            CalendarView calendarView = new CalendarView(getContext());
            calendarView.setOnDateChangeListener(this);
            timeChooseDialog = new AlertDialog.Builder(getContext()).setTitle("选择时间").setView(calendarView).create();
        }
        timeChooseDialog.show();
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        String time = year + "-" + month + "-" + dayOfMonth;
        ToastUtil.newToast(getContext(), time);
        if (timeChooseDialog.isShowing()) timeChooseDialog.dismiss();
        if (isEndTime) {
            etEndTime.setText(time);
        } else {
            etStartTime.setText(time);
        }
    }

    private TroubleUploadRequest getTroubleUploadParams() {
        TroubleUploadRequest request = new TroubleUploadRequest();
        request.setInterfaceCode("");
        request.setRequestTime(System.currentTimeMillis());
        request.setUserId(UserInfo.getInstance().getUid());
        TroubleUploadRequest.DataBean dataBean = new TroubleUploadRequest.DataBean();
        TroubleUploadRequest.DataBean.customerserviceListBean listBean = new TroubleUploadRequest.DataBean.customerserviceListBean();
        listBean.setType("1");
        listBean.setContent(etTroubleContent.getText().toString());
        listBean.setTitle(etTroubleTitle.getText().toString());
        listBean.setFaultType(tvTroubleType.getText().toString());
        listBean.setTerminaNum(getTerminalNumber());

        String startTime = etStartTime.getText().toString();
        String endTime = etEndTime.getText().toString();
        listBean.setReservationMaintenance(startTime + "~" + endTime);
        dataBean.setCustomerserviceList(listBean);
        request.setData(dataBean);
        return request;
    }

    private String getTerminalNumber() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(LoginActivity.TERMINAL_NUMBER, "");
    }

    @OnClick({R.id.bt_upload, R.id.tv_trouble_type, R.id.tv_quick_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload:
                net.uploadTrouble(getTroubleUploadParams());
                break;
            case R.id.tv_trouble_type:
                showFaultTypeDialog();
                break;
            case R.id.tv_quick_question:
                showQuickQuestionDialog();
                break;
        }
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_UPLOAD_TROUBLE) {

        }
    }

    private void showFaultTypeDialog() {
        typeSelectPopupWindow = new ListPopupWindow(getContext());
        ArrayAdapter<String> fixedGameTypeListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, faultTypes);
        typeSelectPopupWindow.setAdapter(fixedGameTypeListAdapter);
        typeSelectPopupWindow.setAnchorView(tvTroubleType);
        typeSelectPopupWindow.setDropDownGravity(Gravity.BOTTOM);
        typeSelectPopupWindow.setWidth(tvTroubleType.getWidth());
        typeSelectPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        typeSelectPopupWindow.setModal(true);
        typeSelectPopupWindow.setVerticalOffset(10);
        typeSelectPopupWindow.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            if (typeSelectPopupWindow.isShowing()) typeSelectPopupWindow.dismiss();
            tvTroubleType.setText(faultTypes[position]);
        });
        typeSelectPopupWindow.show();
    }

    private void showQuickQuestionDialog() {
        quickQuestionPopupWindow = new ListPopupWindow(getContext());
        ArrayAdapter<String> fixedGameTypeListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, quickQuestions);
        quickQuestionPopupWindow.setAdapter(fixedGameTypeListAdapter);
        quickQuestionPopupWindow.setAnchorView(tvQuickQuestion);
        quickQuestionPopupWindow.setDropDownGravity(Gravity.BOTTOM);
        quickQuestionPopupWindow.setWidth(tvQuickQuestion.getWidth());
        quickQuestionPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        quickQuestionPopupWindow.setModal(true);
        quickQuestionPopupWindow.setVerticalOffset(10);
        quickQuestionPopupWindow.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            if (quickQuestionPopupWindow.isShowing()) quickQuestionPopupWindow.dismiss();
            tvTroubleType.setText(faultTypes[position]);
            etTroubleTitle.setText(quickQuestions[position]);
            etTroubleContent.setText(quickQuestions[position]);
        });
        quickQuestionPopupWindow.show();
    }
}
