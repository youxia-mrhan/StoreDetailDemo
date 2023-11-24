package com.example.storedetaildemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * 获取bar相关的工具类
 */
public class BarUtils {

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        int statusBarHeight = 0;
        try{
            int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if(resId >0){
                statusBarHeight = context.getResources().getDimensionPixelSize(resId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 设置状态栏是否可见
     */
    public static void setStatusBarVisibility(@NonNull Window window, final boolean isVisible) {
        if (isVisible) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 获取标题栏高度
     * 多用ActionBar代替
     * @param context
     * @param window
     * @return
     */
    public static int getTitleBarHeight(Context context, Window window){
        int titleBarHeight = 0;
        int contentTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        titleBarHeight = getStatusBarHeight(context) - contentTop;
        return  titleBarHeight;
    }


    /**
     * 获取actionBar高度
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(
                    tv.data, context.getResources().getDisplayMetrics()
            );
        }
        return 0;
    }


    /**
     * 获取导航栏高度
     */
    public static int getNavBarHeight(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }


}

