package com.sxmh.wt.lotterysystem.fragment.salelottery.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gprinter.command.EscCommand;
import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.base.BaseFragment;
import com.sxmh.wt.lotterysystem.bean.GameRule;
import com.sxmh.wt.lotterysystem.bean.InterfaceCode;
import com.sxmh.wt.lotterysystem.bean.NotOpenResponseCallBack;
import com.sxmh.wt.lotterysystem.bean.OnCommitSuccessListener;
import com.sxmh.wt.lotterysystem.bean.Strategy;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
import com.sxmh.wt.lotterysystem.bean.request.Fast3CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Fast3NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.response.Fast3CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.FixedTypeResponse;
import com.sxmh.wt.lotterysystem.bean.response.GameListQueryResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.fragment.main.SaleLotteryFragment;
import com.sxmh.wt.lotterysystem.fragment.salelottery.Fast3Fragment;
import com.sxmh.wt.lotterysystem.util.Constants;
import com.sxmh.wt.lotterysystem.util.LotteryUtil;
import com.sxmh.wt.lotterysystem.util.NUtil;
import com.sxmh.wt.lotterysystem.util.Net;
import com.sxmh.wt.lotterysystem.util.SignUtil;
import com.sxmh.wt.lotterysystem.util.TimeUtil;
import com.sxmh.wt.lotterysystem.util.ToastUtil;

import java.util.List;

public abstract class Fast3ChildBaseFragment extends BaseFragment implements NotOpenResponseCallBack, Strategy {
    protected LinearLayout llAllBallInfo;
    protected Fast3CommitResponse commitResponse;
    protected OnCommitSuccessListener onCommitSuccessListener;

    protected GameListQueryResponse.GameListBean gameListBean;
    protected FixedTypeResponse.GameAddListBean gameAddListBean;
    protected NotOpenQueryResponse notOpenQueryResponse;
    protected GameRule gameRule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initBox();

        getArgumentsData();
        return view;
    }

    private void getArgumentsData() {
        Bundle arguments = getArguments();
        gameListBean = (GameListQueryResponse.GameListBean) arguments.getSerializable(Fast3Fragment.GAME_LIST_BEAN);
        gameAddListBean = (FixedTypeResponse.GameAddListBean) arguments.getSerializable(Fast3Fragment.GAME_ADD_LIST_BEAN);
        gameRule = (GameRule) arguments.getSerializable(Fast3Fragment.GAME_RULE);
    }

    protected JSONObject getData(Fast3CommitRequest.DataBean.OrderInfoBean infoBean) {
        JSONObject data = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("gameAlias", infoBean.getGameAlias());

        JSONArray ticketList = new JSONArray();

        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> list = infoBean.getTicketList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean ticketListBean = list.get(i);
            JSONObject ticketMap = new JSONObject();
            ticketMap.put("ticket", ticketListBean.getTicket());
            ticketMap.put("eachTotalMoney", ticketListBean.getEachTotalMoney());
            ticketMap.put("eachBetMode", ticketListBean.getEachBetMode());
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
        orderInfo.put("dataSource", infoBean.getDataSource());
        orderInfo.put("gamePlayNum", infoBean.getGamePlayNum());
        data.put("orderInfo", orderInfo);
        return data;
    }

    @Override
    public void updateContent(int request, Object content) {
        if (request == Net.REQUEST_COMMIT_FAST_3) {
            commitResponse = (Fast3CommitResponse) content;
            net.k3Printer(getPrinterNoticeRequest());
            onCommitSuccessListener.OnCommitSuccess();
        } else if (request == Net.REQUEST_PRINTER_FAST3) {
            ToastUtil.newToast(getContext(), "打印通知成功");
        }
    }

    private Fast3NotifyRequest getPrinterNoticeRequest() {
        Fast3NotifyRequest noticeRequest = new Fast3NotifyRequest();
        noticeRequest.setRequestTime(TimeUtil.get10IntTimeStamp());
        noticeRequest.setAccountName(UserInfo.getInstance().getName());
        noticeRequest.setInterfaceCode(InterfaceCode.PL5_PRINTER);
        Fast3NotifyRequest.DataBean data = new Fast3NotifyRequest.DataBean();
        Fast3NotifyRequest.DataBean.PrinterInfoBean printerInfo = new Fast3NotifyRequest.DataBean.PrinterInfoBean();
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

        Fast3CommitResponse.DataBean.OrderInfoBean orderInfo = commitResponse.getData().getOrderInfo();
        esc.addText(orderInfo.getGameAlias() + "\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addPrintAndLineFeed();

        List<Fast3CommitResponse.DataBean.OrderInfoBean.TicketListBean> ticketList = orderInfo.getTicketList();

        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
        esc.addText("第 " + orderInfo.getDrawNumber() + " 期      " + NUtil.getFormatDate(drawListBean.getEndTime()) + "开奖\n");

        // TODO: 2018/10/18 0018 安全码暂时注释以便演示
//        esc.addText(commitResponse.getSafetyCode() + "\n");
//        esc.addSetAbsolutePrintPosition((short) 10);

        esc.addText("-------------------------------------\n");
        esc.addText(orderInfo.getBetDouble() + "倍   " + orderInfo.getMultiDraw() + "期      合计 " + orderInfo.getTotalMoney() + " 莫币\n");
        esc.addText(orderInfo.getDrawNumber() + "\n");
        esc.addPrintAndLineFeed();

        int size = ticketList.size();
        for (int j = 0; j < size; j++) {
            Fast3CommitResponse.DataBean.OrderInfoBean.TicketListBean bean = ticketList.get(j);
            String betMode = bean.getEachBetMode();
            esc.addText(LotteryUtil.getBetModeByNum(betMode) + " " + bean.getTicket() + "\n");
        }
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

    @Override
    public void commitLottery(LinearLayout llAllBallInfo, Bundle params) {
        this.llAllBallInfo = llAllBallInfo;
        net.commitFast3(getCommitFast3Request(params));
    }

    protected String getTotalBetMode(List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList) {
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

    @Override
    public void notifyResponse(NotOpenQueryResponse notOpenQueryResponse) {
        this.notOpenQueryResponse = notOpenQueryResponse;
    }

    private Fast3CommitRequest getCommitFast3Request(Bundle params) {
        Fast3CommitRequest fast3CommitRequest = new Fast3CommitRequest();
        fast3CommitRequest.setInterfaceCode(InterfaceCode.CREATE_ORDER);
        fast3CommitRequest.setAccountName(UserInfo.getInstance().getName());
        fast3CommitRequest.setRequestTime(TimeUtil.get10IntTimeStamp());

        Fast3CommitRequest.DataBean dataBean = new Fast3CommitRequest.DataBean();
        Fast3CommitRequest.DataBean.OrderInfoBean infoBean = new Fast3CommitRequest.DataBean.OrderInfoBean();

        infoBean.setGameAlias(Constants.GAME_ALIAS_K3);

        List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> ticketList = getTicketList();
        infoBean.setTicketList(ticketList);
        infoBean.setTerminal(UserInfo.getInstance().getEpnumbei());
        infoBean.setMultiDraw(params.getString(SaleLotteryFragment.MULTI_DRAW));
        infoBean.setBetDouble(params.getString(SaleLotteryFragment.BET_DOUBLE));
        infoBean.setNoteNumber(params.getString(SaleLotteryFragment.BET_NUM));
        infoBean.setTotalMoney(params.getString(SaleLotteryFragment.TOTAL_MONEY));
        infoBean.setBetMode(getTotalBetMode(ticketList));
        NotOpenQueryResponse.DrawListBean drawListBean = notOpenQueryResponse.getDrawList().get(0);
        infoBean.setDrawNumber(drawListBean.getDrawNumber());
        infoBean.setDataSource(Constants.DATA_SOURCE_ANDROID_END);
        infoBean.setGamePlayNum(gameAddListBean.getGamePlayNum());
        dataBean.setOrderInfo(infoBean);
        fast3CommitRequest.setData(dataBean);

        fast3CommitRequest.setSign(SignUtil.sign(getData(infoBean)));
        return fast3CommitRequest;
    }

    protected abstract List<Fast3CommitRequest.DataBean.OrderInfoBean.TicketListBean> getTicketList();

    public void setOnCommitSuccessListener(OnCommitSuccessListener onCommitSuccessListener) {
        this.onCommitSuccessListener = onCommitSuccessListener;
    }
}