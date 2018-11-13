package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

public class Arrange5CommitResponse {

    /**
     * betStatus : 02
     * code : 00000
     * data : {"orderInfo":{"betDouble":"1","betMode":"01","dataSource":"0","drawNumber":"2018283","gameAlias":"pl5","multiDraw":"1","noteNumber":"2","terminal":"ZDMHI1539326614","ticketList":[{"eachBetMode":"01","eachTotalMoney":"20","ticket":"7 1 3 7 4"},{"eachBetMode":"01","eachTotalMoney":"20","ticket":"2 9 8 2 7"}],"totalMoney":"40"}}
     * ketStore : 0x8cccc92cb825dbd2bad7fe7ce78cc0aada67f8eb17c5b258025e60f6cbefe8a2
     * message : 终端下注成功!
     * orderCode : 201810171601004642904762
     * orderStatus : 00
     * safetyCode : F3346622-59CBEE81-56C317FF-86BC7C0F
     * state : 00
     */

    private String betStatus;
    private String code;
    private DataBean data;
    private String ketStore;
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

    public String getKetStore() {
        return ketStore;
    }

    public void setKetStore(String ketStore) {
        this.ketStore = ketStore;
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
         * orderInfo : {"betDouble":"1","betMode":"01","dataSource":"0","drawNumber":"2018283","gameAlias":"pl5","multiDraw":"1","noteNumber":"2","terminal":"ZDMHI1539326614","ticketList":[{"eachBetMode":"01","eachTotalMoney":"20","ticket":"7 1 3 7 4"},{"eachBetMode":"01","eachTotalMoney":"20","ticket":"2 9 8 2 7"}],"totalMoney":"40"}
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
             * dataSource : 0
             * drawNumber : 2018283
             * gameAlias : pl5
             * multiDraw : 1
             * noteNumber : 2
             * terminal : ZDMHI1539326614
             * ticketList : [{"eachBetMode":"01","eachTotalMoney":"20","ticket":"7 1 3 7 4"},{"eachBetMode":"01","eachTotalMoney":"20","ticket":"2 9 8 2 7"}]
             * totalMoney : 40
             */

            private String betDouble;
            private String betMode;
            private String dataSource;
            private String drawNumber;
            private String gameAlias;
            private String multiDraw;
            private String noteNumber;
            private String terminal;
            private String totalMoney;
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

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
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

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }

            public List<TicketListBean> getTicketList() {
                return ticketList;
            }

            public void setTicketList(List<TicketListBean> ticketList) {
                this.ticketList = ticketList;
            }

            public static class TicketListBean {
                /**
                 * eachBetMode : 01
                 * eachTotalMoney : 20
                 * ticket : 7 1 3 7 4
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
