package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.MerchantBean;
import com.example.storedetaildemo.databinding.WidgetMerchantStoreIntroduceHoBinding;
import com.example.storedetaildemo.util.DisplayUtils;
import com.example.storedetaildemo.util.ScreenUtils;

/**
 * 商家店铺简介
 */
public class MerchantStoreIntroduceHo extends FrameLayout {

    private WidgetMerchantStoreIntroduceHoBinding binding;
    private MerchantBean merchantBean;

    public MerchantStoreIntroduceHo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_merchant_store_introduce_ho,this);
        binding = WidgetMerchantStoreIntroduceHoBinding.bind(getChildAt(0));
    }

    public void initData(MerchantBean merchantBean) {
        this.merchantBean = merchantBean;
        initView();
    }

    private void initView() {
        binding.storeTitle.setText(merchantBean.getStore_title());

        // 图片宽高尺寸
        int imageAverageWH = (ScreenUtils.getScreenWidth(getContext()) - DisplayUtils.dp2px(getContext(), 32) - (DisplayUtils.dp2px(getContext(), 8) * 3)) / 4;

        for (int i = 0; i < 4; i++) {
            View itemImage = View.inflate(getContext(), R.layout.widget_merchant_store_img_ho, null);
            binding.storeImageGroup.addView(itemImage);

            ImageView userUrl = itemImage.findViewById(R.id.user_url);
            TextView imageViewMask = itemImage.findViewById(R.id.image_view_mask);

            userUrl.setImageResource(getImageRes(merchantBean.getStore_images().get(i).getImage()));

            if (i != 3) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemImage.getLayoutParams();
                layoutParams.rightMargin = DisplayUtils.dp2px(getContext(),8);
            } else {
                itemImage.findViewById(R.id.image_view_mask_box).setVisibility(View.VISIBLE);
            }

            LayoutParams userUrlLayoutParams = (LayoutParams) userUrl.getLayoutParams();
            userUrlLayoutParams.width = imageAverageWH;
            userUrlLayoutParams.height = imageAverageWH;
            userUrl.setLayoutParams(userUrlLayoutParams);

            LayoutParams maskLayoutParams = (LayoutParams) imageViewMask.getLayoutParams();
            maskLayoutParams.width = imageAverageWH;
            maskLayoutParams.height = imageAverageWH;
            imageViewMask.setLayoutParams(maskLayoutParams);
        }
    }

    private int getImageRes(String url) {
        switch (url) {
            case "merchant_image01": return R.mipmap.merchant_image01;
            case "merchant_image02": return R.mipmap.merchant_image02;
            case "merchant_image03": return R.mipmap.merchant_image03;
            case "merchant_image04": return R.mipmap.merchant_image04;
        }
        return R.mipmap.merchant_image01;
    }

}
