package com.example.storedetaildemo.common;

import android.app.Application;

import com.example.storedetaildemo.util.BarUtils;
import com.example.storedetaildemo.util.ScreenUtils;

public class App extends Application {

    private static App INSTANCE;

    public static int AppStatusBarHeight;
    public static int AppScreenHeight;

    public static synchronized App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        AppStatusBarHeight = BarUtils.getStatusBarHeight(this);
        AppScreenHeight = ScreenUtils.getScreenHeight(this);
    }

}
