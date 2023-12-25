package com.example.home_store_detail.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.baselibrary.util.DisplayUtils;
import com.youth.banner.indicator.BaseIndicator;

/**
 * 轮播图指示器
 */
public class MBannerIndicator extends BaseIndicator {

    public MBannerIndicator(Context context) {
        this(context, null);
    }

    public MBannerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MBannerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) {
            return;
        }

        //间距*（总数-1）+选中宽度+默认宽度*（总数-1）
        int width = (count - 1) * config.getIndicatorSpace() + config.getSelectedWidth() + config.getNormalWidth() * (count - 1);
        setMeasuredDimension(width, config.getHeight());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) {
            return;
        }

        float left = 0;
        for (int i = 0; i < count; i++) {
            int indicatorWidth = 0;
            int radius = 0;

            if (config.getCurrentPosition() == i) {
                // 选中样式
                indicatorWidth = config.getSelectedWidth();
                mPaint.setColor(config.getSelectedColor());
                radius = DisplayUtils.dp2px(getContext(),360);

                RectF rectF = new RectF(left, 0, left + indicatorWidth, config.getHeight());
                canvas.drawRoundRect(
                        rectF,
                        radius,
                        radius,
                        mPaint);

            } else {
                // 未选中样式
                indicatorWidth = config.getNormalWidth();
                mPaint.setColor(config.getNormalColor());
                radius = DisplayUtils.dp2px(getContext(),4);

                RectF rectF = new RectF(left, 0, left + indicatorWidth, config.getHeight());
                canvas.drawRoundRect(
                        rectF,
                        radius,
                        radius,
                        mPaint);
            }

            left += indicatorWidth + config.getIndicatorSpace();

        }
    }

}