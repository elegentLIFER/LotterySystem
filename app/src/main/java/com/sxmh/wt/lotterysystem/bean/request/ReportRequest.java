package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class ReportRequest extends BaseRequest {

    /**
     * interfaceCode : reportQuery
     * requestTime : 1534228500
     * accountName : wg
     * data : {"reportInfo":{"startTime":"1523609380","endTime":"1523609405"}}
     */

    private String interfaceCode;
    private int requestTime;
    private String accountName;
    private DataBean data;

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

    public static class DataBean {
        /**
         * reportInfo : {"startTime":"1523609380","endTime":"1523609405"}
         */

        private ReportInfoBean reportInfo;

        public ReportInfoBean getReportInfo() {
            return reportInfo;
        }

        public void setReportInfo(ReportInfoBean reportInfo) {
            this.reportInfo = reportInfo;
        }

        public static class ReportInfoBean {
            /**
             * startTime : 1523609380
             * endTime : 1523609405
             */

            private String startTime;
            private String endTime;

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }
}
