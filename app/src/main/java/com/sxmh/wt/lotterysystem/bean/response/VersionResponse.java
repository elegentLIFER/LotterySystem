package com.sxmh.wt.lotterysystem.bean.response;

import com.google.gson.annotations.SerializedName;

public class VersionResponse {

    /**
     * code : 00000
     * state : 00
     * data : null
     * message : 有新版本
     * updateInfo : {"package":"com.sxmh.wt.lotterysystem","version":"1.1.0","url":"http://compressdk.oss-cn-shanghai.aliyuncs.com/user-dir/ECwxy37Gbw1541035251962.apk","description":"检测到新版本，是否更新？"}
     */

    private String code;
    private String state;
    private Object data;
    private String message;
    private UpdateInfoBean updateInfo;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpdateInfoBean getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(UpdateInfoBean updateInfo) {
        this.updateInfo = updateInfo;
    }

    public static class UpdateInfoBean {
        /**
         * package : com.sxmh.wt.lotterysystem
         * version : 1.1.0
         * url : http://compressdk.oss-cn-shanghai.aliyuncs.com/user-dir/ECwxy37Gbw1541035251962.apk
         * description : 检测到新版本，是否更新？
         */

        @SerializedName("package")
        private String packageX;
        private String version;
        private String url;
        private String description;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
