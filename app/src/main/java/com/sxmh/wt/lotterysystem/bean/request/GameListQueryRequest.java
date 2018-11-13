package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class GameListQueryRequest extends BaseRequest {


    /**
     * interfaceCode : querGameList
     * requestTime : 1455606858
     * accountName : wg
     * data : {"initGameReq":{"gameName":"双色球","launch":"00","pageNo":"0","fuzzySearch":""}}
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
         * initGameReq : {"gameName":"双色球","launch":"00","pageNo":"0","fuzzySearch":""}
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
             * gameName : 双色球
             * launch : 00
             * pageNo : 0
             * fuzzySearch :
             */

            private String gameName;
            private String launch;
            private String pageNo;
            private String fuzzySearch;

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

            public String getFuzzySearch() {
                return fuzzySearch;
            }

            public void setFuzzySearch(String fuzzySearch) {
                this.fuzzySearch = fuzzySearch;
            }
        }
    }
}
