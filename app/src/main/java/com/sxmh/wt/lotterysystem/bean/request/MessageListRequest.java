package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class MessageListRequest extends BaseRequest {

    /**
     * interfaceCode :
     * requestTime : 1455606858805
     * userId  : 1455606858805
     * data : {"terminalInfo":{"terminalId":"00","current_page":"00","status":"0","num":"00"}}
     */

    private String interfaceCode;
    private long requestTime;
    private long userId;
    private DataBean data;

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * terminalInfo : {"terminalId":"00","current_page":"00","status":"0","num":"00"}
         */

        private TerminalInfoBean terminalInfo;

        public TerminalInfoBean getTerminalInfo() {
            return terminalInfo;
        }

        public void setTerminalInfo(TerminalInfoBean terminalInfo) {
            this.terminalInfo = terminalInfo;
        }

        public static class TerminalInfoBean {
            /**
             * terminalId : 00
             * current_page : 00
             * status : 0
             * num : 00
             */

            private String terminalId;
            private String current_page;
            private String status;
            private String num;

            public String getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(String terminalId) {
                this.terminalId = terminalId;
            }

            public String getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(String current_page) {
                this.current_page = current_page;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
