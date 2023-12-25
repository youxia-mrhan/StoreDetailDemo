package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.ScreenUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.databinding.StoreDetailLvMerchantCardHBinding;
import com.google.android.material.card.MaterialCardView;

/**
 * 商家 信息
 */
public class StoreDetailMerchantCardH extends BaseView {

    private StoreDetailLvMerchantCardHBinding bind;

    public StoreDetailMerchantCardH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_merchant_card_h,this);
        bind = StoreDetailLvMerchantCardHBinding.bind(getChildAt(0));
    }

    public void initView(StoreDetailVMH.MerchantInfoBean merchantInfoBean) {
        bind.setMerchantInfo(merchantInfoBean);

        // 图片宽高尺寸，平均显示4个
        int imageAverageWH = (ScreenUtils.getScreenWidth(getContext()) - DisplayUtils.dp2px(getContext(), 32) - (DisplayUtils.dp2px(getContext(), 8) * 3)) / 4;

        for (int i = 0; i < 4; i++) {
            View itemImage = LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_merchant_img_h,bind.storeImageGroup,false);
            bind.storeImageGroup.addView(itemImage);

            ImageView storeImg = itemImage.findViewById(R.id.store_img);
            TextView imageViewMask = itemImage.findViewById(R.id.image_view_mask);

            storeImg.setImageResource(getMerchantImage(merchantInfoBean.getStore_images().get(i).getImage()));

            if (i != 3) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemImage.getLayoutParams();
                layoutParams.rightMargin = DisplayUtils.dp2px(getContext(),8);
            } else {
                itemImage.findViewById(R.id.image_view_mask_box).setVisibility(View.VISIBLE);
            }

            MaterialCardView.LayoutParams userUrlLayoutParams = (MaterialCardView.LayoutParams) storeImg.getLayoutParams();
            userUrlLayoutParams.width = imageAverageWH;
            userUrlLayoutParams.height = imageAverageWH;
            storeImg.setLayoutParams(userUrlLayoutParams);

            MaterialCardView.LayoutParams maskLayoutParams = (MaterialCardView.LayoutParams) imageViewMask.getLayoutParams();
            maskLayoutParams.width = imageAverageWH;
            maskLayoutParams.height = imageAverageWH;
            imageViewMask.setLayoutParams(maskLayoutParams);
        }
    }

}
