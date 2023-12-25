package com.example.home_store_detail.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.CustomizeUtils;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.ScreenUtils;
import com.example.baselibrary.util.ShapeUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.adapter.StoreDetailShoppingCartAdpH;
import com.example.home_store_detail.databinding.StoreDetailLvShoppingCartWindowHBinding;
import com.example.provider.common.App;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * 购物车 对话框
 * <p>
 * 解释一下为什么不用 Dialog 或 DialogFragment，
 * 因为Dialog是图层是顶级，它会把 购物车 盖住
 */
public class StoreDetailShoppingCartWindowH extends BaseView {

    private int storeId = -1;
    private static StoreDetailLvShoppingCartWindowHBinding bind;

    private static ValueAnimator windowValueAnimator;
    private static int WINDOW_ANIMATOR_TIME = 300;
    public static boolean windowAnimatorEnd = true; // 动画是否结束
    public static boolean isShowWindow = false; // 对话框是否显示

    private static ViewGroup parentView; // 父级
    private static int showLayersIndex; // 在父级中显示的图层位置 从 0 开始
    public static StoreDetailShoppingCartWindowH instance;
    private static int maxExpandDistance = 0; // 最大展开距离
    public static boolean isExpand = false; // 是否展开
    private static AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;

    private StoreDetailShoppingCartWindowH(@NonNull Context context) {
        super(context);
    }

    /**
     * 创建 实例
     *
     * @param context
     * @param storeId 店铺id
     * @return
     */
    private static StoreDetailShoppingCartWindowH createInstance(@NonNull Context context,
                                                                 int storeId) {
        accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        if (instance == null) {
            instance = new StoreDetailShoppingCartWindowH(context, storeId);
        }
        return instance;
    }

    private int mScrollPointerId; // 当前最新放在屏幕上的手指
    private boolean mOriginalScrollPointerUp = true; // 原来放在屏幕上的手指离开了屏幕
    private int mLastTouchX; // 上一次触摸的X坐标
    private int mLastTouchY; // 上一次触摸的Y坐标
    private int mInitialTouchX; // 初始化触摸的X坐标
    private int mInitialTouchY; // 初始化触摸的Y坐标
    private MotionEvent copyEv;
    private boolean direction = true; // true：向上 false：向下
    private boolean currentDragOperateEnd = true; // 拖动操作 是否结束
    private boolean isMask = false; // 点击的是否是遮罩区域


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        isMask = false;
        boolean isUp = false;

        // 返回正在执行的操作，不包含触摸点索引信息。即事件类型，如MotionEvent.ACTION_DOWN
        final int action = event.getActionMasked();
        int actionIndex = event.getActionIndex(); // Action的索引

        // 复制事件信息创建一个新的事件，防止被污染
        copyEv = MotionEvent.obtain(event);

        // 461 是购物车对话框 内容区域高度
        int topMargin = Math.abs(ScreenUtils.getScreenHeight(App.getInstance()) - DisplayUtils.dp2px(App.getInstance(), 461));
        if (bind != null) {
            isMask = event.getY() < (topMargin + Math.abs(bind.getRoot().getTranslationY()));
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN: { // 手指按下
                if (!isMask) {
                    currentDragOperateEnd = false;
                }
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

                int curY = 0;
                if (mLastTouchY - y > 0.5f) {
                    direction = true; // 向上滑
                    curY = -dy;
                } else if (y - mLastTouchY > 0.5f) {
                    direction = false; // 向下滑
                    curY = dy;
                }

                dragWindow(dy, direction, isMask);

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
            case MotionEvent.ACTION_UP: // 手指离开，滑动事件结束
            case MotionEvent.ACTION_CANCEL: { //手势取消，释放各种资源
                isUp = true;
                if (!isMask) {
                    onStopDragging();
                }
            }
            break;

        }

        // 回收滑动事件，方便重用
        copyEv.recycle();

        if (isExpand) {
            if (bind == null) return true;

            // 事件穿透范围，购物车是叠加在最上层的
            if (isMask && currentDragOperateEnd) {
                offsetAnimatorShrink();
            }
            if (isUp) {
                currentDragOperateEnd = true;
            }
            return true;

        } else {
            if (isUp) {
                currentDragOperateEnd = true;
            }
            return false;
        }
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
     * 拖拽
     */
    @SuppressLint("ResourceAsColor")
    private void dragWindow(int dy, boolean direction, boolean isMask) {
        if (bind == null || !windowAnimatorEnd || isMask) {
            return;
        }

        bind.storeDetailShoppingCartList.stopScroll();

        if (!bind.storeDetailShoppingCartList.canScrollVertically(-1) ||
                bind.getRoot().getTranslationY() != 0) {
            int curDy = Math.abs(dy);
            int v = 0;
            if (direction) {
                if (Math.abs(bind.getRoot().getTranslationY()) == 0) return;
                v = (int) (bind.getRoot().getTranslationY() - curDy);
                if (v < 0) {
                    v = 0;
                    isExpand = true;
                }
            } else {
                if (Math.abs(bind.getRoot().getTranslationY()) == maxExpandDistance) return;
                v = (int) (bind.getRoot().getTranslationY() + curDy);
                if (v > maxExpandDistance) {
                    v = maxExpandDistance;
                    isExpand = false;
                }
            }

            float progress = (float) v / maxExpandDistance;

            // 将进度值 负数 转为 正数
            BigDecimal num = new BigDecimal(progress);
            BigDecimal positiveNum = num.abs();
            progress = positiveNum.floatValue();

            if (CustomizeUtils.isScientificNotation(String.valueOf(progress))) { // 出现科学计数格式 比如 6E+4
                // 1 - 0.99 = 0.01
                // 0.99 > 0.01 = 1
                // 0.01 < 0.99 = 0
                float tempAbs = Math.abs(1f - progress);
                if (progress > tempAbs) { // 无限接近 1
                    progress = 1f;
                } else if (progress < tempAbs) { // 无限接近 0
                    progress = 0f;
                } else {
                    // 相等
                    if (isExpand) {
                        progress = 0f;
                    } else {
                        progress = 1f;
                    }
                }
            }

            float newProgress = 1 - progress;
            instance.setCartWelfareInfoStyle(newProgress);

            // 计算颜色渐变值的类 0 - 1
            ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            instance.setBackgroundColor((int) argbEvaluator.evaluate(
                    progress,
                    com.example.baselibrary.R.color.color_66000000,
                    android.R.color.transparent
            ));

            bind.getRoot().setTranslationY(v);
        }
    }

    /**
     * 拖拽停止 回弹动画
     */
    private void onStopDragging() {
        if (bind.getRoot().getTranslationY() == 0 ||
            Math.abs(bind.getRoot().getTranslationY()) == maxExpandDistance ||
            !windowAnimatorEnd) {
            return;
        }
        float progress = (float) Math.abs(bind.getRoot().getTranslationY()) / maxExpandDistance;
        if (progress < 0.35) {
            offsetAnimatorExpand();
        } else {
            offsetAnimatorShrink();
        }
    }

    /**
     * 显示 购物车对话框
     *
     * @param context
     * @param storeId     店铺id
     * @param parent      父级View
     * @param layersIndex 显示在父级View中的图层位置，从0开始
     */
    public static void show(@NonNull Context context,
                            int storeId,
                            ViewGroup parent,
                            int layersIndex) {

        if (instance != null) {
            instance.refreshData();
        } else {
            parentView = parent;
            showLayersIndex = layersIndex;
            StoreDetailShoppingCartWindowH windowH = StoreDetailShoppingCartWindowH.createInstance(context, storeId);
            parentView.addView(windowH, parentView.getChildCount() - layersIndex);
            isShowWindow = true;
        }
    }

    /**
     * 删除购物车 对话框
     */
    public static void dismiss() {
        if (parentView == null) return;
        int targetViewIndex = findTargetView();
        if (parentView.getChildCount() == 0 || targetViewIndex == -1)
            return;

        if (isExpand) {
            // 40 是显示福利值容器高度
            int newMaxExpandDistance = maxExpandDistance + DisplayUtils.dp2px(App.getInstance(), 40);

            windowValueAnimator = ValueAnimator.ofInt(0, newMaxExpandDistance);
            windowValueAnimator.setDuration(WINDOW_ANIMATOR_TIME);
            windowValueAnimator.setInterpolator(accelerateDecelerateInterpolator);
            windowValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    int animatedValue = (int) animation.getAnimatedValue();
                    if (Math.abs(bind.getRoot().getTranslationY()) == newMaxExpandDistance) {
                        windowValueAnimator.setDuration(0);
                        windowValueAnimator.cancel();
                        return;
                    }

                    bind.getRoot().setTranslationY(animatedValue);

                    float progress = (float) animatedValue / newMaxExpandDistance;

                    // 将进度值 负数 转为 正数
                    BigDecimal num = new BigDecimal(progress);
                    BigDecimal positiveNum = num.abs();
                    progress = positiveNum.floatValue();

                    if (CustomizeUtils.isScientificNotation(String.valueOf(progress))) { // 出现科学计数格式 比如 6E+4
                        // 1 - 0.99 = 0.01
                        // 0.99 > 0.01 = 1
                        // 0.01 < 0.99 = 0
                        float tempAbs = Math.abs(1f - progress);
                        if (progress > tempAbs) { // 无限接近 1
                            progress = 1f;
                        } else if (progress < tempAbs) { // 无限接近 0
                            progress = 0f;
                        } else {
                            // 相等
                            if (isExpand) {
                                progress = 0f;
                            } else {
                                progress = 1f;
                            }
                        }
                    }
                    // 计算颜色渐变值的类 0 - 1
                    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                    instance.setBackgroundColor(
                            (int) argbEvaluator.evaluate(
                                    progress,
                                    com.example.baselibrary.R.color.color_66000000,

                                    android.R.color.transparent
                            )
                    );
                }
            });
            windowValueAnimator.addListener(new AnimatorListenerAdapter() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    instance.refreshData();
                    instance.setBackgroundColor(android.R.color.transparent);
                    windowAnimatorEnd = false;
                    isExpand = false;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    windowAnimatorEnd = true;
                    isExpand = false;
                    parentView.removeViewAt(targetViewIndex);
                    isShowWindow = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    super.onAnimationCancel(animation);
                    dismiss();
                    windowAnimatorEnd = true;
                    parentView.removeViewAt(targetViewIndex);
                    isShowWindow = false;
                }
            });
            windowValueAnimator.start();

        } else {
            parentView.removeViewAt(targetViewIndex);
            isShowWindow = false;
        }

    }

    /**
     * 查找对话框 层级
     *
     * @return
     */
    public static int findTargetView() {
        int targetIndex = -1;
        for (int i = 0; i < parentView.getChildCount(); i++) {
            if (parentView.getChildAt(i) instanceof StoreDetailShoppingCartWindowH) {
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        bind = null;
        windowAnimatorEnd = true;
        isShowWindow = false;
        parentView = null;
        showLayersIndex = 0;
        instance = null;
        maxExpandDistance = 0;
        isExpand = false;
        accelerateDecelerateInterpolator = null;
        stopAnimator();
    }

    private static void stopAnimator() {
        if (windowValueAnimator != null) {
            windowValueAnimator.setDuration(0);
            windowValueAnimator.cancel();
            windowValueAnimator = null;
        }
    }

    private StoreDetailShoppingCartWindowH(@NonNull Context context, int storeId) {
        super(context);
        this.storeId = storeId;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_shopping_cart_window_h, this);
        View firstView = getChildAt(0);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) firstView.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        firstView.setLayoutParams(params);
        bind = StoreDetailLvShoppingCartWindowHBinding.bind(firstView);
        refreshData();
        // 421 是商品列表高度，56 是购物车高度
        maxExpandDistance = DisplayUtils.dp2px(getContext(), 421) - DisplayUtils.dp2px(getContext(), 56);
        bind.getRoot().setTranslationY(maxExpandDistance);
        setBackgroundColor(android.R.color.transparent);

        setCartWelfareInfoStyle(0);
    }

    /**
     * 设置 福利容器View样式
     *
     * @param progress
     */
    public void setCartWelfareInfoStyle(float progress) {
        float radius = DisplayUtils.dip2px(App.getInstance(), progress * 12); // 圆角
        GradientDrawable drawable = ShapeUtils.newShape()
                .setCornerRadius(radius, radius, 0, 0)
                .setColor(getContext().getResources().getColor(com.example.baselibrary.R.color.color_FFF8D2))
                .getDrawable();
        bind.cartWelfareInfo.setBackground(drawable);
    }

    /**
     * 刷新列表数据
     */
    public void refreshData() {
        ShoppingCartVMH shoppingCart = ShoppingCartListH.getStoreShoppingCart(storeId, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        if (!isExpand && // 展开
                !shoppingCart.products.isEmpty() && // 判空
                shoppingCart.findCartProduct(shoppingCart.products.get(shoppingCart.products.size() - 1)) == null) { // 有新商品加入
            Collections.reverse(shoppingCart.products); // 倒序
        }
        StoreDetailShoppingCartAdpH cartAdpH = new StoreDetailShoppingCartAdpH(getContext(), shoppingCart.products, storeId);
        bind.storeDetailShoppingCartList.setLayoutManager(layoutManager);
        bind.storeDetailShoppingCartList.setAdapter(cartAdpH);
        bind.storeDetailShoppingCartList.setNestedScrollingEnabled(false); // 禁止嵌套滚动，不然StoreDetailTabPageBehaviorH 会接收到
    }

    /**
     * 展开 或 收缩（点击）
     */
    public static void expandOrShrink() {
        if (isExpand && windowAnimatorEnd) {
            offsetAnimatorShrink();
        } else if (!isExpand && windowAnimatorEnd) {
            offsetAnimatorExpand();
        }
    }

    /**
     * 展开（点击）
     */
    public static void offsetAnimatorExpand() {
        windowValueAnimator = ValueAnimator.ofInt((int) bind.getRoot().getTranslationY(), 0);
        windowValueAnimator.setDuration(WINDOW_ANIMATOR_TIME);
        windowValueAnimator.setInterpolator(accelerateDecelerateInterpolator);
        windowValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                if (Math.abs(bind.getRoot().getTranslationY()) == 0) {
                    windowValueAnimator.setDuration(0);
                    windowValueAnimator.cancel();
                    return;
                }
                bind.storeDetailShoppingCartList.stopScroll();
                bind.getRoot().setTranslationY(animatedValue);
                float progress = (float) animatedValue / maxExpandDistance;

                // 将进度值 负数 转为 正数
                BigDecimal num = new BigDecimal(progress);
                BigDecimal positiveNum = num.abs();
                progress = positiveNum.floatValue();

                if (CustomizeUtils.isScientificNotation(String.valueOf(progress))) { // 出现科学计数格式 比如 6E+4
                    // 1 - 0.99 = 0.01
                    // 0.99 > 0.01 = 1
                    // 0.01 < 0.99 = 0
                    float tempAbs = Math.abs(1f - progress);
                    if (progress > tempAbs) { // 无限接近 1
                        progress = 1f;
                    } else if (progress < tempAbs) { // 无限接近 0
                        progress = 0f;
                    } else {
                        // 相等
                        if (isExpand) {
                            progress = 0f;
                        } else {
                            progress = 1f;
                        }
                    }
                }


                float newProgress = 1 - progress;
                instance.setCartWelfareInfoStyle(newProgress);

                // 计算颜色渐变值的类 0 - 1
                ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                instance.setBackgroundColor((int) argbEvaluator.evaluate(
                        newProgress,
                        android.R.color.transparent,
                        com.example.baselibrary.R.color.color_66000000
                ));
            }
        });
        windowValueAnimator.addListener(new AnimatorListenerAdapter() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                instance.setBackgroundColor(com.example.baselibrary.R.color.color_66000000);
                windowAnimatorEnd = false;
                isExpand = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                windowAnimatorEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                dismiss();
                windowAnimatorEnd = true;
                isExpand = false;
            }
        });
        windowValueAnimator.start();
    }

    /**
     * 收缩（点击）
     */
    public static void offsetAnimatorShrink() {
        windowValueAnimator = ValueAnimator.ofInt((int) bind.getRoot().getTranslationY(), maxExpandDistance);
        windowValueAnimator.setDuration(WINDOW_ANIMATOR_TIME);
        windowValueAnimator.setInterpolator(accelerateDecelerateInterpolator);
        windowValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                if (Math.abs(bind.getRoot().getTranslationY()) == maxExpandDistance) {
                    windowValueAnimator.setDuration(0);
                    windowValueAnimator.cancel();
                    return;
                }

                bind.storeDetailShoppingCartList.stopScroll();
                bind.getRoot().setTranslationY(animatedValue);
                float progress = (float) animatedValue / maxExpandDistance;

                // 将进度值 负数 转为 正数
                BigDecimal num = new BigDecimal(progress);
                BigDecimal positiveNum = num.abs();
                progress = positiveNum.floatValue();

                if (CustomizeUtils.isScientificNotation(String.valueOf(progress))) { // 出现科学计数格式 比如 6E+4
                    // 1 - 0.99 = 0.01
                    // 0.99 > 0.01 = 1
                    // 0.01 < 0.99 = 0
                    float tempAbs = Math.abs(1f - progress);
                    if (progress > tempAbs) { // 无限接近 1
                        progress = 1f;
                    } else if (progress < tempAbs) { // 无限接近 0
                        progress = 0f;
                    } else {
                        // 相等
                        if (isExpand) {
                            progress = 0f;
                        } else {
                            progress = 1f;
                        }
                    }
                }

                float newProgress = 1 - progress;
                instance.setCartWelfareInfoStyle(newProgress);

                // 计算颜色渐变值的类 0 - 1
                ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                instance.setBackgroundColor((int) argbEvaluator.evaluate(
                        progress,
                        com.example.baselibrary.R.color.color_66000000,
                        android.R.color.transparent
                ));
            }
        });
        windowValueAnimator.addListener(new AnimatorListenerAdapter() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                instance.setBackgroundColor(android.R.color.transparent);
                windowAnimatorEnd = false;
                isExpand = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                windowAnimatorEnd = true;
                isExpand = false;
                if (bind != null) {
                    bind.storeDetailShoppingCartList.scrollToPosition(0); // item 恢复到第一行
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                dismiss();
                windowAnimatorEnd = true;
                if (bind != null) {
                    bind.storeDetailShoppingCartList.scrollToPosition(0); // item 恢复到第一行
                }
            }
        });
        windowValueAnimator.start();
    }

}
