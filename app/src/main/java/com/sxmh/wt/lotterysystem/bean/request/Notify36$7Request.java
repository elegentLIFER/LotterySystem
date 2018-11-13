package com.sxmh.wt.lotterysystem.bean.request;

public class Notify36$7Request {

    /**
     * interfaceCode : 36Selection7Printer
     * requestTime : 1455606858
     * accountName : wg
     * data : {"printerInfo":{"orderCode":"201810171604005228581052","drawNumber":"2018122","gameAlias":"36x7"}}
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
         * printerInfo : {"orderCode":"201810171604005228581052","drawNumber":"2018122","gameAlias":"36x7"}
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
             * orderCode : 201810171604005228581052
             * drawNumber : 2018122
             * gameAlias : 36x7
             */

            private String orderCode;
            private String drawNumber;
            private String gameAlias;

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
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
