package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ChangePswResponse {

    /**
     * code : 40001
     * state : 00
     * message : 检查人未登录
     * data : {"userInfo":{"oldPass":"00","newPass":"2018026"}}
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
         * userInfo : {"oldPass":"00","newPass":"2018026"}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * oldPass : 00
             * newPass : 2018026
             */

            private String oldPass;
            private String newPass;

            public String getOldPass() {
                return oldPass;
            }

            public void setOldPass(String oldPass) {
                this.oldPass = oldPass;
            }

            public String getNewPass() {
                return newPass;
            }

            public void setNewPass(String newPass) {
                this.newPass = newPass;
            }
        }
    }
}
