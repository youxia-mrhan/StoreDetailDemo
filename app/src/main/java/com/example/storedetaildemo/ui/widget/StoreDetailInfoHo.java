package com.example.storedetaildemo.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.storedetaildemo.R;
import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.common.App;
import com.example.storedetaildemo.common.ViewState;
import com.example.storedetaildemo.common.base.BaseView;
import com.example.storedetaildemo.databinding.WidgetDetailInfoHoBinding;
import com.example.storedetaildemo.util.BarUtils;
import com.example.storedetaildemo.util.CustomizeUtils;

import java.math.BigDecimal;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 店铺信息
 */
public class StoreDetailInfoHo extends BaseView {

    private WidgetDetailInfoHoBinding binding;
    private final int ANIMATOR_DURATION = 400;
    public static boolean expandAnimatorEnd = true; // 公告 执行展开/收缩动画，是否结束

    public static int EXPAND = 0; // 公告 完全展开
    public static int SHIRK = 1; // 公告 完全收缩
    public static int FLOAT = 2; // 公告 位置浮动
    public static int dragPositionState = SHIRK; // 拖动公告 状态

    public static int STOP_FLOAT = 0; // 公告 处于完全展开 或 完全收缩 状态
    public static int TOP_FLOAT = 1; // 公告 正在向上浮动
    public static int BOTTOM_FLOAT = 2; // 公告 正在向下浮动
    public static int float_direction = STOP_FLOAT; // 公告浮动方向状态（就是运动方向，向上或向下）

    private boolean isExpand = false;

    private int initCardMeasuredHeight;
    public StoreDetailBehavior storeDetailBehavior;

    // 最大展开距离
    private int maxExpandDistance = 0;
    private AccelerateDecelerateInterpolator interpolator;
    public AnimatorListenerAdapter1 animListener;
    private StoreDetailRootViewHo parent;
    private float cartProgress = 1f;

    public StoreDetailInfoHo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_detail_info_ho, this);
        binding = WidgetDetailInfoHoBinding.bind(getChildAt(0));
        initView();
    }

    private void initView() {
        Glide.with(getContext())
                .asBitmap()
                .load(R.mipmap.store_70_img)
                .transform(new BlurTransformation(65, 2)).into(binding.bgImg);

        interpolator = new AccelerateDecelerateInterpolator();

        binding.announcementContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent.mScrollState == parent.SCROLL_STATE_IDLE) {
                    expandOrShrinkAnnouncement();
                }
            }
        });

    }

    public void setStoreDetailBehavior(StoreDetailBehavior storeDetailBehavior) {
        this.storeDetailBehavior = storeDetailBehavior;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSpec = MeasureSpec.makeMeasureSpec((int) (heightSize + Math.abs(getTranslationY())), heightMode);
        super.onMeasure(widthMeasureSpec, measureSpec);
    }

    @Override
    public void fistInit() {
        super.fistInit();
        parent = (StoreDetailRootViewHo) getParent();

        ConstraintLayout.LayoutParams bgImgParams = (ConstraintLayout.LayoutParams) binding.bgImg.getLayoutParams();
        bgImgParams.height += App.AppStatusBarHeight;

        ConstraintLayout.LayoutParams contentCardBoxParams = (ConstraintLayout.LayoutParams) binding.contentCardBox.getLayoutParams();
        contentCardBoxParams.topMargin += App.AppStatusBarHeight;

        initCardMeasuredHeight = binding.contentCard.getHeight();
        maxExpandDistance = App.AppScreenHeight - binding.otherBox.getTop() - BarUtils.getStatusBarHeight(getContext());

        // 记录View状态
        ViewState.stateSave(binding.announcementContent, R.id.viewStateStart).alpha(1f);
        ViewState.stateSave(binding.announcementContent, R.id.viewStateEnd).alpha(0);
        ViewState.stateSave(binding.widgetStoreAnnouncement.getRoot(), R.id.viewStateStart).alpha(0);
        ViewState.stateSave(binding.widgetStoreAnnouncement.getRoot(), R.id.viewStateEnd).alpha(1f);
    }

    public int getMaxExpandDistance() {
        return maxExpandDistance;
    }

    /**
     * 点击缩略公告View 展开/收缩公告
     */
    public void expandOrShrinkAnnouncement() {
        boolean direction = false;
        if (binding.contentCard.getHeight() == initCardMeasuredHeight) {
            // Log.d("TAG","公告完全收缩");
            direction = false;
            isExpand = false;
        } else if (binding.contentCard.getHeight() == (initCardMeasuredHeight + maxExpandDistance + (int) Math.abs(getTranslationY()))) {
            // Log.d("TAG","公告完全展开");
            direction = true;
            isExpand = true;
        }
        updateDragState(binding.contentCard.getHeight(), direction);

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
            ObjectAnimator.ofFloat(binding.dropIcon, View.ROTATION, 0, 180f)
                    .setDuration(ANIMATOR_DURATION)
                    .start();
        } else {
            ObjectAnimator.ofFloat(binding.dropIcon, View.ROTATION, 180f, 0)
                    .setDuration(ANIMATOR_DURATION)
                    .start();
        }
    }

    /**
     * 公告显示/隐藏（点击缩略公告View）
     */
    private void announcementShowHide() {
        float start = 0;
        float end = 0;
        if (isExpand) {
            start = 0;
            end = 1f;
        } else {
            start = 1f;
            end = 0;
        }

        ViewState.statesChangeByAnimation(
                new View[]{binding.announcementContent, binding.widgetStoreAnnouncement.getRoot()},
                R.id.viewStateStart,
                R.id.viewStateEnd,
                start,
                end,
                null, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        if (isExpand) {
                            binding.widgetStoreAnnouncement.getRoot().setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (!isExpand) {
                            binding.widgetStoreAnnouncement.getRoot().setVisibility(View.GONE);
                        }
                    }
                },
                ANIMATOR_DURATION, 0l);
    }

    /**
     * 公告 展开/收缩（点击缩略公告View）
     */
    private void expandUI() {
        ViewGroup.LayoutParams params = binding.contentCard.getLayoutParams();
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
            startDistance = binding.contentCard.getHeight();
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
                    cartProgress = 1 - progress;
                } else {
                    direction = true;
                    cartProgress = progress;
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

                storeDetailBehavior.getStoreDetailCartView().effectByOffset(cartProgress);

                params.height = (int) currentDistance;
                binding.contentCard.setLayoutParams(params);
                animListener.onAnimationUpdate(progress, isExpand);
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
            if (binding.widgetStoreAnnouncement.getRoot().getVisibility() == View.GONE) {
                binding.widgetStoreAnnouncement.getRoot().setVisibility(View.VISIBLE);
            }
        } else if (progress == 0) {
            if (binding.widgetStoreAnnouncement.getRoot().getVisibility() == View.VISIBLE) {
                binding.widgetStoreAnnouncement.getRoot().setVisibility(View.GONE);
            }
        }

        binding.announcementContent.setAlpha(1 - progress * 2); // 公告缩略文本view
        binding.widgetStoreAnnouncement.getRoot().setAlpha(progress); // 展开公告view
    }

    /**
     * 旋转公告 下拉图标（跟随拖拽）
     *
     * @param progress
     */
    private void rotationAnnouncementIcon(float progress) {
        float maxRotation = 180f; // 最大旋转角度
        binding.dropIcon.setPivotX(binding.dropIcon.getWidth() / 2f); // X轴旋转中心点
        binding.dropIcon.setPivotY(binding.dropIcon.getHeight() / 2f); // Y轴旋转中心点
        binding.dropIcon.setRotation(maxRotation * progress); // 开始旋转
    }

    /**
     * 改变公告view 高度（跟随拖拽）
     *
     * @param curDrag
     * @param direction
     */
    private float updateAnnouncementHeight(int curDrag, boolean direction) {
        ViewGroup.LayoutParams params = binding.contentCard.getLayoutParams();

        float progress = Math.abs(storeDetailBehavior.getmStoreDetailPager2View().getTranslationY()) / storeDetailBehavior.getMaxOffsetBottom();
        if (storeDetailBehavior.getmStoreDetailPager2View().getTranslationY() == 0) {
            progress = 0f;
            // Log.d("TAG", "完全收缩");
        } else if (Math.abs(storeDetailBehavior.getmStoreDetailPager2View().getTranslationY()) == storeDetailBehavior.getMaxOffsetBottom()) {
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

        cartProgress = 1 - progress;
        if (CustomizeUtils.isScientificNotation(String.valueOf(cartProgress))) { // 出现科学计数格式 比如 6E+4
            // 1 - 0.99 = 0.01
            // 0.99 > 0.01 = 1
            // 0.01 < 0.99 = 0
            float tempAbs = Math.abs(1f - cartProgress);
            if (cartProgress > tempAbs) { // 无限接近 1
                cartProgress = 1f;
            } else if (cartProgress < tempAbs) { // 无限接近 0
                cartProgress = 0f;
            } else {
                // 相等
                if (direction) {
                    cartProgress = 1f;
                } else {
                    cartProgress = 0f;
                }
            }
        }

        storeDetailBehavior.getStoreDetailCartView().effectByOffset(cartProgress);

        int proV = (int) (progress * maxExpandDistance);
        int curHeight = proV + initCardMeasuredHeight;
        params.height = curHeight;
        binding.contentCard.setLayoutParams(params);

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

    public interface AnimatorListenerAdapter1 {
        void onAnimationStart(Animator animator);

        void onAnimationUpdate(float progress, boolean isExpand);

        void onAnimationEnd();
    }

    public void initData() {

    }

}