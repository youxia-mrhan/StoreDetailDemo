package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.DetailSelectBean;
import com.example.storedetaildemo.databinding.WidgetSelectItemHoBinding;
import com.example.storedetaildemo.databinding.WidgetSelectItemIconHoBinding;
import com.example.storedetaildemo.util.DisplayUtils;

public class SelectItemHo extends FrameLayout {

    private DetailSelectBean.ProductSelectBean productSelectBean;
    public WidgetSelectItemIconHoBinding iconHoBinding;
    public WidgetSelectItemHoBinding itemHoBinding;

    public SelectItemHo(@NonNull Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DisplayUtils.dp2px(getContext(),52)
        );
        setLayoutParams(params);
    }

    public void setSelectData(DetailSelectBean.ProductSelectBean productSelectBean) {
        this.productSelectBean = productSelectBean;
        initView();
    }

    private void initView() {
        removeAllViews();
        if (productSelectBean.isIs_hot() || productSelectBean.isIs_must_order()) {
            iconItem();
        } else {
            defaultItem();
        }
    }

    private void defaultItem() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_select_item_ho, this);
        itemHoBinding = WidgetSelectItemHoBinding.bind(getChildAt(0));
        itemHoBinding.selectTitle.setText(productSelectBean.getTitle());
    }

    private void iconItem() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_select_item_icon_ho, this);
        iconHoBinding = WidgetSelectItemIconHoBinding.bind(getChildAt(0));
        iconHoBinding.selectTitle.setText(productSelectBean.getTitle());
        if (productSelectBean.isIs_hot()) {
            iconHoBinding.selectIcon.setImageResource(R.mipmap.hot_icon);
        } else {
            iconHoBinding.selectIcon.setImageResource(R.mipmap.must_order_icn);
        }
    }

}
