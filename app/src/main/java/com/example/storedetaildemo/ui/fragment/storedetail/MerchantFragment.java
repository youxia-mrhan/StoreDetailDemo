package com.example.storedetaildemo.ui.fragment.storedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.storedetaildemo.bean.MerchantBean;
import com.example.storedetaildemo.common.base.BaseFragment;
import com.example.storedetaildemo.databinding.FragmentMerchantBinding;
import com.example.storedetaildemo.ui.widget.MerchantTitleBtnHo;
import com.example.storedetaildemo.ui.widget.StoreDetailPagerView2Ho;
import com.example.storedetaildemo.util.DisplayUtils;

/**
 * 商家页面
 */
public class MerchantFragment extends BaseFragment implements StoreDetailPagerView2Ho.ScrollableViewProvider {

    private FragmentMerchantBinding binding;

    private MerchantBean merchantBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMerchantBinding.inflate(
                inflater,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMerchantStoreIntroduce();
        initMerchantTitle();
    }

    public void initData(MerchantBean merchantBean) {
        this.merchantBean = merchantBean;
    }

    private void initMerchantStoreIntroduce() {
        binding.merchantStoreIntroduce.initData(merchantBean);
    }

    private void initMerchantTitle() {
        MerchantTitleBtnHo merchantTitleBtnHo = new MerchantTitleBtnHo(getContext());
        MerchantTitleBtnHo merchantTitleBtnHo2 = new MerchantTitleBtnHo(getContext());
        MerchantTitleBtnHo merchantTitleBtnHo3 = new MerchantTitleBtnHo(getContext());
        binding.titleBtnGroup.addView(merchantTitleBtnHo);
        binding.titleBtnGroup.addView(merchantTitleBtnHo2);
        binding.titleBtnGroup.addView(merchantTitleBtnHo3);

        merchantTitleBtnHo.initData("营业时间",merchantBean.getBusiness_time());
        merchantTitleBtnHo2.initData("商家资质",null);
        merchantTitleBtnHo3.initData("投诉商家",null);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)merchantTitleBtnHo3.getLayoutParams();
        layoutParams.topMargin = DisplayUtils.dp2px(getContext(),8);
        merchantTitleBtnHo3.setLayoutParams(layoutParams);
    }

    @Override
    public View getScrollableView() {
        return binding.getRoot();
    }

    @Override
    public void parentOnDown(MotionEvent event) {
        // Log.d("TAG","商家 --- 手指按下");
    }

    @Override
    public void parentOnUp(MotionEvent event) {
        // Log.d("TAG","商家 --- 手指抬起");
    }

    @Override
    public boolean offsetScrollView(int dy, boolean direction,boolean fling) {
        return false;
    }
}