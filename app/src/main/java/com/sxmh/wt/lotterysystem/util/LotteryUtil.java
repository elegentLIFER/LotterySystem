package com.sxmh.wt.lotterysystem.util;

public class LotteryUtil {
    public static String getBetModeByNum(String mode) {
        if (Constants.BET_MODE_SINGLE.equals(mode)) {
            return "单式";
        } else if (Constants.BET_MODE_DOUBLE.equals(mode)) {
            return "复式";
        } else if (Constants.BET_MODE_DRAG_DOUBLE.equals(mode)) {
            return "胆拖复式";
        } else if (Constants.BET_MODE_COMPOUND.equals(mode)) {
            return "混合投注";
        }
        return "";
    }
}
