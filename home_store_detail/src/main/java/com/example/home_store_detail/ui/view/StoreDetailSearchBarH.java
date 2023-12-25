package com.example.home_store_detail.ui.view;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.ShapeUtils;
import com.example.baselibrary.util.StatusBarUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailLvSearchBarHBinding;

/**
 * 搜索栏
 */
public class StoreDetailSearchBarH extends BaseView {

    private StoreDetailLvSearchBarHBinding bind;

    private LinearInterpolator interpolator;

    private StoreDetailTabPageBehaviorH tabPageBehavior;

    private float offset;
    private int[] initSearchIcBoxLayout = new int[2];

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    public StoreDetailSearchBarH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_search_bar_h, this);
        bind = StoreDetailLvSearchBarHBinding.bind(getChildAt(0));
        offset = 0f;
        interpolator = new LinearInterpolator();
    }

    public void initData(StoreDetailVMH.StoreInfoBean storeInfo) {

        GradientDrawable drawable = ShapeUtils.newShape()
                .setCornerRadius(DisplayUtils.dp2px(getContext(), 18)) // 圆角
                .setColor(getResources().getColor(com.example.baselibrary.R.color.color_F5F6F7)) // 背景
                .getDrawable();
        bind.searchHintText.setBackground(drawable);

        bind.setStoreInfo(storeInfo); // 店铺信息
        bind.backIconBox.setOnClickListener(new OnClickListener() { // 返回上一级 按钮
            @Override
            public void onClick(View v) {
                Activity context = (Activity) getContext();
                context.finish();
            }
        });

        bind.searchBar.post(new Runnable() {

            @Override
            public void run() {
                int distance = bind.searchBar.getWidth() - bind.searchIcBox.getWidth();
                initSearchIcBoxLayout[0] = bind.searchIcBox.getLeft() + distance;
                initSearchIcBoxLayout[1] = initSearchIcBoxLayout[0]  + bind.searchIcBox.getWidth();

                // 初始化隐藏搜索栏
                bind.searchIcBox.layout(initSearchIcBoxLayout[0], bind.searchIcBox.getTop(), initSearchIcBoxLayout[1], bind.searchIcBox.getBottom());
                bind.searchIcBox.setAlpha(1f);
                bind.searchHintText.layout(bind.searchHintText.getRight(), bind.searchHintText.getTop(), bind.searchHintText.getRight(), bind.searchHintText.getBottom());
                bind.searchHintText.setAlpha(1f);
            }
        });

    }

    /**
     * 改变bar背景颜色
     */
    public float updateBarColor(int dy, float offsetMax) {
        if (dy > 0 && offset == offsetMax) return 1f;
        else if (dy < 0 && offset == 0f) return 0f;

        offset += dy;
        if (offset > offsetMax) {
            offset = offsetMax;
        } else if (offset < 0) {
            offset = 0f;
        }

        float progress = (offset / offsetMax);

        // 这里一定要用 匀速插值器，如果用其他插值器，数值有波动，会导致 偏移抖动
        // 因为我偏移布局，进度用的就是这里的effect
        // 必须是 当前进度 比 上一次进度大，还需要它们之间的差值 极小且平均，只有匀速插值器 符合

        // 和插值器关联
        final float effect = interpolator.getInterpolation(progress);

        if (effect > 0.75) {
            StatusBarUtils.translucentStatusBar((Activity) getContext(), true); // 沉浸式状态栏 黑色
        } else {
            StatusBarUtils.translucentStatusBar((Activity) getContext(), false); // 沉浸式状态栏 白色
        }

        // 整体布局偏移距离 达到40%时生效，如果不想延迟，将 newEffect 改用为 effect
        final float newEnd = (float) (offsetMax * 0.4);
        final float newOffset = (float) (offset - newEnd);
        final float newStart = (float) newOffset > newEnd ? newEnd : newOffset;
        float newEffect = (newStart / newEnd) < 0 ? 0 : (newStart / newEnd);
        // Log.d("TAG", "effect：" + effect + " --- newEffect：" + newEffect);

        // 计算颜色渐变值的类 0 - 1
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        final int e = (int) argbEvaluator.evaluate(newEffect, Color.WHITE, getResources().getColor(com.example.baselibrary.R.color.color_303133));
        final int e2 = (int) argbEvaluator.evaluate(newEffect, Color.WHITE, getResources().getColor(com.example.baselibrary.R.color.color_C0C4CC));
        setBackgroundColor(
                (int) argbEvaluator.evaluate(
                        newEffect,
                        getContext().getResources().getColor(android.R.color.transparent),
                        getContext().getResources().getColor(android.R.color.white)
                )
        );

        bind.searchHintText.setTextColor((int) argbEvaluator.evaluate(
                newEffect,
                getContext().getResources().getColor(android.R.color.transparent),
                getContext().getResources().getColor(com.example.baselibrary.R.color.color_C0C4CC)
        ));

        Drawable backWhite = resDrawable(com.example.baselibrary.R.mipmap.back_white);
        Drawable searchWhite = resDrawable(com.example.baselibrary.R.mipmap.search_white);
        Drawable shareWhite = resDrawable(com.example.baselibrary.R.mipmap.share_white);
        bind.backIcon.setImageDrawable(tintDrawable(backWhite, ColorStateList.valueOf(e)));
        bind.searchIc.setImageDrawable(tintDrawable(searchWhite, ColorStateList.valueOf(e2)));
        bind.shareIcon.setImageDrawable(tintDrawable(shareWhite, ColorStateList.valueOf(e)));

        int left = (int) (bind.searchHintText.getRight() * (1 - newEffect));
        if (left > initSearchIcBoxLayout[0]) {
            left = initSearchIcBoxLayout[0];
        }
        int right = (int) left + bind.searchIcBox.getWidth();
        bind.searchIcBox.layout(left, bind.searchIcBox.getTop(), right, bind.searchIcBox.getBottom());
        bind.searchHintText.setAlpha(newEffect);
        bind.searchHintText.layout((int) (bind.searchHintText.getRight() * (1 - newEffect)), bind.searchHintText.getTop(), bind.searchHintText.getRight(), bind.searchHintText.getBottom());

        return effect;
    }

    /**
     * 将图片变色
     */
    private Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    /**
     * 获取要变色的图片资源
     */
    public Drawable resDrawable(int drawableRes) {
        Drawable drawable = getContext().getDrawable(drawableRes);

        if (drawable != null) {
            drawable.setBounds(0, 0,
                    (int) (drawable.getMinimumWidth()),
                    (int) (drawable.getMinimumHeight()));
        }
        return drawable;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 重置
        Drawable backWhite = resDrawable(com.example.baselibrary.R.mipmap.back_white);
        Drawable searchWhite = resDrawable(com.example.baselibrary.R.mipmap.search_white);
        Drawable shareWhite = resDrawable(com.example.baselibrary.R.mipmap.share_white);
        bind.backIcon.setImageDrawable(tintDrawable(backWhite, ColorStateList.valueOf(Color.WHITE)));
        bind.searchIc.setImageDrawable(tintDrawable(searchWhite, ColorStateList.valueOf(Color.WHITE)));
        bind.shareIcon.setImageDrawable(tintDrawable(shareWhite, ColorStateList.valueOf(Color.WHITE)));
    }

}
