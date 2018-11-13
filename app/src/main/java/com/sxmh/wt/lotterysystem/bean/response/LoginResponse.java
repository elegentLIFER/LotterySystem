package com.sxmh.wt.lotterysystem.bean.response;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class LoginResponse {

    /**
     * code : 00000
     * state : 00
     * data : {"userInfo":{"userName":"admin","passWord":"Win2008!"}}
     * message : 登录成功
     * userInfo : {"session_name":"SESS66136e8a029e982c383e21ea2f2ee144","sessid":"yKpwOvkiYWTytkPTQ1ZHadH5iBzm2RD00P2kB-5Ew5Q","token":"7JDAXRDhkGfX34wxcgXPrHLuuAjcf9fzz41feidfw3g","role":"administrator","user":{"uid":"1","name":"admin","epid ":"admin","epnumbei ":"admin","number":"admin","mail":"liwenhao@linuxce.cn"}}
     */

    private String code;
    private String state;
    private DataBean data;
    private String message;
    private UserInfoBeanX userInfo;

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

    public UserInfoBeanX getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBeanX userInfo) {
        this.userInfo = userInfo;
    }

    public static class DataBean {
        /**
         * userInfo : {"userName":"admin","passWord":"Win2008!"}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * userName : admin
             * passWord : Win2008!
             */

            private String userName;
            private String passWord;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPassWord() {
                return passWord;
            }

            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }
        }
    }

    public static class UserInfoBeanX {
        /**
         * session_name : SESS66136e8a029e982c383e21ea2f2ee144
         * sessid : yKpwOvkiYWTytkPTQ1ZHadH5iBzm2RD00P2kB-5Ew5Q
         * token : 7JDAXRDhkGfX34wxcgXPrHLuuAjcf9fzz41feidfw3g
         * role : administrator
         * user : {"uid":"1","name":"admin","epid ":"admin","epnumbei ":"admin","number":"admin","mail":"liwenhao@linuxce.cn"}
         */

        private String session_name;
        private String sessid;
        private String token;
        private String role;
        private UserBean user;

        public String getSession_name() {
            return session_name;
        }

        public void setSession_name(String session_name) {
            this.session_name = session_name;
        }

        public String getSessid() {
            return sessid;
        }

        public void setSessid(String sessid) {
            this.sessid = sessid;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * uid : 1
             * name : admin
             * epid  : admin
             * epnumbei  : admin
             * number : admin
             * mail : liwenhao@linuxce.cn
             */

            private String uid;
            private String name;
            private String epid;
            private String epnumbei;
            private String number;
            private String mail;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getMail() {
                return mail;
            }

            public void setMail(String mail) {
                this.mail = mail;
            }
        }
    }
}
