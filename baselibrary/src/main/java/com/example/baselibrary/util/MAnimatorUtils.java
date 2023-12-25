package com.example.baselibrary.util;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 动画工具类
 */
public class MAnimatorUtils {

    /**
     * 布局动画
     *
     * @param duration
     * @return
     */
    public static LayoutTransition mLayoutTransition(int duration) {
        // 这个动画类型 随便填
        ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, View.TRANSLATION_Y, 1).
                setDuration(duration);

        LayoutTransition layoutTransition = new LayoutTransition();
        // 重点在这 APPEARING：view被添加（可见）到ViewGroup会触发的动画
        layoutTransition.setAnimator(LayoutTransition.APPEARING, addAnimator);
        return layoutTransition;
    }

}
