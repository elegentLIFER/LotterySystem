package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class CashRequest extends BaseRequest {

    /**
     * interfaceCode : terminalCashPrize
     * requestTime : 1455606858
     * accountId : 1
     * data : {"cashPrizeInfo":{"orderCode":"20180413164900444793"}}
     */

    private String interfaceCode;
    private String requestTime;
    private String accountId;
    private DataBean data;

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
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

    public static class DataBean {
        /**
         * cashPrizeInfo : {"orderCode":"20180413164900444793"}
         */

        private CashPrizeInfoBean cashPrizeInfo;

        public CashPrizeInfoBean getCashPrizeInfo() {
            return cashPrizeInfo;
        }

        public void setCashPrizeInfo(CashPrizeInfoBean cashPrizeInfo) {
            this.cashPrizeInfo = cashPrizeInfo;
        }

        public static class CashPrizeInfoBean {
            /**
             * orderCode : 20180413164900444793
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
