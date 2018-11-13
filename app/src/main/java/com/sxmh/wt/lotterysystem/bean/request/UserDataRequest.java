package com.sxmh.wt.lotterysystem.bean.request;

public class UserDataRequest {

    /**
     * interfaceCode :
     * requestTime : 1450869189960
     * data : {"userInfo":{"terminalId":32}}
     */

    private String interfaceCode;
    private long requestTime;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userInfo : {"terminalId":32}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * terminalId : 32
             */

            private int terminalId;

            public int getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(int terminalId) {
                this.terminalId = terminalId;
            }
        }
    }
}
