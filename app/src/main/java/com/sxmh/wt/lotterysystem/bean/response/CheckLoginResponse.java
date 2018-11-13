package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class CheckLoginResponse {


    /**
     * code : 00000
     * data : {"shopOrderInfo":{"colorPeriod":"2018042","colorPeriodId":"54","multiple":"1","noteNumber":"1","numberPeriods":"1","palyType":"152","terminal":"111","terminalId":"2","ticket":"01 02 03 04 05 12 22|10,01 02 05 08 09 12 20|15","totalMoney":"400"}}
     * message : 终端下注成功!
     * orderCode : 20180418185100243405
     * payState : 00
     * qrCode : 115465465465464561
     * state : 00
     * stateInjection : 02
     */

    private String code;
    private DataBean data;
    private String message;
    private String orderCode;
    private String payState;
    private String qrCode;
    private String state;
    private String stateInjection;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateInjection() {
        return stateInjection;
    }

    public void setStateInjection(String stateInjection) {
        this.stateInjection = stateInjection;
    }

    public static class DataBean {
        /**
         * shopOrderInfo : {"colorPeriod":"2018042","colorPeriodId":"54","multiple":"1","noteNumber":"1","numberPeriods":"1","palyType":"152","terminal":"111","terminalId":"2","ticket":"01 02 03 04 05 12 22|10,01 02 05 08 09 12 20|15","totalMoney":"400"}
         */

        private ShopOrderInfoBean shopOrderInfo;

        public ShopOrderInfoBean getShopOrderInfo() {
            return shopOrderInfo;
        }

        public void setShopOrderInfo(ShopOrderInfoBean shopOrderInfo) {
            this.shopOrderInfo = shopOrderInfo;
        }

        public static class ShopOrderInfoBean {
            /**
             * colorPeriod : 2018042
             * colorPeriodId : 54
             * multiple : 1
             * noteNumber : 1
             * numberPeriods : 1
             * palyType : 152
             * terminal : 111
             * terminalId : 2
             * ticket : 01 02 03 04 05 12 22|10,01 02 05 08 09 12 20|15
             * totalMoney : 400
             */

            private String colorPeriod;
            private String colorPeriodId;
            private String multiple;
            private String noteNumber;
            private String numberPeriods;
            private String palyType;
            private String terminal;
            private String terminalId;
            private String ticket;
            private String totalMoney;

            public String getColorPeriod() {
                return colorPeriod;
            }

            public void setColorPeriod(String colorPeriod) {
                this.colorPeriod = colorPeriod;
            }

            public String getColorPeriodId() {
                return colorPeriodId;
            }

            public void setColorPeriodId(String colorPeriodId) {
                this.colorPeriodId = colorPeriodId;
            }

            public String getMultiple() {
                return multiple;
            }

            public void setMultiple(String multiple) {
                this.multiple = multiple;
            }

            public String getNoteNumber() {
                return noteNumber;
            }

            public void setNoteNumber(String noteNumber) {
                this.noteNumber = noteNumber;
            }

            public String getNumberPeriods() {
                return numberPeriods;
            }

            public void setNumberPeriods(String numberPeriods) {
                this.numberPeriods = numberPeriods;
            }

            public String getPalyType() {
                return palyType;
            }

            public void setPalyType(String palyType) {
                this.palyType = palyType;
            }

            public String getTerminal() {
                return terminal;
            }

            public void setTerminal(String terminal) {
                this.terminal = terminal;
            }

            public String getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(String terminalId) {
                this.terminalId = terminalId;
            }

            public String getTicket() {
                return ticket;
            }

            public void setTicket(String ticket) {
                this.ticket = ticket;
            }

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }
        }
    }
}