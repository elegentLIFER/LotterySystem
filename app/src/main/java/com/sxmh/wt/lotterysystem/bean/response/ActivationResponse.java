package com.sxmh.wt.lotterysystem.bean.response;

public class ActivationResponse {

    /**
     * code : 40001
     * state : 00
     * message : 操作人未登录
     * data : {}
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
    }
}
