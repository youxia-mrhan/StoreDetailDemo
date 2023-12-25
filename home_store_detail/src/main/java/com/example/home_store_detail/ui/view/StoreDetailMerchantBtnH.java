package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.baselibrary.ui.view.BaseView;
import com.example.home_store_detail.R;
import com.example.home_store_detail.databinding.StoreDetailLvMerchantBtnHBinding;

/**
 * 商家 按钮项
 */
public class StoreDetailMerchantBtnH extends BaseView {

    private final StoreDetailLvMerchantBtnHBinding bind;
    private String mainTitle;
    private String deputyTitle;

    public StoreDetailMerchantBtnH(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_merchant_btn_h, this);
        bind = StoreDetailLvMerchantBtnHBinding.bind(getChildAt(0));
    }

    public void initData(String mainTitle, String deputyTitle) {
        this.mainTitle = mainTitle;
        this.deputyTitle = deputyTitle;
        initView();
    }

    private void initView() {
        bind.mainTitle.setText(mainTitle);
        if (deputyTitle != null) {
            bind.deputyTitle.setVisibility(View.VISIBLE);
            bind.deputyTitle.setText(deputyTitle);
        }
    }

}
