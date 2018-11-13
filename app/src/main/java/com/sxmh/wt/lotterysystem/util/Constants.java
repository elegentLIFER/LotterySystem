package com.sxmh.wt.lotterysystem.util;

import android.os.Environment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class Constants {

    public static final String APP_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/莫桑比克";

    /**
     * 请求头键名
     */
    public static final String ACCEPT = "application/json";
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    public static final String SESSION_NAME = "session_name";
    public static final String SESSION_ID = "sessid";
    public static final String TOKEN = "token";

    // SharedPreference名字
    public static final String SP_NAME = "user_info";

    // 接口返回码
    public static final String CODE_SUCCESS = "00000";
    public static final String CODE_FAILED = "40001";
    public static final String CODE_NULL_PARAM = "40003";
    public static final String CODE_EIGHT = "40008";
    public static final String CODE_NINE = "40009";

    // 玩法类型
    public static final String GAME_ALIAS_SSQ = "ssq";
    public static final String GAME_ALIAS_K3 = "k3";
    public static final String GAME_ALIAS_36X7 = "36x7";
    public static final String GAME_ALIAS_KL8 = "kl8";
    public static final String GAME_ALIAS_PL5 = "pl5";

    // 投注方式
    public static final String BET_MODE_SINGLE = "01";
    public static final String BET_MODE_DOUBLE = "02";
    public static final String BET_MODE_DRAG_DOUBLE = "03";
    public static final String BET_MODE_COMPOUND = "04";

    // 数据源
    public static final String DATA_SOURCE_ANDROID_END = "0";

    // 本地广播
    public static final String ACTION = "com.sxmh.wt.lotterysystem.LOCAL_BROADCAST";
    public static final String PREINTER_STATUS = "printer_status";
//    public static final String STATUS_CONNECTED = "status_connected";
//    public static final String STATUS_OUT_OF_PAPER = "status_out_of_paper";
//    public static final String STATUS_ERROR_OCCUR = "status_error_occur";
//    public static final String STATUS_COVER_OPEN = "status_cover_open";


}
