package com.example.storedetaildemo.common.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.storedetaildemo.R;

public class BaseView extends FrameLayout {

    // 初始化，确保只执行一次
    private boolean initFlag = false;

    public BaseView(@NonNull Context context) {
        super(context);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!initFlag) {
                    fistInit();
                    initFlag = true;
                }
            }
        });
    }

    // 只执行一次
    public void fistInit() {}

    public int getAssetImage100(String imagePath) {
        switch (imagePath) {
            case "product_100_01": return R.mipmap.product_100_01;
            case "product_100_02": return R.mipmap.product_100_02;
            case "product_100_03": return R.mipmap.product_100_03;
        }
        return R.drawable.ic_launcher_background;
    }

}
