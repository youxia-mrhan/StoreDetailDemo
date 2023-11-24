package com.example.storedetaildemo.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.storedetaildemo.bean.CommentBean;
import com.example.storedetaildemo.bean.DetailProductBean;
import com.example.storedetaildemo.bean.DetailSelectBean;
import com.example.storedetaildemo.bean.MerchantBean;
import com.example.storedetaildemo.common.base.BaseActivity;
import com.example.storedetaildemo.databinding.ActivityStoreDetailBinding;
import com.example.storedetaildemo.util.ReadAssertJson;
import com.google.gson.Gson;


public class StoreDetailActivity extends BaseActivity {

    private ActivityStoreDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData() {
        // 点餐页面 数据
        String json = ReadAssertJson.redJson("detail_select_list_data.json", this);
        final DetailSelectBean selectBean = new Gson().fromJson(json, DetailSelectBean.class);
        String json2 = ReadAssertJson.redJson("detail_product_list_data.json", this);
        final DetailProductBean productBean = new Gson().fromJson(json2, DetailProductBean.class);

        // 评论页面 数据
        String json3 = ReadAssertJson.redJson("comment_list_data.json", this);
        final CommentBean commentBean = new Gson().fromJson(json3, CommentBean.class);

        // 商家页面 数据
        String json4 = ReadAssertJson.redJson("merchant_info_data.json", this);
        final MerchantBean merchantBean = new Gson().fromJson(json4, MerchantBean.class);

        binding.storeDetailList.initView(selectBean,productBean,commentBean,merchantBean);
    }

}
