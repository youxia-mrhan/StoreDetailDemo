package com.example.home_store_detail.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import androidx.lifecycle.ViewModelProvider;

import com.example.baselibrary.ui.activity.BaseActivity;
import com.example.baselibrary.util.ReadAssertJson;
import com.example.baselibrary.util.StatusBarUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.ui.view.StoreDetailSkeletonH;
import com.example.home_store_detail.databinding.StoreDetailAtyMainHBinding;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.google.gson.Gson;

public class StoreDetailMainAtyH extends BaseActivity {

    private StoreDetailAtyMainHBinding bind;
    private StoreDetailVMH storeDetailViewModel;
    private FrameLayout decorView; // 根视图
    private StoreDetailSkeletonH skeletonH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtils.translucentStatusBar(this, false); // 沉浸式状态栏
        super.onCreate(savedInstanceState);

        int storeId = getIntent().getIntExtra("storeId", -1);
        StoreDetailVMH storeDetailVMH = initData(storeId);

        bind = StoreDetailAtyMainHBinding.bind(getLayoutInflater().inflate(R.layout.store_detail_aty_main_h, null));
        bind.getRoot().setAlpha(0);
        setContentView(bind.getRoot());

        // 店铺详情 ViewModel
        ViewModelProvider.Factory storeDetailFactory = (ViewModelProvider.Factory) new StoreDetailVMH.StoreDetailVMFactory(storeDetailVMH);
        storeDetailViewModel = new ViewModelProvider(this, storeDetailFactory).get(StoreDetailVMH.class);
        bind.info.initData(storeDetailViewModel.getStore_info());
        bind.tabPage.initData(storeDetailViewModel);
        bind.searchBar.initData(storeDetailViewModel.getStore_info());
        bind.shoppingCart.initData(storeDetailVMH.getStore_info().getId(), this);
        // bind.setLifecycleOwner(this);

        decorView = (FrameLayout) getWindow().getDecorView();
        skeletonH = new StoreDetailSkeletonH(this); // 骨架屏
        skeletonH.showSkeleton(decorView);
    }


    @Override
    public void fistInitWindowFocus() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                skeletonH.removeSkeleton(); // 删除骨架屏
            }
        }, 1500);
        bind.getRoot().setAlpha(1f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShoppingCartListH.clearNullShoppingCart();
    }

    /**
     * 加载 模拟Json数据
     *
     * @return
     */
    private StoreDetailVMH initData(int storeId) {
        String json = "";
        if (storeId == 0) {
            json = ReadAssertJson.redJson("store_detail_data.json", this);
        } else {
            json = ReadAssertJson.redJson("store_detail_data02.json", this);
        }
        return new Gson().fromJson(json, StoreDetailVMH.class);
    }
}