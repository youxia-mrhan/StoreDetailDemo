package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.databinding.WidgetMerchantTitleBtnHoBinding;

/**
 * 商家标题 按钮项
 */
public class MerchantTitleBtnHo extends FrameLayout {

    private final WidgetMerchantTitleBtnHoBinding binding;
    private String mainTitle;
    private String deputyTitle;

    public MerchantTitleBtnHo(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.widget_merchant_title_btn_ho,this);
        binding = WidgetMerchantTitleBtnHoBinding.bind(getChildAt(0));
    }

    public void initData(String mainTitle,String deputyTitle) {
        this.mainTitle = mainTitle;
        this.deputyTitle = deputyTitle;
        initView();
    }

    private void initView() {
        binding.mainTitle.setText(mainTitle);
        if (deputyTitle != null) {
            binding.deputyTitle.setVisibility(View.VISIBLE);
            binding.deputyTitle.setText(deputyTitle);
        }
    }

}
