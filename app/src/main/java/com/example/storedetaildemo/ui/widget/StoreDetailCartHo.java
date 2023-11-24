package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.common.base.BaseView;
import com.example.storedetaildemo.databinding.WidgetDetailCartHaveHoBinding;
import com.example.storedetaildemo.databinding.WidgetDetailCartHoBinding;

/**
 * 底部购物车
 */
public class StoreDetailCartHo extends BaseView {

    private final WidgetDetailCartHoBinding binding;
    private final WidgetDetailCartHaveHoBinding haveHoBinding;
    public StoreDetailBehavior storeDetailBehavior;

    public StoreDetailCartHo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_detail_cart_ho, this);
        binding = WidgetDetailCartHoBinding.bind(getChildAt(0));
        haveHoBinding = WidgetDetailCartHaveHoBinding.bind(binding.widgetDetailCartHave.getRoot());
        haveHoBinding.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void fistInit() {
        super.fistInit();
    }

    public void setStoreDetailBehavior(StoreDetailBehavior storeDetailBehavior) {
        this.storeDetailBehavior = storeDetailBehavior;
    }

    /**
     * 动态计算变化
     */
    public void effectByOffset(float progress) {
        if (storeDetailBehavior.getmStoreDetailPager2View().getCurrentPagerIndex() != 0) {
            if (binding.getRoot().getVisibility() != View.GONE) {
                binding.getRoot().setVisibility(View.GONE);
            }
            return;
        }
        if (storeDetailBehavior.getmStoreDetailPager2View().getTranslationY() == storeDetailBehavior.getMaxOffsetBottom()) {
            hideOfShow(View.GONE);
        } else {
            hideOfShow(View.VISIBLE);
        }
        binding.getRoot().setAlpha(progress);
    }

    /**
     * 隐藏或显示
     */
    public void hideOfShow(int viewState) {
        if (storeDetailBehavior.getmStoreDetailPager2View().getCurrentPagerIndex() != 0) {
            if (binding.getRoot().getVisibility() != View.GONE) {
                binding.getRoot().setVisibility(View.GONE);
            }
            return;
        }
        if (viewState == View.GONE) {
            if (binding.getRoot().getVisibility() != View.GONE) {
                binding.getRoot().setVisibility(View.GONE);
            }
        } else if (viewState == View.VISIBLE){
            if (binding.getRoot().getVisibility() != View.VISIBLE) {
                binding.getRoot().setVisibility(View.VISIBLE);
            }
        }
    }

}
