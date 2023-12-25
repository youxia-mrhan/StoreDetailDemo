package com.example.baselibrary.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baselibrary.R;

public class BaseView extends FrameLayout {

    private boolean initFlag = false; // 初始化，确保只执行一次
    private boolean initWindowFocusFlag = false; // 初始化，确保只执行一次

    public BaseView(@NonNull Context context) {
        super(context);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!initFlag) {
                    fistInitTreeObserver();
                    initFlag = true;
                }
            }
        });
    }

    // 只执行一次的方法
    public void fistInitTreeObserver() {}

    // 这个方法在 getViewTreeObserver 之后执行
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!initWindowFocusFlag) {
            fistInitWindowFocus();
            initWindowFocusFlag = true;
        }
    }

    // 只执行一次的方法
    public void fistInitWindowFocus() {}

    public static int getStoreAssetImage160(String imagePath) {
        switch (imagePath) {
            case "store_160_img01": return R.mipmap.store_160_img01;
            case "store_160_img02": return R.mipmap.store_160_img02;
            case "store_160_img03": return R.mipmap.store_160_img03;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getAssetImage332(String imagePath) {
        switch (imagePath) {
            case "hot_332_test01": return R.mipmap.hot_332_test01;
            case "hot_332_test02": return R.mipmap.hot_332_test02;
            case "hot_332_test03": return R.mipmap.hot_332_test03;
            case "hot_332_test04": return R.mipmap.hot_332_test04;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getAssetImage240(String imagePath) {
        switch (imagePath) {
            case "welfare_240_test01": return R.mipmap.welfare_240_test01;
            case "welfare_240_test02": return R.mipmap.welfare_240_test02;
            case "welfare_240_test03": return R.mipmap.welfare_240_test03;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getAssetImage200(String imagePath) {
        switch (imagePath) {
            case "store_200_test01": return R.mipmap.store_200_test01;
            case "store_200_test02": return R.mipmap.store_200_test02;
            case "store_200_test03": return R.mipmap.store_200_test03;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getAssetImage160(String imagePath) {
        switch (imagePath) {
            case "order_160_test01": return R.mipmap.order_160_test01;
            case "order_160_test02": return R.mipmap.order_160_test02;
            case "order_160_test03": return R.mipmap.order_160_test03;
            case "order_160_test04": return R.mipmap.order_160_test04;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getAssetImage100(String imagePath) {
        switch (imagePath) {
            case "product_100_01": return R.mipmap.product_100_01;
            case "product_100_02": return R.mipmap.product_100_02;
            case "product_100_03": return R.mipmap.product_100_03;
            case "product_100_04": return R.mipmap.product_100_04;
            case "product_100_05": return R.mipmap.product_100_05;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getAssetImage70(String imagePath) {
        switch (imagePath) {
            case "store_70_img": return R.mipmap.store_70_img;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getLevelImage(int level) {
        switch (level) {
            case 0: return R.mipmap.blessing;
            case 1: return R.mipmap.lu;
            case 2: return R.mipmap.life;
            case 3: return R.mipmap.jubilee;
        }
        return R.drawable.ic_launcher_background;
    }

    public static int getMerchantImage(String imagePath) {
        switch (imagePath) {
            case "merchant_image01": return R.mipmap.merchant_image01;
            case "merchant_image02": return R.mipmap.merchant_image02;
            case "merchant_image03": return R.mipmap.merchant_image03;
            case "merchant_image04": return R.mipmap.merchant_image04;
        }
        return R.mipmap.merchant_image01;
    }

    public static int getCarouselAssetImage(String imagePath) {
        switch (imagePath) {
            case "carousel_image01": return R.mipmap.store_carousel;
            case "carousel_image02": return R.mipmap.store_carousel02;
            case "carousel_image03": return R.mipmap.store_carousel03;
            case "carousel_image04": return R.mipmap.store_carousel04;
            case "carousel_image05": return R.mipmap.store_carousel05;
        }
        return R.drawable.ic_launcher_background;
    }

}
