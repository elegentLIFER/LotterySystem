package com.sxmh.wt.lotterysystem.bean.response;

import java.util.List;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class NotOpenQueryResponse {

    /**
     * code : 00000
     * drawList : [{"drawId":16606,"drawNumber":"2018122","endTime":1539864000000,"gameAlias":"ssq","gameId":152,"gameName":"双色球","prizeTime":1539871200000,"status":"00"}]
     * message : 奖期查询成功!
     * state : 00
     */

    private String code;
    private String message;
    private String state;
    private List<DrawListBean> drawList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public static class DrawListBean {
        /**
         * drawId : 16606
         * drawNumber : 2018122
         * endTime : 1539864000000
         * gameAlias : ssq
         * gameId : 152
         * gameName : 双色球
         * prizeTime : 1539871200000
         * status : 00
         */

        private int drawId;
        private String drawNumber;
        private long endTime;
        private String gameAlias;
        private int gameId;
        private String gameName;
        private long prizeTime;
        private String status;

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

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getGameAlias() {
            return gameAlias;
        }

        public void setGameAlias(String gameAlias) {
            this.gameAlias = gameAlias;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public long getPrizeTime() {
            return prizeTime;
        }

        public void setPrizeTime(long prizeTime) {
            this.prizeTime = prizeTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
