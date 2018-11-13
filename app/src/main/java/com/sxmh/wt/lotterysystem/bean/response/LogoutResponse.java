package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class LogoutResponse {

    /**
     * code : 00000
     * state : 00
     * message : 用户已退出
     */

    private String code;
    private String state;
    private String message;

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
}
