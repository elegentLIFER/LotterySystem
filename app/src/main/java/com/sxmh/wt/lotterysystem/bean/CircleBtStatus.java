package com.sxmh.wt.lotterysystem.bean;

/**
 * 圆形按钮的信息
 * Created by Wang Tao on 2018/4/16 0016.
 */

public class CircleBtStatus {
    private int Color;
    private int num;
    private boolean isPressed;

    public CircleBtStatus() {
    }

    public CircleBtStatus(int color, int num, boolean isPressed) {
        Color = color;
        this.num = num;
        this.isPressed = isPressed;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }
}
