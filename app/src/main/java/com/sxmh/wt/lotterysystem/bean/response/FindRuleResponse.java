package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

public class FindRuleResponse {
    /**
     * code : 00000
     * data : {"ruleInfo":{"gameAlias":"36x7"}}
     * message : 查询规则成功!
     * ruleList : [{"number":"5","ruleName":"退票时间","ruleNum":"R008"},{"number":"99","ruleName":"注数","ruleNum":"R001"},{"number":"99","ruleName":"倍数","ruleNum":"R002"},{"number":"12","ruleName":"期数","ruleNum":"R003"},{"number":"5000000","ruleName":"最高中奖奖金","ruleNum":"R004"},{"number":"2000","ruleName":"最高投注金额","ruleNum":"R005"},{"number":"5","ruleName":"不允许投注倒计时","ruleNum":"R006"},{"number":"20","ruleName":"最小投注金额","ruleNum":"R007"}]
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;
    private List<RuleListBean> ruleList;

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

    public List<RuleListBean> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleListBean> ruleList) {
        this.ruleList = ruleList;
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

    public static class RuleListBean {
        /**
         * number : 5
         * ruleName : 退票时间
         * ruleNum : R008
         */

        private String number;
        private String ruleName;
        private String ruleNum;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getRuleName() {
            return ruleName;
        }

        public void setRuleName(String ruleName) {
            this.ruleName = ruleName;
        }

        public String getRuleNum() {
            return ruleNum;
        }

        public void setRuleNum(String ruleNum) {
            this.ruleNum = ruleNum;
        }
    }
}
