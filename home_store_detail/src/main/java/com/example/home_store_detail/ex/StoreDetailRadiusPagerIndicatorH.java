package com.example.home_store_detail.ex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.buildins.ArgbEvaluatorHolder;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.model.PositionData;

import java.util.Arrays;
import java.util.List;

/**
 * 只有顶部有圆角的 指示器
 */
public class StoreDetailRadiusPagerIndicatorH extends View implements IPagerIndicator {
    public static final int MODE_MATCH_EDGE = 0;   // 直线宽度 == title宽度 - 2 * mXOffset
    public static final int MODE_WRAP_CONTENT = 1;    // 直线宽度 == title内容宽度 - 2 * mXOffset
    public static final int MODE_EXACTLY = 2;  // 直线宽度 == mLineWidth

    private int mMode;  // 默认为MODE_MATCH_EDGE模式

    private Interpolator mLinearInterpolator = new LinearInterpolator();

    private float mYOffset;   // 相对于底部的偏移量，如果你想让直线位于title上方，设置它即可
    private float mLineHeight;
    private float mXOffset;
    private float mLineWidth;
    private float mRoundRadius;

    private Paint mPaint;
    private List<PositionData> mPositionDataList;
    private List<Integer> mColors;

    private RectF mLineRect = new RectF();

    private String commentCount;

    public StoreDetailRadiusPagerIndicatorH(Context context, String commentCount) {
        super(context);
        this.commentCount = commentCount;
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mLineHeight = UIUtil.dip2px(context, 3);
        mLineWidth = UIUtil.dip2px(context, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 保存最原始的roundRect
        canvas.saveLayer(mLineRect, mPaint, Canvas.ALL_SAVE_FLAG);

        // 保存头部2圆角的roundRect
        canvas.drawRect(mLineRect.left, (mLineRect.top + mLineRect.bottom) / 2, mLineRect.right, mLineRect.bottom, mPaint);

        // 保存掉头部2圆角的roundRect
        canvas.drawRoundRect(mLineRect, mRoundRadius, mRoundRadius, mPaint);

        // 保存叠加后的内容
        canvas.saveLayer(mLineRect, mPaint, Canvas.ALL_SAVE_FLAG);

        // 清空所有的图像矩阵修改状态
        canvas.restore();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPositionDataList == null || mPositionDataList.isEmpty()) {
            return;
        }

        // 计算颜色
        if (mColors != null && mColors.size() > 0) {
            int currentColor = mColors.get(Math.abs(position) % mColors.size());
            int nextColor = mColors.get(Math.abs(position + 1) % mColors.size());
            int color = ArgbEvaluatorHolder.eval(positionOffset, currentColor, nextColor);
            mPaint.setColor(color);
        }

        // 计算锚点位置
        PositionData current = FragmentContainerHelper.getImitativePositionData(mPositionDataList, position);
        PositionData next = FragmentContainerHelper.getImitativePositionData(mPositionDataList, position + 1);

        float leftX = 0;
        float nextLeftX = 0;
        float rightX = 0;
        float nextRightX = 0;
        if (mMode == MODE_MATCH_EDGE) {
            leftX = current.mLeft + mXOffset;
            nextLeftX = next.mLeft + mXOffset;
            rightX = current.mRight - mXOffset;
            nextRightX = next.mRight - mXOffset;
        } else if (mMode == MODE_WRAP_CONTENT) {

            // 让指示器 处于标题前两个字 正下方
            int deviation = 0; // 这是误差值

            if (commentCount.length() == 2) { // 最小长度
                deviation = 11;
            } else if (commentCount.length() == 3) {
                deviation = 23;
            } else if (commentCount.length() == 4) {
                deviation = 30;
            } else if (commentCount.length() == 5) { // 最大长度
                deviation = 42;
            }

            if (position == 0) { // 点餐

                PositionData current01 = FragmentContainerHelper.getImitativePositionData(mPositionDataList, 0);
                PositionData next01 = FragmentContainerHelper.getImitativePositionData(mPositionDataList, 1);
                float leftX01 = current01.mContentLeft + mXOffset;
                float nextLeftX01 = next01.mContentLeft + mXOffset;
                float rightX01 = current01.mContentRight - mXOffset;
                float nextRightX01 = next01.mContentRight - mXOffset;
                leftX = leftX01;
                nextLeftX = nextLeftX01 += deviation;
                rightX = rightX01;
                nextRightX = nextRightX01 -= deviation;

            } else if (position == 1) { // 评价

                PositionData current02 = FragmentContainerHelper.getImitativePositionData(mPositionDataList, 1);
                PositionData next02 = FragmentContainerHelper.getImitativePositionData(mPositionDataList, 2);
                float leftX02 = current02.mContentLeft + mXOffset;
                float nextLeftX02 = next02.mContentLeft + mXOffset;
                float rightX02 = current02.mContentRight - mXOffset;
                float nextRightX02 = next02.mContentRight - mXOffset;
                leftX = leftX02 += deviation;
                nextLeftX = nextLeftX02;
                rightX = rightX02 -= deviation;
                nextRightX = nextRightX02;

            } else if (position == 2) { // 商家

                PositionData current03 = FragmentContainerHelper.getImitativePositionData(mPositionDataList, 2);
                PositionData next03 = FragmentContainerHelper.getImitativePositionData(mPositionDataList, 3);
                float leftX03 = current03.mContentLeft + mXOffset;
                float nextLeftX03 = next03.mContentLeft + mXOffset;
                float rightX03 = current03.mContentRight - mXOffset;
                float nextRightX03 = next03.mContentRight - mXOffset;
                leftX = leftX03;
                nextLeftX = nextLeftX03;
                rightX = rightX03;
                nextRightX = nextRightX03;

            }

        } else {    // MODE_EXACTLY
            leftX = current.mLeft + (current.width() - mLineWidth) / 2;
            nextLeftX = next.mLeft + (next.width() - mLineWidth) / 2;
            rightX = current.mLeft + (current.width() + mLineWidth) / 2;
            nextRightX = next.mLeft + (next.width() + mLineWidth) / 2;
        }

        mLineRect.left = leftX + (nextLeftX - leftX) * mLinearInterpolator.getInterpolation(positionOffset);
        mLineRect.right = rightX + (nextRightX - rightX) * mLinearInterpolator.getInterpolation(positionOffset);
        mLineRect.top = getHeight() - mLineHeight - mYOffset;
        mLineRect.bottom = getHeight() - mYOffset;

        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPositionDataProvide(List<PositionData> dataList) {
        mPositionDataList = dataList;
    }

    public float getYOffset() {
        return mYOffset;
    }

    public void setYOffset(float yOffset) {
        mYOffset = yOffset;
    }

    public float getXOffset() {
        return mXOffset;
    }

    public void setXOffset(float xOffset) {
        mXOffset = xOffset;
    }

    public float getLineHeight() {
        return mLineHeight;
    }

    public void setLineHeight(float lineHeight) {
        mLineHeight = lineHeight;
    }

    public float getLineWidth() {
        return mLineWidth;
    }

    public void setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
    }

    public float getRoundRadius() {
        return mRoundRadius;
    }

    public void setRoundRadius(float roundRadius) {
        mRoundRadius = roundRadius;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        if (mode == MODE_EXACTLY || mode == MODE_MATCH_EDGE || mode == MODE_WRAP_CONTENT) {
            mMode = mode;
        } else {
            throw new IllegalArgumentException("mode " + mode + " not supported.");
        }
    }

    public Paint getPaint() {
        return mPaint;
    }

    /**
     * 返回底部画线的RectF mLineRect。
     *
     * @return
     */
    public RectF getLineRect() {
        return mLineRect;
    }

    public List<Integer> getColors() {
        return mColors;
    }

    public void setColors(Integer... colors) {
        mColors = Arrays.asList(colors);
    }

}
