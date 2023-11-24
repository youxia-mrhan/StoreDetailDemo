package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class MNestedScrollViewBox extends NestedScrollView {

    public MNestedScrollViewBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 使用NestedScrollView嵌套RecyclerView，会导致RecyclerView复用机制失效，RecyclerView会将所有数据一次性全部加载。
    // 解决方法：重写measureChildWithMargins，让NestedScrollView测量RecyclerView时 不使用MeasureSpec.UNSPECIFIED模式即可。
    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        child.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    // NestedScrollView嵌套RecyclerView时，RecyclerView的高度是无限大
    // 所以要将RecyclerView设置固定高度
    //
    // OrderFoodFragment.java 中的代码：
    //
    // binding.getRoot().post(new Runnable() {
    //    @Override
    //    public void run() {
    //        binding.selectList.getLayoutParams().height = binding.getRoot().getHeight(); // 使用NestedScrollView的高度
    //        binding.productList.getLayoutParams().height = binding.getRoot().getHeight();
    //        binding.selectList.setLayoutParams(binding.selectList.getLayoutParams());
    //        binding.productList.setLayoutParams(binding.productList.getLayoutParams());
    //    }
    // });

}
