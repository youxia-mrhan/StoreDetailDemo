package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.adapter.BannerImgAdapter;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 */
public class StoreDetailCarouselH extends BaseView {

    private Banner banner;

    public StoreDetailCarouselH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initView(List<StoreDetailVMH.OrderFoodInfoBean.CarouselsBean> carousels) {
        FrameLayout root = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_carousel_h, this, false);
        addView(root);

        List<Integer> carouselImages = new ArrayList<Integer>();
        for (int i = 0; i < carousels.size(); i++) {
            carouselImages.add(getCarouselAssetImage(carousels.get(i).getImage_url()));
        }
        banner = new Banner(getContext()){
            @Override
            public boolean canScrollVertically(int direction) {
                return false; // 禁止垂直 滑动
            }
        };
        banner.setNestedScrollingEnabled(false); // 禁止嵌套滚动，不然StoreDetailTabPageBehaviorH 会接收到

        root.addView(banner);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) banner.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;

        BannerImgAdapter adapter = new BannerImgAdapter(carouselImages);
        banner.setAdapter(adapter);

        MBannerIndicator indicator = new MBannerIndicator(getContext());
        banner.setIndicator(indicator);

        // 添加画廊效果
        banner.setBannerGalleryEffect(8, 6);

        // 指示器选中和默认的颜色
        banner.setIndicatorNormalColor(getContext().getResources().getColor(com.example.baselibrary.R.color.color_1A000000));
        banner.setIndicatorSelectedColor(getContext().getResources().getColor(com.example.baselibrary.R.color.color_FFBA00));

        // 指示器的位置
        banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);

        // 指示器下边距
        IndicatorConfig.Margins margins = new IndicatorConfig.Margins();
        margins.rightMargin = DisplayUtils.dp2px(getContext(), 8);
        margins.bottomMargin = DisplayUtils.dp2px(getContext(), 8);
        banner.setIndicatorMargins(margins);

        // 指示器间距
        banner.setIndicatorSpace(DisplayUtils.dp2px(getContext(), 4));

        // 指示器高度
        banner.setIndicatorHeight(DisplayUtils.dp2px(getContext(), 6));

        // 未选中指示器的宽度
        banner.setIndicatorNormalWidth(DisplayUtils.dp2px(getContext(), 6));

        // 选中指示器的宽度
        banner.setIndicatorSelectedWidth(DisplayUtils.dp2px(getContext(), 10));

    }

    /**
     * 切换 点餐、评价、商家 页面容器 时
     * 如果 轮播图正在切换，会卡在中间
     *
     * 在切换的时候 设置指定轮播图 来避免
     */
    public void setBannerItem() {
        if (banner == null) return;
        banner.setCurrentItem(banner.getCurrentItem());
    }

}
