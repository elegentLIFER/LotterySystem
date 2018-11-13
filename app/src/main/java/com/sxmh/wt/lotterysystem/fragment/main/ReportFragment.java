package com.sxmh.wt.lotterysystem.fragment.main;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.PrinterNoticeRequest;
import com.sxmh.wt.lotterysystem.bean.request.ReportRequest;
import com.sxmh.wt.lotterysystem.bean.response.ReportResponse;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.PrinterService;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.MyRadioButton;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class ReportFragment extends BaseFragment {
    @InjectView(R.id.rb_report_class)
    MyRadioButton rbReportClass;
    @InjectView(R.id.rb_report_day)
    MyRadioButton rbReportDay;
    @InjectView(R.id.rb_report_week)
    MyRadioButton rbReportWeek;
    @InjectView(R.id.rb_report_month)
    MyRadioButton rbReportMonth;
    @InjectView(R.id.bt_print)
    Button btPrint;
    @InjectView(R.id.tv_report_type)
    TextView tvReportType;
    @InjectView(R.id.tv_operator)
    TextView tvOperator;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_sale_bet_num)
    TextView tvSaleBetNum;
    @InjectView(R.id.tv_sale_money)
    TextView tvSaleMoney;
    @InjectView(R.id.tv_cash_bet_num)
    TextView tvCashBetNum;
    @InjectView(R.id.tv_cash_money)
    TextView tvCashMoney;
    @InjectView(R.id.tv_sum_money)
    TextView tvSumMoney;
    @InjectView(R.id.ll_report)
    LinearLayout llReport;
    @InjectView(R.id.image)
    ImageView image;

    private static final String TAG = "NaviFragment";
    private static final int REPORT_CLASS = 0;
    private static final int REPORT_DAY = 1;
    private static final int REPORT_WEEK = 2;
    private static final int REPORT_MONTH = 3;

    private int reportType = REPORT_CLASS;

    private PrinterService.PrinterBinder printerBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            printerBinder = (PrinterService.PrinterBinder) service;
            printerBinder.setConnectByBlueTooth(false);
            printerBinder.connect();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            printerBinder = null;
        }
    };

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected void initData() {
        net.getReport(getReportRequest());

        Intent intent = new Intent(getContext(), PrinterService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ReportRequest getReportRequest() {
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setAccountName(UserInfo.getInstance().getName());
        reportRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        reportRequest.setInterfaceCode(InterfaceCode.REPORT_QUERY);
        ReportRequest.DataBean dataBean = new ReportRequest.DataBean();
        ReportRequest.DataBean.ReportInfoBean infoBean = new ReportRequest.DataBean.ReportInfoBean();

        switch (reportType) {
            case REPORT_CLASS:
                infoBean.setStartTime(TimeUtil.getTodayStart() + "");
                infoBean.setEndTime(TimeUtil.getTodayEnd() + "");
                break;
            case REPORT_DAY:
                infoBean.setStartTime(TimeUtil.getTodayStart() + "");
                infoBean.setEndTime(TimeUtil.getTodayEnd() + "");
                break;
            case REPORT_WEEK:
                infoBean.setStartTime(TimeUtil.getTodayStart() + "");
                infoBean.setEndTime(TimeUtil.getTodayEnd() + "");
                break;
            case REPORT_MONTH:
                infoBean.setStartTime(TimeUtil.getMonthStart() + "");
                infoBean.setEndTime(TimeUtil.getTodayEnd() + "");
                break;
        }
        dataBean.setReportInfo(infoBean);
        reportRequest.setData(dataBean);
        return reportRequest;
    }

    @OnClick({R.id.rb_report_class, R.id.rb_report_day, R.id.rb_report_week, R.id.rb_report_month, R.id.bt_print})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_report_class:
                reportType = REPORT_CLASS;
                refreshState();
                net.getReport(getReportRequest());
                break;
            case R.id.rb_report_day:
                reportType = REPORT_DAY;
                refreshState();
                net.getReport(getReportRequest());
                break;
            case R.id.rb_report_week:
                reportType = REPORT_WEEK;
                refreshState();
                net.getReport(getReportRequest());
                break;
            case R.id.rb_report_month:
                reportType = REPORT_MONTH;
                refreshState();
                net.getReport(getReportRequest());
                break;
            case R.id.bt_print:
//                net.printerNotice(getPrinterNoticeRequest());
                if (printerBinder.isDeviceConnected()) {
                    printerBinder.print(print().getCommand());
                    return;
                }
                new AlertDialog.Builder(getContext())
                        .setTitle("连接打印机？")
                        .setPositiveButton("连接", (dialog, which) -> printerBinder.connect())
                        .setNegativeButton("取消", null).create().show();
                break;
            default:
                break;
        }
    }

    public EscCommand print() {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);

//        Bitmap viewBitmap = NUtil.createViewBitmap(llReport);
//        esc.addRastBitImage(viewBitmap, 550, 0);
//        image.setImageBitmap(viewBitmap);
        esc.addText(tvReportType.getText().toString() + "\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addPrintAndLineFeed();

        esc.addSetAbsolutePrintPosition((short) 10);
        esc.addText("操作员：             " + tvOperator.getText().toString() + "\n");
        esc.addText("日期：               " + tvDate.getText().toString() + "\n");
        esc.addText("时间：               " + tvTime.getText().toString() + "\n");
        esc.addText("---------------------------------\n");
        esc.addText("科目       注数       金额\n");
        esc.addText("---------------------------------\n");
        esc.addText("销售        " + tvSaleBetNum.getText().toString() + "        " + tvSaleMoney.getText().toString() + "\n");
        esc.addText("兑奖        " + tvCashBetNum.getText().toString() + "        " + tvCashMoney.getText().toString() + "\n");
        esc.addText("---------------------------------\n");
        esc.addText("总计                  " + tvSumMoney.getText().toString() + "\n");

        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        esc.addPrintAndFeedLines((byte) 6);
        esc.addCutPaper();
        esc.addQueryPrinterStatus();
        return esc;
    }

    private PrinterNoticeRequest getPrinterNoticeRequest() {
        PrinterNoticeRequest request = new PrinterNoticeRequest();
        request.setAccountId(UserInfo.getInstance().getUid());
        request.setSign("6D9B00C2F6CD4EB0921527432A632031");
        request.setInterfaceCode(InterfaceCode.PRINTER_NOTICE);
        request.setRequestTime(TimeUtil.get10IntTimeStamp());
        PrinterNoticeRequest.DataBean.InitPrinterNoticePayReqBean reqBean = new PrinterNoticeRequest.DataBean.InitPrinterNoticePayReqBean();
        reqBean.setOrderCode("20180419152500485928");
        PrinterNoticeRequest.DataBean dataBean = new PrinterNoticeRequest.DataBean();
        dataBean.setInitPrinterNoticePayReq(reqBean);
        request.setData(dataBean);
        return request;
    }

    /**
     * 切换不同表类型后更新状态和数据
     */
    private void refreshState() {
        switch (reportType) {
            case REPORT_CLASS:
                rbReportClass.setPressed(true);
                rbReportDay.setPressed(false);
                rbReportWeek.setPressed(false);
                rbReportMonth.setPressed(false);
                break;
            case REPORT_DAY:
                rbReportClass.setPressed(false);
                rbReportDay.setPressed(true);
                rbReportWeek.setPressed(false);
                rbReportMonth.setPressed(false);
                break;
            case REPORT_WEEK:
                rbReportClass.setPressed(false);
                rbReportDay.setPressed(false);
                rbReportWeek.setPressed(true);
                rbReportMonth.setPressed(false);
                break;
            case REPORT_MONTH:
                rbReportClass.setPressed(false);
                rbReportDay.setPressed(false);
                rbReportWeek.setPressed(false);
                rbReportMonth.setPressed(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_GET_REPORT) {
            ReportResponse response = (ReportResponse) content;
            ToastUtil.newToast(getContext(), "报表获取成功");
            // 表类型
            switch (reportType) {
                case REPORT_CLASS:
                    tvReportType.setText(getString(R.string.report_class));
                    break;
                case REPORT_DAY:
                    tvReportType.setText(getString(R.string.report_day));
                    break;
                case REPORT_WEEK:
                    tvReportType.setText(getString(R.string.report_week));
                    break;
                case REPORT_MONTH:
                    tvReportType.setText(getString(R.string.report_month));
                    break;
            }

            tvOperator.setText(UserInfo.getInstance().getRole());
            tvDate.setText(TimeUtil.getDate());
            tvTime.setText(TimeUtil.getTime());
            List<ReportResponse.ReportListBean> reportList = response.getReportList();
            ReportResponse.ReportListBean saleInfo = reportList.get(0);
            tvSaleBetNum.setText(saleInfo.getBetNum() + "");
            tvSaleMoney.setText(saleInfo.getMoney() + "");
            ReportResponse.ReportListBean cashInfo = reportList.get(1);
            tvCashBetNum.setText(cashInfo.getBetNum() + "");
            tvCashMoney.setText(cashInfo.getMoney() + "");
            tvSumMoney.setText(saleInfo.getMoney() + cashInfo.getMoney() + "");
        }
    }

}
