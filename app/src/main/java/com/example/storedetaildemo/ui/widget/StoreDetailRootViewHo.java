package com.example.storedetaildemo.ui.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.common.FlingTask;

/**
 * 这里主要功能为：
 * 1、拖动viewpager2以外的区域时，整体偏移
 * 2、将没有消耗完的惯性速度，指定 给其他滚动view
 */
public class StoreDetailRootViewHo extends CoordinatorLayout {

    boolean initFlag = false;
    private StoreDetailPagerView2Ho storeDetailList;
    final Handler mHandler;
    private final int mTouchSlop; // 移动的距离大于这个像素值的时候，会认为是在滑动
    private final int mMinimumVelocity; // 最小的速度
    private final int mMaximumVelocity; // 最大的速度
    private VelocityTracker mVelocityTracker; // 速度跟踪器
    private int mScrollPointerId; // 当前最新放在屏幕伤的手指
    private int mLastTouchX; // 上一次触摸的X坐标
    private int mLastTouchY; // 上一次触摸的Y坐标
    private int mInitialTouchX; // 初始化触摸的X坐标
    private int mInitialTouchY; // 初始化触摸的Y坐标
    public final int SCROLL_STATE_IDLE = 0; // 没有滚动
    public final int SCROLL_STATE_DRAGGING = 1; // 被手指拖动情况下滚动
    public final int SCROLL_STATE_SETTLING = 2; // 没有被手指拖动情况下的滚动
    public int mScrollState = SCROLL_STATE_IDLE; // 滚动状态

    // 在测试过程中，通过速度正负值判断方向，方向有概率不准确
    // 所以我在dispatchTouchEvent里自己处理
    public static boolean direction = true; // true：向上 false：向下

    private final AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
    private FlingTask flingTask; // 惯性任务

    public static int OTHER_EVENT_LEVEL = 1; // 正在执行viewPager2上方区域
    public static int VIEWPAGER2_EVENT_LEVEL = 2; // 正在执行viewPager2区域
    public static int NO_EVENT = 0; // 没有触摸事件

    // 由于两块触摸区域，属于兄弟级，所以同时只可以执行一个
    //    如果先拖动触发A事件，触发的同时屏蔽B事件	---	up事件后 恢复初始值
    //    反之
    //    如果先拖动触发B事件，触发的同时屏蔽A事件	---	up事件后 恢复初始值
    public static int EVENT_LEVEL = NO_EVENT; // 事件优先级

    private StoreDetailBehavior storeDetailBehavior;
    public static int viewPager2PositionY;

    public static int firstDownPositionX;
    public static int firstDownPositionY;
    private ValueAnimator valueAnimator;
    private float xVel, yVel;

    public StoreDetailRootViewHo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler(Looper.getMainLooper());
        accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();

        // 一些系统的预定义值:
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

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

    public static boolean layoutOffsetRecord = false; // 偏移记录，在up事件前，是否偏移过位置

    /**
     * 惯性任务
     *
     * @param velocityX X轴速度
     * @param velocityY Y轴速度
     * @return
     */
    private boolean fling(int velocityX, int velocityY, boolean direction) {
        if (Math.abs(velocityY) > mMinimumVelocity) {
            flingTask = new FlingTask(Math.abs(velocityY), mHandler, new FlingTask.FlingTaskCallback() {
                @Override
                public void executeTask(int dy) {
                    int tempDy = 0;
                    if (direction) { // 向上滑动
                        tempDy = dy;
                    } else { // 向下滑动
                        tempDy = -dy;
                    }
                    storeDetailBehavior.layoutFoldingFromParent(tempDy, false);
                }

                @Override
                public void stopTask() {
                    setScrollState(SCROLL_STATE_IDLE);
                }

                @Override
                public void executeConsumptionTask(int velocityY) {
                    jitterAnimation(velocityX, velocityY, direction);

                    if (direction) { // 向上滑动
                        if (storeDetailBehavior.isPagerToMax()) {
                            // Log.d("TAG", "向上滑动：未消耗速度：" + velocityY);
                            offsetScrollView(velocityY, true, true);
                        }
                    } else { // 向下滑动
                        if (storeDetailBehavior.isPagerToInit()) {
                            if (layoutOffsetRecord) {
                                // Log.d("TAG", "向下滑动：未消耗速度：" + velocityY);
                                offsetScrollView(velocityY, false, true);
                            }
                        }
                    }
                    layoutOffsetRecord = false;
                }
            });

            flingTask.run();
            setScrollState(SCROLL_STATE_SETTLING);
            return true;
        }
        return false;
    }

    private final int MAX_JITTER_VELOCITY_Y = 6000; // Y轴最大速度
    private final int JITTER_DISTANCE = 80; // 向下抖动距离

    /**
     * 公告 抖动动画
     * <p>
     * 下滑惯性 到达初始位置时执行
     */
    public void jitterAnimation(int velocityX, int velocityY, boolean direction) {
        if (Math.abs(velocityY) > MAX_JITTER_VELOCITY_Y && // 速度超过6000
                !direction && // 向下滑动
                layoutOffsetRecord) {  // 偏移记录
            startTransgression(JITTER_DISTANCE, false); // 抖动
            onStopDragging();
        }
    }

    /**
     * 停止惯性滚动任务
     */
    public void stopFling() {
        if (mScrollState == SCROLL_STATE_SETTLING) {
            if (flingTask != null) {
                flingTask.stopTask();
                setScrollState(SCROLL_STATE_IDLE);
            }
        }
    }

    /**
     * 通过拖动viewPager2上方区域，控制滚动列表
     */
    public boolean offsetScrollView(int dy, boolean direction, boolean fling) {
        return storeDetailBehavior.sharedScrollVelocity(dy, direction, fling);
    }

    public MotionEvent copyEv;
    private boolean startFling; // 开启惯性

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        boolean eventAddedToVelocityTracker = false;

        boolean canSlide = false; // 是否可以滑动 非viewPager2区域（viewPager2上方的区域）
        startFling = true;

        // 获取一个新的VelocityTracker对象来观察滑动的速度
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        // 返回正在执行的操作，不包含触摸点索引信息。即事件类型，如MotionEvent.ACTION_DOWN
        final int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();// Action的索引

        // 复制事件信息创建一个新的事件，防止被污染
        copyEv = MotionEvent.obtain(event);

        if (storeDetailBehavior == null) {
            storeDetailBehavior = storeDetailList.storeDetailBehavior;
        }
        viewPager2PositionY = storeDetailBehavior.lockScrollView();
        if ((int) copyEv.getY() < viewPager2PositionY) {
            // Log.d("TAG", "不是viewPager2区域");

            if (StoreDetailInfoHo.expandAnimatorEnd) {

                if (EVENT_LEVEL == NO_EVENT || EVENT_LEVEL == OTHER_EVENT_LEVEL) {
                    EVENT_LEVEL = OTHER_EVENT_LEVEL;
                    canSlide = true;
                }
            }
        } else {
            // Log.d("TAG", "是viewPager2区域");
            canSlide = false;

            // 正在执行 viewPager2上方区域拖动事件，
            // 但是手指坐标已经进入viewPager2范围时，此时拖动事件就会停止
            // 在这里继续执行
            if (EVENT_LEVEL == OTHER_EVENT_LEVEL) {
                canSlide = true;
            }
        }

        // 当viewPager2的transitionY为0，即为初始化位置，
        // 向上滑这个值为负数，回归初始位置为0
        // 从初始值向下滑，为正数
        // viewPager2位置在初始值下方时，我称为 越界操作

        switch (action) {
            case MotionEvent.ACTION_DOWN: { // 手指按下
                stopFling();
                firstDownPositionX = (int) event.getX();
                firstDownPositionY = (int) event.getY();
                storeDetailBehavior.parentOnDown(event);

                // 特定触摸点相关联的触摸点id,获取第一个触摸点的id
                mScrollPointerId = event.getPointerId(0);

                // 记录down事件的X、Y坐标
                mInitialTouchX = mLastTouchX = (int) (event.getX() + 0.5f);
                mInitialTouchY = mLastTouchY = (int) (event.getY() + 0.5f);
            }
            break;
            case MotionEvent.ACTION_POINTER_DOWN: { // 多个手指按下
                // 更新mScrollPointerId,表示只会响应最近按下的手势事件
                mScrollPointerId = event.getPointerId(actionIndex);

                // 更新最近的手势坐标
                mInitialTouchX = mLastTouchX = (int) (event.getX() + 0.5f);
                mInitialTouchY = mLastTouchY = (int) (event.getY() + 0.5f);
            }
            break;
            case MotionEvent.ACTION_MOVE: { // 手指移动
                setScrollState(SCROLL_STATE_DRAGGING);

                // 根据mScrollPointerId获取触摸点下标
                final int index = event.findPointerIndex(mScrollPointerId);

                // 根据move事件产生的x，y来计算偏移量dx，dy
                final int x = (int) (event.getX() + 0.5f);
                final int y = (int) (event.getY() + 0.5f);

                int dx = Math.abs(mLastTouchX - x);
                int dy = Math.abs(mLastTouchY - y);

                int curDy = 0;

                // 在手指拖动状态下滑动
                if (mScrollState == SCROLL_STATE_DRAGGING) {
                    if (mLastTouchY - y > 0.5f) {
                        direction = true;
                        curDy = dy;
                    } else if (y - mLastTouchY > 0.5f) {
                        direction = false;
                        curDy = -dy;
                    }
                }


                if (canSlide) { // 触摸viewpager2上方区域
                    // Log.d("TAG", "不是viewPager2区域");

                    int announcementY = storeDetailBehavior.getSlideAnnouncementY(); // 公告view的top位置
                    if (StoreDetailInfoHo.expandAnimatorEnd) { // 展开/收缩 公告动画停止

                        if (StoreDetailInfoHo.dragPositionState != StoreDetailInfoHo.EXPAND) { // 不是完全展开状态
                            if (direction) {
                                if (storeDetailBehavior.isPagerToMax()) { // 整体布局上拉到最大
                                    offsetScrollView(curDy, direction, false); // 触摸viewpager2上方区域，控制RecyclerView向上滚动
                                } else if (storeDetailList.getTranslationY() <= 0) { // 触摸viewpager2上方区域，控制整体布局向上滑动
                                    storeDetailBehavior.layoutFoldingFromParent(curDy, false);
                                    layoutOffsetRecord = true;
                                } else {
                                    if (y > announcementY) { // 触摸范围
                                        startTransgression(curDy, direction);   // 处于初始位置下方时，触摸viewpager2上方区域，向上滑（越界操作）
                                    }
                                }
                            } else {
                                if (storeDetailList.getTranslationY() < 0) { // 触摸viewpager2上方区域，控制整体布局下移，极限位置是初始位置
                                    storeDetailBehavior.layoutFoldingFromParent(curDy, false);
                                    layoutOffsetRecord = true;
                                } else {
                                    if (y > announcementY) { // 触摸范围
                                        startTransgression(curDy, direction);   // 处于初始位置，触摸viewpager2上方区域，向下滑（越界操作）
                                    }
                                }
                            }
                        } else { // 公告完全展开
                            if (y > announcementY) { // 触摸范围
                                // 公告展开时向上滑，关闭公告
                                if (mLastTouchY - y > 20) {
                                    storeDetailBehavior.closeAnnouncement();
                                }
                            }
                        }
                    }

                } else if (StoreDetailInfoHo.expandAnimatorEnd && // 展开/收缩 公告动画停止
                        StoreDetailInfoHo.dragPositionState != StoreDetailInfoHo.EXPAND) { // 公告状态 不是完全展开状态

                    // 触摸viewPager2区域 进行越界操作

                    if (dy > dx) { // 避免干扰 viewPager2水平滑动
                        // 当前scrollview内的item已经下拉到顶部时
                        if (!storeDetailBehavior.getmStoreDetailPager2View().getScrollableView().canScrollVertically(-1)) {
                            startTransgression(curDy, direction);
                        }
                    }
                }

                mLastTouchX = x;
                mLastTouchY = y;
            }
            break;
            case MotionEvent.ACTION_POINTER_UP: { // 多个手指离开
                // 选择一个新的触摸点来处理结局，重新处理坐标
                onPointerUp(event);
            }
            break;
            case MotionEvent.ACTION_UP: { // 手指离开，滑动事件结束
                storeDetailBehavior.parentOnUp(event);
                mVelocityTracker.addMovement(copyEv);
                eventAddedToVelocityTracker = true;

                // 计算滑动速度
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);

                // 最后一次 X/Y 轴的滑动速度
                xVel = -mVelocityTracker.getXVelocity(mScrollPointerId);
                yVel = -mVelocityTracker.getYVelocity(mScrollPointerId);

                if (startFling && // 开启惯性
                        storeDetailList.getTranslationY() <= 0 && // 非越界操作
                        Math.abs(yVel) > mTouchSlop) { // 速度大于此值，表示滑动了

                    if (canSlide && // 处于viewPager2上方的区域
                            StoreDetailInfoHo.expandAnimatorEnd && // 展开/收缩 公告动画停止
                            (StoreDetailInfoHo.dragPositionState == StoreDetailInfoHo.SHIRK)) { // 公告完全收缩

                        if (!((xVel != 0 || yVel != 0) && fling((int) xVel, (int) yVel, direction))) { // 执行惯性方法
                            setScrollState(SCROLL_STATE_IDLE); // 如果当前速度 小于最小速度，设置滑动状态为 无滚动
                        }

                    } else {
                        setScrollState(SCROLL_STATE_IDLE); // 设置滑动状态为 无滚动
                    }

                } else {
                    setScrollState(SCROLL_STATE_IDLE); // 设置滑动状态为 无滚动
                }

                resetScroll(); // 重置速度跟踪器
                EVENT_LEVEL = NO_EVENT; // 重置触摸状态

                if (storeDetailList.getTranslationY() > 0 && // 进行了越界操作
                        StoreDetailInfoHo.expandAnimatorEnd && // 展开/收缩公告动画结束
                        StoreDetailInfoHo.dragPositionState == StoreDetailInfoHo.FLOAT) { // 公告位置处于浮动
                    onStopDragging();
                }
            }
            break;
            case MotionEvent.ACTION_CANCEL: { //手势取消，释放各种资源
                cancelScroll(); // 退出滑动
            }
            break;
        }

        if (!eventAddedToVelocityTracker) {
            // 回收滑动事件，方便重用，调用此方法你不能再接触事件
            mVelocityTracker.addMovement(copyEv);
        }

        // 回收滑动事件，方便重用
        copyEv.recycle();
        return true;
    }

    /**
     * pagerView2页面从初始位置 向下拖动
     *
     * @param curDy
     * @param direction
     */
    private void startTransgression(int curDy, boolean direction) {
        if (Math.abs(yVel) > MAX_JITTER_VELOCITY_Y) { // 触发抖动
            curDy = JITTER_DISTANCE;
            yVel = 0f;
        }

        if (direction) {
            if (storeDetailList.getTranslationY() > 0) {
                storeDetailBehavior.layoutFoldingFromParent(curDy, true);
                storeDetailBehavior.trackDraggingProgress(curDy, true); // 跟随拖拽，更新公告view状态
            }
        } else {
            if (storeDetailList.getTranslationY() >= 0) {
                if (Math.abs(storeDetailList.getTranslationY()) <= storeDetailBehavior.getMaxOffsetBottom()) {
                    startFling = false;
                    storeDetailBehavior.layoutFoldingFromParent(curDy, true);
                    storeDetailBehavior.trackDraggingProgress(curDy, false); // 跟随拖拽，更新公告view状态
                }
            }
        }
    }

    /**
     * 根据拖拽的百分比，进行公告展开或收缩的过渡动画
     */
    private void onStopDragging() {
        yVel = 0f;
        if (storeDetailList.getTranslationY() <= 0) { // 当前处于初始位置
            return;
        }

        final int defaultDisplayHeight = storeDetailList.getHeight() - storeDetailBehavior.getPagerView2TopOffsetMaxDistance();
        // 滑动距离超过 mStoreContentLayoutView原始位置的30%时
        if (storeDetailList.getTranslationY() > defaultDisplayHeight * 0.3) {
            // 滑动到底部
            valueAnimator = ObjectAnimator.ofInt((int) storeDetailList.getTranslationY(), (int) storeDetailBehavior.getMaxOffsetBottom());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    float value = Float.parseFloat(animation.getAnimatedValue().toString());
                    int curDy = (int) Math.abs(value - storeDetailList.getTranslationY());
                    storeDetailList.setTranslationY(value); // storeDetailList 偏移到底部
                    storeDetailBehavior.trackDraggingProgress(curDy, false); // 跟随过渡值，展开公告
                }
            });

        } else {
            // 反之 回弹到初始位置
            valueAnimator = ObjectAnimator.ofInt(-(int) storeDetailList.getTranslationY(), 0);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    float value = Float.parseFloat(animation.getAnimatedValue().toString());
                    int curDy = (int) Math.abs(value + storeDetailList.getTranslationY());
                    storeDetailList.setTranslationY(-value); // storeDetailList 偏移到初始位置
                    storeDetailBehavior.trackDraggingProgress(curDy, true); // 跟随过渡值，收缩公告
                }
            });
        }

        valueAnimator.setDuration(250);
        valueAnimator.setInterpolator(accelerateDecelerateInterpolator);
        valueAnimator.start();
    }

    /**
     * 有新手指触摸屏幕，更新初始坐标
     *
     * @param e
     */
    private void onPointerUp(MotionEvent e) {
        final int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == mScrollPointerId) {
            // Pick a new pointer to pick up the slack.
            final int newIndex = actionIndex == 0 ? 1 : 0;
            mScrollPointerId = e.getPointerId(newIndex);
            mInitialTouchX = mLastTouchX = (int) (e.getX(newIndex) + 0.5f);
            mInitialTouchY = mLastTouchY = (int) (e.getY(newIndex) + 0.5f);
        }
    }

    /**
     * 手指离开屏幕
     */
    private void cancelScroll() {
        resetScroll();
        setScrollState(SCROLL_STATE_IDLE);
    }

    /**
     * 重置速度
     */
    private void resetScroll() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
        }
    }

    /**
     * 更新 滚动状态
     *
     * @param state
     */
    private void setScrollState(int state) {
        if (state == mScrollState) {
            return;
        }
        mScrollState = state;
    }

    /**
     * 第一次初始化
     */
    public void fistInit() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof StoreDetailPagerView2Ho) {
                storeDetailList = ((StoreDetailPagerView2Ho) getChildAt(i));
                break;
            }
        }
    }

    /**
     * 重置静态变量
     */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager2PositionY = 0;
        firstDownPositionX = 0;
        firstDownPositionY = 0;
        direction = true;
        layoutOffsetRecord = false;
    }
}


