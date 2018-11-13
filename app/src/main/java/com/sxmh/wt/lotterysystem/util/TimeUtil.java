package com.sxmh.wt.lotterysystem.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间工具
 * Created by Administrator on 2018/5/4 0004.
 */
public class TimeUtil {

    /**
     * 获取当前10位整数时间戳
     * @return
     */
    public static int get10IntTimeStamp(){
        return ((int) (System.currentTimeMillis() / 1000));
    }

    /**
     * 取得今天零点的十位时间戳
     *
     * @return
     */
    public static long getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 取得今天结束的十位时间戳
     *
     * @return
     */
    public static long getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }

    /**
     * 本周开始时间戳
     */
    public static long getWeekStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获取星期一开始时间戳
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime().getTime();
    }

    /**
     * 本周结束时间戳
     */
    public static long getWeekEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获取星期日结束时间戳
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime().getTime();
    }

    /**
     * 取得本月开始的十位时间戳
     *
     * @return
     */
    public static long getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }

    /**
     * 获取当前格式化日期
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取当前格式化时间
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }
}
