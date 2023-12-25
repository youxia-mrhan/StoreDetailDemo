package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.baselibrary.ui.view.BaseView;
import com.example.home_store_detail.R;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.databinding.StoreDetailLvProductTypeHBinding;

/**
 * 商品类型 列表项
 */
public class StoreDetailProductTypeH extends BaseView {

    public StoreDetailLvProductTypeHBinding bind;

    public StoreDetailProductTypeH(@NonNull Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
    }

    public void initView(StoreDetailVMH.OrderFoodInfoBean.ProductTypeBean productType) {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_product_type_h, this);
        bind = StoreDetailLvProductTypeHBinding.bind(getChildAt(0));
        bind.setProductType(productType);

        if (productType.getImage_id() == 0) {
            bind.typeIcon.setImageResource(R.mipmap.hot_icon);
        } else if (productType.getImage_id() == 1) {
            bind.typeIcon.setImageResource(R.mipmap.must_order_icn);
        }
    }

    /**
     * 更新为高亮样式
     */
    public void updateHighlightState() {
        setBackgroundResource(com.example.baselibrary.R.color.white);
        bind.typeTitle.setTextColor(getContext().getResources().getColor(com.example.baselibrary.R.color.color_303133));
    }

    /**
     * 更新为默认样式
     */
    public void updateDefaultState() {
        setBackgroundResource(com.example.baselibrary.R.color.color_F5F6F7);
        bind.typeTitle.setTextColor(getContext().getResources().getColor(com.example.baselibrary.R.color.color_909399));
    }

}
