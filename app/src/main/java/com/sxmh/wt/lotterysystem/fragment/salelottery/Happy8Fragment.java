package com.sxmh.wt.lotterysystem.fragment.salelottery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvSelectNumberRoundAdapter;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.bean.ClearSelectedListCallback;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.FindRuleRequest;
import com.sxmh.wt.lotterysystem.bean.request.FixedTypeRequest;
import com.sxmh.wt.lotterysystem.bean.request.Happy8CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Happy8NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.FixedTypeResponse;
import com.sxmh.wt.lotterysystem.bean.response.Happy8CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.BaseSelectFragment;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.LotteryUtil;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.RandomUtil;
import com.sxmh.wt.lotterysystem.util.SignUtil;
import com.sxmh.wt.lotterysystem.util.TimeManager;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.RestTimeView;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class Happy8Fragment extends BaseSelectFragment implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "Happy8Fragment";
    @InjectView(R.id.rest_time_view)
    RestTimeView restTimeView;
    @InjectView(R.id.rv_select_red)
    RecyclerView rvSelectRed;
    @InjectView(R.id.tv_fixed_type)
    TextView tvFixedType;
    @InjectView(R.id.cb_feipan)
    CheckBox cbFeipan;

    private static final int RECYCLER_ADAPTER_SPAN = 11;
    private static final int MAX_OF_BALL = 80;

    private LinearLayout llAllBallInfo;
    private List<CircleBtStatus> statusListRed;
    private RvSelectNumberRoundAdapter adapterRed;

    private ListPopupWindow modeSelectPopWin;
    private List<FixedTypeResponse.GameAddListBean> gameAddList;
    private int machineSelectNum = 1;

    private Happy8CommitResponse commitResponse;
    private FixedTypeResponse.GameAddListBean gameAddListBean;
    private FeiPanCallBack feiPanCallBack;

    private ClearSelectedListCallback clearSelectedListCallback;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_happy_8;
    }

    @Override
    protected void initData() {
        gameAddList = new ArrayList<>();
        net.selectGameAdd(getFixedTypeRequestParams());
        cbFeipan.setOnCheckedChangeListener(this);
    }

    @Override
    public void initBox() {
        statusListRed = new ArrayList<>();
        for (int i = 1; i <= MAX_OF_BALL; i++) {
            statusListRed.add(new CircleBtStatus(Color.RED, i, false));
        }
        adapterRed = new RvSelectNumberRoundAdapter(getContext(), statusListRed, 1);
        rvSelectRed.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_ADAPTER_SPAN));
        rvSelectRed.setAdapter(adapterRed);
    }

    private FixedTypeRequest getFixedTypeRequestParams() {
        FixedTypeRequest fixedTypeRequest = new FixedTypeRequest();
        fixedTypeRequest.setAccountName(UserInfo.getInstance().getName());
        fixedTypeRequest.setInterfaceCode(InterfaceCode.SELECT_GAME_ADD);
        fixedTypeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());

        FixedTypeRequest.DataBean.GameInfoBean infoBean = new FixedTypeRequest.DataBean.GameInfoBean();
        FixedTypeRequest.DataBean dataBean = new FixedTypeRequest.DataBean();
        infoBean.setGameAlias(gameListBean.getAlias());
        dataBean.setGameInfo(infoBean);
        fixedTypeRequest.setData(dataBean);
        return fixedTypeRequest;
    }

    @Override
    public void updateContent(int request, Object content) {
        super.updateContent(request, content);
        if (request == Net.REQUEST_FIXED_TYPE) {
            FixedTypeResponse response = (FixedTypeResponse) content;
            gameAddList = response.getGameAddList();
            if (gameAddList != null && gameAddList.size() > 0) {
                gameAddListBean = gameAddList.get(0);
            }
        } else if (request == Net.REQUEST_COMMIT_HAPPY8) {
            commitResponse = (Happy8CommitResponse) content;
            net.kl8Printer(getPrinterNoticeRequest());
            onCommitSuccessListener.OnCommitSuccess();
        } else if (request == Net.REQUEST_PRINTER_HAPPY8) {
            ToastUtil.newToast(getContext(), "打印通知成功");
        }
    }

    private Happy8NotifyRequest getPrinterNoticeRequest() {
        Happy8NotifyRequest noticeRequest = new Happy8NotifyRequest();
        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        noticeRequest.setAccountName(UserInfo.getInstance().getName());
        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
        Happy8NotifyRequest.DataBean data = new Happy8NotifyRequest.DataBean();
        Happy8NotifyRequest.DataBean.PrinterInfoBean printerInfo = new Happy8NotifyRequest.DataBean.PrinterInfoBean();
        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
        printerInfo.setDrawNumber(drawListBean.getDrawNumber());
        printerInfo.setGameAlias(drawListBean.getGameAlias());
        printerInfo.setOrderCode(commitResponse.getOrderCode());
        data.setPrinterInfo(printerInfo);
        data.setPrinterInfo(printerInfo);
        noticeRequest.setData(data);
        return noticeRequest;
    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        int[] randomIntRed = RandomUtil.getRandomInt(1, MAX_OF_BALL, machineSelectNum);
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();
        for (int i = 0; i < randomIntRed.length; i++) {
            sbiSelectBallInfo.addRedBall(randomIntRed[i]);
        }
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setBetNum(1);
        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<SelectedBallInfo> infoArrayList = new ArrayList<>();
        List<Integer> reds = getRedSelectNumList();
        int redNumber = reds.size();
        if (redNumber == 0) return null;
        if (redNumber > 10) {
            ToastUtil.newToast(getContext(), "不能超过10个球");
            return null;
        }
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();
        for (int i = 0; i < redNumber; i++) {
            sbiSelectBallInfo.addRedBall(reds.get(i));
        }
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetNum(1);

        resetWaitArea();
        infoArrayList.add(sbiSelectBallInfo);
        return infoArrayList;
    }

    @Override
    public void resetWaitArea() {
        for (CircleBtStatus status : statusListRed) {
            status.setIsPressed(false);
        }
        adapterRed.notifyDataSetChanged();
    }

    private List<Integer> getRedSelectNumList() {
        int sizeRed = statusListRed.size();
        List<Integer> selectedRedList = new ArrayList<>();

        for (int i = 0; i < sizeRed; i++) {
            CircleBtStatus circleBtStatus = statusListRed.get(i);
            if (circleBtStatus.getIsPressed()) {
                selectedRedList.add(circleBtStatus.getNum());
            }
        }
        return selectedRedList;
    }

    @Override
    public void commitLottery(LinearLayout llAllBallInfo, Bundle params) {
        this.llAllBallInfo = llAllBallInfo;
        net.commitHappy8(getHappy8CommitRequest(params));
    }

    private Happy8CommitRequest getHappy8CommitRequest(Bundle params) {
        Happy8CommitRequest happy8CommitRequest = new Happy8CommitRequest();
        happy8CommitRequest.setInterfaceCode(InterfaceCode.CREATE_ORDER);
        happy8CommitRequest.setAccountName(UserInfo.getInstance().getName());
        happy8CommitRequest.setRequestTime(TimeUtil.get10IntTimeStamp());

        Happy8CommitRequest.DataBean dataBean = new Happy8CommitRequest.DataBean();
        Happy8CommitRequest.DataBean.OrderInfoBean infoBean = new Happy8CommitRequest.DataBean.OrderInfoBean();

        infoBean.setGameAlias(Constants.GAME_ALIAS_KL8);

        List<Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList = getTicketList();
        infoBean.setTicketList(ticketList);
        infoBean.setTerminal(UserInfo.getInstance().getEpnumbei());
        infoBean.setMultiDraw(params.getString(SaleLotteryFragment.MULTI_DRAW));
        infoBean.setBetDouble(params.getString(SaleLotteryFragment.BET_DOUBLE));
        infoBean.setNoteNumber(ticketList.size() + "");
        infoBean.setTotalMoney(params.getString(SaleLotteryFragment.TOTAL_MONEY));
        infoBean.setBetMode(Constants.BET_MODE_SINGLE);
        if (notOpenQueryResponse != null && notOpenQueryResponse.getDrawList().size() > 0) {
            NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
            infoBean.setDrawNumber(drawListBean.getDrawNumber());
        }
        infoBean.setFrisbeeStatus(cbFeipan.isChecked() ? "01" : "00");
        infoBean.setDataSource(Constants.DATA_SOURCE_ANDROID_END);
        infoBean.setGamePlayNum(gameAddListBean.getGamePlayNum());

        dataBean.setOrderInfo(infoBean);
        happy8CommitRequest.setData(dataBean);

        happy8CommitRequest.setSign(SignUtil.sign(getData(infoBean)));
        return happy8CommitRequest;
    }

    private JSONObject getData(Happy8CommitRequest.DataBean.OrderInfoBean infoBean) {
        JSONObject data = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("gameAlias", infoBean.getGameAlias());

        JSONArray ticketList = new JSONArray();

        List<Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean> list = infoBean.getTicketList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean ticketListBean = list.get(i);
            JSONObject ticketMap = new JSONObject();
            ticketMap.put("ticket", ticketListBean.getTicket());
            ticketMap.put("eachBetMode", ticketListBean.getEachBetMode());
            ticketMap.put("eachTotalMoney", ticketListBean.getEachTotalMoney());
            ticketList.add(ticketMap);
        }
        orderInfo.put("ticketList", ticketList);
        orderInfo.put("terminal", infoBean.getTerminal());
        orderInfo.put("multiDraw", infoBean.getMultiDraw());
        orderInfo.put("betDouble", infoBean.getBetDouble());
        orderInfo.put("noteNumber", infoBean.getNoteNumber());
        orderInfo.put("totalMoney", infoBean.getTotalMoney());
        orderInfo.put("betMode", infoBean.getBetMode());
        orderInfo.put("drawNumber", infoBean.getDrawNumber());
        orderInfo.put("frisbeeStatus", infoBean.getFrisbeeStatus());
        orderInfo.put("gameAlias", infoBean.getGameAlias());
        orderInfo.put("dataSource", infoBean.getDataSource());
        orderInfo.put("gamePlayNum", infoBean.getGamePlayNum());
        data.put("orderInfo", orderInfo);
        return data;
    }

    private List<Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
        int childCount = llAllBallInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
            List<String> redNumList = selectedBallInfo.getRedNumList();

            // 将红球数字放在String中
            int sizeR = redNumList.size();
            for (int j = 0; j < sizeR; j++) {
                stringBuilder.append(redNumList.get(j));
                if (j != sizeR - 1) {
                    stringBuilder.append(" ");
                }
            }
            Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = new Happy8CommitRequest.DataBean.OrderInfoBean.TicketListBean();
            bean.setTicket(stringBuilder.toString());
            bean.setEachBetMode(selectedBallInfo.getBetMode());
            bean.setEachTotalMoney(selectedBallInfo.getPerCost() + "");
            ticketListBean.add(bean);
        }
        return ticketListBean;
    }

    @Override
    protected void refreshRestTime(String drawNumber, String restTime) {
        restTimeView.setDraw(drawNumber);
        restTimeView.setEndTime(restTime);
    }

    @OnClick(R.id.tv_fixed_type)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fixed_type:
                showFixedTypeDialog();
                break;
        }
    }

    private void showFixedTypeDialog() {
        modeSelectPopWin = new ListPopupWindow(getContext());
        int size = gameAddList.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = gameAddList.get(i).getGamePlayName();
        }
        ArrayAdapter<String> fixedGameTypeListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strings);
        modeSelectPopWin.setAdapter(fixedGameTypeListAdapter);
        modeSelectPopWin.setAnchorView(tvFixedType);
        modeSelectPopWin.setDropDownGravity(Gravity.BOTTOM);
        modeSelectPopWin.setWidth(tvFixedType.getWidth());
        modeSelectPopWin.setHeight(ListPopupWindow.WRAP_CONTENT);
        modeSelectPopWin.setModal(true);
        modeSelectPopWin.setVerticalOffset(10);
        modeSelectPopWin.setOnItemClickListener(this);
        modeSelectPopWin.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gameAddListBean = gameAddList.get(position);
        tvFixedType.setText(gameAddListBean.getGamePlayName());

        machineSelectNum = position + 1;
        adapterRed.setMaxCanSelect(machineSelectNum);
        modeSelectPopWin.dismiss();
        resetWaitArea();
        clearSelectedListCallback.toClear();
    }

    @Override
    public EscCommand print() {
        if (commitResponse == null) return new EscCommand();
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();

        esc.addPrintAndLineFeed();
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        esc.addRastBitImage(b, 420, 0);
        esc.addPrintAndLineFeed();

        esc.addText("快乐8\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addPrintAndLineFeed();

        Happy8CommitResponse.DataBean.OrderInfoBean orderInfo = commitResponse.getData().getOrderInfo();
        List<Happy8CommitResponse.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();

        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(endTime) + "开奖\n");

        esc.addText(commitResponse.getSafetyCode() + "\n");
        esc.addSetAbsolutePrintPosition((short) 10);

        esc.addText("-------------------------------------\n");
        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addPrintAndLineFeed();

        int size = ticketList.size();
        for (int j = 0; j < size; j++) {
            Happy8CommitResponse.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
            String betMode = bean.getEachBetMode();
            esc.addText(LotteryUtil.getBetModeByNum(betMode) + " " + bean.getTicket() + "\n");
        }

        String frisbeeStatus = orderInfo.getFrisbeeStatus();
        esc.addText("01".equals(frisbeeStatus) ? "(快乐飞盘)" : "");
        esc.addPrintAndLineFeed();
        esc.addText("-------------------------------------\n");
        esc.addText("感谢您为公益事业贡献 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addText("公益体彩乐善人生公益体彩乐善\n");
        esc.addText("公益体彩乐善人生\n");
        esc.addText("回龙观北京人家XXXXXXX\n");

        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        //QRCode命令打印 此命令只在支持QRCode命令打印的机型才能使用。 在不支持二维码指令打印的机型上，则需要发送二维条码图片
        // 设置纠错等级
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        esc.addSelectSizeOfModuleForQRCode((byte) 10);// 设置qrcode模块大小
        // 设置qrcode内容
        esc.addStoreQRCodeData(commitResponse.getOrderCode());
        esc.addPrintQRCode();// 打印QRCode
        esc.addPrintAndLineFeed();

        esc.addPrintAndFeedLines((byte) 6);
        esc.addCutPaper();
        esc.addQueryPrinterStatus();
        return esc;
    }

    public void setClearSelectedListCallback(ClearSelectedListCallback clearSelectedListCallback) {
        this.clearSelectedListCallback = clearSelectedListCallback;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        feiPanCallBack.onFeiPanSelected(isChecked);
    }

    public interface FeiPanCallBack {
        void onFeiPanSelected(boolean isChecked);
    }

    public void setFeiPanCallBack(FeiPanCallBack feiPanCallBack) {
        this.feiPanCallBack = feiPanCallBack;
    }
}
