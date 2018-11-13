package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Administrator on 2018/5/7 0007.
 */


public class TroubleUploadResponse {


    /**
     * code : 40001
     * state : 00
     * message : 创建人未登录
     * data : {"customerserviceList":{"type":"1","content":"00","title":"00","faultType":"00","reservationMaintenance":"00"}}
     */

    private String code;
    private String state;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * customerserviceList : {"type":"1","content":"00","title":"00","faultType":"00","reservationMaintenance":"00"}
         */

        private CustomerserviceListBean customerserviceList;

        public CustomerserviceListBean getCustomerserviceList() {
            return customerserviceList;
        }

        public void setCustomerserviceList(CustomerserviceListBean customerserviceList) {
            this.customerserviceList = customerserviceList;
        }

        public static class CustomerserviceListBean {
            /**
             * type : 1
             * content : 00
             * title : 00
             * faultType : 00
             * reservationMaintenance : 00
             */

            private String type;
            private String content;
            private String title;
            private String faultType;
            private String reservationMaintenance;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFaultType() {
                return faultType;
            }

            public void setFaultType(String faultType) {
                this.faultType = faultType;
            }

            public String getReservationMaintenance() {
                return reservationMaintenance;
            }

            public void setReservationMaintenance(String reservationMaintenance) {
                this.reservationMaintenance = reservationMaintenance;
            }
        }
    }
}
