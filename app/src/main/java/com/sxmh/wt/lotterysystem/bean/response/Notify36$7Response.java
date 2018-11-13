package com.sxmh.wt.lotterysystem.bean.response;

public class Notify36$7Response {

    /**
     * code : 00000
     * data : {"printerInfo":{"drawNumber":"2018122","gameAlias":"36x7","orderCode":"201810171604005228581052"}}
     * message : 出票成功!
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * printerInfo : {"drawNumber":"2018122","gameAlias":"36x7","orderCode":"201810171604005228581052"}
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
             * drawNumber : 2018122
             * gameAlias : 36x7
             * orderCode : 201810171604005228581052
             */

            private String drawNumber;
            private String gameAlias;
            private String orderCode;

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

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }
        }
    }
}
