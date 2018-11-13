package com.sxmh.wt.lotterysystem.bean.response;

public class RefundTicketResponse {

    /**
     * code : 00000
     * data : {"orderInfo":{"drawNumber":"2018109","gameAlias":"36x7","orderCode":"201810101804003047711245"}}
     * message : 退票成功!
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
         * orderInfo : {"drawNumber":"2018109","gameAlias":"36x7","orderCode":"201810101804003047711245"}
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
             * drawNumber : 2018109
             * gameAlias : 36x7
             * orderCode : 201810101804003047711245
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
