package com.sxmh.wt.lotterysystem;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.sxmh.wt.lotterysystem.util.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wang Tao on 2018/4/9 0009.
 */

public class MyApplication extends Application {
    private List<Activity> activityList;
    public static MyApplication myApplication;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityList = new ArrayList<>();
        myApplication = this;

        File file = new File(Constants.APP_PATH);
        if (!file.exists()) file.mkdirs();
    }

    /**
     * 获取MyApplication实例（单例）
     *
     * @return
     */
    public static synchronized MyApplication getInstance() {
        return myApplication;
    }

    /**
     * 添加Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 删除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
            activity.finish();
        }
    }

    /**
     * 退出APP
     */
    public void quitApp() {
        synchronized (activityList) {
            for (Activity activity :
                    activityList) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 初始化屏幕宽高
     */
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}
