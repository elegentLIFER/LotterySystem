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
import com.sxmh.wt.lotterysystem.bean.request.CommitLotteryRequest;
import com.sxmh.wt.lotterysystem.bean.request.DoubleBallNotifyRequest;
import com.sxmh.wt.lotterysystem.bean.request.Pl5NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Arrange5CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.CommitLotteryResponse;
import com.sxmh.wt.lotterysystem.bean.response.DoubleBallNotifyResponse;
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

public class DoubleBallFragment extends BaseSelectFragment {
    @InjectView(R.id.rest_time_view)
    RestTimeView restTimeView;
    @InjectView(R.id.rv_select_red)
    RecyclerView rvSelectRed;
    @InjectView(R.id.rv_select_blue)
    RecyclerView rvSelectBlue;

    private static final int MAX_RED_IN_DOUBLE_BALL = 33;
    private static final int MAX_BLUE_AMOUNT = 16;
    private static final int RECYCLER_ADAPTER_SPAN = 11;

    private RvSelectNumberRoundAdapter adapterRed;
    private RvSelectNumberRoundAdapter adapterBlue;
    private List<CircleBtStatus> statusListRed;
    private List<CircleBtStatus> statusListBlue;

    private LinearLayout llAllBallInfo;

    private CommitLotteryResponse commitResponse;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_double_ball;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initBox() {
        initRedBalls();
        initBlueBalls();
    }

    private void initRedBalls() {
        statusListRed = new ArrayList<>();
        for (int i = 1; i < 34; i++) {
            statusListRed.add(new CircleBtStatus(Color.RED, i, false));
        }
        adapterRed = new RvSelectNumberRoundAdapter(getContext(), statusListRed, MAX_RED_IN_DOUBLE_BALL);
        rvSelectRed.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_ADAPTER_SPAN));
        rvSelectRed.setAdapter(adapterRed);
    }

    private void initBlueBalls() {
        statusListBlue = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            statusListBlue.add(new CircleBtStatus(Color.BLUE, i, false));
        }
        adapterBlue = new RvSelectNumberRoundAdapter(getContext(), statusListBlue, MAX_BLUE_AMOUNT);
        rvSelectBlue.setLayoutManager(new GridLayoutManager(getContext(), RECYCLER_ADAPTER_SPAN));
        rvSelectBlue.setAdapter(adapterBlue);
    }

    @Override
    public void updateContent(int request, Object content) {
        super.updateContent(request, content);

        if (request == Net.REQUEST_COMMIT_LOTTERY) {
            commitResponse = (CommitLotteryResponse) content;
            net.ssqPrinter(getPrinterNoticeRequest());
            onCommitSuccessListener.OnCommitSuccess();
        } else if (request == Net.REQUEST_PRINTER_DOUBLE_BALL) {
            ToastUtil.newToast(getContext(), "打印通知成功");
        }
    }

    private DoubleBallNotifyRequest getPrinterNoticeRequest() {
        DoubleBallNotifyRequest noticeRequest = new DoubleBallNotifyRequest();
        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        String uid = UserInfo.getInstance().getUid();
        noticeRequest.setAccountId(Integer.valueOf(uid));
        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
        DoubleBallNotifyRequest.DataBean data = new DoubleBallNotifyRequest.DataBean();
        DoubleBallNotifyRequest.DataBean.PrinterInfoBean printerInfo = new DoubleBallNotifyRequest.DataBean.PrinterInfoBean();
        printerInfo.setGameId(gameListBean.getId());
        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
        printerInfo.setDrawNumber(drawListBean.getDrawNumber());
        printerInfo.setGameAlias(drawListBean.getGameAlias());
        printerInfo.setOrderCode(commitResponse.getOrderCode());
        data.setPrinterInfo(printerInfo);
        data.setPrinterInfo(printerInfo);
        noticeRequest.setData(data);
        return noticeRequest;
    }

    /**
     * 得到已经选中的蓝球数字
     *
     * @return
     */
    private List<Integer> getBlueSelectNumList() {
        int sizeBlue = statusListBlue.size();
        List<Integer> selectedBlueList = new ArrayList<>();

        // 遍历蓝球列表查找已经选中的
        for (int i = 0; i < sizeBlue; i++) {
            CircleBtStatus circleBtStatus = statusListBlue.get(i);
            if (circleBtStatus.getIsPressed()) {
                selectedBlueList.add(circleBtStatus.getNum());
            }
        }
        return selectedBlueList;
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
    public SelectedBallInfo selectOneByMachine() {
        int[] randomIntRed = RandomUtil.getRandomInt(1, MAX_RED_IN_DOUBLE_BALL, 6);
        int[] randomIntBlue = RandomUtil.getRandomInt(1, MAX_BLUE_AMOUNT, 1);

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        for (int randomRed : randomIntRed) {
            sbiSelectBallInfo.addRedBall(randomRed);
        }
        for (int randomBlue : randomIntBlue) {
            sbiSelectBallInfo.addBlueBall(randomBlue);
        }
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setBetNum(1);
        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<SelectedBallInfo> infoArrayList = new ArrayList<>();

        List<Integer> selectedRedList = getRedSelectNumList();
        List<Integer> selectedBlueList = getBlueSelectNumList();
        int redNum = selectedRedList.size();
        int blueNum = selectedBlueList.size();
        if (redNum < 6 || blueNum == 0) {
            ToastUtil.newToast(getContext(), "请至少选择6个红球和1个蓝球");
            return null;
        }

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        for (int i = 0; i < redNum; i++) sbiSelectBallInfo.addRedBall(selectedRedList.get(i));
        for (int i = 0; i < blueNum; i++) sbiSelectBallInfo.addBlueBall(selectedBlueList.get(i));

        boolean singleMode = (redNum == 6 && blueNum == 1);
        sbiSelectBallInfo.setBetMode(singleMode ? Constants.BET_MODE_SINGLE : Constants.BET_MODE_DOUBLE);

        int betNum = NUtil.C2(6, redNum) * blueNum;
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
        for (CircleBtStatus status : statusListBlue) {
            status.setIsPressed(false);
        }
        adapterBlue.notifyDataSetChanged();
        adapterRed.notifyDataSetChanged();
    }

    @Override
    public void commitLottery(LinearLayout llAllBallInfo, Bundle params) {
        this.llAllBallInfo = llAllBallInfo;
        net.commitLottery(getCommitLotteryRequest(params));
    }

    private CommitLotteryRequest getCommitLotteryRequest(Bundle params) {
        CommitLotteryRequest commitLotteryRequest = new CommitLotteryRequest();
        commitLotteryRequest.setInterfaceCode(InterfaceCode.CREATE_ORDER);
        int uid = Integer.valueOf(UserInfo.getInstance().getUid());
        commitLotteryRequest.setAccountId(uid);
        commitLotteryRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        CommitLotteryRequest.DataBean dataBean = new CommitLotteryRequest.DataBean();
        CommitLotteryRequest.DataBean.OrderInfoBean infoBean = new CommitLotteryRequest.DataBean.OrderInfoBean();

        infoBean.setGameId(gameListBean.getId() + "");
        infoBean.setGameAlias(Constants.GAME_ALIAS_SSQ);

        List<CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean> ticketList = getTicketList();
        infoBean.setTicketList(ticketList);
        infoBean.setTerminalId(UserInfo.getInstance().getEpid());
        infoBean.setTerminal(UserInfo.getInstance().getEpnumbei());
        infoBean.setMultiDraw(params.getString(SaleLotteryFragment.MULTI_DRAW));
        infoBean.setBetDouble(params.getString(SaleLotteryFragment.BET_DOUBLE));
        infoBean.setNoteNumber(params.getString(SaleLotteryFragment.BET_NUM));
        infoBean.setTotalMoney(params.getString(SaleLotteryFragment.TOTAL_MONEY));
        infoBean.setBetMode(Constants.BET_MODE_SINGLE);
        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
        infoBean.setDrawId(drawListBean.getDrawId() + "");
        infoBean.setDrawNumber(drawListBean.getDrawNumber());
        infoBean.setDataSource(Constants.DATA_SOURCE_ANDROID_END);

        dataBean.setOrderInfo(infoBean);
        commitLotteryRequest.setSign(SignUtil.sign(getData(infoBean)));
        commitLotteryRequest.setData(dataBean);
        return commitLotteryRequest;
    }

    private JSONObject getData(CommitLotteryRequest.DataBean.OrderInfoBean infoBean) {
        JSONObject data = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("gameId", infoBean.getGameId());
        orderInfo.put("gameAlias", infoBean.getGameAlias());

        JSONArray ticketList = new JSONArray();

        List<CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean> list = infoBean.getTicketList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean ticketListBean = list.get(i);
            JSONObject ticketMap = new JSONObject();
            ticketMap.put("ticket", ticketListBean.getTicket());
            ticketMap.put("eachBetMode", ticketListBean.getEachBetMode());
            ticketMap.put("eachTotalMoney", ticketListBean.getEachTotalMoney());
            ticketList.add(ticketMap);
        }
        orderInfo.put("ticketList", ticketList);
        orderInfo.put("terminalId", infoBean.getTerminalId());
        orderInfo.put("terminal", infoBean.getTerminal());
        orderInfo.put("multiDraw", infoBean.getMultiDraw());
        orderInfo.put("betDouble", infoBean.getBetDouble());
        orderInfo.put("noteNumber", infoBean.getNoteNumber());
        orderInfo.put("totalMoney", infoBean.getTotalMoney());
        orderInfo.put("betMode", infoBean.getBetMode());
        orderInfo.put("drawId", infoBean.getDrawId());
        orderInfo.put("drawNumber", infoBean.getDrawNumber());
        orderInfo.put("gameAlias", infoBean.getGameAlias());
        orderInfo.put("dataSource", infoBean.getDataSource());
        data.put("orderInfo", orderInfo);
        return data;
    }

    private List<CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
        int childCount = llAllBallInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
            List<String> redNumList = selectedBallInfo.getRedNumList();
            List<String> blueNumList = selectedBallInfo.getBlueNumList();

            int sizeR = redNumList.size();
            for (int j = 0; j < sizeR; j++) {
                stringBuilder.append(redNumList.get(j));
                if (j != sizeR - 1) {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append("|");
                }
            }
            int sizeB = blueNumList.size();
            for (int j = 0; j < sizeB; j++) {
                stringBuilder.append(blueNumList.get(j));
                if (j != sizeB - 1) {
                    stringBuilder.append(" ");
                }
            }
            CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean bean = new CommitLotteryRequest.DataBean.OrderInfoBean.TicketListBean();
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

        esc.addText("双色球\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addPrintAndLineFeed();

        CommitLotteryResponse.DataBean.OrderInfoBean orderInfo = commitResponse.getData().getOrderInfo();
        List<CommitLotteryResponse.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();

        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(endTime) + "开奖\n");
        esc.addText("1100115-807700-074994-357206 806525\n");
        esc.addSetAbsolutePrintPosition((short) 10);

        esc.addText("------------------------------------- \n");
        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addPrintAndLineFeed();

        int size = ticketList.size();
        for (int j = 0; j < size; j++) {
            CommitLotteryResponse.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
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
