package com.example.baselibrary.util;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.regex.Pattern;

/**
 * 自定义的工具类型、方法 合集
 */
public class CustomizeUtils {

    /**
     * 获取背景颜色 值
     * @return
     */
    @SuppressLint("ResourceAsColor")
    public static int getBackgroundColor(View view) {
        int mColor = android.R.color.transparent;
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            mColor = colorDrawable.getColor();
        }
        return mColor;
    }

    /**
     * 判断是否是科学计数法 true是科学计数法,false不是科学计数法
     * 比如 6E+4
     *
     * @param number
     * @return
     */
    public static boolean isScientificNotation(String number) {
        Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)[Ee]{1}[\\+-]?[0-9]*");
        return pattern.matcher(number.trim()).matches();
    }

    /**
     * 防止快速点击
     */
    public static class AntiShake {

        private int minTime = 1000;

        // 两次点击间隔不能少于1000ms
        private int MIN_DELAY_TIME;

        private long lastClickTime = 0;

        public AntiShake(int minTime) {
            this.minTime = minTime;
            MIN_DELAY_TIME = minTime;
        }

        public boolean isFastClick() {
            boolean flag = true;
            long currentClickTime = System.currentTimeMillis();
            if ((currentClickTime - lastClickTime) < MIN_DELAY_TIME) {
                flag = false;
            }
            lastClickTime = currentClickTime;
            return flag;
        }

    }

}
