package com.example.storedetaildemo.ui.widget;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.databinding.WidgetDetailBarHoBinding;

/**
 * 顶部bar
 */
public class StoreDetailBarHo extends FrameLayout {

    private WidgetDetailBarHoBinding binding;

    private AccelerateDecelerateInterpolator interpolator;

    public StoreDetailBehavior storeDetailBehavior;

    public void setStoreDetailBehavior(StoreDetailBehavior storeDetailBehavior) {
        this.storeDetailBehavior = storeDetailBehavior;
    }

    private float offset;

    public StoreDetailBarHo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_detail_bar_ho, this);
        binding = WidgetDetailBarHoBinding.bind(getChildAt(0));
        offset = 0f;
        interpolator = new AccelerateDecelerateInterpolator();
        initView();
    }

    private void initView() {
        binding.title.setText("1973上海老街馄炖铺");
        binding.title.setTextColor(getContext().getResources().getColor(android.R.color.transparent, null));
        binding.backIconBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity context = (Activity) getContext();
                context.finish();
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

        // 和插值器关联
        final float effect = interpolator.getInterpolation(progress);

        // 计算颜色渐变值的类 0 - 1
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        final int e = (int) argbEvaluator.evaluate(effect, Color.WHITE, (int) 0xFF303133);
        setBackgroundColor(
                (int) argbEvaluator.evaluate(
                        effect,
                        getContext().getResources().getColor(android.R.color.transparent, null),
                        getContext().getResources().getColor(android.R.color.white, null)
                )
        );

        Drawable backWhite = resDrawable(R.mipmap.back_white);
        Drawable shareWhite = resDrawable(R.mipmap.share_white);
        binding.backIcon.setImageDrawable(tintDrawable(backWhite, ColorStateList.valueOf(e)));
        binding.shareIcon.setImageDrawable(tintDrawable(shareWhite, ColorStateList.valueOf(e)));

        binding.title.setTextColor((int) argbEvaluator.evaluate(
                effect,
                getContext().getResources().getColor(android.R.color.transparent, null),
                getContext().getResources().getColor(R.color.color_303133, null)
        ));
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
        updateBarColor(-storeDetailBehavior.getPagerView2TopOffsetMaxDistance(), storeDetailBehavior.getPagerView2TopOffsetMaxDistance());
    }
}
