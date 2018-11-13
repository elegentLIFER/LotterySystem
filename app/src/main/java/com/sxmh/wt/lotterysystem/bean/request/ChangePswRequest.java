package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ChangePswRequest extends BaseRequest {

    /**
     * interfaceCode :
     * requestTime : 1450869189960
     * data : {"userInfo":{"oldPass":"00","newPass":"2018026"}}
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
