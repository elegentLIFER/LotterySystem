package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class LoginRequest extends BaseRequest {

    /**
     * interfaceCode :
     * requestTime : 1455606858805
     * data : {"userInfo":{"userName":"00","isTerminal ":"1","numBercard ":"00","passWord":"2018026"}}
     */

    private String interfaceCode;
    private long requestTime;
    private DataBean data;

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userInfo : {"userName":"00","isTerminal ":"1","numBercard ":"00","passWord":"2018026"}
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
             * userName : 00
             * isTerminal  : 1
             * numBercard  : 00
             * passWord : 2018026
             */

            private String userName;
            private String isTerminal;
            private String numBercard;
            private String passWord;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getIsTerminal() {
                return isTerminal;
            }

            public void setIsTerminal(String isTerminal) {
                this.isTerminal = isTerminal;
            }

            public String getNumBercard() {
                return numBercard;
            }

            public void setNumBercard(String numBercard) {
                this.numBercard = numBercard;
            }

            public String getPassWord() {
                return passWord;
            }

            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }
        }
    }
}
