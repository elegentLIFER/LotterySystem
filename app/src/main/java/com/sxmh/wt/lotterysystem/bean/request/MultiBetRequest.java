package com.sxmh.wt.lotterysystem.bean.request;

public class MultiBetRequest {

    /**
     * interfaceCode : findMultiphase
     * requestTime : 1534747080
     * accountId : 1
     * data : {"drawInfo":{"gameId":"152"}}
     */

    private String interfaceCode;
    private int requestTime;
    private int accountId;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
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
         * drawInfo : {"gameId":"152"}
         */

        private DrawInfoBean drawInfo;

        public DrawInfoBean getDrawInfo() {
            return drawInfo;
        }

        public void setDrawInfo(DrawInfoBean drawInfo) {
            this.drawInfo = drawInfo;
        }

        public static class DrawInfoBean {
            /**
             * gameId : 152
             */

            private String gameId;

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }
        }
    }
}
