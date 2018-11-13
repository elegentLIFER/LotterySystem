package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class MessageListResponse {

    /**
     * code : 40001
     * state : 00
     * message : 消息查看人未登录
     * data : {"terminalInfo":{"terminalId":"","current_page":"1","num":"1"}}
     */

    private String code;
    private String state;
    private String message;
    private DataBean data;
    /**
     * newsList : {"total":1,"num":"10","list":[{"id":"2","name":null,"INFORMATIONNAME":"00","priority":null,"status":"1"}]}
     */

    private NewsListBean newsList;

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

    public NewsListBean getNewsList() {
        return newsList;
    }

    public void setNewsList(NewsListBean newsList) {
        this.newsList = newsList;
    }

    public static class DataBean {
        /**
         * terminalInfo : {"terminalId":"","current_page":"1","num":"1"}
         */

        private TerminalInfoBean terminalInfo;

        public TerminalInfoBean getTerminalInfo() {
            return terminalInfo;
        }

        public void setTerminalInfo(TerminalInfoBean terminalInfo) {
            this.terminalInfo = terminalInfo;
        }

        public static class TerminalInfoBean {
            /**
             * terminalId :
             * current_page : 1
             * num : 1
             */

            private String terminalId;
            private String current_page;
            private String num;

            public String getTerminalId() {
                return terminalId;
            }

            public void setTerminalId(String terminalId) {
                this.terminalId = terminalId;
            }

            public String getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(String current_page) {
                this.current_page = current_page;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }

    public static class NewsListBean {
        /**
         * total : 1
         * num : 10
         * list : [{"id":"2","name":null,"INFORMATIONNAME":"00","priority":null,"status":"1"}]
         */

        private int total;
        private String num;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * name : null
             * INFORMATIONNAME : 00
             * priority : null
             * status : 1
             */

            private String id;
            private String name;
            private String INFORMATIONNAME;
            private int priority;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getINFORMATIONNAME() {
                return INFORMATIONNAME;
            }

            public void setINFORMATIONNAME(String INFORMATIONNAME) {
                this.INFORMATIONNAME = INFORMATIONNAME;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
