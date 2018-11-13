package com.sxmh.wt.lotterysystem.bean.request;

public class ActivationRequest {

    /**
     * interfaceCode :
     * requestTime : 1455606858805
     * userId : 1234567890
     * data : {"terminalInfo":{"numbercard":"00","userName":"00","passWord":"00"}}
     */

    private String interfaceCode;
    private long requestTime;
    private String userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
         * terminalInfo : {"numbercard":"00","userName":"00","passWord":"00"}
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
             * numbercard : 00
             * userName : 00
             * passWord : 00
             */

            private String numbercard;
            private String userName;
            private String passWord;

            public String getNumbercard() {
                return numbercard;
            }

            public void setNumbercard(String numbercard) {
                this.numbercard = numbercard;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPassWord() {
                return passWord;
            }

            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }
        }


    }


}
