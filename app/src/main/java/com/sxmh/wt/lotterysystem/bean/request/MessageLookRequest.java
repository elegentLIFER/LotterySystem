package com.sxmh.wt.lotterysystem.bean.request;

public class MessageLookRequest {

    /**
     * interfaceCode :
     * requestTime : 1455606858805
     * userId : 1234567890
     * data : {"terminalnewsInfo":{"id":"00"}}
     */

    private String interfaceCode;
    private long requestTime;
    private String userId;
    private DataBean data;

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

    public static class DataBean {
        /**
         * terminalnewsInfo : {"id":"00"}
         */

        private TerminalnewsInfoBean terminalnewsInfo;

        public TerminalnewsInfoBean getTerminalnewsInfo() {
            return terminalnewsInfo;
        }

        public void setTerminalnewsInfo(TerminalnewsInfoBean terminalnewsInfo) {
            this.terminalnewsInfo = terminalnewsInfo;
        }

        public static class TerminalnewsInfoBean {
            /**
             * id : 00
             */

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
