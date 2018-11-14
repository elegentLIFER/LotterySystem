package com.sxmh.wt.lotterysystem.bean;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class UserInfo {
    public static final String ROLE_CLECK = "clerk";
    public static final String ROLE_CENTRALISTRATOR = "centralistrator";
    private String sessionName;
    private String sessionId;
    private String token;
    private String role;
    private String name;
    private String uid;
    private String mail;
    private String epid;
    private String epnumbei;

    private static volatile UserInfo userInfo;

    private UserInfo() {
    }

    public static UserInfo getInstance() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public String getEpid() {
        return epid;
    }

    public void setEpid(String epid) {
        this.epid = epid;
    }

    public String getEpnumbei() {
        return epnumbei;
    }

    public void setEpnumbei(String epnumbei) {
        this.epnumbei = epnumbei;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
