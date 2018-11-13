package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

public class BetHistoryResponse {

    /**
     * bettingList : [{"betDouble":1,"betMode":"01","betNum":"01 08 10 11 20 25 27","buyTime":1539757154000,"compoundStatus":"00","drawNumber":"2018122","frisbeeStatus":"00","gameName":"36选7","multiDraw":"1","noteNumber":1,"orderId":"201810171419001493042837","orderMoney":"20"},{"betDouble":1,"betMode":"01","betNum":"02 06 11 16 21 22 36","buyTime":1539757154000,"compoundStatus":"00","drawNumber":"2018122","frisbeeStatus":"00","gameName":"36选7","multiDraw":"1","noteNumber":1,"orderId":"201810171419001455860593","orderMoney":"20"}]
     * code : 00000
     * data : {"bettingInfo":{"gameAlias":"36x7"}}
     * message : 终端历史投注信息查询 成功!
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;
    private List<BettingListBean> bettingList;

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

    public List<BettingListBean> getBettingList() {
        return bettingList;
    }

    public void setBettingList(List<BettingListBean> bettingList) {
        this.bettingList = bettingList;
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

    public static class BettingListBean {
        /**
         * betDouble : 1
         * betMode : 01
         * betNum : 01 08 10 11 20 25 27
         * buyTime : 1539757154000
         * compoundStatus : 00
         * drawNumber : 2018122
         * frisbeeStatus : 00
         * gameName : 36选7
         * multiDraw : 1
         * noteNumber : 1
         * orderId : 201810171419001493042837
         * orderMoney : 20
         */

        private int betDouble;
        private String betMode;
        private String betNum;
        private long buyTime;
        private String compoundStatus;
        private String drawNumber;
        private String frisbeeStatus;
        private String gameName;
        private String multiDraw;
        private int noteNumber;
        private String orderId;
        private String orderMoney;

        public int getBetDouble() {
            return betDouble;
        }

        public void setBetDouble(int betDouble) {
            this.betDouble = betDouble;
        }

        public String getBetMode() {
            return betMode;
        }

        public void setBetMode(String betMode) {
            this.betMode = betMode;
        }

        public String getBetNum() {
            return betNum;
        }

        public void setBetNum(String betNum) {
            this.betNum = betNum;
        }

        public long getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(long buyTime) {
            this.buyTime = buyTime;
        }

        public String getCompoundStatus() {
            return compoundStatus;
        }

        public void setCompoundStatus(String compoundStatus) {
            this.compoundStatus = compoundStatus;
        }

        public String getDrawNumber() {
            return drawNumber;
        }

        public void setDrawNumber(String drawNumber) {
            this.drawNumber = drawNumber;
        }

        public String getFrisbeeStatus() {
            return frisbeeStatus;
        }

        public void setFrisbeeStatus(String frisbeeStatus) {
            this.frisbeeStatus = frisbeeStatus;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getMultiDraw() {
            return multiDraw;
        }

        public void setMultiDraw(String multiDraw) {
            this.multiDraw = multiDraw;
        }

        public int getNoteNumber() {
            return noteNumber;
        }

        public void setNoteNumber(int noteNumber) {
            this.noteNumber = noteNumber;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(String orderMoney) {
            this.orderMoney = orderMoney;
        }
    }
}
