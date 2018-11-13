package com.sxmh.wt.lotterysystem.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class GameListQueryResponse {

    /**
     * code : 00000
     * data : {"initGameReq":{"fuzzySearch":"","gameName":"双色球","launch":"00","pageNo":"0"}}
     * gameList : [{"alias":"ssq","approvalNum":"X464645485D","approvalTime":1455552000000,"gameName":"双色球","id":152,"launch":"00","onSaleTime":1519142400000,"poolMoney":"1172527454","ruleSummary":"无","stopNoticeTime":1518537600000}]
     * message : 玩法查询成功!
     * pageCount : 1
     * pageNum : 10
     * state : 00
     * totalNum : 1
     */

    private String code;
    private DataBean data;
    private String message;
    private String pageCount;
    private String pageNum;
    private String state;
    private String totalNum;
    private List<GameListBean> gameList;

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

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public List<GameListBean> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameListBean> gameList) {
        this.gameList = gameList;
    }

    public static class DataBean {
        /**
         * initGameReq : {"fuzzySearch":"","gameName":"双色球","launch":"00","pageNo":"0"}
         */

        private InitGameReqBean initGameReq;

        public InitGameReqBean getInitGameReq() {
            return initGameReq;
        }

        public void setInitGameReq(InitGameReqBean initGameReq) {
            this.initGameReq = initGameReq;
        }

        public static class InitGameReqBean {
            /**
             * fuzzySearch :
             * gameName : 双色球
             * launch : 00
             * pageNo : 0
             */

            private String fuzzySearch;
            private String gameName;
            private String launch;
            private String pageNo;

            public String getFuzzySearch() {
                return fuzzySearch;
            }

            public void setFuzzySearch(String fuzzySearch) {
                this.fuzzySearch = fuzzySearch;
            }

            public String getGameName() {
                return gameName;
            }

            public void setGameName(String gameName) {
                this.gameName = gameName;
            }

            public String getLaunch() {
                return launch;
            }

            public void setLaunch(String launch) {
                this.launch = launch;
            }

            public String getPageNo() {
                return pageNo;
            }

            public void setPageNo(String pageNo) {
                this.pageNo = pageNo;
            }
        }
    }

    public static class GameListBean implements Serializable{
        /**
         * alias : ssq
         * approvalNum : X464645485D
         * approvalTime : 1455552000000
         * gameName : 双色球
         * id : 152
         * launch : 00
         * onSaleTime : 1519142400000
         * poolMoney : 1172527454
         * ruleSummary : 无
         * stopNoticeTime : 1518537600000
         */

        private String alias;
        private String approvalNum;
        private long approvalTime;
        private String gameName;
        private int id;
        private String launch;
        private long onSaleTime;
        private String poolMoney;
        private String ruleSummary;
        private long stopNoticeTime;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getApprovalNum() {
            return approvalNum;
        }

        public void setApprovalNum(String approvalNum) {
            this.approvalNum = approvalNum;
        }

        public long getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(long approvalTime) {
            this.approvalTime = approvalTime;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLaunch() {
            return launch;
        }

        public void setLaunch(String launch) {
            this.launch = launch;
        }

        public long getOnSaleTime() {
            return onSaleTime;
        }

        public void setOnSaleTime(long onSaleTime) {
            this.onSaleTime = onSaleTime;
        }

        public String getPoolMoney() {
            return poolMoney;
        }

        public void setPoolMoney(String poolMoney) {
            this.poolMoney = poolMoney;
        }

        public String getRuleSummary() {
            return ruleSummary;
        }

        public void setRuleSummary(String ruleSummary) {
            this.ruleSummary = ruleSummary;
        }

        public long getStopNoticeTime() {
            return stopNoticeTime;
        }

        public void setStopNoticeTime(long stopNoticeTime) {
            this.stopNoticeTime = stopNoticeTime;
        }
    }
}
