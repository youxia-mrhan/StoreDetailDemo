package com.example.home_store_detail.behavior;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.util.ScreenUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.ui.view.StoreDetailCoordinatorH;
import com.example.home_store_detail.ui.view.StoreDetailInfoH;
import com.example.home_store_detail.ui.view.StoreDetailNestedScrollViewH;
import com.example.home_store_detail.ui.view.StoreDetailSearchBarH;
import com.example.home_store_detail.ui.view.StoreDetailShoppingCartH;
import com.example.home_store_detail.ui.view.StoreDetailShoppingCartWindowH;
import com.example.home_store_detail.ui.view.StoreDetailSpecificationWindowH;
import com.example.home_store_detail.ui.view.StoreDetailTabPageH;
import com.example.home_store_detail.ui.view.StoreDetailViewPager2BoxH;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.provider.common.App;

/**
 * 协调布局 行为对象
 * 功能：布局偏移业务
 */
public class StoreDetailTabPageBehaviorH extends CoordinatorLayout.Behavior<StoreDetailTabPageH> {

    private StoreDetailInfoH storeDetailInfo; // 店铺信息
    private StoreDetailTabPageH storeDetailTabPage; // 点餐/评价/商家 容器
    private StoreDetailSearchBarH storeDetailSearchBar; // 搜索栏
    private StoreDetailShoppingCartH storeDetailShoppingCart; // 购物车
    private boolean fistInit = false; // 第一次初始化
    private int tabPageDefaultPositionDistance; // 点餐/评价/商家 容器初始化位置距离
    private int tabPageTopOffsetMaxDistance; // 点餐/评价/商家 容器向上最大偏移距离
    private int mVerticalPagingTouch = 0; // 点餐/评价/商家 容器 垂直触摸滑动距离
    private boolean mIsFling = false; // 惯性滚动
    private boolean mIsFlingAndDown = false; // 是否处于惯性滑动且有触摸动作插入
    private final Handler mHandler = new Handler(); // 更新UI
    private boolean isPagerToMax = false; // 点餐/评价/商家 容器，向上偏移至最大值
    private boolean isPagerToInit = true; // 点餐/评价/商家 容器，偏移至初始值
    private int mFoldingDy = 0; // 整体偏移量
    private StoreDetailViewPager2BoxH viewPager2Box; // 点餐/评价/商家 的根容器
    private int maxOffsetBottom; // 点餐/评价/商家 容器 向下偏移到屏幕外的最大距离
    private StoreDetailCoordinatorH rootCoordinator; // 根布局
    private int slideAnnouncementY; // 可以滑动下拉公告的起点范围
    private int startVelocityX, startVelocityY = 0; // 开始速度

    public StoreDetailViewPager2BoxH getViewPager2Box() {
        return viewPager2Box;
    }

    public StoreDetailTabPageH getStoreDetailTabPage() {
        return storeDetailTabPage;
    }

    public StoreDetailShoppingCartH getStoreDetailShoppingCart() {
        return storeDetailShoppingCart;
    }

    public int getTabPageTopOffsetMaxDistance() {
        return tabPageTopOffsetMaxDistance;
    }

    public int getMaxOffsetBottom() {
        return maxOffsetBottom;
    }

    public boolean isPagerToMax() {
        return isPagerToMax;
    }

    public boolean isPagerToInit() {
        return isPagerToInit;
    }

    public int getSlideAnnouncementY() {
        return slideAnnouncementY;
    }

    /**
     * 一定要写构造器，CoordinatorLayout是通过反射创建对象的
     */
    public StoreDetailTabPageBehaviorH(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 嵌套滑动开始（ACTION_DOWN），确定 Behavior 是否要监听此次事件
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        // Log.d("TAG", "onStartNestedScroll：" + StoreDetailCoordinatorH.currentScrollViewTag + "---" + SystemClock.elapsedRealtime() + "---" + type + "---" + type);
        if (mIsFling && type == ViewCompat.TYPE_TOUCH) {
            // 处于惯性滑动时有触摸动作插入
            mIsFlingAndDown = true;
        }
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * 确定使用BehaviorView初始化参数设置，位置、高度等
     */
    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull StoreDetailTabPageH child, int layoutDirection) {
        // Log.d("TAG", "onLayoutChild："+ StoreDetailCoordinatorH.currentScrollViewTag +  "---"+ SystemClock.elapsedRealtime());

        rootCoordinator = (StoreDetailCoordinatorH) parent;

        child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (fistInit) return;
                fistInit = true;
                storeDetailTabPage = child;
                viewPager2Box = child.findViewById(R.id.vp2_box);

                storeDetailSearchBar.setTabPageBehavior(StoreDetailTabPageBehaviorH.this);
                storeDetailInfo.setTabPageBehavior(StoreDetailTabPageBehaviorH.this);
                storeDetailTabPage.setTabPageBehavior(StoreDetailTabPageBehaviorH.this);
                storeDetailShoppingCart.setTabPageBehavior(StoreDetailTabPageBehaviorH.this);

                CoordinatorLayout.LayoutParams tabPageLayoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

                // 点餐/评论/商家 容器 高度，此时它还没有布局完成
                tabPageLayoutParams.height = ScreenUtils.getScreenHeight(App.getInstance());

                tabPageDefaultPositionDistance = storeDetailInfo.getTabPageDefaultMarginTop(); // 点餐/评价/商家 容器 初始位置
                tabPageTopOffsetMaxDistance = tabPageDefaultPositionDistance - storeDetailSearchBar.getHeight(); // 点餐/评价/商家 容器向上最大偏移距离
                tabPageLayoutParams.topMargin = tabPageDefaultPositionDistance;
                child.setLayoutParams(tabPageLayoutParams);

                // 点餐/评价/商家 容器 向下偏移到屏幕外的距离
                maxOffsetBottom = storeDetailInfo.getMaxExpandDistance();

                // 可以拖拽下拉公告的起点范围
                slideAnnouncementY = storeDetailInfo.getSlideAnnouncementY();
            }
        });
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull StoreDetailTabPageH child, @NonNull View dependency) {
        // Log.d("TAG","layoutDependsOn："+ StoreDetailCoordinatorH.currentScrollViewTag +  "---"+ SystemClock.elapsedRealtime());

        if (dependency.getId() == R.id.info) {
            storeDetailInfo = (StoreDetailInfoH) dependency;
            storeDetailInfo.animListener = mAnimListener();
        } else if (dependency.getId() == R.id.search_bar) {
            storeDetailSearchBar = (StoreDetailSearchBarH) dependency;
        } else if (dependency.getId() == R.id.shopping_cart) {
            storeDetailShoppingCart = (StoreDetailShoppingCartH) dependency;
        } else {
            return false;
        }
        return true;
    }

    /**
     * 惯性滑动准备开始
     */
    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, float velocityX, float velocityY) {
        // Log.d("TAG","onNestedPreFling："+ StoreDetailCoordinatorH.currentScrollViewTag +  "---"+ SystemClock.elapsedRealtime());

        mIsFling = true;
        mIsFlingAndDown = false;

        startVelocityX = (int) velocityX;
        startVelocityY = (int) velocityY;

        // 默认返回false，表示执行惯性
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    /**
     * 嵌套滚动中
     * <p>
     * 核心业务代码
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // Log.d("TAG", "onNestedPreScroll：" + StoreDetailCoordinatorH.currentScrollViewTag + "---" + SystemClock.elapsedRealtime() + "---" + type);

        try {
            child.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

            mVerticalPagingTouch += dy;
            if (viewPager2Box.isScrollable && viewPager2Box.scroll_direction == StoreDetailViewPager2BoxH.VERTICAL) {
                viewPager2Box.isScrollable = false; // 屏蔽 viewPager横向滑动干扰
            } else if (viewPager2Box.scroll_direction == StoreDetailViewPager2BoxH.HORIZONTAL) {
                consumed[1] = dy;
                return;
            }

            if (Math.abs(child.getTranslationY()) == tabPageTopOffsetMaxDistance) { // 点餐/评论/商家 容器，向上偏移至最大值
                isPagerToMax = true;
            } else {
                isPagerToMax = false;
            }

            if (Math.abs(child.getTranslationY()) == 0) { // 点餐/评论/商家 容器，偏移至初始值
                isPagerToInit = true;
            } else {
                isPagerToInit = false;
            }

            StoreDetailNestedScrollViewH rootScrollView = (StoreDetailNestedScrollViewH) child.getRootScrollView();
            int carouselTranslationY = (int) rootScrollView.getTranslationY(); // 点餐/评价/商家 根View
            int pageDecorationHeight = child.getTabPageDecorationHeight(); // 轮播图 / 评分容器 高度

            if (type == ViewCompat.TYPE_NON_TOUCH && (isPagerToInit || isPagerToMax)) { // 惯性滚动 && (整体布局向上偏移至最大 || 整体布局偏移至初始位置)

                // 向上滑
                if ((child.getScrollableView() instanceof RecyclerView) &&
                        !child.getScrollableView().canScrollVertically(1) && // 当前scrollview内的item已经上拉到底部时
                        StoreDetailCoordinatorH.direction) {

                    if (Math.abs(carouselTranslationY) == pageDecorationHeight) { // 轮播图 / 评分容器 已经完全收缩
                        // Log.d("TAG","向上滑 滑动到底部，关闭嵌套滚动");
                        child.onScrollStopNested();
                    }

                } else if ((child.getScrollableView() instanceof StoreDetailNestedScrollViewH) && // 商家 根View
                        StoreDetailCoordinatorH.direction) {
                    child.onScrollStopNested();
                }

                // 向下滑
                if ((child.getScrollableView() instanceof RecyclerView) &&
                        (child.getScrollableView().canScrollVertically(1) &&
                                !child.getScrollableView().canScrollVertically(-1)) && // 当前scrollview内的item已经下拉到顶部时
                        !StoreDetailCoordinatorH.direction) {

                    if (Math.abs(carouselTranslationY) == 0) { // 轮播图 / 评分容器 已经完全展开
                        if (isPagerToInit) { // 点餐/评论/商家 容器，偏移至初始值
                            int remainingV = Math.abs(startVelocityY - mVerticalPagingTouch);
                            rootCoordinator.jitterAnimation(startVelocityX, remainingV, StoreDetailCoordinatorH.direction); // 抖动动画
                        }
                        // Log.d("TAG","向下滑 滑动到顶部，关闭嵌套滚动");
                        child.onScrollStopNested();
                    }
                } else if ((child.getScrollableView() instanceof StoreDetailNestedScrollViewH) && // 商家 根View
                        !StoreDetailCoordinatorH.direction) {
                    if (Math.abs(carouselTranslationY) == 0) { // 轮播图 / 评分容器 已经完全展开
                        if (isPagerToInit) { // 点餐/评论/商家 容器，偏移至初始值
                            int remainingV = Math.abs(startVelocityY - mVerticalPagingTouch);
                            rootCoordinator.jitterAnimation(startVelocityX, remainingV, StoreDetailCoordinatorH.direction); // 抖动动画
                        }
                        // Log.d("TAG","向下滑 滑动到顶部，关闭嵌套滚动");
                        child.onScrollStopNested();
                    }
                }
            }

            if (type == ViewCompat.TYPE_NON_TOUCH && mIsFlingAndDown) {
                //当处于惯性滑动时，有触摸动作进入，屏蔽惯性滑动，以防止滚动错乱
                consumed[1] = dy;
                return;
            }

            boolean updateViewState = false; // 整体布局是否可以偏移
            boolean startTransgression = false; // 公告是否可以拖拽

            if (StoreDetailCoordinatorH.direction) { // 向上滑动

                if (Math.abs(child.getTranslationY()) != tabPageTopOffsetMaxDistance && // 点餐/评价/商家 容器 没有偏移到最大值之前
                        child.getTranslationY() <= 0) { // 点餐/评价/商家 容器 在初始位置 或者 在初始位置上方
                    // 一直消耗当前scrollview滚动值
                    updateViewState = true;

                } else if (child.getTranslationY() > 0 && // 位置在初始位置下方
                        type == ViewCompat.TYPE_TOUCH) { // 被手指拖动
                    // Log.d("TAG", "从初始位置下方向上滑");
                    startTransgression = true;
                }

            } else { // 向下滑动

                if (!child.getScrollableView().canScrollVertically(-1) && // 当前scrollview内的item已经下拉到顶部时
                        child.getTranslationY() < 0 && // 点餐/评价/商家 容器 不在初始位置
                        carouselTranslationY == 0) { // 轮播图 / 评分容器 已经完全展开
                    // 整体布局向下偏移
                    updateViewState = true;

                    if (type == ViewCompat.TYPE_NON_TOUCH && // 惯性
                            isPagerToMax && // 点餐/评价/商家 容器 向上偏移到最大
                            !StoreDetailCoordinatorH.direction) { // 向下滑动
                        updateViewState = false;
                    }

                } else if (!child.getScrollableView().canScrollVertically(-1) && // 当前scrollview内的item已经下拉到顶部时
                        child.getTranslationY() >= 0 && // 在初始位置 或者 在初始位置下方
                        type == ViewCompat.TYPE_TOUCH && // 被手指拖动
                        carouselTranslationY == 0) { // 轮播图 / 评分容器 已经完全展开
                    // Log.d("TAG", "初始位置向下拉");
                    startTransgression = true;
                }

            }

            if (updateViewState) {
                // 顶部bar 跟随变色
                final float effect = storeDetailSearchBar.updateBarColor(dy, tabPageTopOffsetMaxDistance);
                final float transY = -tabPageTopOffsetMaxDistance * effect;

                // 店铺信息 跟随偏移
                storeDetailInfo.setTranslationY(transY);

                if (transY != child.getTranslationY()) {
                    child.setTranslationY(transY); //
                    consumed[1] = dy;
                    StoreDetailCoordinatorH.layoutOffsetRecord = true;
                }

            } else if (startTransgression) { // 手指触摸 StoreDetailTabPageH 页面从初始位置 向下拖动
                // 拖动公告功能，已经在StoreDetailCoordinatorH中统一处理，这里留作 业务扩展

                consumed[1] = dy;
            } else {

                // 轮播图折叠
                if (StoreDetailCoordinatorH.direction) { // 向上滑动
                    if (Math.abs(carouselTranslationY) != pageDecorationHeight) { // 没有折叠
                        consumed[1] = dy;
                        float curY = carouselTranslationY - dy;
                        if (Math.abs(curY) > pageDecorationHeight) {
                            curY = -pageDecorationHeight;
                        }
                        rootScrollView.setTranslationY(curY); // 开始收缩 轮播图 / 评分容器
                    } else { // 已经折叠完成
                        if (type == ViewCompat.TYPE_NON_TOUCH) { // 惯性
                            sharedScrollVelocity(dy, true, true); // 将未消耗的速度 分给指定滚动列表
                        }
                    }
                } else { // 向下滑动
                    if (Math.abs(carouselTranslationY) != 0) { // 处于折叠状态
                        if (!child.getScrollableView().canScrollVertically(-1)) { // 已经下拉到顶时
                            consumed[1] = dy;
                            float curY = carouselTranslationY + Math.abs(dy);
                            if (curY > 0) {
                                curY = 0;
                            }
                            rootScrollView.setTranslationY(curY); // 开始展开 轮播图 / 评分容器
                        } else {
                            if (type == ViewCompat.TYPE_NON_TOUCH) { // 惯性
                                sharedScrollVelocity(dy, false, true); // 将未消耗的速度 分给指定滚动列表
                            }
                        }
                    } else {
                        if (type == ViewCompat.TYPE_NON_TOUCH) { // 惯性
                            sharedScrollVelocity(dy, false, true); // 将未消耗的速度 分给指定滚动列表
                        }
                    }
                }

            }

            if (!StoreDetailInfoH.expandAnimatorEnd || // 展开公告动画还没有结束
                    StoreDetailInfoH.dragPositionState == StoreDetailInfoH.FLOAT) { // 公告处于被拉伸状态
                consumed[1] = dy;
            }

        } catch (Exception e) {
            consumed[1] = dy;
            e.printStackTrace();
        }
    }

    /**
     * 注意这个方法，会执行两遍
     * 开始时调用一次，最后滚动停止时再调用一次
     */
    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int type) {
        // Log.d("TAG", "onStopNestedScroll："+ StoreDetailCoordinatorH.currentScrollViewTag +  "---"+ SystemClock.elapsedRealtime() + "---"+ type);

        if (type == ViewCompat.TYPE_NON_TOUCH) {
            // 惯性滑动结束
            mIsFling = false;
            startVelocityX = 0;
            startVelocityY = 0;
        }
        mVerticalPagingTouch = 0;
        viewPager2Box.isScrollable = true;
    }

    /**
     * 当被依赖的 View 状态改变时回调
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull StoreDetailTabPageH child, @NonNull View dependency) {
        // Log.d("TAG", "onDependentViewChanged："+ StoreDetailCoordinatorH.currentScrollViewTag +  "---"+ SystemClock.elapsedRealtime());
        return true;
    }

    float preOffsetY = 0f; // 上一次偏移距离

    /**
     * 滚动动画（公告 展开/收缩时，mStoreDetailPager2View 下移/上移 动画）
     */
    private StoreDetailInfoH.AnimatorListenerAdapter1 mAnimListener() {
        return new StoreDetailInfoH.AnimatorListenerAdapter1() {
            @Override
            public void onAnimationStart(Animator animator) {
                preOffsetY = 0f;

                // 避免 展开公告时，如果执行子recyclerView的惯性没有结束，会出现 Y偏移值 加倍bug
                mIsFlingAndDown = true;
            }

            @Override
            public void onAnimationUpdate(float progress, boolean isExpand) {

                // 注意，推荐使用int类型，浮点型计算时，可能会出现误差
                int v = (int) (progress * (int) (maxOffsetBottom + Math.abs(storeDetailInfo.getTranslationY())));
                int v2 = (int) (v - preOffsetY);

                if (isExpand) {
                    storeDetailTabPage.setTranslationY(storeDetailTabPage.getTranslationY() + v2);
                } else {
                    storeDetailTabPage.setTranslationY(storeDetailTabPage.getTranslationY() - v2);
                }

                if (v == maxOffsetBottom) {
                    preOffsetY = 0f;
                } else {
                    preOffsetY = v;
                }

            }

            @Override
            public void onAnimationEnd() {
                preOffsetY = 0f;
            }

        };
    }

    /**
     * 整体布局，向上偏移至最大值
     */
    public void offsetTopMax() {
        // 开始向上偏移
        mFoldingDy = 0;
        mHandler.post(mOffsetTopRunnable());
    }

    /**
     * 整体布局，是否偏移到最大值
     */
    public boolean arriveTopMax() {
        return isPagerToMax;
    }

    /**
     * 整体布局，向上偏移任务
     */
    private Runnable mOffsetTopRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                if (mFoldingDy < tabPageTopOffsetMaxDistance) {
                    mFoldingDy += 4; // 影响偏移速度，值越大速度越快
                    layoutFolding(mFoldingDy, false);
                    mHandler.post(this);
                } else {
                    isPagerToMax = true;
                    isPagerToInit = false;
                }
            }
        };
    }

    /**
     * 通过动画使整体布局，向上偏移到最大值
     */
    public void layoutFolding(int dy, boolean transgression) {

        mVerticalPagingTouch += dy;

        if (!transgression) {
            final float effect = storeDetailSearchBar.updateBarColor(dy, tabPageTopOffsetMaxDistance);
            final float transY = -tabPageTopOffsetMaxDistance * effect;
            storeDetailInfo.setTranslationY(transY);
            storeDetailTabPage.setTranslationY(transY);
        } else {
            int absDy = Math.abs(dy);
            int curTranslationY = 0;
            if (StoreDetailCoordinatorH.direction) {
                curTranslationY = (int) (storeDetailTabPage.getTranslationY() - absDy);
                if (curTranslationY <= 0) {
                    curTranslationY = 0;
                    // Log.d("TAG", "收缩完毕");
                }
            } else {
                curTranslationY = (int) (storeDetailTabPage.getTranslationY() + absDy);
                if (curTranslationY >= storeDetailInfo.getMaxExpandDistance()) {
                    curTranslationY = storeDetailInfo.getMaxExpandDistance();
                    // Log.d("TAG", "展开完毕");
                }
            }
            storeDetailTabPage.setTranslationY(curTranslationY);
        }

        if (Math.abs(storeDetailTabPage.getTranslationY()) == tabPageTopOffsetMaxDistance) {
            // 点餐/评论/商家 容器，向上偏移至最大值
            isPagerToMax = true;
        } else {
            isPagerToMax = false;
        }

        if (Math.abs(storeDetailTabPage.getTranslationY()) == 0) {
            // 点餐/评论/商家 容器，偏移至初始值
            isPagerToInit = true;
        } else {
            isPagerToInit = false;
        }
    }

    /**
     * 整体偏移，来自StoreDetailCoordinatorH调用
     */
    public void layoutFoldingFromParent(int dy, boolean transgression) {
        // 避免 如果执行子recyclerView的惯性没有结束，会出现 Y偏移值 加倍bug
        mIsFlingAndDown = true;
        layoutFolding(dy, transgression);
    }

    /**
     * 应用于 手指点击区域 是否在 目标区域
     */
    public int lockScrollView() {

        int pageDecorationHeight = storeDetailTabPage.getTabPageDecorationHeight();
        float rootScrollTranslationY = storeDetailTabPage.getRootScrollView().getTranslationY();
        int decorationHeight = (int) (pageDecorationHeight + rootScrollTranslationY); // 轮播图 / 评分容器所占区域 高度
        if (decorationHeight < 0) {
            decorationHeight = 0;
        }
        if (decorationHeight > pageDecorationHeight) {
            decorationHeight = pageDecorationHeight;
        }

        int currentFragmentIndex = storeDetailTabPage.getCurrentPagerIndex();
        if (currentFragmentIndex == 1) { // 评价页面
            decorationHeight += storeDetailTabPage.getTabPageFloatHeight(); // 增加 目标区域
        }

        return (int) (tabPageDefaultPositionDistance + storeDetailTabPage.storeTabLayout.getHeight() + storeDetailTabPage.getTranslationY() + decorationHeight);
    }

    /**
     * 关闭公告
     */
    public void closeAnnouncement() {
        storeDetailInfo.expandOrShrinkAnnouncement();
    }

    /**
     * 手指按下
     */
    public void parentOnDown(MotionEvent event) {
        if (mIsFling) {
            // 避免 如果执行子recyclerView的惯性没有结束，会出现 Y偏移值 加倍bug
            mIsFlingAndDown = true;
        }
        storeDetailTabPage.parentOnDown(event);
    }

    /**
     * 手指抬起
     */
    public void parentOnUp(MotionEvent event) {
        storeDetailTabPage.parentOnUp(event);
    }

    /**
     * 将未消耗完的速度指定给其他滚动view
     */
    public boolean sharedScrollVelocity(int dy, boolean direction, boolean fling) {
        return storeDetailTabPage.sharedScrollVelocity(dy, direction, fling);
    }

    /**
     * 根据拖动，时时改变 公告view状态
     *
     * @param curDrag
     */
    public void trackDraggingProgress(int curDrag, boolean direction) {
        storeDetailInfo.trackDraggingProgress(curDrag, direction);
    }

    /**
     * 创建商品规格 对话框
     *
     * @param productBean
     */
    public void createSpecificationWindow(StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean, int storeId) {
        StoreDetailSpecificationWindowH.show(rootCoordinator.getContext(), productBean, storeId, rootCoordinator, 0);
    }

    /**
     * 删除商品规格 对话框
     */
    public void removeSpecificationWindow() {
        StoreDetailSpecificationWindowH.dismiss();
    }


    /**
     * 创建购物车 底部对话框
     */
    @SuppressLint("ResourceAsColor")
    public void createShoppingCartWindow(int storeId) {
        StoreDetailShoppingCartWindowH.show(rootCoordinator.getContext(), storeId, rootCoordinator, StoreDetailSpecificationWindowH.isShowWindow ? 2 : 1);
    }

    /**
     * 删除购物车 底部对话框
     */
    public void removeShoppingCartWindow() {
        StoreDetailShoppingCartWindowH.dismiss();
    }


    public interface LayoutExpandControl {
        /**
         * 整体布局，向上偏移至最大值
         */
        void offsetTopMax();

        /**
         * 当前整体布局，是否偏移到最大值
         */
        boolean arriveTopMax();
    }

}
