package com.sxmh.wt.lotterysystem.util;

import android.content.pm.PackageManager;

import com.sxmh.wt.lotterysystem.MyApplication;

/**
 * Created by Wang Tao on 2018/4/19 0019.
 */

public class VersionUtil {

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = MyApplication.myApplication.getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName() {
        String versionName = "";
        try {
            versionName = MyApplication.myApplication.getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }
}
