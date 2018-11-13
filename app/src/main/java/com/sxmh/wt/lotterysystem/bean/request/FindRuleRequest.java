package com.sxmh.wt.lotterysystem.bean.request;

public class FindRuleRequest {

    /**
     * interfaceCode : findRule
     * requestTime : 1539761820
     * accountName : wg
     * data : {"ruleInfo":{"gameAlias":"36x7"}}
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
         * ruleInfo : {"gameAlias":"36x7"}
         */

        private RuleInfoBean ruleInfo;

        public RuleInfoBean getRuleInfo() {
            return ruleInfo;
        }

        public void setRuleInfo(RuleInfoBean ruleInfo) {
            this.ruleInfo = ruleInfo;
        }

        public static class RuleInfoBean {
            /**
             * gameAlias : 36x7
             */

            private String gameAlias;

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }
        }
    }
}
