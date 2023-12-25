package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.baselibrary.adapter.PageFragmentAdapter;
import com.example.baselibrary.ex.ViewPager2HelperEx;
import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailLvTabPageHBinding;
import com.example.home_store_detail.ex.StoreDetailRadiusPagerIndicatorH;
import com.example.home_store_detail.ex.StoreDetailSimplePagerTitleViewH;
import com.example.home_store_detail.ui.fragment.StoreDetailEvaluateFrmH;
import com.example.home_store_detail.ui.fragment.StoreDetailMerchantFrmH;
import com.example.home_store_detail.ui.fragment.StoreDetailOrderFoodFrmH;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 点餐、评价、商家 页面容器
 */
public class StoreDetailTabPageH extends BaseView {

    private StoreDetailLvTabPageHBinding bind;
    public StoreDetailTabPageBehaviorH tabPageBehavior;
    private List<String> tabs = new ArrayList<>(Arrays.asList("点餐", "评价", "商家"));
    public MagicIndicator storeTabLayout;
    private StoreDetailVMH storeDetail;
    private int currentPagerIndex = 0;
    private List<Fragment> fragments;

    public StoreDetailTabPageH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_tab_page_h, this);
        bind = StoreDetailLvTabPageHBinding.bind(getChildAt(0));
    }

    public void initData(StoreDetailVMH storeDetail) {
        this.storeDetail = storeDetail;
    }

    @Override
    public void fistInitTreeObserver() {
        storeTabLayout = (MagicIndicator)bind.storeTabLayout;

        StoreDetailVMH.OrderFoodInfoBean orderFoodInfo = storeDetail.getOrder_food_info(); // 点餐信息
        StoreDetailVMH.EvaluateInfoBean evaluateInfo = storeDetail.getEvaluate_info(); // 评价信息
        StoreDetailVMH.MerchantInfoBean merchantInfo = storeDetail.getMerchant_info(); // 商家信息

        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        // 是否充满，每个tab平均分布，默认false
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                StoreDetailSimplePagerTitleViewH simplePagerTitleView = new StoreDetailSimplePagerTitleViewH(context);
                if (index == 1) {
                    String evaluateCount = "";
                    if (storeDetail.getStore_info().getEvaluate_total_count() > 0) {
                        evaluateCount = " " + storeDetail.getStore_info().getEvaluate_total_count();
                        simplePagerTitleView.setText(tabs.get(index) + evaluateCount);

                        // 让指示器 处于标题前两个字 正下方
                        int leftPadding = 0; // 这是误差值

                        if (evaluateCount.length() == 2) { // 最小长度
                            leftPadding = 20;
                        } else if (evaluateCount.length() == 3) {
                            leftPadding = 40;
                        } else if (evaluateCount.length() == 4) {
                            leftPadding = 58;
                        } else if (evaluateCount.length() == 5) { // 最大长度
                            leftPadding = 74;
                        }
                        simplePagerTitleView.setPadding(leftPadding, 0, 0, 0);
                    } else {
                        simplePagerTitleView.setText(tabs.get(index));

                    }
                } else {
                    simplePagerTitleView.setText(tabs.get(index));
                }

                simplePagerTitleView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StoreDetailInfoH.dragPositionState != StoreDetailInfoH.FLOAT) {
                            bind.storeTabView.setCurrentItem(index);
                        }
                    }
                });

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                String evaluateCount = "";
                if (storeDetail.getStore_info().getEvaluate_total_count() > 0) {
                    evaluateCount = " " + storeDetail.getStore_info().getEvaluate_total_count();
                }
                StoreDetailRadiusPagerIndicatorH indicator = new StoreDetailRadiusPagerIndicatorH(context, evaluateCount);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(getResources().getColor(com.example.baselibrary.R.color.color_F9230A));

                //  indicator.setLineWidth(DisplayUtils.dp2px(context, 12));
                indicator.setLineHeight(DisplayUtils.dp2px(context, 3));

                // 圆角
                indicator.setRoundRadius(DisplayUtils.dp2px(context, 2));

                // 拉伸动画
                // indicator.setStartInterpolator(new AccelerateInterpolator());
                // indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

                return indicator;
            }

        });
        bind.storeTabLayout.setNavigator(commonNavigator);

        // bind.storeTabView.setUserInputEnabled(false); // 禁止水平滑动
        bind.storeTabView.setNestedScrollingEnabled(false); // 禁止嵌套滚动

        StoreDetailOrderFoodFrmH orderFoodFragment = new StoreDetailOrderFoodFrmH(); // 点餐
        orderFoodFragment.setLayoutExpandControl(new StoreDetailTabPageBehaviorH.LayoutExpandControl() {
            @Override
            public void offsetTopMax() {
                if (tabPageBehavior != null) {
                    tabPageBehavior.offsetTopMax();
                }
            }

            @Override
            public boolean arriveTopMax() {
                if (tabPageBehavior != null) {
                    return tabPageBehavior.arriveTopMax();
                }
                return false;
            }
        });

        StoreDetailEvaluateFrmH evaluateFragment = new StoreDetailEvaluateFrmH(); // 评价
        evaluateFragment.setLayoutExpandControl(new StoreDetailTabPageBehaviorH.LayoutExpandControl() {
            @Override
            public void offsetTopMax() {
                if (tabPageBehavior != null) {
                    tabPageBehavior.offsetTopMax();
                }
            }

            @Override
            public boolean arriveTopMax() {
                if (tabPageBehavior != null) {
                    return tabPageBehavior.arriveTopMax();
                }
                return false;
            }
        });

        StoreDetailMerchantFrmH merchantFragment = new StoreDetailMerchantFrmH();

        fragments = new ArrayList<>(Arrays.asList(orderFoodFragment, evaluateFragment, merchantFragment));

        PageFragmentAdapter adapter = new PageFragmentAdapter((FragmentActivity) getContext(), fragments);
        bind.storeTabView.setAdapter(adapter);
        ViewPager2HelperEx.bind(bind.storeTabLayout, bind.storeTabView);

        orderFoodFragment.initData(orderFoodInfo);
        evaluateFragment.initData(evaluateInfo);
        merchantFragment.initData(merchantInfo);

        bind.storeTabView.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    orderFoodFragment.setBannerItem();
                }
                if (tabPageBehavior == null || tabPageBehavior.getStoreDetailShoppingCart() == null) {
                    return;
                }
                currentPagerIndex = position;
                if (position == 0) {
                    tabPageBehavior.getStoreDetailShoppingCart().hideOfShow(View.VISIBLE);
                } else {
                    tabPageBehavior.getStoreDetailShoppingCart().hideOfShow(View.GONE);
                }
            }
        });
    }

    public int getCurrentPagerIndex() {
        return currentPagerIndex;
    }

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
        StoreDetailOrderFoodFrmH orderFoodFrmH = (StoreDetailOrderFoodFrmH) fragments.get(0);
        StoreDetailEvaluateFrmH evaluateFrmH = (StoreDetailEvaluateFrmH) fragments.get(1);
        StoreDetailMerchantFrmH merchantFrmH = (StoreDetailMerchantFrmH) fragments.get(2);

        orderFoodFrmH.setTabPageBehavior(tabPageBehavior);
        evaluateFrmH.setTabPageBehavior(tabPageBehavior);
        merchantFrmH.setTabPageBehavior(tabPageBehavior);
        bind.vp2Box.setTabPageBehavior(tabPageBehavior);
    }

    public View getScrollableView() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        return provider.getScrollableView();
    }

    public View getMainScrollView() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        return provider.getMainScrollView();
    }

    public View getRootScrollView() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        return provider.getRootScrollView();
    }

    public void onScrollStopNested() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        provider.onScrollStopNested();
    }

    public void parentOnDown(MotionEvent event) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        provider.parentOnDown(event);
    }

    public void parentOnUp(MotionEvent event) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        provider.parentOnUp(event);
    }

    public int getTabPageDecorationHeight() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        return provider.getTabPageDecorationHeight();
    }

    public int getTabPageFloatHeight() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        return provider.getTabPageFloatHeight();
    }

    public boolean sharedScrollVelocity(int dy, boolean direction, boolean fling) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        return provider.offsetScrollView(dy, direction, fling);
    }

    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(bind.storeTabView.getCurrentItem());
        provider.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    public interface ScrollableViewProvider {
        View getScrollableView();

        View getRootScrollView();

        View getMainScrollView();

        void parentOnDown(MotionEvent event);

        void parentOnUp(MotionEvent event);

        boolean offsetScrollView(int dy, boolean direction, boolean fling);

        void onScrollStopNested();

        int getTabPageDecorationHeight();

        int getTabPageFloatHeight();

        void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type);
    }
}
