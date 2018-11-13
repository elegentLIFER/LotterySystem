package com.sxmh.wt.lotterysystem.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wang Tao on 2018/4/20 0020.
 */

public class NUtil {
    private static final String TAG = "NUtil";
    static List<String[]> arrayList = new ArrayList<>();

    public static void main(String[] args) {
//        String[] a = {"1", "2", "3"};
//        String[] b = {"甲", "乙", "丙"};
//        String[] c = {"红", "黄", "蓝"};
//        String[] d = {"红1", "黄1", "蓝1"};
//
//        ArrayList<String[]> strings = new ArrayList<>();
//        strings.add(a);
//        strings.add(b);
//        strings.add(c);
//
//        doExchange(strings);
//        String[] dd = arrayList.get(0);
//        System.out.println(dd[0] + "  " + dd[1] + "  " + dd[2]);

        int i = C2(7, 8);
        System.out.println(i);
    }

    public static List<List<Integer>> getThreeZuhe(List<Integer> data) {
        int length = data.size();
        List<List<Integer>> intArrList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(data.get(i));
                    integers.add(data.get(j));
                    integers.add(data.get(k));
                    intArrList.add(integers);
                }
            }
        }

        return intArrList;
    }

    public static List<List<Integer>> getTwoZuhe(List<Integer> data) {
        int length = data.size();
        List<List<Integer>> intArrList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(data.get(i));
                integers.add(data.get(j));
                intArrList.add(integers);
            }
        }
        return intArrList;
    }


    public static void doExchange(List arrayLists) {
        int len = arrayLists.size();
        //判断数组size是否小于2，如果小于说明已经递归完成了，否则你们懂得的，不懂？断续看代码
        if (len < 2) {
            arrayList = arrayLists;
            return;
        }
        //拿到第一个数组
        int len0;
        if (arrayLists.get(0) instanceof String[]) {
            String[] arr0 = (String[]) arrayLists.get(0);
            len0 = arr0.length;
        } else {
            len0 = ((ArrayList<String>) arrayLists.get(0)).size();
        }

        //拿到第二个数组
        String[] arr1 = (String[]) arrayLists.get(1);
        int len1 = arr1.length;

        //计算当前两个数组一共能够组成多少个组合
        int lenBoth = len0 * len1;

        //定义临时存放排列数据的集合
        ArrayList<ArrayList<String>> tempArrayLists = new ArrayList<>(lenBoth);

        //第一层for就是循环arrayLists第一个元素的
        for (int i = 0; i < len0; i++) {
            //第二层for就是循环arrayLists第二个元素的
            for (int j = 0; j < len1; j++) {
                //判断第一个元素如果是数组说明，循环才刚开始
                if (arrayLists.get(0) instanceof String[]) {
                    String[] arr0 = (String[]) arrayLists.get(0);
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(arr0[i]);
                    arr.add(arr1[j]);
                    //把排列数据加到临时的集合中
                    tempArrayLists.add(arr);
                } else {
                    //到这里就明循环了最少一轮啦，我们把上一轮的结果拿出来继续跟arrayLists的下一个元素排列
                    ArrayList<ArrayList<String>> arrtemp = (ArrayList<ArrayList<String>>) arrayLists.get(0);
                    ArrayList<String> arr = new ArrayList<>();
                    for (int k = 0; k < arrtemp.get(i).size(); k++) {
                        arr.add(arrtemp.get(i).get(k));
                    }
                    arr.add(arr1[j]);
                    tempArrayLists.add(arr);
                }
            }
        }

        //这是根据上面排列的结果重新生成的一个集合
        List newArrayLists = new ArrayList<>();
        //把还没排列的数组装进来，看清楚i=2的喔，因为前面两个数组已经完事了，不需要再加进来了
        for (int i = 2; i < arrayLists.size(); i++) {
            newArrayLists.add(arrayLists.get(i));
        }
        //记得把我们辛苦排列的数据加到新集合的第一位喔，不然白忙了
        newArrayLists.add(0, tempArrayLists);

        //你没看错，我们这整个算法用到的就是递归的思想。
        doExchange(newArrayLists);
    }

    /**
     * 根据微妙获得日期
     *
     * @param sd
     * @return
     */
    public static String getFormatDate(long sd) {
        Date dat = new Date(sd);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sb = format.format(gc.getTime());
        return sb;
    }

    /**
     * @param mss
     * @return
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + "天" + hours + "小时" + minutes + "分"
                + seconds + "秒";
    }

    /**
     * 将数字转换成彩票中的格式。列如：1 ----> 01
     *
     * @param num
     * @return
     */
    public static String intToLotteryPattern(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return num + "";
    }

    /**
     * Get android device id for QR-Code image.
     *
     * @param context
     * @return deviceId
     */
    public static String getDeviceId(Context context) {
        String wlan0MacAddr = getWlan0MacAddr().replaceAll(":", "").trim();
        String eth0MacAddr = getEth0MacAddr().replaceAll(":", "").trim();
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID).trim();

        if (wlan0MacAddr == null || wlan0MacAddr.equals("")) {
            wlan0MacAddr = "null";
        }
        if (eth0MacAddr == null || eth0MacAddr.equals("")) {
            eth0MacAddr = "null";
        }

        return wlan0MacAddr + "." + eth0MacAddr + "." + androidId;
    }

    /**
     * Get mqtt client id for push service, and it must be a sole value.
     *
     * @return mqttClientId
     */
    public static String getMqttClientId() {
        String wlan0MacAddr = getWlan0MacAddr().replaceAll(":", "").trim();
        String eth0MacAddr = getEth0MacAddr().replaceAll(":", "").trim();
        long time = System.currentTimeMillis();

        if (wlan0MacAddr == null || wlan0MacAddr.equals("")) {
            wlan0MacAddr = "null";
        }
        if (eth0MacAddr == null || eth0MacAddr.equals("")) {
            eth0MacAddr = "null";
        }

        return wlan0MacAddr + "." + eth0MacAddr + "." + time;
    }

    /**
     * Get wlan0 mac address.
     *
     * @return macAddress
     */
    public static String getWlan0MacAddr() {
        return do_exec("cat /sys/class/net/wlan0/address").trim();
    }

    /**
     * Get eth0 mac address.
     *
     * @return macAddress
     */
    public static String getEth0MacAddr() {
        return do_exec("cat /sys/class/net/eth0/address").trim();
    }

    /**
     * Execute cmd.
     *
     * @param cmd
     * @return
     */
    public static String do_exec(String cmd) {
        String s = "";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                s += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 生成条形码
     */
    public static Bitmap generateBarCode(String content, int widthPix, int heightPix) {
        //配置参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 容错级别 这里选择最高H级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        MultiFormatWriter writer = new MultiFormatWriter();

        // 图像数据转换，使用了矩阵转换 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = writer.encode(content, BarcodeFormat.CODE_128, widthPix, heightPix, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        int[] pixels = new int[widthPix * heightPix];
        //  下面这里按照二维码的算法，逐个生成二维码的图片，
        // 两个for循环是图片横列扫描的结果
        for (int y = 0; y < heightPix; y++) {
            for (int x = 0; x < widthPix; x++) {
                if (bitMatrix.get(x, y)) {
                    pixels[y * widthPix + x] = 0xff000000; // 黑色
                } else {
                    pixels[y * widthPix + x] = 0xffffffff;// 白色
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);
        return bitmap;
    }

    //求排列数
    public static int A(int up, int bellow) {
        int result = 1;
        for (int i = up; i > 0; i--) {
            result *= bellow;
            bellow--;
        }
        return result;
    }

    //求组合数，这个也不需要了。定义式，不使用互补率
    public static int C2(int up, int below) {
//			int denominator=factorial(up);//分母up的阶乘
        //分母
        int denominator = A(up, up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        int numerator = A(up, below);//分子的排列数
        return numerator / denominator;
    }

//    //应用组合数的互补率简化计算量
//    public static int C(int up, int below) {
//        int helf = below / 2;
//        if (up > helf) {
//            System.out.print(up + "---->");
//            up = below - up;
//            System.out.print(up + "\n");
//
//        }
//        int denominator = A(up, up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
//        //分子
//        int numerator = A(up, below);//分子的排列数
//        return numerator / denominator;
//    }

    /**
     * 根据View创建Bitmap
     *
     * @param v
     * @return
     */
    public static Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }


    //找到数组中的最大值
    public static int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) if (value > max) max = value;
        return max;
    }
}
