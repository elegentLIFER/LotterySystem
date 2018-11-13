package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class CashInfoResponse {

    /**
     * cashPrizeList : [{"DRAWID":16164,"ID":5978614,"MULTIDRAW":"1","betNum":"10 11 18 26 27 28 33","buyTime":1539757024000,"drawNumber":"2018121","gameName":"36选7","keyStore":"","prizeTime":1539698400000,"qrCode":"115465465465464561","realpayMoney":"20","safetyCode":"43F52BAE-EE1546FD-59CDD3A0-BC9BE95B","terminalNum":"222","winAmount":"50","winMoney":"50","winState":"01"}]
     * code : 00000
     * data : {"orderInfo":{"gameAlias":"36x7","orderCode":"201810171417000426939354"}}
     * message : 兑奖查询成功!
     * state : 00
     * winList : [{"winLevel":5,"winMoney":50,"winNum":1}]
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;
    private List<CashPrizeListBean> cashPrizeList;
    private List<WinListBean> winList;

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

    public List<CashPrizeListBean> getCashPrizeList() {
        return cashPrizeList;
    }

    public void setCashPrizeList(List<CashPrizeListBean> cashPrizeList) {
        this.cashPrizeList = cashPrizeList;
    }

    public List<WinListBean> getWinList() {
        return winList;
    }

    public void setWinList(List<WinListBean> winList) {
        this.winList = winList;
    }

    public static class DataBean {
        /**
         * orderInfo : {"gameAlias":"36x7","orderCode":"201810171417000426939354"}
         */

        private OrderInfoBean orderInfo;

        public OrderInfoBean getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoBean orderInfo) {
            this.orderInfo = orderInfo;
        }

        public static class OrderInfoBean {
            /**
             * gameAlias : 36x7
             * orderCode : 201810171417000426939354
             */

            private String gameAlias;
            private String orderCode;

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

    public static class CashPrizeListBean {
        /**
         * DRAWID : 16164
         * ID : 5978614
         * MULTIDRAW : 1
         * betNum : 10 11 18 26 27 28 33
         * buyTime : 1539757024000
         * drawNumber : 2018121
         * gameName : 36选7
         * keyStore :
         * prizeTime : 1539698400000
         * qrCode : 115465465465464561
         * realpayMoney : 20
         * safetyCode : 43F52BAE-EE1546FD-59CDD3A0-BC9BE95B
         * terminalNum : 222
         * winAmount : 50
         * winMoney : 50
         * winState : 01
         */

        private int DRAWID;
        private int ID;
        private String MULTIDRAW;
        private String betNum;
        private long buyTime;
        private String drawNumber;
        private String gameName;
        private String keyStore;
        private long prizeTime;
        private String qrCode;
        private String realpayMoney;
        private String safetyCode;
        private String terminalNum;
        private String winAmount;
        private String winMoney;
        private String winState;

        public int getDRAWID() {
            return DRAWID;
        }

        public void setDRAWID(int DRAWID) {
            this.DRAWID = DRAWID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getMULTIDRAW() {
            return MULTIDRAW;
        }

        public void setMULTIDRAW(String MULTIDRAW) {
            this.MULTIDRAW = MULTIDRAW;
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

        public String getDrawNumber() {
            return drawNumber;
        }

        public void setDrawNumber(String drawNumber) {
            this.drawNumber = drawNumber;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getKeyStore() {
            return keyStore;
        }

        public void setKeyStore(String keyStore) {
            this.keyStore = keyStore;
        }

        public long getPrizeTime() {
            return prizeTime;
        }

        public void setPrizeTime(long prizeTime) {
            this.prizeTime = prizeTime;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getRealpayMoney() {
            return realpayMoney;
        }

        public void setRealpayMoney(String realpayMoney) {
            this.realpayMoney = realpayMoney;
        }

        public String getSafetyCode() {
            return safetyCode;
        }

        public void setSafetyCode(String safetyCode) {
            this.safetyCode = safetyCode;
        }

        public String getTerminalNum() {
            return terminalNum;
        }

        public void setTerminalNum(String terminalNum) {
            this.terminalNum = terminalNum;
        }

        public String getWinAmount() {
            return winAmount;
        }

        public void setWinAmount(String winAmount) {
            this.winAmount = winAmount;
        }

        public String getWinMoney() {
            return winMoney;
        }

        public void setWinMoney(String winMoney) {
            this.winMoney = winMoney;
        }

        public String getWinState() {
            return winState;
        }

        public void setWinState(String winState) {
            this.winState = winState;
        }
    }

    public static class WinListBean {
        /**
         * winLevel : 5
         * winMoney : 50
         * winNum : 1
         */

        private int winLevel;
        private int winMoney;
        private int winNum;

        public int getWinLevel() {
            return winLevel;
        }

        public void setWinLevel(int winLevel) {
            this.winLevel = winLevel;
        }

        public int getWinMoney() {
            return winMoney;
        }

        public void setWinMoney(int winMoney) {
            this.winMoney = winMoney;
        }

        public int getWinNum() {
            return winNum;
        }

        public void setWinNum(int winNum) {
            this.winNum = winNum;
        }
    }
}
