package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.BindingAdapter;

import com.example.baselibrary.util.ScreenUtils;
import com.example.provider.common.App;

/**
 * 处理 NestedScrollView 嵌套 RecyclerView
 * 导致 RecyclerView复用机制失效 问题
 */
public class StoreDetailNestedScrollViewH extends NestedScrollView {

    public StoreDetailNestedScrollViewH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 使用NestedScrollView嵌套RecyclerView，会导致RecyclerView复用机制失效，
     * RecyclerView会将所有数据一次性全部加载。
     * <p>
     * 解决方法：重写measureChildWithMargins，让NestedScrollView测量RecyclerView时
     * 不使用MeasureSpec.UNSPECIFIED模式即可。
     */
    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        child.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    /**
     * 给RecyclerView设置固定高度
     * 使用NestedScrollView嵌套RecyclerView，需要 RecyclerView设置固定高度，不然RecyclerView的复用机制会失效
     * @param view
     * @param use
     */
    @BindingAdapter("setStoreDetailViewInitHeight")
    public static void setStoreDetailViewInitHeight(View view,boolean use) {
        if (!use) return;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = ScreenUtils.getScreenHeight(App.getInstance()); // 屏幕最大高度
        view.setLayoutParams(params);
    }

}
