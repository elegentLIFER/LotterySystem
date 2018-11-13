package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class CommitLotteryResponse {

    /**
     * betStatus : 02
     * code : 00000
     * data : {"orderInfo":{"betDouble":"1","betMode":"01","drawId":"3387","drawList":[{"drawId":"3595","drawNumber":"2018099"},{"drawId":"3594","drawNumber":"2018098"}],"drawNumber":"2018097","gameAlias":"ssq","gameId":"152","multiDraw":"3","noteNumber":"1","terminal":"111","terminalId":"2","ticketList":[{"eachBetMode":"01","eachTotalMoney":"200","ticket":"01 02 03 04 05 12 22|10"},{"eachBetMode":"01","eachTotalMoney":"200","ticket":"01 02 03 04 05 12 20|10"}],"totalMoney":"400"}}
     * message : 终端下注成功!
     * orderCode : 201809041615000760713279
     * orderStatus : 00
     * safetyCode : 115465465465464561
     * state : 00
     */

    private String betStatus;
    private String code;
    private DataBean data;
    private String message;
    private String orderCode;
    private String orderStatus;
    private String safetyCode;
    private String state;

    public String getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(String betStatus) {
        this.betStatus = betStatus;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSafetyCode() {
        return safetyCode;
    }

    public void setSafetyCode(String safetyCode) {
        this.safetyCode = safetyCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * orderInfo : {"betDouble":"1","betMode":"01","drawId":"3387","drawList":[{"drawId":"3595","drawNumber":"2018099"},{"drawId":"3594","drawNumber":"2018098"}],"drawNumber":"2018097","gameAlias":"ssq","gameId":"152","multiDraw":"3","noteNumber":"1","terminal":"111","terminalId":"2","ticketList":[{"eachBetMode":"01","eachTotalMoney":"200","ticket":"01 02 03 04 05 12 22|10"},{"eachBetMode":"01","eachTotalMoney":"200","ticket":"01 02 03 04 05 12 20|10"}],"totalMoney":"400"}
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
             * betDouble : 1
             * betMode : 01
             * drawId : 3387
             * drawList : [{"drawId":"3595","drawNumber":"2018099"},{"drawId":"3594","drawNumber":"2018098"}]
             * drawNumber : 2018097
             * gameAlias : ssq
             * gameId : 152
             * multiDraw : 3
             * noteNumber : 1
             * terminal : 111
             * terminalId : 2
             * ticketList : [{"eachBetMode":"01","eachTotalMoney":"200","ticket":"01 02 03 04 05 12 22|10"},{"eachBetMode":"01","eachTotalMoney":"200","ticket":"01 02 03 04 05 12 20|10"}]
             * totalMoney : 400
             */

            private String betDouble;
            private String betMode;
            private String drawId;
            private String drawNumber;
            private String gameAlias;
            private String gameId;
            private String multiDraw;
            private String noteNumber;
            private String terminal;
            private String terminalId;
            private String totalMoney;
            private List<DrawListBean> drawList;
            private List<TicketListBean> ticketList;

            public String getBetDouble() {
                return betDouble;
            }

            public void setBetDouble(String betDouble) {
                this.betDouble = betDouble;
            }

            public String getBetMode() {
                return betMode;
            }

            public void setBetMode(String betMode) {
                this.betMode = betMode;
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

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }

            public String getMultiDraw() {
                return multiDraw;
            }

            public void setMultiDraw(String multiDraw) {
                this.multiDraw = multiDraw;
            }

            public String getNoteNumber() {
                return noteNumber;
            }

            public void setNoteNumber(String noteNumber) {
                this.noteNumber = noteNumber;
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

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }

            public List<DrawListBean> getDrawList() {
                return drawList;
            }

            public void setDrawList(List<DrawListBean> drawList) {
                this.drawList = drawList;
            }

            public List<TicketListBean> getTicketList() {
                return ticketList;
            }

            public void setTicketList(List<TicketListBean> ticketList) {
                this.ticketList = ticketList;
            }

            public static class DrawListBean {
                /**
                 * drawId : 3595
                 * drawNumber : 2018099
                 */

                private String drawId;
                private String drawNumber;

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
            }

            public static class TicketListBean {
                /**
                 * eachBetMode : 01
                 * eachTotalMoney : 200
                 * ticket : 01 02 03 04 05 12 22|10
                 */

                private String eachBetMode;
                private String eachTotalMoney;
                private String ticket;

                public String getEachBetMode() {
                    return eachBetMode;
                }

                public void setEachBetMode(String eachBetMode) {
                    this.eachBetMode = eachBetMode;
                }

                public String getEachTotalMoney() {
                    return eachTotalMoney;
                }

                public void setEachTotalMoney(String eachTotalMoney) {
                    this.eachTotalMoney = eachTotalMoney;
                }

                public String getTicket() {
                    return ticket;
                }

                public void setTicket(String ticket) {
                    this.ticket = ticket;
                }
            }
        }
    }
}
