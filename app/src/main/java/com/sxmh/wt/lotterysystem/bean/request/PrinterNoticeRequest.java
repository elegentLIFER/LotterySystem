package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class PrinterNoticeRequest extends BaseRequest {

    /**
     * interfaceCode : printerNotice
     * requestTime : 1455606858
     * accountId : 1
     * data : {"initPrinterNoticePayReq":{"orderCode":"20180419152500485928"}}
     * sign : 6D9B00C2F6CD4EB0921527432A632031
     */

    private String interfaceCode;
    private int requestTime;
    private String accountId;
    private DataBean data;
    private String sign;

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(int requestTime) {
        this.requestTime = requestTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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
