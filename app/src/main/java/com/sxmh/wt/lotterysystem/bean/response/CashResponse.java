package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class CashResponse {

    /**
     * code : 00000
     * data : {"cashPrizeInfo":{"orderCode":"20180413164900444793"}}
     * message : 终端兑奖 成功!
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;

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