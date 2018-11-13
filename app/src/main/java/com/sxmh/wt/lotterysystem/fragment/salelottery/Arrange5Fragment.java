package com.sxmh.wt.lotterysystem.fragment.salelottery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.Arrange5CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Pl5NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Arrange5CommitResponse;
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
import com.sxmh.wt.lotterysystem.view.Arrange5BallSelectView;
import com.sxmh.wt.lotterysystem.view.RestTimeView;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class Arrange5Fragment extends BaseSelectFragment {
    @InjectView(R.id.rest_time_view)
    RestTimeView restTimeView;
    @InjectView(R.id.arrange5_view_wan)
    Arrange5BallSelectView arrange5ViewWan;
    @InjectView(R.id.arrange5_view_qian)
    Arrange5BallSelectView arrange5ViewQian;
    @InjectView(R.id.arrange5_view_bai)
    Arrange5BallSelectView arrange5ViewBai;
    @InjectView(R.id.arrange5_view_shi)
    Arrange5BallSelectView arrange5ViewShi;
    @InjectView(R.id.arrange5_view_ge)
    Arrange5BallSelectView arrange5ViewGe;

    private LinearLayout llAllBallInfo;
    private int betNum;

    private Arrange5CommitResponse commitResponse;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_arrange_5;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initBox() {
        arrange5ViewWan.setTvTitle("万位");
        arrange5ViewQian.setTvTitle("千位");
        arrange5ViewBai.setTvTitle("百位");
        arrange5ViewShi.setTvTitle("十位");
        arrange5ViewGe.setTvTitle("个位");
    }

    @Override
    public void updateContent(int request, Object content) {
        super.updateContent(request, content);
        if (request == Net.REQUEST_COMMIT_ARRANGE_5) {
            commitResponse = (Arrange5CommitResponse) content;
            net.pl5Printer(getPrinterNoticeRequest());
            onCommitSuccessListener.OnCommitSuccess();
        } else if (request == Net.REQUEST_PRINTER_PL5) {
            ToastUtil.newToast(getContext(), "打印通知成功");
        }
    }

    private Pl5NotifyRequest getPrinterNoticeRequest() {
        Pl5NotifyRequest noticeRequest = new Pl5NotifyRequest();
        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        noticeRequest.setAccountName(UserInfo.getInstance().getName());
        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
        Pl5NotifyRequest.DataBean data = new Pl5NotifyRequest.DataBean();
        Pl5NotifyRequest.DataBean.PrinterInfoBean printerInfo = new Pl5NotifyRequest.DataBean.PrinterInfoBean();
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
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.setChangeFormat(false);
        sbiSelectBallInfo.hidePlusSymbol();
        for (int i = 0; i < 5; i++) {
            sbiSelectBallInfo.addRedBall(RandomUtil.getRandomInt(1, 9, 1)[0]);
        }
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        betNum = 1;
        sbiSelectBallInfo.setBetNum(betNum);

        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<Integer> wanSelectedNumList = arrange5ViewWan.getSelectedNumList();
        List<Integer> qianSelectedNumList = arrange5ViewQian.getSelectedNumList();
        List<Integer> baiSelectedNumList = arrange5ViewBai.getSelectedNumList();
        List<Integer> shiSelectedNumList = arrange5ViewShi.getSelectedNumList();
        List<Integer> geSelectedNumList = arrange5ViewGe.getSelectedNumList();
        int wanSize = wanSelectedNumList.size();
        int qianSize = qianSelectedNumList.size();
        int baiSize = baiSelectedNumList.size();
        int shiSize = shiSelectedNumList.size();
        int geSize = geSelectedNumList.size();

        if (wanSize == 0 || qianSize == 0 || baiSize == 0 || shiSize == 0 || geSize == 0) {
            ToastUtil.newToast(getContext(), "请选择五位号码");
            return null;
        }

        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.setChangeFormat(false);
        sbiSelectBallInfo.hidePlusSymbol();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wanSize; i++) {
            sb.append(wanSelectedNumList.get(i) + "");
        }
        sbiSelectBallInfo.addRedBallByString(sb.toString());

        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < qianSize; i++) {
            sb1.append(qianSelectedNumList.get(i) + "");
        }
        sbiSelectBallInfo.addRedBallByString(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < baiSize; i++) {
            sb2.append(baiSelectedNumList.get(i) + "");
        }
        sbiSelectBallInfo.addRedBallByString(sb2.toString());

        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < shiSize; i++) {
            sb3.append(shiSelectedNumList.get(i) + "");
        }
        sbiSelectBallInfo.addRedBallByString(sb3.toString());

        StringBuilder sb4 = new StringBuilder();
        for (int i = 0; i < geSize; i++) {
            sb4.append(geSelectedNumList.get(i) + "");
        }
        sbiSelectBallInfo.addRedBallByString(sb4.toString());

        resetWaitArea();

        boolean isDouble = wanSize > 1 || qianSize > 1 || baiSize > 1 || shiSize > 1 || geSize > 1;
        sbiSelectBallInfo.setBetMode(isDouble ? Constants.BET_MODE_DOUBLE : Constants.BET_MODE_SINGLE);

        betNum = wanSize * qianSize * baiSize * shiSize * geSize;
        sbiSelectBallInfo.setPerCost(betNum * gameRule.getR007());
        sbiSelectBallInfo.setBetNum(betNum);

        List<SelectedBallInfo> infoArrayList = new ArrayList<>();
        infoArrayList.add(sbiSelectBallInfo);
        return infoArrayList;
    }

    @Override
    public void resetWaitArea() {

    }

    @Override
    public void commitLottery(LinearLayout llAllBallInfo, Bundle params) {
        this.llAllBallInfo = llAllBallInfo;
        net.commitArrange5(getArrange5CommitRequest(params));
    }

    private Arrange5CommitRequest getArrange5CommitRequest(Bundle params) {
        Arrange5CommitRequest arrange5CommitRequest = new Arrange5CommitRequest();
        arrange5CommitRequest.setInterfaceCode(InterfaceCode.CREATE_ORDER);
        arrange5CommitRequest.setAccountName(UserInfo.getInstance().getName());
        arrange5CommitRequest.setRequestTime(TimeUtil.get10IntTimeStamp());

        Arrange5CommitRequest.DataBean dataBean = new Arrange5CommitRequest.DataBean();
        Arrange5CommitRequest.DataBean.OrderInfoBean infoBean = new Arrange5CommitRequest.DataBean.OrderInfoBean();
        infoBean.setGameAlias(Constants.GAME_ALIAS_PL5);

        List<Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList = getTicketList();
        infoBean.setTicketList(ticketList);
        infoBean.setTerminal(UserInfo.getInstance().getEpnumbei());
        infoBean.setMultiDraw(params.getString(SaleLotteryFragment.MULTI_DRAW));
        infoBean.setBetDouble(params.getString(SaleLotteryFragment.BET_DOUBLE));
        infoBean.setNoteNumber(betNum + "");
        infoBean.setTotalMoney(params.getString(SaleLotteryFragment.TOTAL_MONEY));

        infoBean.setBetMode(getTotalBetMode(ticketList));
        infoBean.setDataSource(Constants.DATA_SOURCE_ANDROID_END);
        if (notOpenQueryResponse != null && notOpenQueryResponse.getDrawList().size() > 0) {
            NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
            infoBean.setDrawNumber(drawListBean.getDrawNumber());
        }
        dataBean.setOrderInfo(infoBean);
        arrange5CommitRequest.setData(dataBean);
        arrange5CommitRequest.setSign(SignUtil.sign(getData(infoBean)));
        return arrange5CommitRequest;
    }

    private String getTotalBetMode(List<Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList) {
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

    private JSONObject getData(Arrange5CommitRequest.DataBean.OrderInfoBean infoBean) {
        JSONObject data = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("gameAlias", infoBean.getGameAlias());

        JSONArray ticketList = new JSONArray();

        List<Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean> list = infoBean.getTicketList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean ticketListBean = list.get(i);
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

    private List<Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
        int childCount = llAllBallInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
            List<String> redNumList = selectedBallInfo.getRedNumList();

            String betMode = selectedBallInfo.getBetMode();
            boolean isSingle = (betMode == Constants.BET_MODE_SINGLE);
            int sizeR = redNumList.size();
            for (int j = 0; j < sizeR; j++) {
                String str = redNumList.get(j);
                for (int k = 0; k < str.length(); k++) {
                    stringBuilder.append(str.substring(k, k + 1));
                    if (k != str.length() - 1) {
                        stringBuilder.append(" ");
                    }
                }
                if (j != sizeR - 1) {
                    if (isSingle) {
                        stringBuilder.append(" ");
                    } else {
                        stringBuilder.append("|");
                    }
                }
            }
            Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = new Arrange5CommitRequest.DataBean.OrderInfoBean.TicketListBean();
            bean.setTicket(stringBuilder.toString());
            bean.setEachBetMode(betMode);
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

        esc.addText("排列5\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addPrintAndLineFeed();

        Arrange5CommitResponse.DataBean.OrderInfoBean orderInfo = commitResponse.getData().getOrderInfo();
        List<Arrange5CommitResponse.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();

        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(endTime) + "开奖\n");
        esc.addText("1100115-807700-074994-357206 806525\n");
        esc.addSetAbsolutePrintPosition((short) 10);

        esc.addText("-------------------------------------\n");
        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addPrintAndLineFeed();

        int size = ticketList.size();
        for (int j = 0; j < size; j++) {
            Arrange5CommitResponse.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
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