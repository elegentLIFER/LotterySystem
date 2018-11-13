package com.sxmh.wt.lotterysystem.bean.request;

public class TimeRequest {

    /**
     * interfaceCode : timeCalibration
     * requestTime : 1455606858
     * accountId : 1
     * data : {}
     */

    private String interfaceCode;
    private int requestTime;
    private int accountId;
    private DataBean data;

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(int requestTime) {
        this.requestTime = requestTime;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
