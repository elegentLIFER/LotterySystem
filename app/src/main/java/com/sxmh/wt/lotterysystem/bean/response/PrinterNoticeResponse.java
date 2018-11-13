package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class PrinterNoticeResponse {

    /**
     * betStatus : 00
     * code : 00000
     * data : {"initPrinterNoticePayReq":{"orderCode":"20180419152500485928"}}
     * message : 出票成功!
     * state : 00
     * winState : 02
     */

    private String betStatus;
    private String code;
    private DataBean data;
    private String message;
    private String state;
    private String winState;

    public String getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(String betStatus) {
        this.betStatus = betStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWinState() {
        return winState;
    }

    public void setWinState(String winState) {
        this.winState = winState;
    }

    public static class DataBean {
        /**
         * initPrinterNoticePayReq : {"orderCode":"20180419152500485928"}
         */

        private InitPrinterNoticePayReqBean initPrinterNoticePayReq;

        public InitPrinterNoticePayReqBean getInitPrinterNoticePayReq() {
            return initPrinterNoticePayReq;
        }

        public void setInitPrinterNoticePayReq(InitPrinterNoticePayReqBean initPrinterNoticePayReq) {
            this.initPrinterNoticePayReq = initPrinterNoticePayReq;
        }

        public static class InitPrinterNoticePayReqBean {
            /**
             * orderCode : 20180419152500485928
             */

            private String orderCode;

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }
        }
    }
}
