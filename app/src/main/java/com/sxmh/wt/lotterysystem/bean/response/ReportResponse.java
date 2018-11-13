package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class ReportResponse {

    /**
     * code : 00000
     * data : {"reportInfo":{"endTime":"1523609405","startTime":"1523609380"}}
     * message : 终端报表查询成功!
     * reportList : [{"betNum":83,"money":166,"type":1},{"betNum":50,"money":200,"type":2}]
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;
    private List<ReportListBean> reportList;

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

    public List<ReportListBean> getReportList() {
        return reportList;
    }

    public void setReportList(List<ReportListBean> reportList) {
        this.reportList = reportList;
    }

    public static class DataBean {
        /**
         * reportInfo : {"endTime":"1523609405","startTime":"1523609380"}
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
             * endTime : 1523609405
             * startTime : 1523609380
             */

            private String endTime;
            private String startTime;

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }
    }

    public static class ReportListBean {
        /**
         * betNum : 83
         * money : 166
         * type : 1
         */

        private int betNum;
        private int money;
        private int type;

        public int getBetNum() {
            return betNum;
        }

        public void setBetNum(int betNum) {
            this.betNum = betNum;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
