package com.example.home_store_detail.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.example.baselibrary.ui.fragment.BaseFragment;
import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailFrmMerchantHBinding;
import com.example.home_store_detail.ui.view.StoreDetailCoordinatorH;
import com.example.home_store_detail.ui.view.StoreDetailMerchantBtnH;
import com.example.home_store_detail.ui.view.StoreDetailNestedScrollViewH;
import com.example.home_store_detail.ui.view.StoreDetailTabPageH;

/**
 * 商家页面
 */
public class StoreDetailMerchantFrmH extends BaseFragment implements StoreDetailTabPageH.ScrollableViewProvider {

    private StoreDetailFrmMerchantHBinding bind;
    private StoreDetailVMH.MerchantInfoBean merchantInfo;

    private StoreDetailTabPageBehaviorH tabPageBehavior;

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = StoreDetailFrmMerchantHBinding.inflate(inflater, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        bind.merchantStoreCard.initView(merchantInfo);

        // 按钮
        StoreDetailMerchantBtnH merchantBtnHo = new StoreDetailMerchantBtnH(getContext());
        StoreDetailMerchantBtnH merchantBtnHo2 = new StoreDetailMerchantBtnH(getContext());
        StoreDetailMerchantBtnH merchantBtnHo3 = new StoreDetailMerchantBtnH(getContext());
        bind.titleBtnGroup.addView(merchantBtnHo);
        bind.titleBtnGroup.addView(merchantBtnHo2);
        bind.titleBtnGroup.addView(merchantBtnHo3);

        merchantBtnHo.initData("营业时间", merchantInfo.getBusiness_time());
        merchantBtnHo2.initData("商家资质", null);
        merchantBtnHo3.initData("投诉商家", null);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) merchantBtnHo3.getLayoutParams();
        layoutParams.topMargin = DisplayUtils.dp2px(getContext(), 8);
        merchantBtnHo3.setLayoutParams(layoutParams);
    }

    public void initData(StoreDetailVMH.MerchantInfoBean merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    @Override
    public void parentOnDown(MotionEvent event) {
        // Log.d("TAG","商家 --- 手指按下");
        onScrollStopNested();
    }

    @Override
    public void parentOnUp(MotionEvent event) {
        // Log.d("TAG","商家 --- 手指抬起");
        StoreDetailNestedScrollViewH root = (StoreDetailNestedScrollViewH) bind.getRoot();
        root.onStopNestedScroll(null);
    }

    @Override
    public boolean offsetScrollView(int dy, boolean direction, boolean fling) {
        return false;
    }

    @Override
    public void onScrollStopNested() {
        // 停止嵌套滚动，避免之前列表的惯性滚动还没结束
        StoreDetailNestedScrollViewH root = (StoreDetailNestedScrollViewH) bind.getRoot();
        root.scrollBy(0, 0);
        root.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
    }

    @Override
    public int getTabPageDecorationHeight() {
        return 0;
    }

    @Override
    public int getTabPageFloatHeight() {
        return 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // 业务扩展
    }

    /**
     * 获取当前 scrollView
     */
    @Override
    public View getScrollableView() {
        StoreDetailCoordinatorH.currentScrollViewTag = "商家页 - rootScrollView";
        return bind.getRoot();
    }

    /**
     * 获取 根View
     */
    @Override
    public View getRootScrollView() {
        return bind.getRoot();
    }

    @Override
    public View getMainScrollView() {
        StoreDetailCoordinatorH.currentScrollViewTag = "商家页 - rootScrollView";
        return bind.getRoot();
    }
}