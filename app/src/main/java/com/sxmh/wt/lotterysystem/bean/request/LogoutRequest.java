package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class LogoutRequest extends BaseRequest {

    /**
     * interfaceCode :
     * requestTime : 1450869189960
     */

    private String interfaceCode;
    private long requestTime;

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
}
