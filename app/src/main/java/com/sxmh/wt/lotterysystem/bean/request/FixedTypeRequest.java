package com.sxmh.wt.lotterysystem.bean.request;

public class FixedTypeRequest {

    /**
     * interfaceCode : selectGameAdd
     * requestTime : 1455606858
     * accountName : wg
     * data : {"gameInfo":{"gameAlias":"kl8"}}
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
         * gameInfo : {"gameAlias":"kl8"}
         */

        private GameInfoBean gameInfo;

        public GameInfoBean getGameInfo() {
            return gameInfo;
        }

        public void setGameInfo(GameInfoBean gameInfo) {
            this.gameInfo = gameInfo;
        }

        public static class GameInfoBean {
            /**
             * gameAlias : kl8
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
