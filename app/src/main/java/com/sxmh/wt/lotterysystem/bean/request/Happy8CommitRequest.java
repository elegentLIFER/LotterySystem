package com.sxmh.wt.lotterysystem.bean.request;

import java.util.List;

public class Happy8CommitRequest {

    /**
     * interfaceCode : createOrder
     * requestTime : 1539761820
     * accountName : wg
     * data : {"orderInfo":{"gameAlias":"kl8","ticketList":[{"ticket":"01 02 03 07","eachTotalMoney":"20","eachBetMode":"01"},{"ticket":"01 02 03 15","eachTotalMoney":"20","eachBetMode":"01"}],"terminal":"ZDMHI1539326614","multiDraw":"1","betDouble":"1","noteNumber":"2","totalMoney":"80","betMode":"01","drawNumber":"915759","frisbeeStatus":"01","dataSource":"0","gamePlayNum":"kl8004"}}
     * sign : AA5B31FA832551F5F220B2877AAD0F4C
     */

    private String interfaceCode;
    private int requestTime;
    private String accountName;
    private DataBean data;
    private String sign;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class DataBean {
        /**
         * orderInfo : {"gameAlias":"kl8","ticketList":[{"ticket":"01 02 03 07","eachTotalMoney":"20","eachBetMode":"01"},{"ticket":"01 02 03 15","eachTotalMoney":"20","eachBetMode":"01"}],"terminal":"ZDMHI1539326614","multiDraw":"1","betDouble":"1","noteNumber":"2","totalMoney":"80","betMode":"01","drawNumber":"915759","frisbeeStatus":"01","dataSource":"0","gamePlayNum":"kl8004"}
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
             * gameAlias : kl8
             * ticketList : [{"ticket":"01 02 03 07","eachTotalMoney":"20","eachBetMode":"01"},{"ticket":"01 02 03 15","eachTotalMoney":"20","eachBetMode":"01"}]
             * terminal : ZDMHI1539326614
             * multiDraw : 1
             * betDouble : 1
             * noteNumber : 2
             * totalMoney : 80
             * betMode : 01
             * drawNumber : 915759
             * frisbeeStatus : 01
             * dataSource : 0
             * gamePlayNum : kl8004
             */

            private String gameAlias;
            private String terminal;
            private String multiDraw;
            private String betDouble;
            private String noteNumber;
            private String totalMoney;
            private String betMode;
            private String drawNumber;
            private String frisbeeStatus;
            private String dataSource;
            private String gamePlayNum;
            private List<TicketListBean> ticketList;

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }

            public String getTerminal() {
                return terminal;
            }

            public void setTerminal(String terminal) {
                this.terminal = terminal;
            }

            public String getMultiDraw() {
                return multiDraw;
            }

            public void setMultiDraw(String multiDraw) {
                this.multiDraw = multiDraw;
            }

            public String getBetDouble() {
                return betDouble;
            }

            public void setBetDouble(String betDouble) {
                this.betDouble = betDouble;
            }

            public String getNoteNumber() {
                return noteNumber;
            }

            public void setNoteNumber(String noteNumber) {
                this.noteNumber = noteNumber;
            }

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }

            public String getBetMode() {
                return betMode;
            }

            public void setBetMode(String betMode) {
                this.betMode = betMode;
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

            public String getDataSource() {
                return dataSource;
            }

            public void setDataSource(String dataSource) {
                this.dataSource = dataSource;
            }

            public String getGamePlayNum() {
                return gamePlayNum;
            }

            public void setGamePlayNum(String gamePlayNum) {
                this.gamePlayNum = gamePlayNum;
            }

            public List<TicketListBean> getTicketList() {
                return ticketList;
            }

            public void setTicketList(List<TicketListBean> ticketList) {
                this.ticketList = ticketList;
            }

            public static class TicketListBean {
                /**
                 * ticket : 01 02 03 07
                 * eachTotalMoney : 20
                 * eachBetMode : 01
                 */

                private String ticket;
                private String eachTotalMoney;
                private String eachBetMode;

                public String getTicket() {
                    return ticket;
                }

                public void setTicket(String ticket) {
                    this.ticket = ticket;
                }

                public String getEachTotalMoney() {
                    return eachTotalMoney;
                }

                public void setEachTotalMoney(String eachTotalMoney) {
                    this.eachTotalMoney = eachTotalMoney;
                }

                public String getEachBetMode() {
                    return eachBetMode;
                }

                public void setEachBetMode(String eachBetMode) {
                    this.eachBetMode = eachBetMode;
                }
            }
        }
    }
}

