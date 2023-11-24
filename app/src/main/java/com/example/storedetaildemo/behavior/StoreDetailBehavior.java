package com.example.storedetaildemo.behavior;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.common.App;
import com.example.storedetaildemo.ui.widget.MViewPager2Box;
import com.example.storedetaildemo.ui.widget.StoreDetailBarHo;
import com.example.storedetaildemo.ui.widget.StoreDetailCartHo;
import com.example.storedetaildemo.ui.widget.StoreDetailInfoHo;
import com.example.storedetaildemo.ui.widget.StoreDetailPagerView2Ho;
import com.example.storedetaildemo.ui.widget.StoreDetailRootViewHo;
import com.example.storedetaildemo.util.DisplayUtils;

public class StoreDetailBehavior extends CoordinatorLayout.Behavior<StoreDetailPagerView2Ho> {

    private StoreDetailInfoHo storeDetailInfoView; // 店铺信息
    private StoreDetailPagerView2Ho mStoreDetailPager2View; // 点餐/评论/商家 视图
    private StoreDetailBarHo storeDetailBarView; // 顶部bar
    private StoreDetailCartHo storeDetailCartView; // 底部购物车
    private boolean fistInit = false; // 第一次初始化
    private int pagerView2DefaultPositionDistance; // 点餐/评论/商家 视图初始化位置距离
    private int pagerView2TopOffsetMaxDistance; // 点餐/评论/商家 视图向上最大偏移距离
    private int mVerticalPagingTouch = 0; // 菜单竖项列表(点餐，评价，商家)内容的触摸滑动距离
    private final int mPagingTouchSlop = DisplayUtils.px2dip(App.getInstance(),5); // 触摸倾斜，用于判断ViewPage是否可以水平滑动
    private boolean mIsFling = false; // 惯性滚动
    private boolean mIsFlingAndDown = false; // 是否处于惯性滑动且有触摸动作插入
    private final Handler mHandler = new Handler(); // 更新UI，更新UI需要在主线程
    // private boolean mIsScrollToHideFood = false; // 下滑显示店铺公告
    // private boolean expandAnimatorEnd = true; // 展开店铺公告动画是否结束
    private boolean isPagerToMax = false; // 点餐/评论/商家 视图，向上偏移至最大值
    private boolean isPagerToInit = true; // 点餐/评论/商家 视图，偏移至初始值
    private int mFoldingDy = 0; // 整体偏移量
    private MViewPager2Box viewPager2Box; // 列表的viewPager2
    private View otherBox; // 买单按钮/限时折扣的容器
    private int maxOffsetBottom; // 点餐/评论/商家 视图 向下偏移到屏幕外的距离
    private StoreDetailRootViewHo rootView;
    private int slideAnnouncementY; // 可以滑动下拉公告的起点范围
    private View announcementContainer; // 公告缩略文本View
    private int startVelocityX, startVelocityY = 0;

    public StoreDetailPagerView2Ho getmStoreDetailPager2View() {
        return mStoreDetailPager2View;
    }

    public StoreDetailCartHo getStoreDetailCartView() {
        return storeDetailCartView;
    }

    public int getPagerView2TopOffsetMaxDistance() {
        return pagerView2TopOffsetMaxDistance;
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
    public StoreDetailBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 嵌套滑动开始（ACTION_DOWN），确定 Behavior 是否要监听此次事件
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailPagerView2Ho child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        // Log.d("TAG", "onStartNestedScroll："+ OrderFoodFragment.currentScrollViewTag + "---"+ SystemClock.elapsedRealtime());

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
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull StoreDetailPagerView2Ho child, int layoutDirection) {
        // Log.d("TAG", "onLayoutChild："+ OrderFoodFragment.currentScrollViewTag + "---"+ SystemClock.elapsedRealtime());

        rootView = (StoreDetailRootViewHo) parent;

        child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (fistInit) return;
                fistInit = true;
                mStoreDetailPager2View = child;
                mStoreDetailPager2View.setStoreDetailBehavior(StoreDetailBehavior.this);
                viewPager2Box = child.findViewById(R.id.vp2_box);
                ViewPager2 viewPager2 = (ViewPager2) viewPager2Box.getChildAt(0);
                viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                    }
                });

                // 购物车位置
                CoordinatorLayout.LayoutParams cartViewLayoutParams = (CoordinatorLayout.LayoutParams) storeDetailCartView.getLayoutParams();
                cartViewLayoutParams.topMargin = parent.getHeight() - storeDetailCartView.getHeight();
                storeDetailCartView.setLayoutParams(cartViewLayoutParams);

                CoordinatorLayout.LayoutParams storeDetailListLayoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
                // 点餐/评论/商家 视图 高度
                int pagerView2Height = parent.getHeight() - storeDetailBarView.getHeight();
                storeDetailListLayoutParams.height = pagerView2Height;

                // 点餐/评论/商家 视图 位置
                View otherBox = storeDetailInfoView.findViewById(R.id.other_box);
                pagerView2DefaultPositionDistance = otherBox.getBottom() + App.AppStatusBarHeight;
                pagerView2TopOffsetMaxDistance = pagerView2DefaultPositionDistance - storeDetailBarView.getHeight();
                storeDetailListLayoutParams.topMargin = pagerView2DefaultPositionDistance;
                child.setLayoutParams(storeDetailListLayoutParams);

                // 点餐/评论/商家 视图 向下偏移到屏幕外的距离
                storeDetailInfoView.setStoreDetailBehavior(StoreDetailBehavior.this);
                View barContentBox = storeDetailBarView.findViewById(R.id.bar_content_box);
                int tempMaxOffsetBottom = (App.AppScreenHeight - pagerView2DefaultPositionDistance - App.AppStatusBarHeight - barContentBox.getHeight());
                maxOffsetBottom = tempMaxOffsetBottom + storeDetailBarView.getHeight() + otherBox.getHeight();

                announcementContainer = storeDetailInfoView.findViewById(R.id.announcement_container);
                slideAnnouncementY = App.AppStatusBarHeight + storeDetailBarView.getHeight() + announcementContainer.getTop();

                storeDetailCartView.setStoreDetailBehavior(StoreDetailBehavior.this);
                storeDetailBarView.setStoreDetailBehavior(StoreDetailBehavior.this);
            }
        });
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     * 确定使用 Behavior 产生联动（依赖）的效果的其他View类型
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull StoreDetailPagerView2Ho child, @NonNull View dependency) {
        // Log.d("TAG","layoutDependsOn："+ OrderFoodFragment.currentScrollViewTag + "---"+ SystemClock.elapsedRealtime());

        if (dependency.getId() == R.id.store_detail_info) {
            storeDetailInfoView = (StoreDetailInfoHo) dependency;
            storeDetailInfoView.animListener = mAnimListener();
            otherBox = storeDetailInfoView.findViewById(R.id.other_box);
        } else if (dependency.getId() == R.id.store_detail_bar) {
            storeDetailBarView = (StoreDetailBarHo) dependency;
        } else if (dependency.getId() == R.id.store_detail_cart) {
            storeDetailCartView = (StoreDetailCartHo) dependency;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailPagerView2Ho child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // Log.d("TAG", "onNestedPreScroll："+ OrderFoodFragment.currentScrollViewTag + "---"+ SystemClock.elapsedRealtime());

        try {
            mVerticalPagingTouch += dy;

            if (viewPager2Box.isScrollable && Math.abs(mVerticalPagingTouch) > mPagingTouchSlop) {
                viewPager2Box.isScrollable = false; // 屏蔽 pager横向滑动干扰
            }

            if (type == ViewCompat.TYPE_NON_TOUCH && mIsFlingAndDown) {
                //当处于惯性滑动时，有触摸动作进入，屏蔽惯性滑动，以防止滚动错乱
                consumed[1] = dy;
                return;
            }

            boolean updateViewState = false;
            boolean startTransgression = false;

            if (StoreDetailRootViewHo.direction) { // 向上滑动

                if (Math.abs(child.getTranslationY()) != pagerView2TopOffsetMaxDistance &&
                        child.getTranslationY() <= 0) {
                    // child.getTranslationY() != listTopOffsetMaxDistance：当前整体布局没有偏移到最大值之前
                    // 一直消耗当前scrollview滚动值
                    updateViewState = true;

                } else if (child.getTranslationY() > 0 &&
                        type == ViewCompat.TYPE_TOUCH) {
                    // child.getTranslationY() > 0：位置再初始位置下方
                    // Log.d("TAG", "从初始位置下方向上滑");
                    startTransgression = true;
                }

            } else { // 向下滑动

                if (!child.getScrollableView().canScrollVertically(-1) &&
                        child.getTranslationY() < 0) {
                    // !child.getScrollableView().canScrollVertically(-1)：当前scrollview内的item已经下拉到顶部时
                    // Math.abs(child.getTranslationY()) != 0：不在初始位置
                    // 整体布局向下偏移
                    updateViewState = true;

                    if (type == ViewCompat.TYPE_NON_TOUCH && // 惯性
                            isPagerToMax && // 整体布局向上偏移到最大
                            !StoreDetailRootViewHo.direction && // 向下滑动
                            !child.getScrollableView().canScrollVertically(-1)) { // 当前scrollview内的item已经下拉到顶部时
                        updateViewState = false;
                    }

                } else if (!child.getScrollableView().canScrollVertically(-1) &&
                        child.getTranslationY() >= 0
                        && type == ViewCompat.TYPE_TOUCH) {
                    // !child.getScrollableView().canScrollVertically(-1)：当前scrollview内的item已经下拉到顶部时
                    // Math.abs(child.getTranslationY()) == 0：在初始位置
                    // child.getTranslationY() > 0：位置再初始位置下方
                    // Log.d("TAG", "初始位置向下拉");
                    startTransgression = true;
                }

            }

            if (updateViewState) {
                // 顶部bar 跟随变色
                final float effect = storeDetailBarView.updateBarColor(dy, pagerView2TopOffsetMaxDistance);
                final float transY = -pagerView2TopOffsetMaxDistance * effect;

                // 店铺信息 跟随偏移
                storeDetailInfoView.setTranslationY(transY);

                if (transY != child.getTranslationY()) {
                    child.setTranslationY(transY);
                    consumed[1] = dy;
                    StoreDetailRootViewHo.layoutOffsetRecord = true;
                }

                if (Math.abs(child.getTranslationY()) == pagerView2TopOffsetMaxDistance) {
                    // 点餐/评论/商家 视图，向上偏移至最大值
                    isPagerToMax = true;
                } else {
                    isPagerToMax = false;
                }

                if (Math.abs(child.getTranslationY()) == 0) {
                    // 点餐/评论/商家 视图，偏移至初始值
                    isPagerToInit = true;
                    int remainingV = Math.abs(startVelocityY - mVerticalPagingTouch);
                    rootView.jitterAnimation(startVelocityX, remainingV, StoreDetailRootViewHo.direction);
                } else {
                    isPagerToInit = false;
                }
            } else if (startTransgression) {
                // pagerView2页面从初始位置 向下拖动（已经在StoreDetailRootView中统一处理）
                // int absDy = Math.abs(dy);
                // if (rootView.getDirection()) {
                //    child.setTranslationY(child.getTranslationY() - absDy);
                // } else {
                //    if (type == ViewCompat.TYPE_TOUCH) {
                //        child.setTranslationY(child.getTranslationY() + absDy);
                //    }
                // }
                consumed[1] = dy;
            }

            if (!StoreDetailInfoHo.expandAnimatorEnd ||
                    StoreDetailInfoHo.dragPositionState == StoreDetailInfoHo.FLOAT) {
                consumed[1] = dy;
            }

        } catch (Exception e) {
            consumed[1] = dy;
            e.printStackTrace();
        }
    }

    /**
     * 惯性滑动准备开始
     */
    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailPagerView2Ho child, @NonNull View target, float velocityX, float velocityY) {
        // Log.d("TAG","onNestedPreFling："+ OrderFoodFragment.currentScrollViewTag + "---"+ SystemClock.elapsedRealtime());

        mIsFling = true;
        mIsFlingAndDown = false;

        startVelocityX = (int) velocityX;
        startVelocityY = (int) velocityY;

        // 默认返回false，表示执行惯性
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    /**
     * 注意这个方法，会执行两遍
     * 开始时调用一次，最后滚动停止时再调用一次
     */
    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailPagerView2Ho child, @NonNull View target, int type) {
        // Log.d("TAG", "onStopNestedScroll："+ OrderFoodFragment.currentScrollViewTag + "---"+ SystemClock.elapsedRealtime());

        if (type == ViewCompat.TYPE_NON_TOUCH) {
            // 惯性滑动结束
            mIsFling = false;
            startVelocityX = 0;
            startVelocityY = 0;
        }
        mVerticalPagingTouch = 0;
        viewPager2Box.isScrollable = true;
    }


    // 上一次偏移距离
    float preOffsetY = 0f;

    /**
     * 滚动动画（公告 展开/收缩时，mStoreDetailPager2View 下移/上移 动画）
     */
    private StoreDetailInfoHo.AnimatorListenerAdapter1 mAnimListener() {
        return new StoreDetailInfoHo.AnimatorListenerAdapter1() {
            @Override
            public void onAnimationStart(Animator animator) {
                preOffsetY = 0f;

                // 避免 展开公告时，如果执行子recyclerView的惯性没有结束，会出现 Y偏移值 加倍bug
                mIsFlingAndDown = true;
            }

            @Override
            public void onAnimationUpdate(float progress, boolean isExpand) {

                // 注意，推荐使用int类型，浮点型计算时，可能会出现误差
                int v = (int) (progress * (int) (maxOffsetBottom + Math.abs(storeDetailInfoView.getTranslationY())));
                int v2 = (int) (v - preOffsetY);

                if (isExpand) {
                    mStoreDetailPager2View.setTranslationY(mStoreDetailPager2View.getTranslationY() + v2);
                } else {
                    mStoreDetailPager2View.setTranslationY(mStoreDetailPager2View.getTranslationY() - v2);
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
     * 当被依赖的 View 状态改变时回调
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull StoreDetailPagerView2Ho child, @NonNull View dependency) {
        return true;
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
                if (mFoldingDy < pagerView2TopOffsetMaxDistance) {
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
            final float effect = storeDetailBarView.updateBarColor(dy, pagerView2TopOffsetMaxDistance);
            final float transY = -pagerView2TopOffsetMaxDistance * effect;
            storeDetailInfoView.setTranslationY(transY);
            mStoreDetailPager2View.setTranslationY(transY);
        } else {
            int absDy = Math.abs(dy);
            int curTranslationY = 0;
            if (StoreDetailRootViewHo.direction) {
                curTranslationY = (int) (mStoreDetailPager2View.getTranslationY() - absDy);
                if (curTranslationY <= 0) {
                    curTranslationY = 0;
                    // Log.d("TAG", "收缩完毕");
                }
            } else {
                curTranslationY = (int) (mStoreDetailPager2View.getTranslationY() + absDy);
                if (curTranslationY >= storeDetailInfoView.getMaxExpandDistance()) {
                    curTranslationY = storeDetailInfoView.getMaxExpandDistance();
                    // Log.d("TAG", "展开完毕");
                }
            }
            mStoreDetailPager2View.setTranslationY(curTranslationY);
        }

        if (Math.abs(mStoreDetailPager2View.getTranslationY()) == pagerView2TopOffsetMaxDistance) {
            // 点餐/评论/商家 视图，向上偏移至最大值
            isPagerToMax = true;
        } else {
            isPagerToMax = false;
        }

        if (Math.abs(mStoreDetailPager2View.getTranslationY()) == 0) {
            // 点餐/评论/商家 视图，偏移至初始值
            isPagerToInit = true;
        } else {
            isPagerToInit = false;
        }
    }

    /**
     * 整体偏移，来自StoreDetailRootView调用
     */
    public void layoutFoldingFromParent(int dy, boolean transgression) {
        // 避免 如果执行子recyclerView的惯性没有结束，会出现 Y偏移值 加倍bug
        mIsFlingAndDown = true;
        layoutFolding(dy, transgression);
    }

    /**
     * 手指点击区域 是否是在viewPager2以外的区域
     */
    public int lockScrollView() {
        return (int) (pagerView2DefaultPositionDistance + mStoreDetailPager2View.storeTabLayout.getHeight() + mStoreDetailPager2View.getTranslationY());
    }

    /**
     * 关闭公告
     */
    public void closeAnnouncement() {
        storeDetailInfoView.expandOrShrinkAnnouncement();
    }

    /**
     * 手指按下
     */
    public void parentOnDown(MotionEvent event) {
        if (mIsFling) {
            // 避免 如果执行子recyclerView的惯性没有结束，会出现 Y偏移值 加倍bug
            mIsFlingAndDown = true;
        }
        mStoreDetailPager2View.parentOnDown(event);
    }

    /**
     * 手指抬起
     */
    public void parentOnUp(MotionEvent event) {
        mStoreDetailPager2View.parentOnUp(event);
    }

    /**
     * 将未消耗完的速度指定给其他滚动view
     */
    public boolean sharedScrollVelocity(int dy, boolean direction, boolean fling) {
        return mStoreDetailPager2View.sharedScrollVelocity(dy, direction, fling);
    }

    /**
     * 根据拖动，时时改变 公告view状态
     *
     * @param curDrag
     */
    public void trackDraggingProgress(int curDrag, boolean direction) {
        storeDetailInfoView.trackDraggingProgress(curDrag, direction);
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
