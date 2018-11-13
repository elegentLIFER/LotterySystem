package com.sxmh.wt.lotterysystem.fragment.salelottery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.adapter.RvSelectNumberRoundAdapter;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.Arrange5CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Commit36$7Request;
import com.sxmh.wt.lotterysystem.bean.request.Notify36$7Request;
import com.sxmh.wt.lotterysystem.bean.request.Pl5NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Arrange5CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.Commit36$7Response;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.BaseSelectFragment;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.LotteryUtil;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.RandomUtil;
import com.sxmh.wt.lotterysystem.util.SignUtil;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.RestTimeView;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class _36$7Fragment extends BaseSelectFragment {
    @InjectView(R.id.rest_time_view)
    RestTimeView restTimeView;
    @InjectView(R.id.rv_select_red)
    RecyclerView rvSelectRed;

    private static final int MAX_OF_BALL = 36;
    private static final int RECYCLER_ADAPTER_SPAN = 11;
    private RvSelectNumberRoundAdapter adapterRed;
    private List<CircleBtStatus> statusListRed;
    private LinearLayout llAllBallInfo;

    private Commit36$7Response commitResponse;
    private int betNum;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_36_7;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initBox() {
        statusListRed = new ArrayList<>();
        for (int i = 1; i <= MAX_OF_BALL; i++) {
            statusListRed.add(new CircleBtStatus(Color.RED, i, false));
        }
        adapterRed = new RvSelectNumberRoundAdapter(getContext(), statusListRed, MAX_OF_BALL);
        rvSelectRed.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_ADAPTER_SPAN));
        rvSelectRed.setAdapter(adapterRed);
    }

    @Override
    public void updateContent(int request, Object content) {
        super.updateContent(request, content);

        if (request == Net.REQUEST_COMMIT_36$7) {
            onCommitSuccessListener.OnCommitSuccess();
            commitResponse = (Commit36$7Response) content;
            net._36Selection7Printer(getPrinterNoticeRequest());
        } else if (request == Net.REQUEST_PRINTER_36$7) {
            ToastUtil.newToast(getContext(), "打印通知成功");
        }
    }

    private Notify36$7Request getPrinterNoticeRequest() {
        Notify36$7Request noticeRequest = new Notify36$7Request();
        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        String uid = UserInfo.getInstance().getUid();
        noticeRequest.setAccountName(UserInfo.getInstance().getName());
        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
        Notify36$7Request.DataBean data = new Notify36$7Request.DataBean();
        Notify36$7Request.DataBean.PrinterInfoBean printerInfo = new Notify36$7Request.DataBean.PrinterInfoBean();
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
    protected void refreshRestTime(String drawNumber, String restTime) {
        restTimeView.setDraw(drawNumber);
        restTimeView.setEndTime(restTime);
    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        int[] randomIntRed = RandomUtil.getRandomInt(1, MAX_OF_BALL, 7);

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();

        for (int i = 0; i < randomIntRed.length; i++) {
            sbiSelectBallInfo.addRedBall(randomIntRed[i]);
        }

        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        betNum = 1;
        sbiSelectBallInfo.setBetNum(betNum);
        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<SelectedBallInfo> infoArrayList = new ArrayList<>();

        List<Integer> reds = getRedSelectNumList();

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();

        int size = reds.size();
        for (int i = 0; i < size; i++) {
            sbiSelectBallInfo.addRedBall(reds.get(i));
        }

        sbiSelectBallInfo.setBetMode(size > 7 ? Constants.BET_MODE_DOUBLE : Constants.BET_MODE_SINGLE);
        betNum = NUtil.C2(7, size);
        sbiSelectBallInfo.setPerCost(betNum * gameRule.getR007());
        sbiSelectBallInfo.setBetNum(betNum);

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

    /**
     * 得到已经选中的红球数字
     */
    private List<Integer> getRedSelectNumList() {
        int sizeRed = statusListRed.size();
        List<Integer> selectedRedList = new ArrayList<>();

        // 遍历红球列表查找已经选中的
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
        net.commit36$7Lottery(getCommit36$7Request(params));
    }

    private Commit36$7Request getCommit36$7Request(Bundle params) {
        Commit36$7Request commit36$7Request = new Commit36$7Request();
        commit36$7Request.setInterfaceCode(InterfaceCode.CREATE_ORDER);
        int uid = Integer.valueOf(UserInfo.getInstance().getUid());
        commit36$7Request.setAccountName(UserInfo.getInstance().getName());
        commit36$7Request.setRequestTime(TimeUtil.get10IntTimeStamp());

        Commit36$7Request.DataBean dataBean = new Commit36$7Request.DataBean();
        Commit36$7Request.DataBean.OrderInfoBean infoBean = new Commit36$7Request.DataBean.OrderInfoBean();

        infoBean.setGameAlias(Constants.GAME_ALIAS_36X7);

        List<Commit36$7Request.DataBean.OrderInfoBean.TicketListBean> ticketList = getTicketList();
        infoBean.setTicketList(ticketList);
        infoBean.setTerminal(UserInfo.getInstance().getEpnumbei());
        infoBean.setMultiDraw(params.getString(SaleLotteryFragment.MULTI_DRAW));
        infoBean.setBetDouble(params.getString(SaleLotteryFragment.BET_DOUBLE));
        infoBean.setNoteNumber(betNum + "");
        infoBean.setTotalMoney(params.getString(SaleLotteryFragment.TOTAL_MONEY));

        infoBean.setBetMode(getTotalBetMode(ticketList));
        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
        infoBean.setDrawNumber(drawListBean.getDrawNumber());
        infoBean.setDataSource(Constants.DATA_SOURCE_ANDROID_END);

        dataBean.setOrderInfo(infoBean);
        commit36$7Request.setData(dataBean);
        commit36$7Request.setSign(SignUtil.sign(getData(infoBean)));

        return commit36$7Request;
    }

    private String getTotalBetMode(List<Commit36$7Request.DataBean.OrderInfoBean.TicketListBean> ticketList) {
        String betMode = Constants.BET_MODE_SINGLE;
        int size = ticketList.size();
        for (int i = 0; i < size; i++) {
            String eachBetMode = ticketList.get(i).getEachBetMode();
            if (i == 0) betMode = eachBetMode;

            if (!eachBetMode.equals(betMode)) {
                betMode = Constants.BET_MODE_COMPOUND;
                break;
            }
        }
        return betMode;
    }

    private JSONObject getData(Commit36$7Request.DataBean.OrderInfoBean infoBean) {
        JSONObject data = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("gameAlias", infoBean.getGameAlias());

        JSONArray ticketList = new JSONArray();

        List<Commit36$7Request.DataBean.OrderInfoBean.TicketListBean> list = infoBean.getTicketList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Commit36$7Request.DataBean.OrderInfoBean.TicketListBean ticketListBean = list.get(i);
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
        orderInfo.put("gameAlias", infoBean.getGameAlias());
        orderInfo.put("dataSource", infoBean.getDataSource());
        data.put("orderInfo", orderInfo);
        return data;
    }

    private List<Commit36$7Request.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<Commit36$7Request.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
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

            Commit36$7Request.DataBean.OrderInfoBean.TicketListBean bean = new Commit36$7Request.DataBean.OrderInfoBean.TicketListBean();
            bean.setTicket(stringBuilder.toString());
            bean.setEachBetMode(selectedBallInfo.getBetMode());
            bean.setEachTotalMoney(selectedBallInfo.getPerCost() + "");
            ticketListBean.add(bean);
        }
        return ticketListBean;
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

        esc.addText("36选7\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addPrintAndLineFeed();

        Commit36$7Response.DataBean.OrderInfoBean orderInfo = commitResponse.getData().getOrderInfo();
        List<Commit36$7Response.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();

        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(endTime) + "开奖\n");
        esc.addText("1100115-807700-074994-357206 806525\n");
        esc.addSetAbsolutePrintPosition((short) 10);

        esc.addText("-------------------------------------\n");
        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addPrintAndLineFeed();

        int size = ticketList.size();
        for (int j = 0; j < size; j++) {
            Commit36$7Response.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
            String betMode = bean.getEachBetMode();
            esc.addText(LotteryUtil.getBetModeByNum(betMode) + " " + bean.getTicket() + "\n");
        }
        esc.addPrintAndLineFeed();
        esc.addText("-------------------------------------\n");
        esc.addText("感谢您为公益事业贡献 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addText("公益体彩乐善人生公益体彩乐善\n");
        esc.addText("公益体彩乐善人生\n");
        esc.addText("回龙观北京人家XXXXXXX\n");
        esc.addText("6300-000000007780-5456641215\n");


        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        //QRCode命令打印 此命令只在支持QRCode命令打印的机型才能使用。 在不支持二维码指令打印的机型上，则需要发送二维条码图片
        // 设置纠错等级
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        // 设置qrcode模块大小
        esc.addSelectSizeOfModuleForQRCode((byte) 10);
        // 设置qrcode内容
        esc.addStoreQRCodeData(commitResponse.getOrderCode());
        esc.addPrintQRCode();// 打印QRCode
        esc.addPrintAndLineFeed();

        esc.addPrintAndFeedLines((byte) 6);
        esc.addCutPaper();
        esc.addQueryPrinterStatus();
        return esc;
    }
}
