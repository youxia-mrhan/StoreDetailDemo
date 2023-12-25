package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import com.example.baselibrary.ui.view.BaseView;
import com.example.home_store_detail.R;
import com.example.home_store_detail.databinding.StoreDetailLvSkeletonHBinding;

import me.samlss.broccoli.Broccoli;
import me.samlss.broccoli.BroccoliGradientDrawable;
import me.samlss.broccoli.PlaceholderParameter;

/**
 * 骨架屏
 */
public class StoreDetailSkeletonH extends BaseView {

    private StoreDetailLvSkeletonHBinding skeletonBind;
    private ViewGroup parent;

    public StoreDetailSkeletonH(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return true;
    }

    /**
     * 显示骨架屏
     * @param parentV
     */
    public void showSkeleton(ViewGroup parentV) {
        parent = parentV;
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_skeleton_h, this, true);
        skeletonBind = StoreDetailLvSkeletonHBinding.bind(getChildAt(0));
        parent.addView(this);

        Broccoli mBroccoli = new Broccoli();
        int color1 = Color.parseColor("#DDDDDD");
        int color2 = Color.parseColor("#CCCCCC");

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skStoreBg)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skDetailStoreName)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skStoreImg)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skStoreEvaluate)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skSaleQuantity)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skStoreDistance)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skPersonalPrice)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skStoreAddress)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skCouponGroup)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skAnnouncementContent)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skOfferText)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skTab01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skTab02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skTab03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skCarousel)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType04)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType05)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType06)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skType07)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductTypeTitle)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());


        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductImg01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductName01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductDescribe01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skSaleQuality01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductPrice01)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductImg02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductName02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductDescribe02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skSaleQuality02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductPrice02)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductImg03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductName03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductDescribe03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skSaleQuality03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skProductPrice03)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skBackIcon)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skSearchBarBox)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skShareIcon)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.addPlaceholder(new PlaceholderParameter.Builder()
                .setView(skeletonBind.skCartContainer)
                .setDrawable(new BroccoliGradientDrawable(color1, color2, 0, 1000, new LinearInterpolator()))
                .build());

        mBroccoli.show();
    }

    /**
     * 删除骨架屏
     */
    public void removeSkeleton() {
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChildAt(i) instanceof StoreDetailSkeletonH) {
                parent.removeViewAt(i);
                break;
            }
        }
    }

}
