package com.example.baselibrary.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.baselibrary.R;
import com.example.baselibrary.util.BarUtils;
import com.example.baselibrary.util.StatusBarUtils;

/**
 * 沉浸式appbar
 * 参数：
 *      activity                      ： 必填
 *      isBlack（状态栏内容颜色）        ：选填
 *
 * <xx.xx.xx.xxx.ImmersiveAppBar
 *      ... ...
 *     app: contentColor="false"
 *     tools:context=".ui.activity.MainActivity">
 *
 */
public class ImmersiveBar extends Toolbar {

    private boolean isBlack;

    public ImmersiveBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImmersiveAppBar);
        isBlack = typedArray.getBoolean(R.styleable.ImmersiveAppBar_isBlack, false);
        typedArray.recycle();

        // 状态栏内容颜色：false 白色     true 黑色
        StatusBarUtils.translucentStatusBar((Activity) context, isBlack);

        // 设置顶部内边距：状态栏高度
        setPadding(0, BarUtils.getStatusBarHeight(context), 0, 0);

        // 去除默认边距
        setContentInsetsAbsolute(0,0);
        setContentInsetsRelative(0,0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
