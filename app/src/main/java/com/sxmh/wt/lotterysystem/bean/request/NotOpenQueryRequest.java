package com.sxmh.wt.lotterysystem.bean.request;

import com.sxmh.wt.lotterysystem.base.BaseRequest;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class NotOpenQueryRequest extends BaseRequest {


    /**
     * interfaceCode : drawNotOpenQuery
     * requestTime : 1455606858
     * accountName : wg
     * data : {"drawInfo":{"gameAlias":"ssq"}}
     */

    private String interfaceCode;
    private int requestTime;
    private String accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * drawInfo : {"gameAlias":"ssq"}
         */

        private DrawInfoBean drawInfo;

        public DrawInfoBean getDrawInfo() {
            return drawInfo;
        }

        public void setDrawInfo(DrawInfoBean drawInfo) {
            this.drawInfo = drawInfo;
        }

        public static class DrawInfoBean {
            /**
             * gameAlias : ssq
             */

            private String gameAlias;

            public String getGameAlias() {
                return gameAlias;
            }

            public void setGameAlias(String gameAlias) {
                this.gameAlias = gameAlias;
            }
        }
    }
}
