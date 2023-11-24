package com.example.storedetaildemo.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.util.BarUtils;
import com.example.storedetaildemo.util.StatusUtils;

/**
 * 沉浸式appbar
 * 参数：
 *      activity                      ： 必填
 *      contentColor（状态栏内容颜色）   ： 选填
 *
 * <com.lstm.baselibrary.widget.ImmersiveAppBar
 *      ... ...
 *     app: contentColor="false"
 *     tools:context=".ui.activity.MainActivity">
 *
 */
public class ImmersiveAppBar extends Toolbar {

    public ImmersiveAppBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImmersiveAppBar);
        boolean contentColor = typedArray.getBoolean(R.styleable.ImmersiveAppBar_contentColor, false);
        typedArray.recycle();

        // 状态栏内容颜色：false 白色     true 黑色
        StatusUtils.translucentStatusBar((Activity) context,contentColor);

        // 设置顶部内边距：状态栏高度
        setPadding(0, BarUtils.getStatusBarHeight(getContext()), 0, 0);

        // 去除默认边距
        setContentInsetsAbsolute(0,0);
        setContentInsetsRelative(0,0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
