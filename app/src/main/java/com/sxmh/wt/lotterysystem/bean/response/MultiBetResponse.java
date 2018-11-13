package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

public class MultiBetResponse {

    /**
     * code : 00000
     * data : {"drawInfo":{"gameId":"152"}}
     * drawList : [{"drawId":7517,"drawNumber":"2018105"},{"drawId":7518,"drawNumber":"2018106"},{"drawId":7519,"drawNumber":"2018107"},{"drawId":7520,"drawNumber":"2018108"},{"drawId":7521,"drawNumber":"2018109"},{"drawId":7522,"drawNumber":"2018110"},{"drawId":7523,"drawNumber":"2018111"},{"drawId":7524,"drawNumber":"2018112"},{"drawId":7525,"drawNumber":"2018113"},{"drawId":7526,"drawNumber":"2018114"}]
     * message : 查询多期成功!
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;
    private List<DrawListBean> drawList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DrawListBean> getDrawList() {
        return drawList;
    }

    public void setDrawList(List<DrawListBean> drawList) {
        this.drawList = drawList;
    }

    public static class DataBean {
        /**
         * drawInfo : {"gameId":"152"}
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
             * gameId : 152
             */

            private String gameId;

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }
        }
    }

    public static class DrawListBean {
        /**
         * drawId : 7517
         * drawNumber : 2018105
         */

        private int drawId;
        private String drawNumber;

        public int getDrawId() {
            return drawId;
        }

        public void setDrawId(int drawId) {
            this.drawId = drawId;
        }

        public String getDrawNumber() {
            return drawNumber;
        }

        public void setDrawNumber(String drawNumber) {
            this.drawNumber = drawNumber;
        }
    }
}
