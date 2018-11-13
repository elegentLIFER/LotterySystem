package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class TroubleUploadRequest extends BaseRequest {

    /**
     * interfaceCode :
     * requestTime : 1455606858805
     * userId : 1234567890
     * data : {" customerserviceList":{"type":"1","content":"00","title":"00","faultType":"00","reservationMaintenance":"00","terminaNum":""}}
     */

    private String interfaceCode;
    private long requestTime;
    private String userId;
    private DataBean data;

    public static class DataBean {
        /**
         *  customerserviceList : {"type":"1","content":"00","title":"00","faultType":"00","reservationMaintenance":"00","terminaNum":""}
         */

        private customerserviceListBean customerserviceList;

        public customerserviceListBean getCustomerserviceList() {
            return customerserviceList;
        }

        public void setCustomerserviceList(customerserviceListBean customerserviceList) {
            this.customerserviceList = customerserviceList;
        }

        public static class customerserviceListBean {
            /**
             * type : 1
             * content : 00
             * title : 00
             * faultType : 00
             * reservationMaintenance : 00
             * terminaNum :
             */

            private String type;
            private String content;
            private String title;
            private String faultType;
            private String reservationMaintenance;
            private String terminaNum;



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

            public String getTerminaNum() {
                return terminaNum;
            }

            public void setTerminaNum(String terminaNum) {
                this.terminaNum = terminaNum;
            }
        }
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
