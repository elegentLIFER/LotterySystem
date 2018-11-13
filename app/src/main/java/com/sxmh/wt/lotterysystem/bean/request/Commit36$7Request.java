package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class Commit36$7Request extends BaseRequest {

    /**
     * interfaceCode : createOrder
     * requestTime : 1539762840
     * accountName : wg
     * data : {"orderInfo":{"gameAlias":"36x7","ticketList":[{"ticket":"01 02 03 04 05 12 22","eachTotalMoney":"20","eachBetMode":"01"},{"ticket":"01 02 05 08 09 12 20","eachTotalMoney":"20","eachBetMode":"01"}],"terminal":"ZDMHI1539326614","multiDraw":"1","betDouble":"1","noteNumber":"2","totalMoney":"40","betMode":"01","dataSource":"0","drawNumber":"2018122"}}
     * sign : 9123E21B67F2E8534BC191F4F1AC8AF8
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
         * orderInfo : {"gameAlias":"36x7","ticketList":[{"ticket":"01 02 03 04 05 12 22","eachTotalMoney":"20","eachBetMode":"01"},{"ticket":"01 02 05 08 09 12 20","eachTotalMoney":"20","eachBetMode":"01"}],"terminal":"ZDMHI1539326614","multiDraw":"1","betDouble":"1","noteNumber":"2","totalMoney":"40","betMode":"01","dataSource":"0","drawNumber":"2018122"}
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
             * ticketList : [{"ticket":"01 02 03 04 05 12 22","eachTotalMoney":"20","eachBetMode":"01"},{"ticket":"01 02 05 08 09 12 20","eachTotalMoney":"20","eachBetMode":"01"}]
             * terminal : ZDMHI1539326614
             * multiDraw : 1
             * betDouble : 1
             * noteNumber : 2
             * totalMoney : 40
             * betMode : 01
             * dataSource : 0
             * drawNumber : 2018122
             */

            private String gameAlias;
            private String terminal;
            private String multiDraw;
            private String betDouble;
            private String noteNumber;
            private String totalMoney;
            private String betMode;
            private String dataSource;
            private String drawNumber;
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

            public String getDataSource() {
                return dataSource;
            }

            public void setDataSource(String dataSource) {
                this.dataSource = dataSource;
            }

            public String getDrawNumber() {
                return drawNumber;
            }

            public void setDrawNumber(String drawNumber) {
                this.drawNumber = drawNumber;
            }

            public List<TicketListBean> getTicketList() {
                return ticketList;
            }

            public void setTicketList(List<TicketListBean> ticketList) {
                this.ticketList = ticketList;
            }

            public static class TicketListBean {
                /**
                 * ticket : 01 02 03 04 05 12 22
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
