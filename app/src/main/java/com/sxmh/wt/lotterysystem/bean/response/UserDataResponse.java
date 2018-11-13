package com.sxmh.wt.lotterysystem.bean.response;

public class UserDataResponse {

    /**
     * code : 00000
     * state : 00
     * data : {}
     * message : 成功返回用户列表信息
     * userList : {"TERMINANUM":"ZDMHI1539326614","TERMINANAME":"软件园002","created":"1538139908","username":"wjj001","BALANCE":"800000000","USEAMOUNT":"0","OVERDRAFT":"0","name":"admin","roles":"administrator","systemtime":1539331934}
     */

    private String code;
    private String state;
    private DataBean data;
    private String message;
    private UserListBean userList;

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

    public UserListBean getUserList() {
        return userList;
    }

    public void setUserList(UserListBean userList) {
        this.userList = userList;
    }

    public static class DataBean {
    }

    public static class UserListBean {
        /**
         * TERMINANUM : ZDMHI1539326614
         * TERMINANAME : 软件园002
         * created : 1538139908
         * username : wjj001
         * BALANCE : 800000000
         * USEAMOUNT : 0
         * OVERDRAFT : 0
         * name : admin
         * roles : administrator
         * systemtime : 1539331934
         */

        private String TERMINANUM;
        private String TERMINANAME;
        private String created;
        private String username;
        private String BALANCE;
        private String USEAMOUNT;
        private String OVERDRAFT;
        private String name;
        private String roles;
        private int systemtime;

        public String getTERMINANUM() {
            return TERMINANUM;
        }

        public void setTERMINANUM(String TERMINANUM) {
            this.TERMINANUM = TERMINANUM;
        }

        public String getTERMINANAME() {
            return TERMINANAME;
        }

        public void setTERMINANAME(String TERMINANAME) {
            this.TERMINANAME = TERMINANAME;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBALANCE() {
            return BALANCE;
        }

        public void setBALANCE(String BALANCE) {
            this.BALANCE = BALANCE;
        }

        public String getUSEAMOUNT() {
            return USEAMOUNT;
        }

        public void setUSEAMOUNT(String USEAMOUNT) {
            this.USEAMOUNT = USEAMOUNT;
        }

        public String getOVERDRAFT() {
            return OVERDRAFT;
        }

        public void setOVERDRAFT(String OVERDRAFT) {
            this.OVERDRAFT = OVERDRAFT;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public int getSystemtime() {
            return systemtime;
        }

        public void setSystemtime(int systemtime) {
            this.systemtime = systemtime;
        }
    }
}
