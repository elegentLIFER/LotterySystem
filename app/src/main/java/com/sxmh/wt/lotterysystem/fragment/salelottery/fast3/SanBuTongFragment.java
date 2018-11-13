package com.sxmh.wt.lotterysystem.fragment.salelottery.fast3;

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
import com.sxmh.wt.lotterysystem.adapter.RvSelectNumberRectAdapter;
import com.sxmh.wt.lotterysystem.bean.CircleBtStatus;
import com.sxmh.wt.lotterysystem.bean.NotOpenResponseCallBack;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.Fast3CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Fast3NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Fast3CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.BaseSelectFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.Fast3Fragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.base.Fast3ChildBaseFragment;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.LotteryUtil;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.RandomUtil;
import com.sxmh.wt.lotterysystem.util.SignUtil;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class SanBuTongFragment extends Fast3ChildBaseFragment  {
    @InjectView(R.id.rv_select_box)
    RecyclerView rvSelectBox;

    private List<CircleBtStatus> statusListBox;
    private RvSelectNumberRectAdapter adapterBox;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_sanbutong;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initBox() {
        statusListBox = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            statusListBox.add(new CircleBtStatus(Color.RED, i + 1, false));
        }
        adapterBox = new RvSelectNumberRectAdapter(getContext(), statusListBox, 6);
        rvSelectBox.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvSelectBox.setAdapter(adapterBox);
    }

//    @Override
//    public void updateContent(int request, Object content) {
//        if (request == Net.REQUEST_GET_NOT_OPEN) {
//            notOpenQueryResponse = (NotOpenQueryResponse) content;
//        } else if (request == Net.REQUEST_COMMIT_FAST_3) {
//            commitResponse = (Fast3CommitResponse) content;
//            net.k3Printer(getPrinterNoticeRequest());
//            onCommitSuccessListener.OnCommitSuccess();
//        } else if (request == Net.REQUEST_PRINTER_FAST3) {
//            ToastUtil.newToast(getContext(), "打印通知成功");
//        }
//    }
//
//    private Fast3NotifyRequest getPrinterNoticeRequest() {
//        Fast3NotifyRequest noticeRequest = new Fast3NotifyRequest();
//        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
//        String uid = UserInfo.getInstance().getUid();
//        noticeRequest.setAccountId(Integer.valueOf(uid));
//        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
//        Fast3NotifyRequest.DataBean data = new Fast3NotifyRequest.DataBean();
//        Fast3NotifyRequest.DataBean.PrinterInfoBean printerInfo = new Fast3NotifyRequest.DataBean.PrinterInfoBean();
//        printerInfo.setGameId(gameListBean.getId());
//        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
//        printerInfo.setDrawNumber(drawListBean.getDrawNumber());
//        printerInfo.setGameAlias(drawListBean.getGameAlias());
//        printerInfo.setOrderCode(commitResponse.getOrderCode());
//        data.setPrinterInfo(printerInfo);
//        data.setPrinterInfo(printerInfo);
//        noticeRequest.setData(data);
//        return noticeRequest;
//    }

    @Override
    public SelectedBallInfo selectOneByMachine() {
        int[] randomIntRed = RandomUtil.getRandomInt(1, 6, 3);
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.hidePlusSymbol();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 3; j++) {
            sb.append(randomIntRed[j]);
        }
        sbiSelectBallInfo.addRedBallByString(sb.toString());

        sbiSelectBallInfo.setBetMode(Constants.BET_MODE_SINGLE);
        sbiSelectBallInfo.setPerCost(gameRule.getR007());
        sbiSelectBallInfo.setBetNum(1);
        return sbiSelectBallInfo;
    }

    @Override
    public List<SelectedBallInfo> insertToSelectedArea() {
        List<SelectedBallInfo> infoArrayList = new ArrayList<>();
        List<Integer> boxes = getBoxSelectNumList();
        int size = boxes.size();
        if (size < 3) {
            ToastUtil.newToast(getContext(), "三不同号请至少选择3个号码");
            return null;
        }
        // 单复式格式相同
        SelectedBallInfo sbiSelectBallInfo = new SelectedBallInfo(getContext());
        sbiSelectBallInfo.setChangeFormat(false);
        sbiSelectBallInfo.hidePlusSymbol();

        List<List<Integer>> threeZuhe = NUtil.getThreeZuhe(boxes);
        int size1 = threeZuhe.size();
        for (int i = 0; i < size1; i++) {
            StringBuilder sb = new StringBuilder();
            List<Integer> integers = threeZuhe.get(i);
            for (int j = 0; j < integers.size(); j++) {
                sb.append(integers.get(j));
            }
            sbiSelectBallInfo.addRedBallByString(sb.toString());
        }

        String betMode = size == 3 ? Constants.BET_MODE_SINGLE : Constants.BET_MODE_DOUBLE;
        sbiSelectBallInfo.setBetMode(betMode);
        sbiSelectBallInfo.setPerCost(gameRule.getR007() * size1);
        sbiSelectBallInfo.setBetNum(size1);
        infoArrayList.add(sbiSelectBallInfo);
        resetWaitArea();
        return infoArrayList;
    }

    /**
     * 得到已经选中的盒子数字
     *
     * @return
     */
    private List<Integer> getBoxSelectNumList() {
        int sizeBox = statusListBox.size();
        List<Integer> selectedBoxList = new ArrayList<>();
        for (int i = 0; i < sizeBox; i++) {
            CircleBtStatus circleBtStatus = statusListBox.get(i);
            if (circleBtStatus.getIsPressed()) {
                selectedBoxList.add(circleBtStatus.getNum());
            }
        }
        return selectedBoxList;
    }

    @Override
    public void resetWaitArea() {
        for (CircleBtStatus status : statusListBox) {
            status.setIsPressed(false);
        }
        adapterBox.notifyDataSetChanged();
    }

//    @Override
//    public EscCommand print() {
//        if (commitResponse == null) return new EscCommand();
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
//        Fast3CommitResponse.DataBean.OrderInfoBean orderInfo = commitResponse.getData().getOrderInfo();
//        List<Fast3CommitResponse.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();
//
//        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
//        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(drawListBean.getEndTime()) + "开奖\n");
//        esc.addText("1100115-807700-074994-357206 806525\n");
//        esc.addSetAbsolutePrintPosition((short) 10);
//
//        esc.addText("-------------------------------------\n");
//        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
//        esc.addText("三不同号\n");
//        esc.addPrintAndLineFeed();
//
//        int size = ticketList.size();
//        for (int j = 0; j < size; j++) {
//            Fast3CommitResponse.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
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
//        esc.addStoreQRCodeData(commitResponse.getOrderCode());
//        esc.addPrintQRCode();// 打印QRCode
//        esc.addPrintAndLineFeed();
//
//        esc.addPrintAndFeedLines((byte) 6);
//        esc.addCutPaper();
//        esc.addQueryPrinterStatus();
//        return esc;
//    }

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

    @Override
    protected List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList() {
        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketListBean = new ArrayList<>();
        int childCount = llAllBallInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            SelectedBallInfo selectedBallInfo = (SelectedBallInfo) llAllBallInfo.getChildAt(i);
            List<String> redNumList = selectedBallInfo.getRedNumList();

            // 单式和复式
            int sizeR = redNumList.size();
            for (int j = 0; j < sizeR; j++) {
                String str = redNumList.get(j);
                for (int k = 0; k < 3; k++) {
                    String substring = str.substring(k, k + 1);
                    if (!stringBuilder.toString().contains(substring)) {
                        stringBuilder.append(substring + " ");
                    }
                }
                if (j == sizeR - 1) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
            Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean bean = new Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean();
            bean.setTicket(stringBuilder.toString());
            bean.setEachBetMode(selectedBallInfo.getBetMode());
            bean.setEachTotalMoney(selectedBallInfo.getPerCost() + "");
            ticketListBean.add(bean);
        }
        return ticketListBean;
    }
}