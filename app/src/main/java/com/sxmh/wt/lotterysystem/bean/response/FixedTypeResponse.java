package com.sxmh.wt.lotterysystem.bean.response;

import java.io.Serializable;
import java.util.List;

public class FixedTypeResponse {

    /**
     * code : 00000
     * data : {"gameInfo":{"gameAlias":"kl8"}}
     * gameAddList : [{"gameAlias":"kl8","gamePlayName":"选一","gamePlayNum":"kl8001"},{"gameAlias":"kl8","gamePlayName":"选十","gamePlayNum":"kl8010"}]
     * message : 玩法查询成功!
     * state : 00
     */

    private String code;
    private DataBean data;
    private String message;
    private String state;
    private List<GameAddListBean> gameAddList;

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

    public List<GameAddListBean> getGameAddList() {
        return gameAddList;
    }

    public void setGameAddList(List<GameAddListBean> gameAddList) {
        this.gameAddList = gameAddList;
    }

    public static class DataBean {
        /**
         * gameInfo : {"gameAlias":"kl8"}
         */

        private GameInfoBean gameInfo;

        public GameInfoBean getGameInfo() {
            return gameInfo;
        }

        public void setGameInfo(GameInfoBean gameInfo) {
            this.gameInfo = gameInfo;
        }

        public static class GameInfoBean {
            /**
             * gameAlias : kl8
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

    public static class GameAddListBean implements Serializable{
        /**
         * gameAlias : kl8
         * gamePlayName : 选一
         * gamePlayNum : kl8001
         */

        private String gameAlias;
        private String gamePlayName;
        private String gamePlayNum;

        public String getGameAlias() {
            return gameAlias;
        }

        public void setGameAlias(String gameAlias) {
            this.gameAlias = gameAlias;
        }

        public String getGamePlayName() {
            return gamePlayName;
        }

        public void setGamePlayName(String gamePlayName) {
            this.gamePlayName = gamePlayName;
        }

        public String getGamePlayNum() {
            return gamePlayNum;
        }

        public void setGamePlayNum(String gamePlayNum) {
            this.gamePlayNum = gamePlayNum;
        }
    }
}
