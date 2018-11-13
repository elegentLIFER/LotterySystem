package com.sxmh.wt.lotterysystem.bean.request;

public class DoubleBallNotifyRequest {

    /**
     * interfaceCode : ssqPrinter
     * requestTime : 1455606858
     * accountId : 1
     * data : {"printerInfo":{"orderCode":"20180801105800544805","gameId":152,"drawNumber":"2018089","gameAlias":"ssq"}}
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
         * printerInfo : {"orderCode":"20180801105800544805","gameId":152,"drawNumber":"2018089","gameAlias":"ssq"}
         */

        private PrinterInfoBean printerInfo;

        public PrinterInfoBean getPrinterInfo() {
            return printerInfo;
        }

        public void setPrinterInfo(PrinterInfoBean printerInfo) {
            this.printerInfo = printerInfo;
        }

        public static class PrinterInfoBean {
            /**
             * orderCode : 20180801105800544805
             * gameId : 152
             * drawNumber : 2018089
             * gameAlias : ssq
             */

            private String orderCode;
            private int gameId;
            private String drawNumber;
            private String gameAlias;

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getGameId() {
                return gameId;
            }

            public void setGameId(int gameId) {
                this.gameId = gameId;
            }

            public String getDrawNumber() {
                return drawNumber;
            }

            public void setDrawNumber(String drawNumber) {
                this.drawNumber = drawNumber;
            }

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }
        }
    }
}
