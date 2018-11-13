package com.sxmh.wt.lotterysystem.bean.response;

public class MessageLookResponse {

    /**
     * code : 40001
     * state : 00
     * message : 修改人未登录
     * data : {"terminalnewsInfo":{"id":"00"}}
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
