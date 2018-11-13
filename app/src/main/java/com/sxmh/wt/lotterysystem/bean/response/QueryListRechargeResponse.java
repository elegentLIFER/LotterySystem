package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class QueryListRechargeResponse {

    /**
     * code : 00000
     * data : {"ListRecharge":{"depositNum":"","depositType":"","pageNo":"0","paymentId":"","phone":"","userId":""}}
     * message : 充值信息查询成功!
     * pageCount : 2
     * pageNum : 5
     * paymentList : [{"depositMoney":1,"depositNum":"1","depositType":"00","id":25,"paymentId":"20180417095900389560","paymentState":"00","phone":"1","terminalId":0,"userId":1,"userName":"admin"},{"depositMoney":1,"depositNum":"1","depositType":"00","id":24,"paymentId":"20180417095900329209","paymentState":"00","phone":"1","terminalId":0,"userId":1,"userName":"admin"},{"depositMoney":1,"depositNum":"1","depositType":"00","id":23,"paymentId":"20180417095900186191","paymentState":"00","phone":"1","terminalId":0,"userId":1,"userName":"admin"},{"depositMoney":1,"depositNum":"1","depositType":"01","id":22,"paymentId":"20180417092600463625","paymentState":"00","phone":"1","terminalId":0,"userId":1,"userName":"admin"},{"depositMoney":1,"depositNum":"1","depositType":"01","id":21,"paymentId":"20180417091700365750","paymentState":"00","phone":"1","terminalId":0,"userId":1,"userName":"admin"}]
     * state : 00
     * totalNum : 6
     */

    private String code;
    private DataBean data;
    private String message;
    private String pageCount;
    private String pageNum;
    private String state;
    private String totalNum;
    private List<PaymentListBean> paymentList;

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

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public List<PaymentListBean> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentListBean> paymentList) {
        this.paymentList = paymentList;
    }

    public static class DataBean {
        /**
         * ListRecharge : {"depositNum":"","depositType":"","pageNo":"0","paymentId":"","phone":"","userId":""}
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
             * depositNum :
             * depositType :
             * pageNo : 0
             * paymentId :
             * phone :
             * userId :
             */

            private String depositNum;
            private String depositType;
            private String pageNo;
            private String paymentId;
            private String phone;
            private String userId;

            public String getDepositNum() {
                return depositNum;
            }

            public void setDepositNum(String depositNum) {
                this.depositNum = depositNum;
            }

            public String getDepositType() {
                return depositType;
            }

            public void setDepositType(String depositType) {
                this.depositType = depositType;
            }

            public String getPageNo() {
                return pageNo;
            }

            public void setPageNo(String pageNo) {
                this.pageNo = pageNo;
            }

            public String getPaymentId() {
                return paymentId;
            }

            public void setPaymentId(String paymentId) {
                this.paymentId = paymentId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }

    public static class PaymentListBean {
        /**
         * depositMoney : 1
         * depositNum : 1
         * depositType : 00
         * id : 25
         * paymentId : 20180417095900389560
         * paymentState : 00
         * phone : 1
         * terminalId : 0
         * userId : 1
         * userName : admin
         */

        private int depositMoney;
        private String depositNum;
        private String depositType;
        private int id;
        private String paymentId;
        private String paymentState;
        private String phone;
        private int terminalId;
        private int userId;
        private String userName;

        public int getDepositMoney() {
            return depositMoney;
        }

        public void setDepositMoney(int depositMoney) {
            this.depositMoney = depositMoney;
        }

        public String getDepositNum() {
            return depositNum;
        }

        public void setDepositNum(String depositNum) {
            this.depositNum = depositNum;
        }

        public String getDepositType() {
            return depositType;
        }

        public void setDepositType(String depositType) {
            this.depositType = depositType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getPaymentState() {
            return paymentState;
        }

        public void setPaymentState(String paymentState) {
            this.paymentState = paymentState;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getTerminalId() {
            return terminalId;
        }

        public void setTerminalId(int terminalId) {
            this.terminalId = terminalId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
