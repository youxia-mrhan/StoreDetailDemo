package com.example.home_store_detail.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.common.ComputeFling;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;

/**
 * 这里主要功能为：
 * 1、拖动滚动列表 以外的区域时，整体偏移
 * 2、将没有消耗完的惯性速度，指定 给其他滚动view
 * <p>
 * <p>
 * 目标区域：
 * 从 RecyclerView的 top位置 到 屏幕Top 之间的区域
 * 或者
 * 从 NestedScrollView的 top位置 到 屏幕Top 之间的区域
 * <p>
 * <p>
 * 非目标区域：就是 RecyclerView 或者 NestedScrollView 区域
 */
public class StoreDetailCoordinatorH extends CoordinatorLayout {

    boolean initFlag = false;
    private StoreDetailTabPageH storeDetailTabPageH;
    private final int mTouchSlop; // 移动的距离大于这个像素值的时候，会认为是在滑动
    private final int mMinimumVelocity; // 最小的速度
    private final int mMaximumVelocity; // 最大的速度
    private VelocityTracker mVelocityTracker; // 速度跟踪器
    private int mScrollPointerId; // 当前最新放在屏幕上的手指
    private boolean mOriginalScrollPointerUp = true; // 原来放在屏幕上的手指离开了屏幕
    private int mLastTouchX; // 上一次触摸的X坐标
    private int mLastTouchY; // 上一次触摸的Y坐标
    private int mInitialTouchX; // 初始化触摸的X坐标
    private int mInitialTouchY; // 初始化触摸的Y坐标
    public static final int SCROLL_STATE_IDLE = 0; // 没有滚动
    public static final int SCROLL_STATE_DRAGGING = 1; // 被手指拖动情况下滚动
    public static final int SCROLL_STATE_SETTLING = 2; // 没有被手指拖动情况下的滚动
    public int mScrollState = SCROLL_STATE_IDLE; // 滚动状态

    // 在测试过程中，通过速度正负值判断方向，方向有概率不准确
    // 所以我在dispatchTouchEvent里自己处理
    public static boolean direction = true; // true：向上 false：向下

    // 变化率开始和结束缓慢但从中间加速的插值器
    private final AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;

    public static final int OTHER_EVENT_LEVEL = 1; // 正在执行 目标区域
    public static final int VIEWPAGER2_EVENT_LEVEL = 2; // 正在执行 非目标区域
    public static final int NO_EVENT = 0; // 没有触摸事件

    // 由于两块触摸区域，属于兄弟级，所以同时只可以执行一个
    //    如果先拖动触发A事件，触发的同时屏蔽B事件	---	up事件后 恢复初始值
    //    反之
    //    如果先拖动触发B事件，触发的同时屏蔽A事件	---	up事件后 恢复初始值
    public static int EVENT_LEVEL = NO_EVENT; // 事件优先级

    public static StoreDetailTabPageBehaviorH tabPageBehavior;
    public static int scrollViewPositionY; // 应用于 手指点击区域 是否是在 目标区域
    public static int firstDownPositionX;
    public static int firstDownPositionY;

    private boolean jitterProtect = true; // 确保每次惯性 只触发一次抖动动画

    private ValueAnimator reboundValueAnimator; // 回弹动画对象
    private ValueAnimator flingValueAnimator; // 惯性动画对象

    public static String currentScrollViewTag;

    public StoreDetailCoordinatorH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
            stopFlingTask(); // 先清除上一次的惯性任务
            ComputeFling mFlingComputeFling = new ComputeFling(getContext());
            ComputeFling.FlingTaskInfo taskInfo = mFlingComputeFling.compute(0, velocityY, mMinimumVelocity, mMaximumVelocity);
            jitterProtect = true;

            flingValueAnimator = ValueAnimator.ofFloat(0, (float) taskInfo.getTotalDistance()).setDuration(taskInfo.getmDuration());
            flingValueAnimator.setInterpolator(ComputeFling.sQuinticInterpolator);

            flingValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setScrollState(SCROLL_STATE_SETTLING);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutOffsetRecord = false;
                    setScrollState(SCROLL_STATE_IDLE);
                    flingValueAnimator = null;
                    jitterProtect = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    layoutOffsetRecord = false;
                    setScrollState(SCROLL_STATE_IDLE);
                    flingValueAnimator = null;
                    jitterProtect = true;
                }
            });

            flingValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                float originalAnimatedValue = 0f;

                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    float animatedValue = Float.parseFloat(animation.getAnimatedValue().toString());
                    int curY = (int) (animatedValue - originalAnimatedValue);
                    // Log.d("TAG", "每次偏移的距离 curY：" + curY);
                    int tempDy = 0;
                    boolean runFling = true;
                    if (direction) { // 向上滑动
                        tempDy = curY;
                        if (!tabPageBehavior.isPagerToMax()) {
                            tabPageBehavior.layoutFoldingFromParent(tempDy, false);
                        } else {
                            runFling = offsetScrollView(tempDy, true, true);
                        }
                    } else { // 向下滑动
                        tempDy = -curY;
                        if (!tabPageBehavior.isPagerToInit()) {
                            tabPageBehavior.layoutFoldingFromParent(tempDy, false);
                        } else {
                            if (layoutOffsetRecord) {
                                if (jitterProtect && (reboundValueAnimator == null || !reboundValueAnimator.isRunning())) {
                                    jitterAnimation(velocityX, velocityY, false); // 抖动动画
                                    jitterProtect = false;
                                }
                                runFling = offsetScrollView(tempDy, false, true);
                            }
                        }
                    }
                    originalAnimatedValue = animatedValue;

                    if (!runFling) { // 当前scrollView无法继续滚动时，停止惯性任务
                        stopFlingTask();
                    }
                }
            });
            flingValueAnimator.start();
            return true;
        }
        return false;
    }

    /**
     * 停止惯性滚动任务
     */
    private void stopFlingTask() {
        if (mScrollState == SCROLL_STATE_SETTLING) {
            if (flingValueAnimator != null) {
                flingValueAnimator.setDuration(0);
                flingValueAnimator.cancel();
                flingValueAnimator = null;
            }
        }
        setScrollState(SCROLL_STATE_IDLE);
    }

    /**
     * 停止回弹动画
     */
    private void stopRebound() {
        if (StoreDetailInfoH.dragPositionState == StoreDetailInfoH.FLOAT) {
            if (reboundValueAnimator != null) {
                reboundValueAnimator.setDuration(0);
                reboundValueAnimator.cancel();
                reboundValueAnimator = null;
            }
        }
    }

    private final int MAX_JITTER_VELOCITY_Y = 6000; // Y轴最大速度
    private final int JITTER_DISTANCE = 80; // 向下抖动距离

    /**
     * 公告 抖动动画
     * <p>
     * 下滑惯性 到达初始位置时执行
     */
    public void jitterAnimation(int velocityX, int velocityY, boolean direction) {
        if (!jitterProtect) return;
        if (Math.abs(velocityY) > MAX_JITTER_VELOCITY_Y && // 速度超过6000
                !direction && // 向下滑动
                layoutOffsetRecord) {  // 偏移记录
            startTransgression(JITTER_DISTANCE, false); // 抖动
            onStopDragging();
        }
    }

    /**
     * 通过拖动 目标区域，控制滚动列表
     */
    public boolean offsetScrollView(int dy, boolean direction, boolean fling) {
        return tabPageBehavior.sharedScrollVelocity(dy, direction, fling);
    }

    public MotionEvent copyEv;
    private boolean startFling; // 开启惯性
    boolean isVertical = true; // 是否是 垂直滑动

    /**
     * 目标区域：
     * 从 RecyclerView的 top位置 到 屏幕Top 之间的区域
     * 或者
     * 从 NestedScrollView的 top位置 到 屏幕Top 之间的区域
     * <p>
     * <p>
     * 非目标区域：就是 RecyclerView 或者 NestedScrollView 区域
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        boolean eventAddedToVelocityTracker = false;

        // 是否可以滑动  true：目标区域  false 非目标区域
        boolean canSlide = false;
        startFling = true;

        // 获取一个新的VelocityTracker对象来观察滑动的速度
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        // 返回正在执行的操作，不包含触摸点索引信息。即事件类型，如MotionEvent.ACTION_DOWN
        final int action = event.getActionMasked();
        int actionIndex = event.getActionIndex(); // Action的索引

        // 复制事件信息创建一个新的事件，防止被污染
        copyEv = MotionEvent.obtain(event);

        if (tabPageBehavior == null) {
            tabPageBehavior = storeDetailTabPageH.tabPageBehavior;
        }
        scrollViewPositionY = tabPageBehavior.lockScrollView();

        final int scrollViewPositionY = tabPageBehavior.lockScrollView();
        if ((int) copyEv.getY() < scrollViewPositionY) {
            if (StoreDetailInfoH.expandAnimatorEnd) {
                if (EVENT_LEVEL == NO_EVENT || EVENT_LEVEL == OTHER_EVENT_LEVEL) {
                    EVENT_LEVEL = OTHER_EVENT_LEVEL;
                    canSlide = true;
                }
            }
        } else {
            canSlide = false;

            // 正在执行 目标区域 拖动事件，
            // 但是手指坐标已经进入 非目标区域 范围时，此时拖动事件就会停止
            // 在这里继续执行
            if (EVENT_LEVEL == OTHER_EVENT_LEVEL) {
                canSlide = true;
            }
        }

        /**
         * 当 StoreDetailTabPageH 的transitionY为0，即为初始化位置，
         * 向上滑这个值为负数，回归初始位置为0，从初始值向下滑，为正数
         *
         * StoreDetailTabPageH 位置在初始值下方时，我称为 越界操作
         */

        switch (action) {
            case MotionEvent.ACTION_DOWN: { // 手指按下
                stopFlingTask();

                firstDownPositionX = (int) event.getX();
                firstDownPositionY = (int) event.getY();
                tabPageBehavior.parentOnDown(event);

                // 特定触摸点相关联的触摸点id,获取第一个触摸点的id
                mScrollPointerId = event.getPointerId(0);
                mOriginalScrollPointerUp = false;

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
                // 替换最新触摸点的坐标，避免页面出现跳跃式偏移，比如原来的手指Y点在100，第二个手指触摸点Y点是 1000，
                // Move就会直接跳到 Y坐标 1000
                event.findPointerIndex(index); // 将坐标替换为 最新触摸点的坐标

                // 根据move事件产生的x，y来计算偏移量dx，dy
                final int x = (int) (event.getX() + 0.5f);
                final int y = (int) (event.getY() + 0.5f);

                int dx = Math.abs(mLastTouchX - x);
                int dy = Math.abs(mLastTouchY - y);

                if (mOriginalScrollPointerUp) { // 原来放在屏幕上的手指离开了屏幕
                    dy = 1; // 避免 跳跃式偏移
                    mOriginalScrollPointerUp = false;
                }

                StoreDetailViewPager2BoxH viewPager2Box = tabPageBehavior.getViewPager2Box();

                // 屏蔽 viewPager横向滑动干扰
                if (viewPager2Box.isScrollable && viewPager2Box.scroll_direction == StoreDetailViewPager2BoxH.VERTICAL) {
                    viewPager2Box.isScrollable = false; // 屏蔽 viewPager横向滑动干扰
                } else if (viewPager2Box.scroll_direction == StoreDetailViewPager2BoxH.HORIZONTAL) {
                    isVertical = false;
                }

                int curDy = 0;

                // 在手指拖动状态下滑动
                if (mScrollState == SCROLL_STATE_DRAGGING) {
                    if (mLastTouchY - y > 0.5f) {
                        direction = true; // 向上滑
                        curDy = dy;
                    } else if (y - mLastTouchY > 0.5f) {
                        direction = false; // 向下滑
                        curDy = -dy;
                    }
                }

                boolean canScroll = false; // 当前scrollview内的item已经下拉到顶部时
                View scrollableView = tabPageBehavior.getStoreDetailTabPage().getMainScrollView();
                if (scrollableView instanceof RecyclerView) {
                    canScroll = scrollableView.canScrollVertically(-1);
                } else if (scrollableView instanceof StoreDetailNestedScrollViewH) {
                    canScroll = false;
                }

                StoreDetailNestedScrollViewH rootScrollView = (StoreDetailNestedScrollViewH) tabPageBehavior.getStoreDetailTabPage().getRootScrollView(); // 点餐/评价/商家 根view
                int carouselTranslationY = (int) rootScrollView.getTranslationY();

                if (canSlide) { // 触摸 目标区域
                    // Log.d("TAG", "目标区域");

                    int announcementY = tabPageBehavior.getSlideAnnouncementY(); // 公告view的top位置
                    if (StoreDetailInfoH.expandAnimatorEnd && // 展开/收缩 公告动画停止
                            !StoreDetailSpecificationWindowH.isShowWindow && // 商品规格对话框 消失
                            (!StoreDetailShoppingCartWindowH.isExpand && StoreDetailShoppingCartWindowH.windowAnimatorEnd) // 购物车对话框处于收缩状态，和动画停止
                    ) {

                        if (StoreDetailInfoH.dragPositionState != StoreDetailInfoH.EXPAND) { // 不是完全展开状态
                            if (direction) {
                                if (tabPageBehavior.isPagerToMax()) { // 整体布局上拉到最大
                                    offsetScrollView(curDy, direction, false); // 触摸 目标区域，控制RecyclerView向上滚动
                                } else if (storeDetailTabPageH.getTranslationY() <= 0) { // 触摸 目标区域，控制整体布局向上滑动
                                    if (isVertical) {
                                        tabPageBehavior.layoutFoldingFromParent(curDy, false);
                                        layoutOffsetRecord = true;
                                    }
                                } else {
                                    if ((y > announcementY) && // 触摸范围
                                            !canScroll && // 当前scrollview内的item已经下拉到顶部时
                                            Math.abs(carouselTranslationY) == 0) { // 轮播图 / 评分容器 已经完全展开
                                        startTransgression(curDy, direction);   // 处于初始位置下方时，触摸 目标区域，向上滑（越界操作）
                                    }
                                }
                            } else {
                                if ((storeDetailTabPageH.getTranslationY() < 0) && // 触摸 目标区域，控制整体布局下移，极限位置是初始位置
                                        Math.abs(carouselTranslationY) == 0 && // 轮播图 / 评分容器 已经完全展开
                                        !canScroll) { // 当前scrollview内的item已经下拉到顶部时
                                    if (isVertical) {
                                        tabPageBehavior.layoutFoldingFromParent(curDy, false);
                                        layoutOffsetRecord = true;
                                    }
                                } else {
                                    if ((y > announcementY) && // 触摸范围
                                            !canScroll && // 当前scrollview内的item已经下拉到顶部时
                                            Math.abs(carouselTranslationY) == 0) { // 轮播图 / 评分容器 已经完全展开
                                        startTransgression(curDy, direction);   // 处于初始位置，触摸 目标区域，向下滑（越界操作）
                                    } else {
                                        if (Math.abs(carouselTranslationY) != 0 && // 轮播图 / 评分容器 没有完全展开
                                                !canScroll) { // 当前scrollview内的item已经下拉到顶部时
                                            int curY = carouselTranslationY + Math.abs(dy);
                                            if (curY > 0) {
                                                curY = 0;
                                            }
                                            rootScrollView.setTranslationY(curY); // 开始展开 轮播图 / 评分容器
                                        } else if (canScroll) { // 当前scrollview内的item 没有下拉到顶部时
                                            offsetScrollView(curDy, false, false);
                                        }
                                    }
                                }
                            }
                        } else { // 公告完全展开
                            if (y > announcementY) { // 触摸范围
                                // 公告展开时向上滑，关闭公告
                                if (mLastTouchY - y > 20) {
                                    tabPageBehavior.closeAnnouncement();
                                }
                            }
                        }
                    }

                } else if (StoreDetailInfoH.expandAnimatorEnd && // 展开/收缩 公告动画停止
                        !StoreDetailSpecificationWindowH.isShowWindow && // 商品规格对话框 消失
                        (!StoreDetailShoppingCartWindowH.isExpand && StoreDetailShoppingCartWindowH.windowAnimatorEnd) && // 购物车对话框处于收缩状态，和动画停止
                        StoreDetailInfoH.dragPositionState != StoreDetailInfoH.EXPAND) { // 公告状态 不是完全展开状态
                    // 触摸 非目标区域 进行越界操作
                    if (isVertical) {
                        if (!canScroll && Math.abs(carouselTranslationY) == 0) { // 轮播图 / 评分容器 完全展开，才可以拖动公告
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
                mOriginalScrollPointerUp = true;
            }
            break;
            case MotionEvent.ACTION_UP: { // 手指离开，滑动事件结束
                tabPageBehavior.parentOnUp(event);
                mVelocityTracker.addMovement(copyEv);
                eventAddedToVelocityTracker = true;

                // 计算滑动速度
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);

                // 最后一次 X/Y 轴的滑动速度
                final float xVel = -mVelocityTracker.getXVelocity(mScrollPointerId);
                final float yVel = -mVelocityTracker.getYVelocity(mScrollPointerId);

                if (startFling && // 开启惯性
                        isVertical &&
                        !StoreDetailSpecificationWindowH.isShowWindow && // 商品规格对话框 消失
                        (!StoreDetailShoppingCartWindowH.isExpand && StoreDetailShoppingCartWindowH.windowAnimatorEnd) && // 购物车对话框处于收缩状态，和动画停止
                        storeDetailTabPageH.getTranslationY() <= 0 && // 非越界操作
                        Math.abs(yVel) > mTouchSlop) { // 速度大于此值，表示滑动了

                    if (canSlide && // 处于 目标区域
                            StoreDetailInfoH.expandAnimatorEnd && // 展开/收缩 公告动画停止
                            (StoreDetailInfoH.dragPositionState == StoreDetailInfoH.SHIRK)) { // 公告完全收缩

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

                if (storeDetailTabPageH.getTranslationY() > 0 && // 进行了越界操作
                        StoreDetailInfoH.expandAnimatorEnd && // 展开/收缩公告动画结束
                        !StoreDetailSpecificationWindowH.isShowWindow && // 商品规格对话框 消失
                        (!StoreDetailShoppingCartWindowH.isExpand && StoreDetailShoppingCartWindowH.windowAnimatorEnd) && // 购物车对话框处于收缩状态，和动画停止
                        StoreDetailInfoH.dragPositionState == StoreDetailInfoH.FLOAT) { // 公告位置处于浮动
                    onStopDragging();
                }
                isVertical = true;
                tabPageBehavior.getViewPager2Box().isScrollable = true;
            }
            break;
            case MotionEvent.ACTION_CANCEL: { //手势取消，释放各种资源
                cancelScroll(); // 退出滑动
                isVertical = true;
                tabPageBehavior.getViewPager2Box().isScrollable = true;
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
     * StoreDetailTabPageH 页面从初始位置 向下拖动
     *
     * @param curDy
     * @param direction
     */
    private void startTransgression(int curDy, boolean direction) {
        if (!isVertical) return;
        if (direction) {
            if (storeDetailTabPageH.getTranslationY() > 0) {
                tabPageBehavior.layoutFoldingFromParent(curDy, true);
                tabPageBehavior.trackDraggingProgress(curDy, true); // 跟随拖拽，更新公告view状态
            }
        } else {
            if (storeDetailTabPageH.getTranslationY() >= 0) {
                if (Math.abs(storeDetailTabPageH.getTranslationY()) <= tabPageBehavior.getMaxOffsetBottom()) {
                    startFling = false;
                    tabPageBehavior.layoutFoldingFromParent(curDy, true);
                    tabPageBehavior.trackDraggingProgress(curDy, false); // 跟随拖拽，更新公告view状态
                }
            }
        }
    }

    /**
     * 根据拖拽的百分比，进行公告展开或收缩的过渡动画
     */
    private void onStopDragging() {
        if (storeDetailTabPageH.getTranslationY() <= 0) { // 当前处于初始位置
            return;
        }

        final int defaultDisplayHeight = storeDetailTabPageH.getHeight() - tabPageBehavior.getTabPageTopOffsetMaxDistance();
        // 滑动距离超过 StoreDetailTabPageH页面 原始位置的30%时
        if (storeDetailTabPageH.getTranslationY() > defaultDisplayHeight * 0.3) {
            // 滑动到底部
            reboundValueAnimator = ObjectAnimator.ofInt((int) storeDetailTabPageH.getTranslationY(), (int) tabPageBehavior.getMaxOffsetBottom());
            reboundValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    float value = Float.parseFloat(animation.getAnimatedValue().toString());
                    int curDy = (int) Math.abs(value - storeDetailTabPageH.getTranslationY());
                    storeDetailTabPageH.setTranslationY(value); // StoreDetailTabPageH 页面偏移到底部
                    tabPageBehavior.trackDraggingProgress(curDy, false); // 跟随过渡值，展开公告
                }
            });

        } else {
            // 反之 回弹到初始位置
            reboundValueAnimator = ObjectAnimator.ofInt(-(int) storeDetailTabPageH.getTranslationY(), 0);
            reboundValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    float value = Float.parseFloat(animation.getAnimatedValue().toString());
                    int curDy = (int) Math.abs(value + storeDetailTabPageH.getTranslationY());
                    storeDetailTabPageH.setTranslationY(-value); // StoreDetailTabPageH 页面偏移到初始位置
                    tabPageBehavior.trackDraggingProgress(curDy, true); // 跟随过渡值，收缩公告
                }
            });
        }

        reboundValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                reboundValueAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                reboundValueAnimator = null;
            }
        });

        reboundValueAnimator.setInterpolator(accelerateDecelerateInterpolator);
        reboundValueAnimator.start();
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
            if (getChildAt(i) instanceof StoreDetailTabPageH) {
                storeDetailTabPageH = ((StoreDetailTabPageH) getChildAt(i));
                break;
            }
        }
    }

    /**
     * 获取底部购物车 在屏幕中的位置
     *
     * @return
     */
    public static int[] getCartViewLocalPosition() {
        int[] location = new int[2];
        if (tabPageBehavior == null) return location;
        StoreDetailShoppingCartH cartView = tabPageBehavior.getStoreDetailShoppingCart();
        cartView.getLocationOnScreen(location);
        return location;
    }

    /**
     * 创建商品规格 对话框
     *
     * @param productBean
     */
    public static void createSpecificationWindow(StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean, int storeId) {
        if (tabPageBehavior == null) return;
        tabPageBehavior.createSpecificationWindow(productBean, storeId);
    }

    /**
     * 删除商品规格 对话框
     */
    public static void removeSpecificationWindow() {
        if (tabPageBehavior == null) return;
        tabPageBehavior.removeSpecificationWindow();
    }

    /**
     * 重置静态变量
     */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopFlingTask();
        stopRebound();
        scrollViewPositionY = 0;
        firstDownPositionX = 0;
        firstDownPositionY = 0;
        direction = true;
        layoutOffsetRecord = false;
        tabPageBehavior = null;
        currentScrollViewTag = null;
        EVENT_LEVEL = NO_EVENT;
    }

}
