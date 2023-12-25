package com.example.storedetaildemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.ui.activity.BaseActivity;
import com.example.baselibrary.util.StatusBarUtils;
import com.example.home_store_detail.ui.activity.StoreDetailMainAtyH;
import com.example.storedetaildemo.R;
import com.example.storedetaildemo.databinding.AppAtyMainBinding;

public class AppMainAty extends BaseActivity {

    private AppAtyMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtils.translucentStatusBar(this, false); // 沉浸式状态栏
        super.onCreate(savedInstanceState);

        bind = AppAtyMainBinding.bind(getLayoutInflater().inflate(R.layout.app_aty_main, null));
        setContentView(bind.getRoot());

        bind.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StoreDetailMainAtyH.class);
                Bundle bundle = new Bundle();
                bundle.putInt("storeId",0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        bind.btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StoreDetailMainAtyH.class);
                Bundle bundle = new Bundle();
                bundle.putInt("storeId",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}