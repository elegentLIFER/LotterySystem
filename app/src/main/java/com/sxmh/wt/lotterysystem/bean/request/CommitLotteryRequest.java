package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

import java.util.List;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class CommitLotteryRequest extends BaseRequest {

    /**
     * interfaceCode : createOrder
     * requestTime : 1534818960
     * accountId : 1
     * data : {"orderInfo":{"gameId":"152","gameAlias":"ssq","ticketList":[{"ticket":"01 02 03 04 05 12 22|10","eachTotalMoney":"200","eachBetMode":"01"},{"ticket":"01 02 03 04 05 12 20|10","eachTotalMoney":"200","eachBetMode":"01"}],"terminalId":"2","terminal":"111","multiDraw":"3","betDouble":"1","noteNumber":"1","totalMoney":"400","betMode":"01","dataSource ":"0","drawId":"3387","drawNumber":"2018097"}}
     * sign : 6D9B00C2F6CD4EB0921527432A632031
     */

    private String interfaceCode;
    private int requestTime;
    private int accountId;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class DataBean {
        /**
         * orderInfo : {"gameId":"152","gameAlias":"ssq","ticketList":[{"ticket":"01 02 03 04 05 12 22|10","eachTotalMoney":"200","eachBetMode":"01"},{"ticket":"01 02 03 04 05 12 20|10","eachTotalMoney":"200","eachBetMode":"01"}],"terminalId":"2","terminal":"111","multiDraw":"3","betDouble":"1","noteNumber":"1","totalMoney":"400","betMode":"01","dataSource ":"0","drawId":"3387","drawNumber":"2018097"}
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
             * gameId : 152
             * gameAlias : ssq
             * ticketList : [{"ticket":"01 02 03 04 05 12 22|10","eachTotalMoney":"200","eachBetMode":"01"},{"ticket":"01 02 03 04 05 12 20|10","eachTotalMoney":"200","eachBetMode":"01"}]
             * terminalId : 2
             * terminal : 111
             * multiDraw : 3
             * betDouble : 1
             * noteNumber : 1
             * totalMoney : 400
             * betMode : 01
             * dataSource  : 0
             * drawId : 3387
             * drawNumber : 2018097
             */

            private String gameId;
            private String gameAlias;
            private String terminalId;
            private String terminal;
            private String multiDraw;
            private String betDouble;
            private String noteNumber;
            private String totalMoney;
            private String betMode;
            private String dataSource;
            private String drawId;
            private String drawNumber;
            private List<TicketListBean> ticketList;

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }

            public String getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(String terminalId) {
                this.terminalId = terminalId;
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

            public String getDrawId() {
                return drawId;
            }

            public void setDrawId(String drawId) {
                this.drawId = drawId;
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
                 * ticket : 01 02 03 04 05 12 22|10
                 * eachTotalMoney : 200
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
