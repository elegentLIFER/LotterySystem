//package com.sxmh.wt.lotterysystem.fragment.salelottery.fast3;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.LinearLayout;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.gprinter.command.EscCommand;
//import com.sxmh.wt.lotterysystem.R;
//import com.sxmh.wt.lotterysystem.adapter.RvSelectNumberRectAdapter;
//import com.sxmh.wt.lotterysystem.base.BaseFragment;
//import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
//import com.sxmh.wt.lotterysystem.bean.NotOpenResponseCallBack;
//import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
//import com.sxmh.wt.lotterysystem.bean.OnCommitSuccessListener;
//import com.sxmh.wt.lotterysystem.bean.Strategy;
//import com.sxmh.wt.lotterysystem.bean.UserInfo;
//import com.sxmh.wt.lotterysystem.bean.request.Fast3CommitRequest;
//import com.sxmh.wt.lotterysystem.bean.request.Fast3NotifyRequest;
//import com.sxmh.wt.lotterysystem.bean.response.Fast3CommitResponse;
//import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
//import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
//import com.sxmh.wt.lotterysystem.fragment.salelottery.Fast3Fragment;
//import com.sxmh.wt.lotterysystem.util.Constants;
//import com.sxmh.wt.lotterysystem.util.LotteryUtil;
//import com.sxmh.wt.lotterysystem.util.NUtil;
//import com.sxmh.wt.lotterysystem.util.Net;
//import com.sxmh.wt.lotterysystem.util.RandomUtil;
//import com.sxmh.wt.lotterysystem.util.SignUtil;
//import com.sxmh.wt.lotterysystem.util.TimeUtil;
//import com.sxmh.wt.lotterysystem.util.ToastUtil;
//import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.InjectView;
//
//public class TestFragment extends BaseFragment implements Strategy, NotOpenResponseCallBack {
//    @InjectView(R.id.rv_select_box)
//    RecyclerView rvSelectBox;
//
//    private Fast3CommitRequest fast3CommitRequest;
//    private LinearLayout llAllBallInfo;
//    private List<CircleBtStatus> statusListBox;
//    private RvSelectNumberRectAdapter adapterBox;
//    private int gamePlayId;
//    private String gamePlayNum;
//
//    protected int gameId;
//    protected NotOpenQueryResponse notOpenQueryResponse;
//    protected OnCommitSuccessListener onCommitSuccessListener;
//
//    @Override
//    protected int initLayoutId() {
//        return R.layout.fragment_santong;
//    }
//
//    @Override
//    protected void initData() {
//        initBoxes();
//        Bundle arguments = getArguments();
//        gamePlayId = arguments.getInt(Fast3Fragment.GAME_PLAY_ID);
//        gamePlayNum = arguments.getString(Fast3Fragment.GAME_PLAY_NUM);
//    }
//
//    private void initBoxes() {
//        statusListBox = new ArrayList<>();
//        statusListBox.add(new CircleBtStatus(Color.RED, 111, false));
//        statusListBox.add(new CircleBtStatus(Color.RED, 222, false));
//        statusListBox.add(new CircleBtStatus(Color.RED, 333, false));
//        statusListBox.add(new CircleBtStatus(Color.RED, 444, false));
//        statusListBox.add(new CircleBtStatus(Color.RED, 555, false));
//        statusListBox.add(new CircleBtStatus(Color.RED, 666, false));
//        adapterBox = new RvSelectNumberRectAdapter(getContext(), statusListBox, 6);
//        rvSelectBox.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        rvSelectBox.setAdapter(adapterBox);
//    }
//
//    @Override
//    public void updateContent(int request, Object content) {
//        if (request == Net.REQUEST_GET_NOT_OPEN) {
//            notOpenQueryResponse = (NotOpenQueryResponse) content;
//        } else if (request == Net.REQUEST_COMMIT_FAST_3) {
//            onCommitSuccessListener.OnCommitSuccess();
//            Fast3CommitResponse commitResponse = (Fast3CommitResponse) content;
//            net.k3Printer(getPrinterNoticeRequest(commitResponse));
//        } else if (request == Net.REQUEST_PRINTER_FAST3) {
//            ToastUtil.newToast(getContext(), "打印通知成功");
//        }
//    }
//
//    private Fast3NotifyRequest getPrinterNoticeRequest(Fast3CommitResponse commitResponse) {
//        Fast3NotifyRequest noticeRequest = new Fast3NotifyRequest();
//        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
//        String uid = UserInfo.getInstance().getUid();
//        noticeRequest.setAccountId(Integer.valueOf(uid));
//        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
//        Fast3NotifyRequest.DataBean data = new Fast3NotifyRequest.DataBean();
//        Fast3NotifyRequest.DataBean.PrinterInfoBean printerInfo = new Fast3NotifyRequest.DataBean.PrinterInfoBean();
//        printerInfo.setGameId(gameId);
//        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
//        printerInfo.setDrawNumber(drawListBean.getDrawNumber());
//        printerInfo.setGameAlias(drawListBean.getGameAlias());
//        printerInfo.setOrderCode(commitResponse.getOrderCode());
//        data.setPrinterInfo(printerInfo);
//        data.setPrinterInfo(printerInfo);
//        noticeRequest.setData(data);
//        return noticeRequest;
//    }
//
//    @Override
//    public SelectedBallInfo selectOneByMachine() {
//        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
//        sbiSelectBallInfo.setChangeFormat(false);
//        sbiSelectBallInfo.hidePlusSymbol();
//        int[] randomIntRed = RandomUtil.getRandomInt(1, 6, 1);
//        int num = randomIntRed[0];
//        sbiSelectBallInfo.addRedBall(num);
//        sbiSelectBallInfo.addRedBall(num);
//        sbiSelectBallInfo.addRedBall(num);
//        sbiSelectBallInfo.setPerCost(gameRule.getR007());
//        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
//        sbiSelectBallInfo.setBetNum(1);
//        return sbiSelectBallInfo;
//    }
//
//    @Override
//    public List<SelectedBallInfo> insertToSelectedArea() {
//        List<SelectedBallInfo> infoArrayList = new ArrayList<>();
//        List<Integer> boxes = getBoxSelectNumList();
//        int size = boxes.size();
//        for (int i = 0; i < size; i++) {
//            SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
//            sbiSelectBallInfo.setChangeFormat(false);
//            sbiSelectBallInfo.hidePlusSymbol();
//            int num = boxes.get(i) / 100;
//            sbiSelectBallInfo.addRedBall(num);
//            sbiSelectBallInfo.addRedBall(num);
//            sbiSelectBallInfo.addRedBall(num);
//
//            sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
//            sbiSelectBallInfo.setPerCost(gameRule.getR007());
//            sbiSelectBallInfo.setBetNum(1);
//            infoArrayList.add(sbiSelectBallInfo);
//        }
//        resetWaitArea();
//        return infoArrayList;
//    }
//
//    /**
//     * 得到已经选中的盒子数字
//     *
//     * @return
//     */
//    private List<Integer> getBoxSelectNumList() {
//        int sizeBox = statusListBox.size();
//        List<Integer> selectedBoxList = new ArrayList<>();
//        for (int i = 0; i < sizeBox; i++) {
//            CircleBtStatus circleBtStatus = statusListBox.get(i);
//            if (circleBtStatus.getIsPressed()) {
//                selectedBoxList.add(circleBtStatus.getNum());
//            }
//        }
//        return selectedBoxList;
//    }
//
//    @Override
//    public void commitLottery(LinearLayout llAllBallInfo, Bundle params) {
//        this.llAllBallInfo = llAllBallInfo;
//        fast3CommitRequest = getCommitFast3Request(params);
//        net.commitFast3(getCommitFast3Request(params));
//    }
//
//    private Fast3CommitRequest getCommitFast3Request(Bundle params) {
//        Fast3CommitRequest fast3CommitRequest = new Fast3CommitRequest();
//        fast3CommitRequest.setInterfaceCode(InterfaceCode.CREATE_ORDER);
//        int uid = Integer.valueOf(UserInfo.getInstance().getUid());
//        fast3CommitRequest.setAccountId(uid);
//        fast3CommitRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
//
//        Fast3CommitRequest.DataBean dataBean = new Fast3CommitRequest.DataBean();
//        Fast3CommitRequest.DataBean.OrderInfoBean infoBean = new Fast3CommitRequest.DataBean.OrderInfoBean();
//
//        infoBean.setGameId(gameId + "");
//        infoBean.setGameAlias(Constants.GAME_ALIAS_K3);
//
//        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList = getTicketList();
//        infoBean.setTicketList(ticketList);
//        infoBean.setTerminalId(UserInfo.getInstance().getEpid());
//        infoBean.setTerminal(UserInfo.getInstance().getEpnumbei());
//        infoBean.setMultiDraw(params.getString(SaleLotteryFragment.MULTI_DRAW));
//        infoBean.setBetDouble(params.getString(SaleLotteryFragment.BET_DOUBLE));
//        infoBean.setNoteNumber(ticketList.size() + "");
//        infoBean.setTotalMoney(params.getString(SaleLotteryFragment.TOTAL_MONEY));
//        infoBean.setBetMode(Constants.BET_MODE_DOUBLE);
//        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
//        infoBean.setDrawId(drawListBean.getDrawId() + "");
//        infoBean.setDrawNumber(drawListBean.getDrawNumber());
//        infoBean.setGamePlayId(gamePlayId + "");
//        infoBean.setDataSource(Constants.DATA_SOURCE_ANDROID_END);
//        infoBean.setGamePlayNum(gamePlayNum);
//        dataBean.setOrderInfo(infoBean);
//        fast3CommitRequest.setData(dataBean);
//
//        fast3CommitRequest.setSign(SignUtil.sign(getData(infoBean)));
//        return fast3CommitRequest;
//    }
//
//    @Override
//    public void selectBet() {
//    }
//
//    private void resetWaitArea() {
//        for (CircleBtStatus status : statusListBox) {
//            status.setIsPressed(false);
//        }
//        adapterBox.notifyDataSetChanged();
//    }
//
//    @Override
//    public EscCommand print() {
//        if (fast3CommitRequest == null) {
//            return new EscCommand();
//        }
//        EscCommand esc = new EscCommand();
//        esc.addInitializePrinter();
//
//        esc.addPrintAndLineFeed();
//        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
//        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
//
//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
//        esc.addRastBitImage(b, 420, 0);
//        esc.addPrintAndLineFeed();
//
//        esc.addText("快3\n");
//        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
//        esc.addPrintAndLineFeed();
//
//        Fast3CommitRequest.DataBean.OrderInfoBean orderInfo = fast3CommitRequest.getData().getOrderInfo();
//        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();
//
//        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
//        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(drawListBean.getEndTime()) + "开奖\n");
//        esc.addText("1100115-807700-074994-357206 806525\n");
//        esc.addSetAbsolutePrintPosition((short) 10);
//
//        esc.addText("-------------------------------------\n");
//        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
//        esc.addText("三同号\n");
//        esc.addPrintAndLineFeed();
//
//        int size = ticketList.size();
//        for (int j = 0; j < size; j++) {
//            Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
//            String betMode = bean.getEachBetMode();
//            esc.addText(LotteryUtil.getBetModeByNum(betMode) + " " + bean.getTicket() + "\n");
//        }
//        esc.addPrintAndLineFeed();
//        esc.addText("-------------------------------------\n");
//        esc.addText("感谢您为公益事业贡献 " + orderInfo.getTotalMoney() + " 莫币\n");
//        esc.addText("公益体彩乐善人生公益体彩乐善\n");
//        esc.addText("公益体彩乐善人生\n");
//        esc.addText("回龙观北京人家XXXXXXX\n");
//        esc.addText("6300-000000007780-5456641215\n");
//
//        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
//        //QRCode命令打印 此命令只在支持QRCode命令打印的机型才能使用。 在不支持二维码指令打印的机型上，则需要发送二维条码图片
//        // 设置纠错等级
//        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
//        // 设置qrcode模块大小
//        esc.addSelectSizeOfModuleForQRCode((byte) 10);
//        // 设置qrcode内容
//        esc.addStoreQRCodeData("{\"code\": \"00000\",\"message\": \"获取成功\",\"state\": \"00\",\"timeStamp\": 1537511165063}");
//        esc.addPrintQRCode();// 打印QRCode
//        esc.addPrintAndLineFeed();
//
//        esc.addPrintAndFeedLines((byte) 6);
//        esc.addCutPaper();
//        esc.addQueryPrinterStatus();
//        return esc;
//    }
//
//    private JSONObject getData(Fast3CommitRequest.DataBean.OrderInfoBean infoBean) {
//        JSONObject data = new JSONObject();
//        JSONObject orderInfo = new JSONObject();
//        orderInfo.put("gameId", infoBean.getGameId());
//        orderInfo.put("gameAlias", infoBean.getGameAlias());
//
//        JSONArray ticketList = new JSONArray();
//
//        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> list = infoBean.getTicketList();
//        int size = list.size();
//        for (int i = 0; i < size; i++) {
//            Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean ticketListBean = list.get(i);
//            JSONObject ticketMap = new JSONObject();
//            ticketMap.put("ticket", ticketListBean.getTicket());
//            ticketMap.put("eachTotalMoney", ticketListBean.getEachTotalMoney());
//            ticketMap.put("eachBetMode", ticketListBean.getEachBetMode());
//            ticketList.add(ticketMap);
//        }
//        orderInfo.put("ticketList", ticketList);
//        orderInfo.put("terminalId", infoBean.getTerminalId());
//        orderInfo.put("terminal", infoBean.getTerminal());
//        orderInfo.put("multiDraw", infoBean.getMultiDraw());
//        orderInfo.put("betDouble", infoBean.getBetDouble());
//        orderInfo.put("noteNumber", infoBean.getNoteNumber());
//        orderInfo.put("totalMoney", infoBean.getTotalMoney());
//        orderInfo.put("betMode", infoBean.getBetMode());
//        orderInfo.put("drawId", infoBean.getDrawId());
//        orderInfo.put("drawNumber", infoBean.getDrawNumber());
//        orderInfo.put("gamePlayId", infoBean.getGamePlayId());
//        orderInfo.put("dataSource", infoBean.getDataSource());
//        orderInfo.put("gamePlayNum", infoBean.getGamePlayNum());
//        data.put("orderInfo", orderInfo);
//        return data;
//    }
//
//    private List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
//        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
//        int childCount = llAllBallInfo.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = new Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean();
//
//            StringBuilder stringBuilder = new StringBuilder();
//            SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
//            List<String> redNumList = selectedBallInfo.getRedNumList();
//
//            int sizeR = redNumList.size();
//            for (int j = 0; j < sizeR; j++) {
//                stringBuilder.append(redNumList.get(j));
//                if (j != sizeR - 1) {
//                    stringBuilder.append(" ");
//                }
//            }
//            bean.setTicket(stringBuilder.toString());
//            bean.setEachBetMode(selectedBallInfo.getBetMode());
//            bean.setEachTotalMoney(selectedBallInfo.getPerCost() + "");
//            ticketListBean.add(bean);
//        }
//        return ticketListBean;
//    }
//
//    @Override
//    public void notifyResponse(NotOpenQueryResponse notOpenQueryResponse) {
//        this.notOpenQueryResponse = notOpenQueryResponse;
//    }
//}
