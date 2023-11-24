package com.example.storedetaildemo.common;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;

/**
 * Introduction : View 特定属性持有对象
 */
public class ViewState {

    int marginTop = 0;
    int marginBottom = 0;
    int marginLeft = 0;
    int marginRight = 0;

    int width = 0;
    int height = 0;

    float translationX = 0f;
    float translationY = 0f;

    float scaleX = 0f;
    float scaleY = 0f;

    float rotation = 0f;
    float alpha = 0f;

    public ViewState scaleX(float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public ViewState scaleY(float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    public ViewState sxBy(float value) {
        this.scaleX *= value;
        return this;
    }

    public ViewState syBy(float value) {
        this.scaleY *= value;
        return this;
    }

    public ViewState alpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public ViewState width(int width) {
        this.width = width;
        return this;
    }

    public ViewState height(int height) {
        this.height = height;
        return this;
    }

    public ViewState rotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public ViewState ws(float s) {
        this.width = Integer.parseInt((width * s) + "");
        return this;
    }

    public ViewState hs(float s) {
        this.height = Integer.parseInt((height * s) + "");
        return this;
    }

    public ViewState translationX(float translationX) {
        this.translationX = translationX;
        return this;
    }

    public ViewState translationY(float translationY) {
        this.translationY = translationY;
        return this;
    }

    public ViewState marginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public ViewState marginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public ViewState marginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public ViewState marginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public ViewState copy(View view) {
        this.width = view.getWidth();
        this.height = view.getHeight();
        this.translationX = view.getTranslationX();
        this.translationY = view.getTranslationY();
        this.scaleX = view.getScaleX();
        this.scaleY = view.getScaleY();
        this.rotation = view.getRotation();
        this.alpha = view.getAlpha();

        if (view.getLayoutParams() != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            this.marginTop = marginLayoutParams.topMargin;
            this.marginBottom = marginLayoutParams.bottomMargin;
            this.marginLeft = marginLayoutParams.leftMargin;
            this.marginRight = marginLayoutParams.rightMargin;
        }

        return this;
    }

    /**
     *  --------- 拓展方法 ---------
     */

    /**
     * 通过 setTag 方式保存 view 状态
     */
    public static void stateSet(View view, int tag, ViewState vs) {
        view.setTag(tag, vs);
    }

    /**
     * view 状态读取
     */
    public static ViewState stateRead(View view, int tag) {
        if (view.getTag(tag) != null) {
            return (ViewState) view.getTag(tag);
        }
        return null;
    }

    /**
     * 设置并保存 view 状态
     */
    public static ViewState stateSave(View view, int tag) {
        ViewState vs;
        if (stateRead(view, tag) == null) {
            vs = new ViewState();
        } else {
            vs = stateRead(view, tag);
        }
        vs.copy(view);
        stateSet(view, tag, vs);
        return vs;
    }

    /**
     * 根据指定 View 记录的两种状态来过渡更新 View 状态
     * 如果本身有设置 AnimationUpdateListener 监听则执行 onAnimationUpdate 回调覆盖操作
     *
     * @param startTag 初始状态对应记录 Id
     * @param endTag   最终状态对应记录 Id
     * @param p        变化进度 [0,1]
     */
    public static void stateRefresh(View view, int startTag, int endTag, float p) {
        if (view instanceof AnimationUpdateListener) {
            ((AnimationUpdateListener) view).onAnimationUpdate(startTag, endTag, p);
        } else {
            final ViewState startViewState = stateRead(view, startTag);
            final ViewState endViewState = stateRead(view, endTag);

            if (startViewState != null && endViewState != null) {
                if (startViewState.translationX != endViewState.translationX) {
                    view.setTranslationX(startViewState.translationX + (endViewState.translationX - startViewState.translationX) * p);
                }
                if (startViewState.translationY != endViewState.translationY) {
                    view.setTranslationY(startViewState.translationY + (endViewState.translationY - startViewState.translationY) * p);
                }
                if (startViewState.scaleX != endViewState.scaleX) {
                    view.setScaleX(startViewState.scaleX + (endViewState.scaleX - startViewState.scaleX) * p);
                }
                if (startViewState.scaleY != endViewState.scaleY) {
                    view.setScaleY(startViewState.scaleY + (endViewState.scaleY - startViewState.scaleY) * p);
                }
                if (startViewState.rotation != endViewState.rotation) {
                    view.setRotation((startViewState.rotation + (endViewState.rotation - startViewState.rotation) * p) % 360);
                }
                if (startViewState.alpha != endViewState.alpha) {
                    view.setAlpha(startViewState.alpha + (endViewState.alpha - startViewState.alpha) * p);
                }

                final ViewGroup.LayoutParams o = view.getLayoutParams();
                boolean lpChanged = false;

                if (startViewState.width != endViewState.width) {
                    o.width = (int) (startViewState.width + (endViewState.width - startViewState.width) * p);
                    lpChanged = true;
                }
                if (startViewState.height != endViewState.height) {
                    o.height = (int) (startViewState.height + (endViewState.height - startViewState.height) * p);
                    lpChanged = true;
                }
                if (o != null) {
                    ViewGroup.MarginLayoutParams om = (ViewGroup.MarginLayoutParams) o;
                    if (startViewState.marginTop != endViewState.marginTop) {
                        om.topMargin = (int) (startViewState.marginTop + (endViewState.marginTop - startViewState.marginTop) * p);
                        lpChanged = true;
                    }
                    if (startViewState.marginBottom != endViewState.marginBottom) {
                        om.bottomMargin = (int) (startViewState.marginBottom + (endViewState.marginBottom - startViewState.marginBottom) * p);
                        lpChanged = true;
                    }
                    if (startViewState.marginLeft != endViewState.marginLeft) {
                        om.leftMargin = (int) (startViewState.marginLeft + (endViewState.marginLeft - startViewState.marginLeft) * p);
                        lpChanged = true;
                    }
                    if (startViewState.marginRight != endViewState.marginRight) {
                        om.rightMargin = (int) (startViewState.marginRight + (endViewState.marginRight - startViewState.marginRight) * p);
                        lpChanged = true;
                    }
                    if (lpChanged) view.setLayoutParams(om);
                }
            }
        }
    }

    /**
     * 通过属性动画更新指定 View 状态（应用于多个组件，同一时间使用相同的动画，可以少写重复代码）
     */
    public static ValueAnimator statesChangeByAnimation(
            View[] views,
            int startTag,
            int endTag,
            float start,
            float end,
            AnimationUpdateListener updateCallback,
            AnimatorListenerAdapter updateStateListener,
            long duration,
            long startDelay) {

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setStartDelay(startDelay);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                final float p = (float) animation.getAnimatedValue();
                if (updateCallback != null) {
                    updateCallback.onAnimationUpdate(startTag, endTag, p);
                } else {
                    for (int i = 0; i < views.length; i++) {
                        stateRefresh(views[i], startTag, endTag, p);
                    }
                }
            }
        });
        if (updateStateListener != null) {
            animator.addListener(updateStateListener);
        }
        animator.start();
        return animator;
    }


    /**
     * 动画更新监听器
     */
    public interface AnimationUpdateListener {
        void onAnimationUpdate(int tag1, int tag2, float p);
    }
}
