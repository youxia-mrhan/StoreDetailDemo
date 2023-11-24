package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MViewPager2Box extends FrameLayout {

    public MViewPager2Box(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isScrollable = true;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 由于两块触摸区域，属于兄弟级，所以同时只可以执行一个
        //    如果先拖动触发A事件，触发的同时屏蔽B事件	---	up事件后 恢复初始值
        //    反之
        //    如果先拖动触发B事件，触发的同时屏蔽A事件	---	up事件后 恢复初始值
        if (StoreDetailRootViewHo.EVENT_LEVEL == StoreDetailRootViewHo.NO_EVENT ||
                StoreDetailRootViewHo.EVENT_LEVEL == StoreDetailRootViewHo.VIEWPAGER2_EVENT_LEVEL) {
            StoreDetailRootViewHo.EVENT_LEVEL = StoreDetailRootViewHo.VIEWPAGER2_EVENT_LEVEL;
            return super.dispatchTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isScrollable && super.onTouchEvent(event);
    }

}
