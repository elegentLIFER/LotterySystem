package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class QueryListRechargeRequest extends BaseRequest {

    /**
     * interfaceCode : queryListRecharge
     * requestTime : 1455606858
     * accountId : 1
     * data : {"ListRecharge":{"paymentId":"","depositType":"","depositNum":"","userId":"","phone":"","pageNo":"0"}}
     */

    private String interfaceCode;
    private int requestTime;
    private String accountId;
    private DataBean data;

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

    public static class DataBean {
        /**
         * ListRecharge : {"paymentId":"","depositType":"","depositNum":"","userId":"","phone":"","pageNo":"0"}
         */

        private ListRechargeBean ListRecharge;

        public ListRechargeBean getListRecharge() {
            return ListRecharge;
        }

        public void setListRecharge(ListRechargeBean ListRecharge) {
            this.ListRecharge = ListRecharge;
        }

        public static class ListRechargeBean {
            /**
             * paymentId :
             * depositType :
             * depositNum :
             * userId :
             * phone :
             * pageNo : 0
             */

            private String paymentId;
            private String depositType;
            private String depositNum;
            private String userId;
            private String phone;
            private String pageNo;

            public String getPaymentId() {
                return paymentId;
            }

            public void setPaymentId(String paymentId) {
                this.paymentId = paymentId;
            }

            public String getDepositType() {
                return depositType;
            }

            public void setDepositType(String depositType) {
                this.depositType = depositType;
            }

            public String getDepositNum() {
                return depositNum;
            }

            public void setDepositNum(String depositNum) {
                this.depositNum = depositNum;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPageNo() {
                return pageNo;
            }

            public void setPageNo(String pageNo) {
                this.pageNo = pageNo;
            }
        }
    }
}
