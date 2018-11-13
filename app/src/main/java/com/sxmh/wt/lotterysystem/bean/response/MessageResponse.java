package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class MessageResponse {

    /**
     * code : 40001
     * state : 00
     * message : 修改人未登录
     * data : {"userInfo":[]}
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
        private List<?> userInfo;

        public List<?> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<?> userInfo) {
            this.userInfo = userInfo;
        }
    }
}
