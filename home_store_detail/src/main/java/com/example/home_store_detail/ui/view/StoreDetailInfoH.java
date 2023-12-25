package com.example.home_store_detail.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.CustomizeUtils;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.GlideUtils;
import com.example.baselibrary.util.ScreenUtils;
import com.example.baselibrary.util.ShapeUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailLvDiscountItemHBinding;
import com.example.home_store_detail.databinding.StoreDetailLvInfoHBinding;
import com.example.provider.common.App;
import com.google.android.flexbox.FlexboxLayout;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺信息
 */
public class StoreDetailInfoH extends BaseView {

    private StoreDetailLvInfoHBinding bind;
    private final int ANIMATOR_DURATION = 400;
    public static boolean expandAnimatorEnd = true; // 公告 执行展开/收缩动画，是否结束

    public static final int EXPAND = 0; // 公告 完全展开
    public static final int SHIRK = 1; // 公告 完全收缩
    public static final int FLOAT = 2; // 公告 位置浮动
    public static int dragPositionState = SHIRK; // 拖动公告 状态

    public static final int STOP_FLOAT = 0; // 公告 处于完全展开 或 完全收缩 状态
    public static final int TOP_FLOAT = 1; // 公告 正在向上浮动
    public static final int BOTTOM_FLOAT = 2; // 公告 正在向下浮动
    public static int float_direction = STOP_FLOAT; // 公告浮动方向状态（就是运动方向，向上或向下）

    private boolean isExpand = false;

    private int initCardMeasuredHeight;
    private StoreDetailTabPageBehaviorH tabPageBehavior;

    private int maxExpandDistance = 0; // 最大展开距离
    private AccelerateDecelerateInterpolator interpolator;
    public AnimatorListenerAdapter1 animListener;

    public StoreDetailCoordinatorH parent;

    private float cartAlphaProgress = 1f;
    private int tabPageDefaultMarginTop; // StoreDetailTabPageH 初始位置
    private int slideAnnouncementY; // 可以拖拽下拉公告的起点范围

    public StoreDetailInfoH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_info_h, this);
        bind = StoreDetailLvInfoHBinding.bind(getChildAt(0));
        interpolator = new AccelerateDecelerateInterpolator();
    }

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    public int getMaxExpandDistance() {
        return maxExpandDistance;
    }

    public int getTabPageDefaultMarginTop() {
        return tabPageDefaultMarginTop;
    }

    public int getSlideAnnouncementY() {
        return slideAnnouncementY;
    }

    public void initData(StoreDetailVMH.StoreInfoBean storeInfo) {
        bind.setStoreInfo(storeInfo);
    }

    @Override
    public void fistInitTreeObserver() {

        parent = (StoreDetailCoordinatorH) getParent();

        // 背景图片模糊
        GlideUtils.loadBlurImage(getContext(),
                com.example.baselibrary.R.mipmap.store_70_img,
                bind.storeBg, 50, 2);

        // 店铺图片
        bind.storeImg.setImageResource(getAssetImage70(bind.getStoreInfo().getStore_image()));

        // 循环遍历生成优惠卷
        List<StoreDetailVMH.StoreInfoBean.CouponBean> coupons = bind.getStoreInfo().getCoupon();
        for (int i = 0; i < coupons.size(); i++) {
            TextView coupon = new TextView(getContext());
            bind.couponGroup.addView(coupon);
            coupon.setText(coupons.get(i).getTitle());
            coupon.setTextAppearance(getContext(), com.example.baselibrary.R.style.Font_FFFFFF_12);
            coupon.setPadding(DisplayUtils.dp2px(getContext(), 44), 0, 0, 0);
            coupon.setGravity(Gravity.CENTER_VERTICAL);
            coupon.setBackgroundResource(R.mipmap.coupon_bg);
            FlexboxLayout.LayoutParams couponLayoutParams = (FlexboxLayout.LayoutParams) coupon.getLayoutParams();
            couponLayoutParams.width = DisplayUtils.dp2px(getContext(), 150);
            couponLayoutParams.height = DisplayUtils.dp2px(getContext(), 28);
            couponLayoutParams.topMargin = DisplayUtils.dp2px(getContext(), 12);
            couponLayoutParams.rightMargin = DisplayUtils.dp2px(getContext(), 12);
            coupon.setLayoutParams(couponLayoutParams);
        }

        // 公告详情
        List<StoreDetailVMH.StoreInfoBean.CouponInfosBean> couponInfos = bind.getStoreInfo().getCoupon_infos();
        for (int i = 0; i < couponInfos.size(); i++) {
            // 优惠项
            View discountItem = LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_discount_item_h, bind.announcementLayout.discountInfos, false);
            bind.announcementLayout.discountInfos.addView(discountItem);
            StoreDetailLvDiscountItemHBinding itemBinding = StoreDetailLvDiscountItemHBinding.bind(discountItem);
            itemBinding.setCouponInfo(couponInfos.get(i));
            discountItemStyle(itemBinding.discountType, couponInfos.get(i).getType_id());
        }

        bind.couponGroup.measure(0,0);
        bind.contentCard.measure(0,0);
        bind.storeInfoCardBox.measure(0,0);
        bind.otherBox.measure(0,0);

        initCardMeasuredHeight = bind.contentCard.getMeasuredHeight();

        int[] location = new int[2];
        bind.announcementContainer.getLocationOnScreen(location);
        slideAnnouncementY = location[1]; // 可以拖拽下拉公告的起点范围

        // 最大展开距离 = 屏幕最大高度 - (storeInfoCardBox高度 + 105dp 是storeInfoCardBox 顶部外边距)
        int temp = bind.storeInfoCardBox.getMeasuredHeight() + DisplayUtils.dp2px(getContext(), 105);
        maxExpandDistance = ScreenUtils.getScreenHeight(getContext()) - temp; // 最大展开距离
        tabPageDefaultMarginTop = temp + bind.otherBox.getMeasuredHeight(); // StoreDetailTabPageH 初始位置

        bind.announcementContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent.mScrollState == parent.SCROLL_STATE_IDLE) {
                    expandOrShrinkAnnouncement();
                }
            }
        });
    }

    /**
     * 占位
     * 因为优惠卷是 根据数据 动态生成的，从无到有 会出现闪烁，提前占位，避免这个问题
     * @param view
     * @param size
     */
    @BindingAdapter("setConstraintLvChildMinHeight")
    public static void setConstraintLvChildMinHeight(FlexboxLayout view, int size) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        if (params == null || size == 0) return;
        int minHeight = 0;
        if (size == 1) {
            minHeight = DisplayUtils.dp2px(App.getInstance(),40);
        } else {
            minHeight = DisplayUtils.dp2px(App.getInstance(),80);
        }
        params.matchConstraintMinHeight = minHeight;
        view.setLayoutParams(params);
    }

    /**
     * 点击缩略公告View 展开/收缩公告
     */
    public void expandOrShrinkAnnouncement() {

        boolean direction = false;
        if (bind.contentCard.getHeight() == initCardMeasuredHeight) {
            // Log.d("TAG","公告完全收缩");
            direction = false;
            isExpand = false;
        } else if (bind.contentCard.getHeight() == (initCardMeasuredHeight + maxExpandDistance + (int) Math.abs(getTranslationY()))) {
            // Log.d("TAG","公告完全展开");
            direction = true;
            isExpand = true;
        }
        updateDragState(bind.contentCard.getHeight(), direction);

        if (!expandAnimatorEnd) return;
        if (dragPositionState == FLOAT) return;
        isExpand = !isExpand;
        expandIconRotation();
        announcementShowHide();
        expandUI();
    }

    private void updateDragState(int currentHeight, boolean direction) {
        if (currentHeight == initCardMeasuredHeight) {
            // Log.d("TAG","公告完全收缩");
            dragPositionState = SHIRK;
            float_direction = STOP_FLOAT;
        } else if (currentHeight == (initCardMeasuredHeight + maxExpandDistance + (int) Math.abs(getTranslationY()))) {
            // Log.d("TAG","公告完全展开");
            dragPositionState = EXPAND;
            float_direction = STOP_FLOAT;
        } else {
            // Log.d("TAG","公告位置浮动");
            dragPositionState = FLOAT;
            if (direction) {
                float_direction = TOP_FLOAT;
            } else {
                float_direction = BOTTOM_FLOAT;
            }
        }
    }

    /**
     * 展开图标 旋转（点击缩略公告View）
     */
    private void expandIconRotation() {
        if (isExpand) {
            ObjectAnimator.ofFloat(bind.dropIcon, View.ROTATION, 0, 180f)
                    .setDuration(ANIMATOR_DURATION)
                    .start();
        } else {
            ObjectAnimator.ofFloat(bind.dropIcon, View.ROTATION, 180f, 0)
                    .setDuration(ANIMATOR_DURATION)
                    .start();
        }
    }

    /**
     * 公告显示/隐藏（点击缩略公告View）
     */
    private void announcementShowHide() {
        ObjectAnimator animator;
        ObjectAnimator animator2;
        if (isExpand) {
            animator = ObjectAnimator.ofFloat(bind.announcementContent, View.ALPHA, 1f, 0)
                    .setDuration(ANIMATOR_DURATION);

            animator2 = ObjectAnimator.ofFloat(bind.announcementLayout.getRoot(), View.ALPHA, 0, 1f)
                    .setDuration(ANIMATOR_DURATION);
        } else {
            animator = ObjectAnimator.ofFloat(bind.announcementContent, View.ALPHA, 0, 1)
                    .setDuration(ANIMATOR_DURATION);

            animator2 = ObjectAnimator.ofFloat(bind.announcementLayout.getRoot(), View.ALPHA, 1f, 0)
                    .setDuration(ANIMATOR_DURATION);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(interpolator);
        animatorSet.play(animator);
        animatorSet.play(animator2);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (isExpand) {
                    bind.announcementLayout.getRoot().setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isExpand) {
                    bind.announcementLayout.getRoot().setVisibility(View.GONE);
                }
            }
        });

        animatorSet.start();
    }

    /**
     * 公告 展开/收缩（点击缩略公告View）
     */
    private void expandUI() {

        ViewGroup.LayoutParams params = bind.contentCard.getLayoutParams();
        ValueAnimator animator = null;

        int totalDistance = 0;
        int startDistance = 0;
        int endDistance = 0;
        if (isExpand) {
            startDistance = initCardMeasuredHeight;
            endDistance = getEndDistance(startDistance, true);
            animator = ValueAnimator.ofFloat(startDistance, endDistance);
            totalDistance = endDistance - startDistance;
        } else {
            startDistance = bind.contentCard.getHeight();
            endDistance = getEndDistance(startDistance, false);
            animator = ValueAnimator.ofFloat(startDistance, endDistance);
            totalDistance = startDistance - endDistance;
        }

        if (animator == null) {
            return;
        }

        animator.setInterpolator(interpolator);
        animator.setDuration(ANIMATOR_DURATION);

        int finalStartDistance = startDistance;
        int finalTotalDistance = totalDistance;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float currentDistance = (float) animation.getAnimatedValue();
                float progress = (currentDistance - finalStartDistance) / finalTotalDistance;

                // 将进度值 负数 转为 正数
                BigDecimal num = new BigDecimal(progress);
                BigDecimal positiveNum = num.abs();
                progress = positiveNum.floatValue();

                boolean direction = false;
                if (isExpand) {
                    direction = false;
                    cartAlphaProgress = 1 - progress;
                } else {
                    direction = true;
                    cartAlphaProgress = progress;
                }

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
                        if (direction) {
                            progress = 0f;
                        } else {
                            progress = 1f;
                        }
                    }
                }

                tabPageBehavior.getStoreDetailShoppingCart().effectByOffset(cartAlphaProgress);

                params.height = (int) currentDistance;
                bind.contentCard.setLayoutParams(params);
                if (animListener != null) {
                    animListener.onAnimationUpdate(progress, isExpand);
                }
                updateDragState((int) currentDistance, direction);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (animListener != null) {
                    animListener.onAnimationStart(animation);
                }
                expandAnimatorEnd = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (animListener != null) {
                    animListener.onAnimationEnd();
                }
                float_direction = STOP_FLOAT;
                expandAnimatorEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                if (animListener != null) {
                    animListener.onAnimationEnd();
                }
                float_direction = STOP_FLOAT;
                expandAnimatorEnd = true;
            }
        });
        animator.start();
    }

    /**
     * 获取终点距离
     * @param startDistance
     * @param isExpand
     * @return
     */
    private int getEndDistance(int startDistance, boolean isExpand) {
        if (isExpand) {
            return startDistance + maxExpandDistance + (int) Math.abs(getTranslationY());
        } else {
            return startDistance - maxExpandDistance - (int) Math.abs(getTranslationY());
        }
    }

    /**
     * 跟随拖拽 时时改变公告View状态
     *
     * @param curDrag
     * @param direction
     */
    public void trackDraggingProgress(int curDrag, boolean direction) {
        float progress = updateAnnouncementHeight(curDrag, direction);
        rotationAnnouncementIcon(progress);
        alphaAnnouncement(progress);
    }

    /**
     * 公告view 显示/隐藏（跟随拖拽）
     *
     * @param progress
     */
    private void alphaAnnouncement(float progress) {
        if (progress > 0) {
            if (bind.announcementLayout.getRoot().getVisibility() == View.GONE) {
                bind.announcementLayout.getRoot().setVisibility(View.VISIBLE);
            }
        } else if (progress == 0) {
            if (bind.announcementLayout.getRoot().getVisibility() == View.VISIBLE) {
                bind.announcementLayout.getRoot().setVisibility(View.GONE);
            }
        }

        bind.announcementContent.setAlpha(1 - progress * 2); // 公告缩略文本view
        bind.announcementLayout.getRoot().setAlpha(progress); // 展开公告view
    }

    /**
     * 旋转公告 下拉图标（跟随拖拽）
     *
     * @param progress
     */
    private void rotationAnnouncementIcon(float progress) {
        float maxRotation = 180f; // 最大旋转角度
        bind.dropIcon.setPivotX(bind.dropIcon.getWidth() / 2f); // X轴旋转中心点
        bind.dropIcon.setPivotY(bind.dropIcon.getHeight() / 2f); // Y轴旋转中心点
        bind.dropIcon.setRotation(maxRotation * progress); // 开始旋转
    }

    /**
     * 改变公告view 高度（跟随拖拽）
     *
     * @param curDrag
     * @param direction
     */
    private float updateAnnouncementHeight(int curDrag, boolean direction) {
        ViewGroup.LayoutParams params = bind.contentCard.getLayoutParams();

        float progress = Math.abs(tabPageBehavior.getStoreDetailTabPage().getTranslationY()) / tabPageBehavior.getMaxOffsetBottom();
        if (tabPageBehavior.getStoreDetailTabPage().getTranslationY() == 0) {
            progress = 0f;
            // Log.d("TAG", "完全收缩");
        } else if (Math.abs(tabPageBehavior.getStoreDetailTabPage().getTranslationY()) == tabPageBehavior.getMaxOffsetBottom()) {
            progress = 1f;
            // Log.d("TAG", "完全展开");
        } else {
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
                    if (direction) {
                        progress = 0f;
                    } else {
                        progress = 1f;
                    }
                }
            }
        }

        cartAlphaProgress = 1 - progress;
        if (CustomizeUtils.isScientificNotation(String.valueOf(cartAlphaProgress))) { // 出现科学计数格式 比如 6E+4
            // 1 - 0.99 = 0.01
            // 0.99 > 0.01 = 1
            // 0.01 < 0.99 = 0
            float tempAbs = Math.abs(1f - cartAlphaProgress);
            if (cartAlphaProgress > tempAbs) { // 无限接近 1
                cartAlphaProgress = 1f;
            } else if (cartAlphaProgress < tempAbs) { // 无限接近 0
                cartAlphaProgress = 0f;
            } else {
                // 相等
                if (direction) {
                    cartAlphaProgress = 1f;
                } else {
                    cartAlphaProgress = 0f;
                }
            }
        }

        tabPageBehavior.getStoreDetailShoppingCart().effectByOffset(cartAlphaProgress);

        int proV = (int) (progress * maxExpandDistance);
        int curHeight = proV + initCardMeasuredHeight;
        params.height = curHeight;
        bind.contentCard.setLayoutParams(params);

        updateDragState(curHeight, direction);

        return progress;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dragPositionState = SHIRK;
        expandAnimatorEnd = true;
        float_direction = STOP_FLOAT;
    }

    /**
     * 优惠卷样式
     *
     * @param view
     * @param typeId 不同优惠卷 类型
     */
    private void discountItemStyle(TextView view, int typeId) {
        int bgColor = com.example.baselibrary.R.color.color_1A7B8996;

        switch (typeId) {
            case 0:
                bgColor = com.example.baselibrary.R.color.color_E6A23C;
                break;
            case 1:
                bgColor = com.example.baselibrary.R.color.color_FF7272;
                break;
            case 2:
                bgColor = com.example.baselibrary.R.color.color_17DE85;
                break;
            case 3:
                bgColor = com.example.baselibrary.R.color.color_F82C1B;
                break;
            case 4:
                bgColor = com.example.baselibrary.R.color.color_FF4628;
                break;
            case 5:
                bgColor = com.example.baselibrary.R.color.color_409EFF;
                break;
            case 6:
                bgColor = com.example.baselibrary.R.color.color_FFBA00;
                break;
        }

        GradientDrawable drawable = ShapeUtils.newShape()
                .setColor(getContext().getResources().getColor(bgColor))
                .setCornerRadius(DisplayUtils.dp2px(getContext(), 5))
                .getDrawable();
        view.setBackground(drawable);
    }

    public interface AnimatorListenerAdapter1 {
        void onAnimationStart(Animator animator);

        void onAnimationUpdate(float progress, boolean isExpand);

        void onAnimationEnd();
    }

}
