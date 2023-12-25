package com.example.home_store_detail.common;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/**
 * 计算出当前惯性，产生的总距离 和 持续时间
 * 再配合ValueAnimator使用，实现惯性效果
 */
public class ComputeFling {

    private static final float INFLEXION = 0.35f; // Tension lines cross at (INFLEXION, 1)
    private static final int NB_SAMPLES = 100;

    // 惯施加在滚动和甩动上的摩擦量。
    // 返回值：表示摩擦系数的标量无量纲值。
    // Fling friction
    private float mFlingFriction = ViewConfiguration.getScrollFriction();

    // A context-specific coefficient adjusted to physical values.
    private float mPhysicalCoeff;

    // 减速率
    private float DECELERATION_RATE = (float) (Math.log(0.78) / Math.log(0.9));

    // Distance to travel along spline animation
    private int mSplineDistance; // 惯性产生的总距离

    // Duration to complete spline component of animation
    private int mSplineDuration; // 惯性持续时间

    // Initial velocity
    private int mVelocity; // 速度

    // Current velocity
    private float mCurrVelocity;

    // Animation starting time, in system milliseconds
    private long mStartTime;

    // Animation duration, in milliseconds
    private int mDuration;

    public ComputeFling(Context context) {
        // 像素密度
        final float ppi = context.getResources().getDisplayMetrics().density * 160.0f;
        mPhysicalCoeff = SensorManager.GRAVITY_EARTH // g (m/s^2) 重力加速度单位：m/s²；重力加速度是一个物体受重力作用的情况下所具有的加速度，也叫自由落体加速度，用g表示。
                * 39.37f // inch/meter
                * ppi
                * 0.84f; // look and feel tuning
    }

    public FlingTaskInfo compute(int start, int velocity, int min, int max) {
        mCurrVelocity = mVelocity = velocity;
        mDuration = mSplineDuration = 0;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();

        double totalDistance = 0.0;

        if (velocity != 0) {
            mDuration = mSplineDuration = getSplineFlingDuration(velocity);
            totalDistance = getSplineFlingDistance(velocity);
            return new FlingTaskInfo(mDuration, totalDistance);
        }
        return new FlingTaskInfo(0, 0);
    }

    private double getSplineDeceleration(int velocity) {
        // Math.abs：获取绝对值
        // Math.log：计算对数
        return Math.log(INFLEXION * Math.abs(velocity) / (mFlingFriction * mPhysicalCoeff));
    }

    /**
     * @param velocity 速度
     * @return 当前惯性持续时间
     */
    private int getSplineFlingDuration(int velocity) {
        final double l = getSplineDeceleration(velocity);
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        // 计算指数函数
        return (int) (1000.0 * Math.exp(l / decelMinusOne));
    }

    /**
     * @param velocity 速度
     * @return 当前惯性产生的总距离
     */
    private double getSplineFlingDistance(int velocity) {
        final double l = getSplineDeceleration(velocity);
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        return mFlingFriction * mPhysicalCoeff * Math.exp(DECELERATION_RATE / decelMinusOne * l);
    }

    /**
     * 惯性参数 实体
     */
    public class FlingTaskInfo {
        private int mDuration;  // 本次惯性滚动 持续的时间
        private double totalDistance; // 本次惯性滚动，总共滚动的距离

        public FlingTaskInfo(int mDuration, double totalDistance) {
            this.mDuration = mDuration;
            this.totalDistance = totalDistance;
        }

        public int getmDuration() {
            return mDuration;
        }

        public double getTotalDistance() {
            return totalDistance;
        }

        @Override
        public String toString() {
            return "FlingTaskInfo{" +
                    "mDuration=" + mDuration +
                    ", totalDistance=" + totalDistance +
                    '}';
        }
    }

    /**
     * 递减插值器
     */
    public static final Interpolator sQuinticInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };

}
