package com.sxmh.wt.lotterysystem.bean.request;

public class BetHistoryRequest {

    /**
     * interfaceCode : historyBettingQuery
     * requestTime : 1534228500
     * accountName : wg
     * data : {"bettingInfo":{"gameAlias":"36x7"}}
     */

    private String interfaceCode;
    private int requestTime;
    private String accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bettingInfo : {"gameAlias":"36x7"}
         */

        private BettingInfoBean bettingInfo;

        public BettingInfoBean getBettingInfo() {
            return bettingInfo;
        }

        public void setBettingInfo(BettingInfoBean bettingInfo) {
            this.bettingInfo = bettingInfo;
        }

        public static class BettingInfoBean {
            /**
             * gameAlias : 36x7
             */

            private String gameAlias;

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }
        }
    }
}
